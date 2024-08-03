package com.orderManage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.controller.object.OrderConfirmForm;
import com.orderManage.controller.object.OrderConfirmSubForm;
import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.Details;
import com.orderManage.model.api.OrderProducts;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.api.TransactionsInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamProductImage;
import com.orderManage.model.param.ParamProductInfo;
import com.orderManage.model.param.ParamPurchaseOrderInfo;
import com.orderManage.model.param.ParamSupplierInfo;
import com.orderManage.model.param.ParamTransactionInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;
import com.orderManage.util.SmarejiApiAccessMock;

/**
 * 発注確定画面サービスクラス
 * 
 * 発注確定画面での操作を行う
 * @author aocon-mac
 *
 */
@Service
public class OrderConfirmService extends OrderManageService {

	/* ログ設定 ※定義必須*/
	@Autowired
	OrderManageLoggingService logger;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccess smarejiApiAccess;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccessMock smarejiApiAccessMock;

	/* 一週間の日数*/
	private final int WEEK_DAYS = 7;
	/**
	 * 発注確定画面初期表示情報を取得
	 * 
	 * 発注確定画面に初期表示する情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderId 発注ID
	 * @return CheckOrderConfirmForm 発注確定画面Formリスト
	 */	
	public OrderConfirmForm getDisplayInfo(SmarejiUser smarejiUser, String orderId) {
		
		logger.info("getDisplayInfoList:発注確認画面初期表示情報取得　処理開始");
		
		OrderConfirmForm orderConfirmForm = new OrderConfirmForm();
		
		
		
		List<OrderConfirmSubForm> displayList = new ArrayList<OrderConfirmSubForm>();
		
		// 発注情報取得API
		PurchaseOrdersInfo purchaseOrdersInfo = getOrderInfo(smarejiUser, orderId);

		// 仕入れ先取得　
		List<SuppliersInfo> suppliersInfoList = getSuppliersInfoList(smarejiUser);

		// 画像取得API
		List<ProductImageInfo> productImageInfoList = getProductImageInfo(smarejiUser, orderId);

		// システム日付取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
        GregorianCalendar gc = new GregorianCalendar();
        String sysDate = sdf.format(gc.getTime()); 
		
		// 今週取引一覧取得API
		List<TransactionsInfo> transactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(1), sysDate);
		
