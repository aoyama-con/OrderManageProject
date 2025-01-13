package com.orderManage.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamProductImage;
import com.orderManage.model.param.ParamPurchaseOrderInfo;
import com.orderManage.model.param.ParamStockInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;
import com.orderManage.util.SmarejiApiAccessMock;


/**
 * サービスのスーパークラス
 * 
 * 各画面のサービスクラスはこれを継承し実装を行う
 * 
 */
@Service
abstract class OrderManageService {

	/* ログ設定 ※定義必須*/
	@Autowired
	OrderManageLoggingService logger;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccess smarejiApiAccess;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccessMock smarejiApiAccessMock;

	/** API引数（取得上限数） */
	protected static int API_LIMIT = 1000;

	/** API引数（取得上限数）ループ時に使用 */
	protected static int API_LIMIT_LOOP = 1000;

	/** APIループ上限数 */
	protected static int API_LOOP_COUNT = 100;
	
	/** ステータス(5:仮発注) **/
	protected static String STATUS_TENTATIVE_ORDER = "5";

	/**
	 * 商品画像一覧を取得
	 * 
	 * 商品画像一覧APIを使用して商品ID・商品画像URLを取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return 商品画像一覧Map
	 */
	public Map<String, String> getProductsImage(SmarejiUser smarejiUser) {
		
		logger.info("getProductsImage:商品画像一覧取得　処理開始");
		
		List<ProductImageInfo> productImageInfoList = new ArrayList<ProductImageInfo>();
		
		// 商品画像を取得(API)
		ParamProductImage paramProductImage = new ParamProductImage();
		
		// 商品画像取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("productId");
		getParam.add("url");
		// 取得パラメータ
		paramProductImage.setFields(getParam);
		//　ソート順
		paramProductImage.setSort("productId");
		// 取得上限数
		paramProductImage.setLimit(API_LIMIT_LOOP);

		for (int page = 1;; page++) {
			paramProductImage.setPage(page);
			
			List<ProductImageInfo> list = smarejiApiAccess.getProductsImage(smarejiUser.getContract().getId(), paramProductImage);
			if (list == null || list.size() == 0) {
				break;
			}
		
			productImageInfoList.addAll(list);
		}

		// TODO StreamAPIでMapにする
//		Iterator<ProductImageInfo> it = productImageInfoList.iterator();
//		Map<String, String> productImageInfoMap = new LinkedHashMap<String, String>();
//		while (it.hasNext()) {
//			// 商品ID、商品画像URLの設定
//			ProductImageInfo st = it.next();
//			productImageInfoMap.put(st.getProductId(), st.getUrl());
//		}

		Map<String, String> productImageInfoMap = productImageInfoList.stream()
				.collect(Collectors.toMap(ProductImageInfo::getProductId, ProductImageInfo::getUrl, (u, v) -> v, LinkedHashMap::new));	// 商品ID、商品画像URL
		
		logger.info("getProductsImage:商品画像一覧取得　処理終了");
		
		return productImageInfoMap;
	}

	/**
	 * 部門一覧を取得
	 * 
	 * 部門一覧APIを使用して商品ID・商品画像URLを取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return 部門一覧Map
	 */
	public Map<String, String> getCategoriesInfo(SmarejiUser smarejiUser) {
		
		logger.info("getCategoriesInfo:部門一覧取得　処理開始");
		
		List<CategorieInfo> categoryInfoList = new ArrayList<CategorieInfo>();
		
		// 部門一覧を取得(API)
		ParamCategorieInfo paramCategorieInfo = new ParamCategorieInfo();
		
		// 部門一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("categoryId");
		getParam.add("categoryCode");
		getParam.add("categoryName");
		// 取得パラメータ
		paramCategorieInfo.setFields(getParam);
		//　ソート順
		paramCategorieInfo.setSort("categoryId");
		// 取得上限数
		paramCategorieInfo.setLimit(API_LIMIT);
		
		categoryInfoList = smarejiApiAccess.getCategoriesInfo(smarejiUser.getContract().getId(), paramCategorieInfo);
		
		// TODO StreamAPIでMapにする
//		Iterator<CategorieInfo> it = categoryInfoList.iterator();
//		Map<String, String> categoryInfoMap = new LinkedHashMap<String, String>();
//		while (it.hasNext()) {
//			// 部門ID、部門名の設定
//			CategorieInfo st = it.next();
//			categoryInfoMap.put(st.getCategoryId(), st.getCategoryName());
//		}

		Map<String, String> categoryInfoMap = categoryInfoList.stream()
				.collect(Collectors.toMap(CategorieInfo::getCategoryId, CategorieInfo::getCategoryName, (u, v) -> v, LinkedHashMap::new)); // 部門ID、部門名
		
		logger.info("getCategoriesInfo:部門一覧取得　処理終了");
		
		return categoryInfoMap;
	}

