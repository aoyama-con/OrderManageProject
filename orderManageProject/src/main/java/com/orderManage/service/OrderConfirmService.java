package com.orderManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orderManage.controller.object.OrderConfirmForm;
import com.orderManage.controller.object.OrderConfirmSubForm;
import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.DeliveryStore;
import com.orderManage.model.api.OrderProducts;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamProductImage;
import com.orderManage.model.param.ParamProductInfo;
import com.orderManage.model.param.ParamPurchaseOrderInfo;
import com.orderManage.model.param.ParamStockInfo;
import com.orderManage.model.param.ParamSupplierInfo;
import com.orderManage.model.param.ParamUpdatePurchasOrderDeliveryStore;
import com.orderManage.model.param.ParamUpdatePurchaseOrder;
import com.orderManage.model.param.ParamUpdatePurchaseOrderProduct;
import com.orderManage.model.session.SmarejiUser;

/**
 * 発注確定画面サービスクラス
 * 
 * 発注確定画面での操作を行う
 * @author aocon-mac
 *
 */
@Service
public class OrderConfirmService extends OrderManageService {

	//2024/08/05 機能削減のためコメントアウト
	/* 一週間の日数*/
//	private final int WEEK_DAYS = 7;
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
		
		// 店舗IDの取得　発注IDに紐づく商品の店舗は店舗選択画面により一つのため、発注情報から店舗IDを取得する
		String storeId = purchaseOrdersInfo.getProducts().get(0).getDeliveryStore().get(0).getStoreId();

		// 仕入れ先取得　
		List<SuppliersInfo> suppliersInfoList = getSuppliersInfoList(smarejiUser);

		// 画像取得API
		List<ProductImageInfo> productImageInfoList = getProductImageInfo(smarejiUser, orderId);

		// 在庫一覧API
		List<StockInfo> stockInfoList = getStockAmountList(smarejiUser, storeId);

//2024/08/05 機能削減のためコメントアウト//////////////////////////////////////////////////////////////////////
		// システム日付取得
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
//        GregorianCalendar gc = new GregorianCalendar();
//        String sysDate = sdf.format(gc.getTime()); 
//		
//		// 今週取引一覧取得API
//		List<TransactionsInfo> transactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(1), sysDate);
//		
//		// 前週取引一覧取得API
//		List<TransactionsInfo> oneTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(2), getToDate(1));
//		
//		// 2週取引一覧取得API
//		List<TransactionsInfo> twoTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(3), getToDate(2));
//		
//		// 3週取引一覧取得API
//		List<TransactionsInfo> threeTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(4), getToDate(3));
//		
//		// 4週取引一覧取得API
//		List<TransactionsInfo> fourTransactionsInfoList = getTransactionsInfo(smarejiUser, getFromDate(5), getToDate(4));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

			// TODO 商品ID設定 商品情報から商品IDがとれない　商品IDは発注情報でとった商品IDを使う？
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
					subForm.setSupplierName(suppliersInfo.getSupplierName());
				}
			}

			// 部門名取得API
			CategorieInfo categorieInfo = getCategoryName(smarejiUser, productsInfo.getCategoryId());
			// 部門名設定
			subForm.setCategoryName(categorieInfo.getCategoryName());