		// 前週取引一覧取得API
		List<TransactionsInfo> oneTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(2), getToDate(1));
		
		// 2週取引一覧取得API
		List<TransactionsInfo> twoTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(3), getToDate(2));
		
		// 3週取引一覧取得API
		List<TransactionsInfo> threeTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(4), getToDate(3));
		
		// 4週取引一覧取得API
		List<TransactionsInfo> fourTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(5), getToDate(4));
		
		// 発注対象商品
		for (OrderProducts product : purchaseOrdersInfo.getProducts()){
			
			OrderConfirmSubForm subForm = new OrderConfirmSubForm();

			// 商品情報取得API
			ProductsInfo productsInfo = getProductsInfo(smarejiUser, product.getProductId());
			
			// グループCD設定
			subForm.setGroupCode(productsInfo.getGroupCode());

			// 画像取得
			for (ProductImageInfo imageInfo : productImageInfoList) {
				if (imageInfo.getProductId().equals(product.getProductId())) {
					subForm.setImgUrl(imageInfo.getUrl());
				}
			}

			// 商品ID設定 商品情報から商品IDがとれない　商品IDは発注情報でとった商品IDを使う？
//			subForm.setProductId(productsInfo.getProductId());
			// 商品ID設定
			subForm.setProductId(product.getProductId());

			// 商品CD設定
			subForm.setProductCd(productsInfo.getProductCode());

			// 商品名設定
			subForm.setProductName(productsInfo.getProductName());

			// 仕入れ先名取得
			for (SuppliersInfo suppliersInfo : suppliersInfoList) {
				if (suppliersInfo.getSupplierId().equals(purchaseOrdersInfo.getRecipientOrderId())) {
					subForm.setImgUrl(suppliersInfo.getSupplierName());
				}
			}

			// 部門名取得API
			CategorieInfo categorieInfo = getCategoryName(smarejiUser, productsInfo.getCategoryId());

			// 部門名設定
			subForm.setCategoryName(categorieInfo.getCategoryName());

			// 販売点数(4週)
			BigDecimal fourAmount = new BigDecimal("0");
			
			// 販売点数(3週)
			BigDecimal threeAmount = new BigDecimal("0");
			
			// 販売点数(2週)
			BigDecimal twoAmount = new BigDecimal("0");
			
			// 販売点数(前週)
			BigDecimal oneAmount = new BigDecimal("0");
			
			// 販売点数(当週)
			BigDecimal amount = new BigDecimal("0");
			
			// 販売点数(4週)取得
			fourAmount.add(getAmount(fourTransactionsInfoList, product.getProductId()));

			// 販売点数(4週)設定
			subForm.setFourWeekNumberSales(fourAmount);
			
			// 販売点数(3週)取得
			fourAmount.add(getAmount(threeTransactionsInfoList, product.getProductId()));

			// 販売点数(3週)設定
			subForm.setThreeWeekNumberSales(threeAmount);
			
			// 販売点数(2週)取得
			fourAmount.add(getAmount(twoTransactionsInfoList, product.getProductId()));

			// 販売点数(2週)設定
			subForm.setTowWeekNumberSales(twoAmount);
			
			// 販売点数(前週)取得
			fourAmount.add(getAmount(oneTransactionsInfoList, product.getProductId()));

			// 販売点数(前週)設定
			subForm.setOneWeekNumberSales(twoAmount);
			
			// 販売点数(当週)取得
			fourAmount.add(getAmount(transactionsInfoList, product.getProductId()));

			// 販売点数(当週)設定
			subForm.setThisWeekNumberSales(amount);

			
			// 入荷予定数設定　　質問するの忘れてた
			
			
//			// 在庫点数設定
//			subForm.setStockAmount(Integer.parseInt(stockAmountMap.get(productsInfo.getProductId())));
			subForm.setStockAmount(0);
			// TODO
			// 在庫日数　計算して取得する？
			subForm.setStockDays(999);
			subForm.setConditionSection("test");
			
			displayList.add(subForm);
		}
		orderConfirmForm.setDisplayList(displayList);
		
		logger.info("getDisplayInfoList:発注確認画面初期表示情報取得　処理終了");
		return orderConfirmForm;
	}

	private BigDecimal getAmount(List<TransactionsInfo> transactionsInfoList, String productId) {
		BigDecimal traAmount = new BigDecimal("0");
		// 販売点数
		for (TransactionsInfo transactionsInfo : transactionsInfoList) {
			List<Details> traDetailsList = transactionsInfo.getDetails();
			for (Details traDetails : traDetailsList) {
				if (traDetails.getProductId().equals(productId)) {
					traAmount.add(new BigDecimal(transactionsInfo.getAmount())) ;
				}
			}
		}
		return traAmount;
	}

	/**
	 * システム日付-n週間前 Fromを取得
	 * 
	 * システム日付-n週間前 Fromを計算する
	 * 
	 * @param int 日数
	 * @return String システム日付 - 日数(YYYY-MM-DD)
	 */
	private String getToDate(int week) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
        String dateString = sdf.format(gc.getTime());
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssTZD");
//		String date = sdf.format(calendar.getTime());
		return dateString;
	}

	/**
	 * システム日付-n週間前 Fromを取得
	 * 
	 * システム日付-n週間前 Fromを計算する
	 * 
	 * @param int 日数
	 * @return String システム日付 - 日数(YYYY-MM-DD)
	 */
	private String getFromDate(int week) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
        String dateString = sdf.format(gc.getTime());       
