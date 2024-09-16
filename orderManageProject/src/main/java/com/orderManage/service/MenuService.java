package com.orderManage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.model.api.StaffInfo;
import com.orderManage.model.api.UserAccessToken;
import com.orderManage.model.param.ParamStaffInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;
import com.orderManage.util.StringUtil;

/**
 * メニュー画面サービスクラス
 * 
 * メニュー画面での操作を行う
 * @author aocon-mac
 *
 */
@Service
public class MenuService extends OrderManageService {

	/* スマレジAPIアクセスクラス定義 */
	@Autowired
	SmarejiApiAccess smarejiApiAccess;

	/**
	 * ユーザアクセストークンを取得
	 * 
	 * @param code アプリ認可コード
	 * @return UserAccessToken ユーザアクセストークン
	 */
	public UserAccessToken getUserAccessToken(String code) {
		
		logger.info("service:ユーザアクセストークン取得処理 start");
		
		UserAccessToken userAccessToken = new UserAccessToken();
		// authorization_code（code）を使用してユーザーアクセストークンをリクエストする
		userAccessToken = smarejiApiAccess.getUserAccessToken(code);
		
		logger.info("service:ユーザアクセストークン取得処理 end");
		
		return userAccessToken;
	}
	
	/**
	 * スマレジユーザを取得
	 * 
	 * @param userAccessToken ユーザアクセストークン
	 * @return SmarejiUser スマレジユーザ
	 */
	public SmarejiUser getSmarejiUser(String userAccessToken) {
		
		logger.info("service:スマレジユーザ取得処理 start");
	
		SmarejiUser smarejiUser = new SmarejiUser();
		// ユーザーアクセストークンを使用してユーザー情報取得する
		smarejiUser = smarejiApiAccess.getSmarejiUserInfo(userAccessToken);		
		
		logger.info("service:スマレジユーザ取得処理 end");
		
		return smarejiUser;
	}
	
	/**
	 * スタッフ情報を取得
	 * 
	 * スタッフ一覧取得APIを使用してスタッフコード・スタッフ名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return Map スタッフコード・スタッフ名
	 */
	public StaffInfo getStaffInfo(SmarejiUser smarejiUser) {
		
		logger.info("service:スタッフ情報取得　処理開始");

		List<StaffInfo> staffInfoList = new ArrayList<StaffInfo>();

		// スタッフ一覧取得パラメータを設定
		ParamStaffInfo param = new ParamStaffInfo();		
		
		// 取得パラメータにスタッフIDを設定
		String staffId = smarejiUser.getContract().getUser_id();
		if (!StringUtil.isEmpty(staffId)) {			
			param.setStaff_id(Integer.parseInt(staffId));	// スタッフIDが取得できた場合のみ条件に設定
		}		

		// スタッフ一覧を取得(API)
		staffInfoList = smarejiApiAccess.getStaffsInfo(smarejiUser.getContract().getId(), param);
		
		Iterator<StaffInfo> it = staffInfoList.iterator();
		StaffInfo staffInfo = new StaffInfo();
		while (it.hasNext()) {
			// スタッフ情報の取得
			staffInfo = it.next();
		}
		
		logger.info("service:スタッフ情報取得　処理終了");
		
		return staffInfo;
	}
}