//2024/08/05 機能削減のためコメントアウト///////////////////////////////////////////////////////////////////////
//			// 販売点数(4週)
//			BigDecimal fourAmount = new BigDecimal("0");
//			
//			// 販売点数(3週)
//			BigDecimal threeAmount = new BigDecimal("0");
//			
//			// 販売点数(2週)
//			BigDecimal twoAmount = new BigDecimal("0");
//			
//			// 販売点数(前週)
//			BigDecimal oneAmount = new BigDecimal("0");
//			
//			// 販売点数(当週)
//			BigDecimal amount = new BigDecimal("0");
//			
//			// 販売点数(4週)取得
//			fourAmount.add(getAmount(fourTransactionsInfoList, product.getProductId()));
//
//			// 販売点数(4週)設定
//			subForm.setFourWeekNumberSales(fourAmount);
//			
//			// 販売点数(3週)取得
//			fourAmount.add(getAmount(threeTransactionsInfoList, product.getProductId()));
//
//			// 販売点数(3週)設定
//			subForm.setThreeWeekNumberSales(threeAmount);
//			
//			// 販売点数(2週)取得
//			fourAmount.add(getAmount(twoTransactionsInfoList, product.getProductId()));
//
//			// 販売点数(2週)設定
//			subForm.setTowWeekNumberSales(twoAmount);
//			
//			// 販売点数(前週)取得
//			fourAmount.add(getAmount(oneTransactionsInfoList, product.getProductId()));
//
//			// 販売点数(前週)設定
//			subForm.setOneWeekNumberSales(twoAmount);
//			
//			// 販売点数(当週)取得
//			fourAmount.add(getAmount(transactionsInfoList, product.getProductId()));
//
//			// 販売点数(当週)設定
//			subForm.setThisWeekNumberSales(amount);
///////////////////////////////////////////////////////////////////////////////////////////////////

			// 在庫点数設定 発注した店舗に紐づく商品IDの在庫点数を設定
			for (StockInfo stockInfo : stockInfoList) {
				if (stockInfo.getProductId().equals(product.getProductId())) {
					subForm.setStockAmount(Integer.parseInt(stockInfo.getStockAmount()));
				}
			}
			
			// 発注数量の設定
			subForm.setOrderingPoint(Integer.valueOf(product.getQuantity()));
			
			displayList.add(subForm);
		}
		orderConfirmForm.setDisplayList(displayList);
		
		logger.info("getDisplayInfoList:発注確認画面初期表示情報取得　処理終了");
		return orderConfirmForm;
	}

	//2024/08/05 機能削減のためコメントアウト
//	private BigDecimal getAmount(List<TransactionsInfo> transactionsInfoList, String productId) {
//		BigDecimal traAmount = new BigDecimal("0");
//		// 販売点数
//		for (TransactionsInfo transactionsInfo : transactionsInfoList) {
//			List<Details> traDetailsList = transactionsInfo.getDetails();
//			for (Details traDetails : traDetailsList) {
//				if (traDetails.getProductId().equals(productId)) {
//					traAmount.add(new BigDecimal(transactionsInfo.getAmount())) ;
//				}
//			}
//		}
//		return traAmount;
//	}

//	/**
//	 * システム日付-n週間前 Fromを取得
//	 * 
//	 * システム日付-n週間前 Fromを計算する
//	 * 
//	 * @param int 日数
//	 * @return String システム日付 - 日数(YYYY-MM-DD)
//	 */
//	private String getToDate(int week) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
//        String dateString = sdf.format(gc.getTime());
////		Calendar calendar = Calendar.getInstance();
////		calendar.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssTZD");
////		String date = sdf.format(calendar.getTime());
//		return dateString;
//	}

