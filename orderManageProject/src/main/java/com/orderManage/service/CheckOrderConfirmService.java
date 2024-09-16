package com.orderManage.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.controller.object.CheckOrderConfirmForm;
import com.orderManage.controller.object.CheckOrderConfirmSubForm;
import com.orderManage.model.api.OrderProducts;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StaffInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.param.ParamPurchaseOrderInfo;
import com.orderManage.model.param.ParamStaffInfo;
import com.orderManage.model.param.ParamSupplierInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;
import com.orderManage.util.SmarejiApiAccessMock;
import com.orderManage.util.StringUtil;

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
	
	/* ステータス(5:仮発注) */
    private final String STATUS_TENTATIVE_ORDER = "5";
	
	/* 1ページ表示数(デフォルト値50) */
	private final int DEF_MAX_DISP_AMOUNT = 50;

	/* ページ番号(デフォルト値1) */
	private final int DEF_CUR_PAGE = 1;
	
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
		
		// 仕入先一覧取得パラメータを設定
		ParamSupplierInfo param = new ParamSupplierInfo();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("supplierId");
		getParam.add("supplierName");
		param.setFields(getParam);
		
		// 仕入先一覧を取得(API)
		supplierInfoList = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の仕入先一覧取得
		//supplierInfoList = smarejiApiAccessMock.getSuppliersInfo("dummyid");
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
		
		logger.info("getStaffInfo:スタッフ一覧取得　処理開始");

		List<StaffInfo> staffInfoList = new ArrayList<StaffInfo>();

		// スタッフ一覧取得パラメータを設定
		ParamStaffInfo param = new ParamStaffInfo();		
		
		// 取得条件
		String staffId = smarejiUser.getContract().getUser_id();
		if (!StringUtil.isEmpty(staffId)) {			
			param.setStaff_id(Integer.parseInt(staffId));	// スタッフIDが取得できた場合のみ条件に設定
		}		
		
		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("staffId");
		getParam.add("staffName");
		param.setFields(getParam);
		
		// スタッフ一覧を取得(API)
		staffInfoList = smarejiApiAccess.getStaffsInfo(smarejiUser.getContract().getId(), param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用のスタッフ一覧取得
		//staffInfoList = smarejiApiAccessMock.getStaffInfo("dummyid");
		/***********************************************/
		
		Iterator<StaffInfo> it = staffInfoList.iterator();
		Map<String, String> staffInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// スタッフコード、スタッフ名の設定
			StaffInfo staff = it.next();
			staffInfoMap.put(staff.getStaffId(), staff.getStaffName());
		}
		
		logger.info("getStaffInfo:スタッフ一覧取得　処理終了");
		
		return staffInfoMap;
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
		
		// 発注一覧を取得(API)
		purchaseOrdersInfoList = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), param);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の発注一覧取得
		//purchaseOrdersInfoList = smarejiApiAccessMock.getPurchaseOrdersInfo("dummyid");
		/***********************************************/
		
		// パラメータの発注管理番号と一致するデータのみに絞り込み		
		List<PurchaseOrdersInfo> resultList = new ArrayList<PurchaseOrdersInfo>();
		for(PurchaseOrdersInfo orderInfo : purchaseOrdersInfoList) {
			if (!orderControlNumber.equals(orderInfo.getIdentificationNo())) {
				continue;
			}

			PurchaseOrdersInfo resultModel = new PurchaseOrdersInfo();
			resultModel.setStorageInfoId(orderInfo.getStorageInfoId());
			resultList.add(resultModel);
		}
		
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
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の発注情報取得		
		//purchaseOrdersInfo = smarejiApiAccessMock.getPurchaseOrderInfo("dummyid");
		/***********************************************/
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
	
	/**
	 * 発注確認画面初期表示情報を取得
	 * 
	 * 発注確認画面に初期表示する情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderControlNumber 発注管理番号
	 * @return CheckOrderConfirmForm 発注確認画面Formリスト
	 */
	public CheckOrderConfirmForm getDisplayInfo(SmarejiUser smarejiUser, String orderControlNumber) {
		
		logger.info("getDisplayInfoList:発注確認画面初期表示情報取得　処理開始");
		
		CheckOrderConfirmForm checkOrderConfirmForm = new CheckOrderConfirmForm();
		List<CheckOrderConfirmSubForm> displayList = new ArrayList<CheckOrderConfirmSubForm>();
				
		// 仕入先一覧取得API
		Map<String, String> supplierMap = getSupplierInfo(smarejiUser);
		
		// スタッフ一覧取得API
		Map<String, String> staffMap = getStaffInfo(smarejiUser);
		
		// 発注一覧取得API（取得条件：契約ID、ステータス"5：仮発注"、発注管理番号）
		List<PurchaseOrdersInfo> purchaseOrdersInfoList = getPurchaseOrdersInfoList(smarejiUser, orderControlNumber);
		
		// 発注一覧の取得件数分、以下の処理を実施
		for (PurchaseOrdersInfo order : purchaseOrdersInfoList){

			// 発注情報取得API（取得条件：発注ID）
			PurchaseOrdersInfo purchaseOrdersInfo = getPurchaseOrdersInfo(smarejiUser, order.getStorageInfoId());
			
			// 発注商品の数量と金額の合計を取得
			BigDecimal countSum = new BigDecimal("0");
			BigDecimal amountSum = new BigDecimal("0");
			
			for (OrderProducts product : purchaseOrdersInfo.getProducts()){				
				BigDecimal count = new BigDecimal(product.getQuantity());
				BigDecimal cost = new BigDecimal(product.getCost());
				countSum = countSum.add(count);
				amountSum = amountSum.add(cost.multiply(count)); 
			}
			
			// 名称取得（仕入先、スタッフ）
			String supplierName = supplierMap.get(purchaseOrdersInfo.getRecipientOrderId());
			String staffName = staffMap.get(purchaseOrdersInfo.getStaffId());
			
			// 画面表示用Formに情報を設定
			DecimalFormat decimalFormat = new DecimalFormat("#,###.#");		
			CheckOrderConfirmSubForm subForm = new CheckOrderConfirmSubForm();
			subForm.setOrderId(purchaseOrdersInfo.getStorageInfoId());		// 発注ID
			subForm.setOrderedDate(purchaseOrdersInfo.getOrderedDate());	// 発注日		
			subForm.setSupplierName(supplierName);							// 仕入先名		
			subForm.setOrderStaffName(staffName);							// 発注者名
			subForm.setOrderCount(decimalFormat.format(countSum));			// 発注点数
			subForm.setOrderAmountSum(decimalFormat.format(amountSum.setScale(1, RoundingMode.HALF_UP))); // 発注金額合計（小数点以下第二位を四捨五入）
			displayList.add(subForm);
		}

		checkOrderConfirmForm.setDisplayList(displayList);
		
		logger.info("getDisplayInfoList:発注確認画面初期表示取得　処理終了");
		
		return checkOrderConfirmForm;
	}
	

	/**
	 * ページング処理
	 * 
	 * @param pageable 
	 * @param form
	 * @return
	 */
	/**
	public Page<CheckOrderConfirmSubForm> test(Pageable pageable, CheckOrderConfirmForm form) {

		List<CheckOrderConfirmSubForm> purchaseOrderInfoList = form.getDisplayList();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<CheckOrderConfirmSubForm> list;
	
		if(purchaseOrderInfoList.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize , purchaseOrderInfoList.size());
			list = purchaseOrderInfoList.subList(startItem, toIndex);
		}
		
		Page<CheckOrderConfirmSubForm> purchaseOrdersInfoPage = new PageImpl<CheckOrderConfirmSubForm>(list, PageRequest.of(currentPage, pageSize),purchaseOrderInfoList.size());
		return purchaseOrdersInfoPage;
		
	}
	**/
}
