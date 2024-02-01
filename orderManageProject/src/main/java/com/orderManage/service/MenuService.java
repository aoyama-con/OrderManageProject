package com.orderManage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.model.api.UserAccessToken;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;

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
}