//	/**
//	 * システム日付-n週間前 Fromを取得
//	 * 
//	 * システム日付-n週間前 Fromを計算する
//	 * 
//	 * @param int 日数
//	 * @return String システム日付 - 日数(YYYY-MM-DD)
//	 */
//	private String getFromDate(int week) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
//        GregorianCalendar gc = new GregorianCalendar();
//        gc.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
//        String dateString = sdf.format(gc.getTime());       
////        gc.setTime(sdf.parse(dateString));      
////        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
////		Calendar calendar = Calendar.getInstance();
////		calendar.add(Calendar.DAY_OF_MONTH, - (WEEK_DAYS * week));
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssTZD");
////		String date = sdf.format(calendar.getTime());
//		return dateString;
//	}
	/**
	 * 発注情報を取得
	 * 
	 * 発注取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderId 発注ID
	 * @return List 商品ID
	 */
	public PurchaseOrdersInfo getOrderInfo(SmarejiUser smarejiUser, String orderId) {

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
		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrderInfo(smarejiUser.getContract().getId(), orderId, param);
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
	 * @param storeId 店舗ID
	 * @return List 在庫一覧リスト
	 */
	public List<StockInfo> getStockAmountList(SmarejiUser smarejiUser, String storeId) {

		logger.info("getStockAmountList:在庫一覧取得　処理開始");

		List<StockInfo> stockInfoList= new ArrayList<StockInfo>();

		ParamStockInfo paramStockInfo = new ParamStockInfo();
		

		// 在庫一取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		
		// 店舗IDの設定
		paramStockInfo.setStore_id(Integer.valueOf(storeId));
		// 取得する項目
		getParam.add("productId");
		getParam.add("stockAmount");
		paramStockInfo.setFields(getParam);

		// 在庫一覧を取得(API)
		stockInfoList = smarejiApiAccess.getStocksInfo(smarejiUser.getContract().getId(), paramStockInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の在庫一覧取得
		//stockInfoList = smarejiApiAccessMock.getStockInfo("dummyid");
		/***********************************************/

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

	//2024/08/05 機能削減のためコメントアウト
//	/**
//	 * 取引一覧取得を取得
//	 * 
//	 * 取引一覧取得APIを使用して取引一覧を取得する
//	 * 
//	 * @param smarejiUser スマレジユーザ情報
//	 * @param 発注先となる仕入先ID recipientOrderId
//	 * @return List<TransactionsInfo>
//	 */
//	private List<TransactionsInfo> getTransactionsInfo(SmarejiUser smarejiUser, String from, String to) {
//
//		logger.info("getTransactionsInfo:取引一覧取得　処理開始");
//
//		List<TransactionsInfo> transactionsInfoList = new ArrayList<TransactionsInfo>();
//		
//		ParamTransactionInfo param = new ParamTransactionInfo();
//		
////		List<String> getParam = new ArrayList<String>();
//		
//		//query Parameters
//		param.setTransaction_date_time_from(from); 
//		param.setTransaction_date_time_to(to); 
//		param.setWith_details("summary");
//
//		// 取得項目
////	 	param.setFields(getParam);
//	 	
//		// 取引一覧を取得(API)
//	 	transactionsInfoList = smarejiApiAccess.getTransactionsInfo(smarejiUser.getContract().getId(), param);
//		/** テスト用 ローカルで動かす用mockを使用 *****************/
//		// テスト用の商品画像一覧を取得
//		//productImageInfo = smarejiApiAccessMock.getProductImageInfo("dummyid");
//		/***********************************************/
//
//		logger.info("getTransactionsInfo:取引一覧取得　処理終了");
//
//		return transactionsInfoList;
//	}
	
	/**
	 * 発注更新を行う
	 * 
	 * 発注確定画面にて入力された発注に対して発注済みとする
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderId 発注ID
	 * @param OrderConfirmList 発注確定画面情報
	 * @return List<SuppliersInfo> 
	 */

	public PurchaseOrdersInfo updatePurchaseOrder(SmarejiUser smarejiUser, String orderId,
			List<OrderConfirmSubForm> OrderConfirmList) {
		
		// 発注情報の取得
		PurchaseOrdersInfo purchaseOrdersInfo = getOrderInfo(smarejiUser, orderId);
		
		// 店舗IDの取得　発注IDに紐づく商品の店舗は店舗選択画面により一つのため、発注情報から店舗IDを取得する
		String storeId = purchaseOrdersInfo.getProducts().get(0).getDeliveryStore().get(0).getStoreId(); 
		
		// 発注更新内容設定
		ParamUpdatePurchaseOrder paramUpdatePurchaseOrder = new ParamUpdatePurchaseOrder();
		// ステータス 発注済み=2
		paramUpdatePurchaseOrder.setStatus("2");
		// 発注処理時のスタッフID
		paramUpdatePurchaseOrder.setStaffId(smarejiUser.getContract().getUser_id());
		// 税丸め（0:四捨五入、1:切り捨て、2:切り上げ） 更新時必須とエラーが発生のため
		paramUpdatePurchaseOrder.setRoundingDivision("0");

		// 発注対象商品リスト（products）
		ArrayList<ParamUpdatePurchaseOrderProduct> opList = new ArrayList<ParamUpdatePurchaseOrderProduct>(); 
		// 発注配送商品リスト（products.deliveryStore）
		ArrayList<ParamUpdatePurchasOrderDeliveryStore> dsList = new ArrayList<ParamUpdatePurchasOrderDeliveryStore>();
		
		// 発注対象商品(products)
		ParamUpdatePurchaseOrderProduct op;
		// 発注配送商品(products.deliveryStore)
		ParamUpdatePurchasOrderDeliveryStore ds;

		// 発注確定画面にて入力された発注内容を更新（発注点数）
		for (OrderConfirmSubForm order : OrderConfirmList) {

			//　該当の商品IDを発注情報から探す(発注対象商品)
			for ( OrderProducts orderProducts : purchaseOrdersInfo.getProducts()) {
				// 商品情報初期化
				dsList = new ArrayList<ParamUpdatePurchasOrderDeliveryStore>();
				op = new ParamUpdatePurchaseOrderProduct();

				// 発注IDが同じものを探す
				if (orderProducts.getProductId().equals(order.getProductId())) {

					// 発注商品ID(自動採番)設定
					op.setStorageInfoProductId(orderProducts.getStorageInfoProductId());
					
					//　該当の店舗IDを発注情報から探す(発注配送商品)
					for (DeliveryStore deliveryStore :orderProducts.getDeliveryStore()) {
						// 発注配送商品初期化
						ds = new ParamUpdatePurchasOrderDeliveryStore();
						
						// 店舗IDが同じものを探す
						if (deliveryStore.getStoreId().equals(storeId)) {
							// 発注配送商品ID(自動採番)設定
							ds.setStorageInfoDeliveryProductId(deliveryStore.getStorageInfoDeliveryProductId());
							// 発注点数設定
							ds.setQuantity(String.valueOf(order.getOrderingPoint()));
						}
						dsList.add(ds);
					}
					
					// products.deliveryStore設定
					op.setDeliveryStore(dsList);
					// products設定
					opList.add(op);
				}
			}
		}
		
		// 発注対象商品の設定
		paramUpdatePurchaseOrder.setProducts(opList);
		// 発注対象店舗の設定（nullを設定しないとエラーとなる）
		paramUpdatePurchaseOrder.setStores(null);

		
		// 発注対象商品 array ///////////////////////////////////////////////////////////		
//		// テスト商品12 設定
////		ParamUpdatePurchaseOrderProduct op = new ParamUpdatePurchaseOrderProduct();
//		// 発注商品ID 更新の場合は指定　登録時自動採番した値
//		op.setStorageInfoProductId("32");
//		// 商品ID NULL設定不可
////		op.setProductId("8000010");
//		// コスト NULL設定不可
////		op.setCost("400");
//		ParamUpdatePurchasOrderDeliveryStore ds = new ParamUpdatePurchasOrderDeliveryStore();
//		ds.setStorageInfoDeliveryProductId("32");
////		ds.setStoreId("3");
//		ds.setQuantity("500");
//		dsList.add(ds);
//		op.setDeliveryStore(dsList);
//		opList.add(op);
//		
//		// テスト商品１ 設定
//		dsList = new ArrayList<ParamUpdatePurchasOrderDeliveryStore>();
//		op = new ParamUpdatePurchaseOrderProduct();
//		// 発注商品ID 更新の場合は指定　登録時自動採番した値
//		op.setStorageInfoProductId("33");
//		// 商品ID NULL設定不可
////		op.setProductId("8000001");
//		// コスト NULL設定不可
////		op.setCost("50");
//		ds = new ParamUpdatePurchasOrderDeliveryStore();
//		ds.setStorageInfoDeliveryProductId("33");
////		ds.setStoreId("3");
//		ds.setQuantity("600");
//		dsList.add(ds);
//		op.setDeliveryStore(dsList);
//		opList.add(op);
//		
//		paramUpdatePurchaseOrder.setProducts(opList);
		//////////////////////////////////////////////////////////////////////////////

		// 発注対象店舗 array ///////////////////////////////////////////////////////////
//		ArrayList<ParamUpdatePurchaseOrderStore> osList = new ArrayList<ParamUpdatePurchaseOrderStore>();
//		ParamUpdatePurchaseOrderStore os = new ParamUpdatePurchaseOrderStore();
////		// 発注配送店舗ID 更新の場合は指定　登録時に自動更新した値
//		os.setStorageInfoDeliveryId("19");
////		// 配送店舗ID NULL設定不可
//////		os.setStorageStoreId("3");
//////		ds.setQuantity("200");
//		osList.add(os);
//		paramUpdatePurchaseOrder.setStores(osList);
		//////////////////////////////////////////////////////////////////////////////
		
		purchaseOrdersInfo = smarejiApiAccess.updatePurchaseOrder(smarejiUser.getContract().getId(), orderId, 
				paramUpdatePurchaseOrder);

		return purchaseOrdersInfo;
	}
}
