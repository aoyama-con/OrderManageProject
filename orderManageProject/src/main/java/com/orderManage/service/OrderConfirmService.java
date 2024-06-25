package com.orderManage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.OrderProducts;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.session.SmarejiUser;

/**
 * 店舗選択画面サービスクラス
 * 
 * 店舗選択画面での操作を行う
 * @author aocon-mac
 *
 */
@Service
public class OrderConfirmService extends OrderManageService {

	/**
	 * 発注情報を取得
	 * 
	 * 発注取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param id 発注ID
	 * @return List 商品ID
	 */
	public List<String> getOrderInfo(SmarejiUser smarejiUser, String id) {

		logger.info("getOrderInfo:発注情報取得　処理開始");

		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();

		// 発注情報を取得(API)
		//		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の発注情報取得
		purchaseOrdersInfo = smarejiApiAccessMock.getPurchaseOrderInfo("dummyid");
		/***********************************************/

		// 発注対象商品取得
		List<OrderProducts> orderProductsList = purchaseOrdersInfo.getProducts();

		// 商品ID取得
		Iterator<OrderProducts> it = orderProductsList.iterator();
		List<String> productIdList = new ArrayList<String>();
		while (it.hasNext()) {
			// 商品IDの設定
			OrderProducts op = it.next();
			productIdList.add(op.getProductId());
		}

		logger.info("getOrderInfo:発注情報取得　処理終了");

		return productIdList;
	}

	/**
	 * 商品情報を取得
	 * 
	 * 商品取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param id 発注ID
	 * @return List 商品ID
	 */
	public ProductsInfo getProductsInfo(SmarejiUser smarejiUser, String id) {

		logger.info("getSupplierInfo:商品情報取得　処理開始");

		ProductsInfo productsInfo = new ProductsInfo();

		// 発注情報を取得(API)
		//		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の発注情報取得
		productsInfo = smarejiApiAccessMock.getProductInfo("dummyid");
		/***********************************************/

		logger.info("getSupplierInfo:商品情報取得　処理終了");

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
	public String getCategoryName(SmarejiUser smarejiUser, String id) {

		logger.info("getCategoryName:部門情報取得　処理開始");

		CategorieInfo categorieInfo = new CategorieInfo();

		// 発注情報を取得(API)
		//		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の発注情報取得
		categorieInfo = smarejiApiAccessMock.getCategorieInfo("dummyid");
		/***********************************************/

		// 部門名を取得
		String categoryName = categorieInfo.getCategoryName();
		
		logger.info("getCategoryName:部門情報取得　処理終了");

		return categoryName;
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
	public Map<String, String> getStockAmountList(SmarejiUser smarejiUser, String id) {

		logger.info("getStockAmountList:在庫一覧取得　処理開始");

		List<StockInfo> stockInfoList= new ArrayList<StockInfo>();

		// 在庫一覧を取得(API)
		//List<StockInfo> = smarejiApiAccess.getStockInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の在庫一覧取得
		stockInfoList = smarejiApiAccessMock.getStockInfo("dummyid");
		/***********************************************/

		// 在庫数取得
		Iterator<StockInfo> it = stockInfoList.iterator();
		Map<String, String> stockAmountMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 商品ID、在庫数の設定
			StockInfo stock = it.next();
			stockAmountMap.put(stock.getProductId(), stock.getStockAmount());
		}

		logger.info("getStockAmountList:在庫一覧取得　処理終了");

		return stockAmountMap;
	}
	/**
	 * 商品画像一覧を取得
	 * 
	 * 商品画像一覧取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param 商品id ID
	 * @return String URL名
	 */
	public String getProductImageInfo(SmarejiUser smarejiUser, String id) {

		logger.info("getProductImageInfo:商品画像一覧取得　処理開始");

		List<ProductImageInfo> productImageInfo = new ArrayList<ProductImageInfo>();

		// 商品画像一覧を取得(API)
		//		productImageInfo = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/
		// テスト用の商品画像一覧を取得
		productImageInfo = smarejiApiAccessMock.getProductImageInfo("dummyid");
		/***********************************************/

		// URLを取得
		String url = productImageInfo.get(0).getUrl();
		
		logger.info("getProductImageInfo:商品画像一覧取得　処理終了");

		return url;
	}
	/**
	 * 取引一覧を取得
	 * 
	 * 取引一覧得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param 商品id ID
	 * @param 取引日時(From) from
	 * @param 取引日時(To) to
	 * @return int 
	 */
	public int getQuantity(SmarejiUser smarejiUser, String id, String from, String to) {
		int quantity = 0;
		return quantity;
	}

}