//        gc.setTime(sdf.parse(dateString));      
//        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssTZD");
//		String date = sdf.format(calendar.getTime());
		return dateString;
	}
	/**
	 * 発注情報を取得
	 * 
	 * 発注取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param id 発注ID
	 * @return List 商品ID
	 */
	public PurchaseOrdersInfo getOrderInfo(SmarejiUser smarejiUser, String storageInfoId) {

		logger.info("getOrderInfo:発注情報取得　処理開始");

		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();
		
		// 発注情報を取得(API)
		ParamPurchaseOrderInfo param = new ParamPurchaseOrderInfo();
		
		// 発注情報取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		
		// 取得条件
		// 発注対象商品、発注配送商品を付加する
		param.setWith_products("all"); 
		
		// 取得項目
		getParam.add("recipientOrderId");	
		param.setFields(getParam);
		
		// 発注情報を取得(API)	  
		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrderInfo(smarejiUser.getContract().getId(), "27", param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の発注情報取得
		//purchaseOrdersInfo = smarejiApiAccessMock.getPurchaseOrderInfo("dummyid");
		/***********************************************/

		logger.info("getOrderInfo:発注情報取得　処理終了");

		return purchaseOrdersInfo;
	}

	/**
	 * 商品情報を取得
	 * 
	 * 商品取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param productId 商品ID
	 * @return List 商品ID
	 */
	public ProductsInfo getProductsInfo(SmarejiUser smarejiUser, String productId) {

		logger.info("getProductsInfo:商品情報取得　処理開始");

		ParamProductInfo paramProductInfo = new ParamProductInfo();
		
		// 商品情報を取得(API)
		ProductsInfo productsInfo = new ProductsInfo();

		// 商品情報取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		
		// 取得する項目
		getParam.add("groupCode");
		getParam.add("productCode");
		getParam.add("productName");
		getParam.add("categoryId");
		paramProductInfo.setFields(getParam);

		// 商品情報を取得(API)
		productsInfo = smarejiApiAccess.getProductInfo(smarejiUser.getContract().getId(), productId, paramProductInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の発注情報取得
		//productsInfo = smarejiApiAccessMock.getProductInfo("dummyid");
		/***********************************************/

		logger.info("getProductsInfo:商品情報取得　処理終了");

		return productsInfo;
	}
	
	/**
	 * 部門情報を取得
	 * 
	 * 部門取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param id 部門ID
	 * @return String 部門名
	 */
	public CategorieInfo getCategoryName(SmarejiUser smarejiUser, String id) {

		logger.info("getCategoryName:部門情報取得　処理開始");

		ParamCategorieInfo paramCategorieInfo = new ParamCategorieInfo();
		
		CategorieInfo categorieInfo = new CategorieInfo();

		// 部門情報取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();

		// 取得する項目
		getParam.add("categoryName");
		paramCategorieInfo.setFields(getParam);

		// 発注情報を取得(API)
		categorieInfo = smarejiApiAccess.getCategorieInfo(smarejiUser.getContract().getId(), id, paramCategorieInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の発注情報取得
		//categorieInfo = smarejiApiAccessMock.getCategorieInfo("dummyid");
		/***********************************************/
		
		logger.info("getCategoryName:部門情報取得　処理終了");

		return categorieInfo;
	}

	/**
	 * 在庫一覧を取得
	 * 
	 * 在庫一覧取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param id 発注ID
	 * @return Map 商品ID・在庫数
	 */
	public List<StockInfo> getStockAmountList(SmarejiUser smarejiUser, String id) {

		logger.info("getStockAmountList:在庫一覧取得　処理開始");

		List<StockInfo> stockInfoList= new ArrayList<StockInfo>();

		// 在庫一覧を取得(API)
//		List<StockInfo> = smarejiApiAccess.getStockInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の在庫一覧取得
		//stockInfoList = smarejiApiAccessMock.getStockInfo("dummyid");
		/***********************************************/

		// 取得する項目
//		getParam.add("stockAmount");

		// 在庫数取得
		Iterator<StockInfo> it = stockInfoList.iterator();
		Map<String, String> stockAmountMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 商品ID、在庫数の設定
			StockInfo stock = it.next();
			stockAmountMap.put(stock.getProductId(), stock.getStockAmount());
		}

		logger.info("getStockAmountList:在庫一覧取得　処理終了");

		return stockInfoList;
	}
	/**
	 * 商品画像一覧を取得
	 * 
	 * 商品画像一覧取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param 商品id ID
	 * @return List<ProductImageInfo> 商品画像一覧
	 */
	public List<ProductImageInfo> getProductImageInfo(SmarejiUser smarejiUser, String id) {

		logger.info("getProductImageInfo:商品画像一覧取得　処理開始");

		List<ProductImageInfo> productImageInfoList = new ArrayList<ProductImageInfo>();
		
		ParamProductImage param = new ParamProductImage();
		
		List<String> getParam = new ArrayList<String>();
		getParam.add("url");
		getParam.add("productId");
	 	param.setFields(getParam);
	 	
		// 商品画像一覧を取得(API)
		productImageInfoList = smarejiApiAccess.getProductsImage(smarejiUser.getContract().getId(), param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の商品画像一覧を取得
		//productImageInfo = smarejiApiAccessMock.getProductImageInfo("dummyid");
		/***********************************************/
		
		logger.info("getProductImageInfo:商品画像一覧取得　処理終了");

		return productImageInfoList;
	}

	/**
	 * 仕入先一覧取得を取得
	 * 
	 * 仕入先一覧取得APIを使用して仕入先一覧を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return List<SuppliersInfo> 
	 */
	private List<SuppliersInfo> getSuppliersInfoList(SmarejiUser smarejiUser) {
		
		logger.info("getSuppliersInfoList:仕入先一覧取得　処理開始");

		List<SuppliersInfo> suppliersInfoList = new ArrayList<SuppliersInfo>();
		
		ParamSupplierInfo param = new ParamSupplierInfo();
		
		List<String> getParam = new ArrayList<String>();
		getParam.add("supplierId");
		getParam.add("supplierName");
	 	param.setFields(getParam);
	 	
		// 仕入先一覧を取得(API)
	 	suppliersInfoList = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の商品画像一覧を取得
		//productImageInfo = smarejiApiAccessMock.getProductImageInfo("dummyid");
		/***********************************************/
		
		logger.info("getSuppliersInfoList:仕入先一覧取得　処理終了");
	
		return suppliersInfoList;
	}

	/**
	 * 取引一覧取得を取得
	 * 
	 * 取引一覧取得APIを使用して取引一覧を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param 発注先となる仕入先ID recipientOrderId
	 * @return List<TransactionsInfo>
	 */
	private List<TransactionsInfo> getTransactionsInfo(SmarejiUser smarejiUser, String from, String to) {

		logger.info("getTransactionsInfo:取引一覧取得　処理開始");

		List<TransactionsInfo> transactionsInfoList = new ArrayList<TransactionsInfo>();
		
		ParamTransactionInfo param = new ParamTransactionInfo();
		
//		List<String> getParam = new ArrayList<String>();
		
		//query Parameters
		param.setTransaction_date_time_from(from); 
		param.setTransaction_date_time_to(to); 
		param.setWith_details("summary");

		// 取得項目
//	 	param.setFields(getParam);
	 	
		// 取引一覧を取得(API)
	 	transactionsInfoList = smarejiApiAccess.getTransactionsInfo(smarejiUser.getContract().getId(), param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の商品画像一覧を取得
		//productImageInfo = smarejiApiAccessMock.getProductImageInfo("dummyid");
		/***********************************************/

		logger.info("getTransactionsInfo:取引一覧取得　処理終了");

		return transactionsInfoList;
	}
}
