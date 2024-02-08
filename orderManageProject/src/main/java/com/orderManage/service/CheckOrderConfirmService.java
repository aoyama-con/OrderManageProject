package com.orderManage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;
import com.orderManage.util.SmarejiApiAccessMock;

/**
 * 発注内容確認画面画面サービスクラス
 * 
 * 発注内容確認画面画面での操作を行う
 * @author makabe
 *
 */
@Service
public class CheckOrderConfirmService extends OrderManageService {

	/* ログ設定 ※定義必須*/
	@Autowired
	OrderManageLoggingService logger;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccess smarejiApiAccess;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccessMock smarejiApiAccessMock;
	
	/**
	 * 仕入先情報を取得
	 * 
	 * 仕入先一覧取得APIを使用して仕入先コード・仕入先名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return Map 仕入先コード・仕入先名
	 */
	public Map<String, String> getSupplierInfo(SmarejiUser smarejiUser) {
		
		logger.info("getSupplierInfo:仕入先情報取得　処理開始");
		
		List<SuppliersInfo> supplierInfoList = new ArrayList<SuppliersInfo>();
		
		// 仕入先一覧を取得(API)
//		supplierInfoList = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の仕入先一覧取得
		supplierInfoList = smarejiApiAccessMock.getSuppliersInfo("dummyid");
		/***********************************************/
		
		Iterator<SuppliersInfo> it = supplierInfoList.iterator();
		Map<String, String> supplierInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 仕入先コード、仕入先名の設定
			SuppliersInfo sup = it.next();
			supplierInfoMap.put(sup.getSupplierId(), sup.getSupplierName());
		}
		
		logger.info("getSupplierInfo:仕入先情報取得　処理終了");
		
		return supplierInfoMap;
	}
	
	/**
	 * スタッフ情報を取得
	 * 
	 * スタッフ一覧取得APIを使用してスタッフコード・スタッフ名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return Map スタッフコード・スタッフ名
	 */
	public Map<String, String> getStaffInfo(SmarejiUser smarejiUser) {
		
		logger.info("getSupplierInfo:スタッフ情報取得　処理開始");
		
		//TODO スタッフ情報のmodel 
		List<SuppliersInfo> supplierInfoList = new ArrayList<SuppliersInfo>();
		
		// スタッフ一覧を取得(API)
//		supplierInfoList = smarejiApiAccess.getStaffInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用のスタッフ一覧取得
		//supplierInfoList = smarejiApiAccessMock.getStaffInfo("dummyid");
		/***********************************************/
		
		Iterator<SuppliersInfo> it = supplierInfoList.iterator();
		Map<String, String> staffInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// スタッフコード、スタッフ名の設定
			SuppliersInfo sup = it.next();
			staffInfoMap.put(sup.getSupplierId(), sup.getSupplierName());
		}
		
		logger.info("getSupplierInfo:スタッフ情報取得　処理終了");
		
		return staffInfoMap;
	}
	
	/**
	 * 発注情報を取得
	 * 
	 * 発注一覧取得APIを使用して発注情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return List 発注コード・発注名
	 */
	public List<PurchaseOrdersInfo> getOrderInfo(SmarejiUser smarejiUser) {
		
		logger.info("getSupplierInfo:発注情報取得　処理開始");
		
		List<PurchaseOrdersInfo> purchaseOrdersInfoList = new ArrayList<PurchaseOrdersInfo>();
		
		// 発注一覧を取得(API)
//		supplierInfoList = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の発注一覧取得
		purchaseOrdersInfoList = smarejiApiAccessMock.getPurchaseOrdersInfo("dummyid");
		/***********************************************/
		
		
		logger.info("getSupplierInfo:発注情報取得　処理終了");
		
		return purchaseOrdersInfoList;
	}
	
}
