package com.orderManage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderManage.model.api.StoreInfo;
import com.orderManage.model.param.ParamStoreInfo;
import com.orderManage.model.session.SmarejiUser;

/**
 * 店舗選択画面サービスクラス
 * 
 * 店舗選択画面での操作を行う
 * 
 * @author aocon-mac
 */
@Service
public class StoreChoiceService extends OrderManageService {

	/** API引数（取得上限数） */
	private static int API_LIMIT = 1000;

	/**
	 * 店舗一覧情報を取得
	 * 
	 * 店舗一覧情報取得APIを使用して店舗コード・店舗名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return 店舗一覧情報Map
	 */
	public Map<String, String> getStoresInfo(SmarejiUser smarejiUser) {
		
		logger.info("getStoresInfo:店舗一覧情報取得　処理開始");
		
		List<StoreInfo> storeInfoList = new ArrayList<StoreInfo>();
		
		// 店舗一覧を取得(API)
		ParamStoreInfo paramStoresInfo = new ParamStoreInfo();
		
		// 店舗一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("storeId");
		getParam.add("storeCode");
		getParam.add("storeName");
		// 取得パラメータ
		paramStoresInfo.setFields(getParam);
		//　ソート順
		paramStoresInfo.setSort("storeId");
		// 取得上限数
		paramStoresInfo.setLimit(API_LIMIT);
		
		storeInfoList = smarejiApiAccess.getStoresInfo(smarejiUser.getContract().getId(), paramStoresInfo);
		
		Iterator<StoreInfo> it = storeInfoList.iterator();
		Map<String, String> storeInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 店舗コード、店舗名の設定
			StoreInfo st = it.next();
			storeInfoMap.put(st.getStoreCode(), st.getStoreName());
		}
		
		logger.info("getStoresInfo:店舗一覧情報取得　処理終了");
		
		return storeInfoMap;
	}

	/**
	 * 店舗情報を取得
	 * 
	 * 店舗情報取得APIを使用して店舗コード・店舗名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param storeId 店舗ID
	 * @return 店舗情報
	 */
	public StoreInfo getStoreInfo(SmarejiUser smarejiUser, String storeId) {

		logger.info("getStoreInfo:店舗情報取得　処理開始");
		
		StoreInfo storeInfo = new StoreInfo();
		
		// 店舗一覧を取得(API)
		ParamStoreInfo paramStoresInfo = new ParamStoreInfo();
		
		// 店舗一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("storeId");
		getParam.add("storeCode");
		getParam.add("storeName");
		// 取得パラメータ
		paramStoresInfo.setFields(getParam);
		//　ソート順
		paramStoresInfo.setSort("storeId");
		// 取得上限数
		paramStoresInfo.setLimit(1);
		
		storeInfo = smarejiApiAccess.getStoreInfo(smarejiUser.getContract().getId(), storeId, paramStoresInfo);
		
		logger.info("getStoreInfo:店舗情報取得　処理終了");
		
		return storeInfo;
	}
}