	/**
	 * 在庫一覧を取得
	 * 
	 * 在庫一覧取得APIを使用して商品ID・在庫数を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param storeId 店舗ID
	 * @return 在庫一覧Map
	 */
	public Map<String, String> getStocksInfo(SmarejiUser smarejiUser, String storeId) {
	
		logger.info("getStocksInfo:在庫一覧取得　処理開始");
		
		List<StockInfo> stocksInfoList = new ArrayList<StockInfo>();
		
		// 在庫一覧取得パラメータを設定
		ParamStockInfo param = new ParamStockInfo();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("storeId");
		getParam.add("productId");
		getParam.add("stockAmount");
		param.setFields(getParam);
		param.setLimit(API_LIMIT_LOOP);

		param.setStore_id(Integer.parseInt(storeId));

		for (int page = 1; page <= API_LOOP_COUNT; page++) {
			param.setPage(page);
		
			// 在庫一覧を取得(API)
			List<StockInfo> list = smarejiApiAccess.getStocksInfo(smarejiUser.getContract().getId(), param);
			if (list == null || list.size() == 0) {
				break;
			}
			
			stocksInfoList.addAll(list);
		}
		
		// TODO StreamAPIでMapにする
//		Iterator<StockInfo> it = stocksInfoList.iterator();
//		Map<String, String> stocksInfoMap = new LinkedHashMap<String, String>();
//		while (it.hasNext()) {
//			// 商品ID、在庫数の設定
//			StockInfo sup = it.next();
//			stocksInfoMap.put(sup.getProductId(), sup.getStockAmount());
//		}

		Map<String, String> stocksInfoMap = stocksInfoList.stream()
				.collect(Collectors.toMap(StockInfo::getProductId, StockInfo::getStockAmount, (u, v) -> v, LinkedHashMap::new));	// 商品ID、在庫数

		logger.info("getStocksInfo:在庫一覧取得　処理終了");
		
		return stocksInfoMap;
	}
	
	/**
	 * 発注一覧情報を取得
	 * 
	 * 発注一覧取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderControlNumber 前画面から受け取った発注管理番号
	 * @return List 発注一覧情報
	 */
	public List<PurchaseOrdersInfo> getPurchaseOrdersInfoList(SmarejiUser smarejiUser, String orderControlNumber) {
		
		logger.info("getPurchaseOrdersInfo:発注一覧取得　処理開始");
		
		List<PurchaseOrdersInfo> purchaseOrdersInfoList = new ArrayList<PurchaseOrdersInfo>();
		
		// 発注一覧取得パラメータを設定
		ParamPurchaseOrderInfo param = new ParamPurchaseOrderInfo();
		
		// 取得条件
		param.setStatus(STATUS_TENTATIVE_ORDER); // ステータス（5：仮発注）
		
		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("storageInfoId");
		getParam.add("identificationNo"); // 発注管理番号
		param.setFields(getParam);
		
		// 取得件数
		param.setLimit(API_LIMIT);
		
		// ソート順（発注日（昇順）、発注ID（昇順））
		param.setSort("orderedDate:asc," + "storageInfoId:asc");
		
		// 発注一覧を取得(API)
		purchaseOrdersInfoList = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), param);
		logger.info("発注一覧取得API取得件数：" + purchaseOrdersInfoList.size());
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の発注一覧取得
		//purchaseOrdersInfoList = smarejiApiAccessMock.getPurchaseOrdersInfo("dummyid");
		/***********************************************/
		
		// パラメータの発注管理番号と一致するデータのみに絞り込み	
		List<PurchaseOrdersInfo> resultList = new ArrayList<PurchaseOrdersInfo>();
		purchaseOrdersInfoList.stream().filter(x -> orderControlNumber.equals(x.getIdentificationNo()))
				.collect(Collectors.toList()).stream().forEach(z -> {
					PurchaseOrdersInfo resultModel = new PurchaseOrdersInfo();
					resultModel.setStorageInfoId(z.getStorageInfoId());
					resultList.add(resultModel);
				});
		
		/*		List<PurchaseOrdersInfo> resultList = new ArrayList<PurchaseOrdersInfo>();
				for(PurchaseOrdersInfo orderInfo : purchaseOrdersInfoList) {
					if (!orderControlNumber.equals(orderInfo.getIdentificationNo())) {
						continue;
					}
		
					PurchaseOrdersInfo resultModel = new PurchaseOrdersInfo();
					resultModel.setStorageInfoId(orderInfo.getStorageInfoId());
					resultList.add(resultModel);
				}*/
		logger.info("発注管理番号：" + orderControlNumber + "、" + "発注取得件数：" + resultList.size());
		
		logger.info("getPurchaseOrdersInfo:発注一覧報取得　処理終了");
		
		return resultList;
	}
	
	/**
	 * 発注情報を取得
	 * 
	 * 発注取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param storageInfoId 発注ID
	 * @return PurchaseOrdersInfo 発注情報
	 */
	public PurchaseOrdersInfo getPurchaseOrdersInfo(SmarejiUser smarejiUser, String storageInfoId) {
		
		logger.info("getSupplierInfo:発注情報取得　処理開始");
				
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();
		
		// 発注情報取得パラメータを設定
		ParamPurchaseOrderInfo param = new ParamPurchaseOrderInfo();
		
		// 取得条件
		param.setWith_products("all"); // 発注対象商品、発注配送商品を付加する
		
		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("storageInfoId");
		getParam.add("recipientOrderId");
		getParam.add("orderedDate");
		getParam.add("staffId");
		param.setFields(getParam);
		
		// 発注情報を取得(API)	  
		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrderInfo(smarejiUser.getContract().getId(), storageInfoId, param);
		/* TODO APIのResponsesコードを判定する　ステータスコード=429の場合｢Retry-After｣に待機時間(秒数)が返されるのでその間待機し再度クエリーを行うようにする(☞ステータスチェックと待機・リトライは汎用部品化することが望ましい)。
		if (終了条件) {
		    return 戻り値;
		} else {
		    メソッド名(引数);
		    return 戻り値;
		}
		*/
	
		logger.info("getPurchaseOrdersInfo:発注情報取得　処理終了");
		
		return purchaseOrdersInfo;
	}
}
