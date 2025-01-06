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
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamProductImage;
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
}
