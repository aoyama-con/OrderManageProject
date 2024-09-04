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
 * @author aocon-mac
 *
 */
@Service
public class StoreChoiceService extends OrderManageService {

	/**
	 * 店舗情報を取得
	 * 
	 * 店舗一覧取得APIを使用して店舗コード・店舗名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return Map 店舗コード・店舗名
	 */
	public Map<String, String> getStoresInfo(SmarejiUser smarejiUser) {
		
		logger.info("getStoreNames:店舗名取得　処理開始");
		
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
		paramStoresInfo.setLimit(3);
		
		storeInfoList = smarejiApiAccess.getStoresInfo(smarejiUser.getContract().getId(), paramStoresInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の店舗一覧取得
//		storeInfoList = smarejiApiAccessMock.getStoresInfo("dummyid");
		/***********************************************/
		
		Iterator<StoreInfo> it = storeInfoList.iterator();
		Map<String, String> storeInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 店舗コード、店舗名の設定
			StoreInfo st = it.next();
			storeInfoMap.put(st.getStoreCode(), st.getStoreName());
		}
		
		logger.info("getStoreNames:店舗名取得　処理終了");
		
		return storeInfoMap;
	}

	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public StoreInfo getStoreInfo(SmarejiUser smarejiUser, String storeId) {

		logger.info("getStoreNames:店舗名取得　処理開始");
		
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
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の店舗一覧取得
//		storeInfo = smarejiApiAccessMock.getStoreInfo("dummyid");
		/***********************************************/
		
		logger.info("getStoreNames:店舗名取得　処理終了");
		
		return storeInfo;
	}
}
