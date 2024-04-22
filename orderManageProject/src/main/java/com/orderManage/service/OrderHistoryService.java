package com.orderManage.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderManage.controller.object.OrderHistoryForm;
import com.orderManage.controller.object.OrderHistorySubForm;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.PurchaseOrdersProductsInfo;
import com.orderManage.model.api.StaffInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.param.ParamPurchaseOrderInfo;
import com.orderManage.model.param.ParamPurchaseOrderProduct;
import com.orderManage.model.param.ParamStaffInfo;
import com.orderManage.model.param.ParamSupplierInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.SmarejiApiAccess;
import com.orderManage.util.SmarejiApiAccessMock;

/**
 * 発注履歴画面サービスクラス
 * 
 * 発注履歴画面での操作を行う
 * @author aocon-mac
 *
 */
@Service
public class OrderHistoryService {

	/* ログ設定 ※定義必須*/
	@Autowired
	OrderManageLoggingService logger;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccess smarejiApiAccess;
	
	/* スマレジAPIアクセスクラス定義 ※定義必須*/
	@Autowired
	SmarejiApiAccessMock smarejiApiAccessMock;
	
	/* ステータス(1：すべて) */
	private final String STATUS_ALL = "1";
	
//	/* ステータス(2:発注済) */
//	private final String STATUS_ORDERED = "2";
//	
//	/* ステータス(5:仮発注) */
//	private final String STATUS_TENTATIVE_ORDER = "5";

	/* 1ページ表示数(デフォルト値50) */
    private final int DEF_MAX_DISP_AMOUNT = 50;
    
	/* ページ番号(デフォルト値1) */
    private final int DEF_CUR_PAGE = 1;



	/**
	 * 発注履歴一覧取得処理
	 * 
	 * 発注履歴一覧の取得処理を行う
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return List 発注一覧リスト
	 */
	public List<PurchaseOrdersInfo> getPurchaseOrderInfoList(SmarejiUser smarejiUser, OrderHistoryForm form) {
		logger.info("getPurchaseOrderInfoList:発注履歴一覧取得　処理開始");
		
		List<PurchaseOrdersInfo> purchaseOrderInfoList = new ArrayList<PurchaseOrdersInfo>();
		
		// 発注一覧情報を取得(API)
		ParamPurchaseOrderInfo paramPurchaseOrderInfo = new ParamPurchaseOrderInfo();
		
		// 発注一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		
		// 取得する項目
		getParam.add("storageInfoId");
		getParam.add("recipientOrderId");
		getParam.add("orderedDate");
		getParam.add("status");
		getParam.add("staffId");
		paramPurchaseOrderInfo.setFields(getParam);

		// ソート順
		paramPurchaseOrderInfo.setSort("status:desc,orderedDate");

		// 取得上限数
		paramPurchaseOrderInfo.setLimit(form.getMaxDisplayAmount());
		
		// 取得ページ番号
		paramPurchaseOrderInfo.setPage(form.getCurrentPage());
		
		// 発注先（仕入先）コード
		paramPurchaseOrderInfo.setRecipient_order_id(form.getSupplierId());

		// ステータス
		if (form.getStatus().equals(STATUS_ALL)) {
			paramPurchaseOrderInfo.setStatus(null);
		} else {
			paramPurchaseOrderInfo.setStatus(form.getStatus());
		}

//		purchaseOrderInfoList = smarejiApiAccessMock.getPurchaseOrdersInfo("dummyid");
		purchaseOrderInfoList = smarejiApiAccess.getPurchaseOrdersInfo
				(smarejiUser.getContract().getId(), paramPurchaseOrderInfo);

		logger.info("getPurchaseOrderInfoList:発注履歴一覧取得　処理終了");
		return purchaseOrderInfoList;
	
	}

	/**
	 * 絞り込み条件設定処理
	 * 
	 * 絞り込み条件設定処理を行う
	 * 
	 * @param form 画面情報
	 * @param session セッション情報
	 * @return OrderHistoryForm 条件を設定した画面情報
	 */
	public OrderHistoryForm setCondition(OrderHistoryForm form, OrderHistoryForm session) {
		logger.info("setCondition:絞り込み条件設定　処理開始");

		// 画面情報 > セッション > デフォルトの優先順位で値を設定する
		// ステータス
		if (Objects.isNull(form.getStatus())) {
			if (Objects.isNull(session)){
				form.setStatus(STATUS_ALL);
			} else {
				form.setStatus(session.getStatus());
			}
		}

		// 仕入先
		if (Objects.isNull(form.getSupplierId())) {
			if (Objects.isNull(session)){
				form.setSupplierId("");
			} else {
				form.setSupplierId(session.getSupplierId());
			}
		}

		// 発注日FROM
		if (Objects.isNull(form.getDateFrom())) {
			if (Objects.isNull(session)){
				form.setDateFrom("");
			} else {
				form.setDateFrom(session.getDateFrom());
			}
		}

		// 発注日TO
		if (Objects.isNull(form.getDateTo())) {
			if (Objects.isNull(session)){
				form.setDateTo("");
			} else {
				form.setDateTo(session.getDateTo());
			}
		}
		
		// 1ページ表示数
		if (Objects.isNull(form.getMaxDisplayAmount()) || form.getMaxDisplayAmount() == 0) {
			if (Objects.isNull(session)){
				form.setMaxDisplayAmount(DEF_MAX_DISP_AMOUNT);
			} else {
				form.setMaxDisplayAmount(session.getMaxDisplayAmount());
			}
		}

		// ページ番号
		if (Objects.isNull(form.getCurrentPage())|| form.getCurrentPage() == 0) {
			if (Objects.isNull(session)){
				form.setCurrentPage(DEF_CUR_PAGE);
			} else {
				form.setCurrentPage(session.getCurrentPage());
			}
		}
		
		logger.info("setCondition:絞り込み条件設定　処理終了");
		return form;
	
	}
	

	/**
	 * 発注履歴絞り込み処理
	 * 
	 * 発注履歴絞り込み処理を行う
	 * 
	 * @param purchaseOrderInfoList 発注一覧候補リスト
	 * @param orderHistoryForm 画面表示項目
	 * @return OrderHistoryForm 画面表示項目
	 * @throws Exception 
	 */
	public OrderHistoryForm filter(SmarejiUser smarejiUser,
			List<PurchaseOrdersInfo> purchaseOrderInfoList,
			OrderHistoryForm orderHistoryForm) {
		logger.info("filter:発注履歴絞り込み　処理開始");

		// 絞り込み結果格納用のリストを作成
		List<OrderHistorySubForm> resultList = new ArrayList<OrderHistorySubForm>();
		
		//検索条件を画面から取得
		String strDateFrom = orderHistoryForm.getDateFrom();
		String strDateTo = orderHistoryForm.getDateTo();
		
		// 発注日（日付型）
		Date dateFrom = null;
		Date dateTo = null;
		
		// 検索条件のセットフラグ
		Boolean dateFromFlg = false;
		Boolean dateToFlg = false;
		
		//発注日FROM
		if (Objects.nonNull(strDateFrom) && !strDateFrom.isEmpty()) {
			try {
				// 日付がyyyy/MM-/ddの形である想定で、Date型に変換
				dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(strDateFrom);
				dateFromFlg = true;
			} catch (ParseException e){
				logger.error(strDateFrom + ":発注日（FROM)：日付型への変換でエラーが発生しました");
			}
		}
		//発注日TO
		if (Objects.nonNull(strDateTo) && !strDateTo.isEmpty()) {
			try {
				// 日付がyyyy/MM/ddの形である想定で、Date型に変換
				dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(strDateTo);
				dateToFlg = true;
			} catch (ParseException e){
				logger.error(strDateTo + ":発注日（TO)：日付型への変換でエラーが発生しました");
			}
		}

		// 検索条件に合致する発注履歴を絞り込む
		for(PurchaseOrdersInfo orderInfo:purchaseOrderInfoList) {
			// 発注日FROMが設定されている場合
			if (dateFromFlg) {
				Date orderedDate = null;
				try {
					// 日付がyyyy-MM-ddの形である想定で、Date型に変換
					orderedDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(orderInfo.getOrderedDate());
				} catch (ParseException e){
					logger.error(orderInfo.getOrderedDate() + ":発注日：日付型への変換でエラーが発生しました");
				}
				// 発注日がdateFrom(検索条件)より前の日付の場合、検索条件を満たさないため次の発注情報へ
				if (orderedDate.compareTo(dateFrom)<0) {
					continue;
				};
			}

			// 発注日TOが設定されている場合
			if (dateToFlg) {
				Date orderedDate = null;
				try {
					// 日付がyyyy-MM-ddの形である想定で、Date型に変換
					orderedDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderInfo.getOrderedDate());
				} catch (ParseException e){
					logger.error(orderInfo.getOrderedDate() + ":発注日：日付型への変換でエラーが発生しました");
				}
				// 発注日がdateTo(検索条件)よりあとの日付の場合、検索条件を満たさないため次の発注情報へ
				if (orderedDate.compareTo(dateTo)>0) {
					continue;
				};
			}

			//すべての条件を満たす場合、絞り込み結果格納用のリストに格納する
			OrderHistorySubForm resultModel = new OrderHistorySubForm();
			resultModel.setOrderId(orderInfo.getStorageInfoId());
			resultModel.setStatus(orderInfo.getStatus());
			resultModel.setOrderDate(orderInfo.getOrderedDate());
			resultModel.setSupplierId(orderInfo.getRecipientOrderId());
			resultModel.setStaffId(orderInfo.getStaffId());
			resultList.add(resultModel);
		}
		
		logger.info("filter:発注履歴絞り込み　処理終了");
		orderHistoryForm.setDisplayList(resultList);
		return orderHistoryForm;
	
	}

	/**
	 * 仕入先一覧取得
	 * 
	 * 共通処理を呼び出し、仕入れ先の店舗一覧情報を取得する。
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param supplierId 仕入先ID
	 * @return Map 店舗コード・店舗名
	 */
	public Map<String, String> getSupplierInfo(SmarejiUser smarejiUser) {
		logger.info("getStoresInfo:仕入先一覧取得　処理開始");
		
		List<SuppliersInfo> supplierInfoList = new ArrayList<SuppliersInfo>();
		
		// 仕入先一覧を取得(API)
		ParamSupplierInfo paramSupplierInfo = new ParamSupplierInfo();
		
		// 仕入先一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("supplierId");
		getParam.add("supplierName");
		// 取得パラメータ
		paramSupplierInfo.setFields(getParam);
		// ソート順
		paramSupplierInfo.setSort("supplierId");
		// 取得上限数
		paramSupplierInfo.setLimit(100);
		// 取得ページ数
		paramSupplierInfo.setPage(1);

//		supplierInfoList = smarejiApiAccessMock.getStoresInfo("dummyid");
		supplierInfoList = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), paramSupplierInfo);

		Iterator<SuppliersInfo> it = supplierInfoList.iterator();
		Map<String, String> supplierInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 仕入先ID、仕入先名の設定
			SuppliersInfo st = it.next();
			supplierInfoMap.put(st.getSupplierId(), st.getSupplierName());
		}
		
		logger.info("getStoresInfo:仕入先一覧取得　処理終了");
		return supplierInfoMap;
	
	}

	/**
	 * スタッフ情報取得
	 * 
	 * 共通処理を呼び出し、スタッフ情報を取得する。
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return Map<String, String> スタッフ情報
	 */
	public Map<String, String>  getStaffInfo(SmarejiUser smarejiUser) {
		logger.info("getStaffInfo:スタッフ情報取得　処理開始");
		
		List<StaffInfo> staffInfoList = new ArrayList<StaffInfo>();
		
		// スタッフ情報を取得(API)
		ParamStaffInfo paramStaffInfo = new ParamStaffInfo();
		
		// スタッフ情報取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("staffId");
		getParam.add("staffName");
		// 取得パラメータ
		paramStaffInfo.setFields(getParam);
		// ソート順
		paramStaffInfo.setSort("staffId");
		
		//スタッフ情報一覧取得
		staffInfoList = smarejiApiAccess.getStaffsInfo(smarejiUser.getContract().getId(), paramStaffInfo);
		
		// Mapに格納
		Iterator<StaffInfo> it = staffInfoList.iterator();
		Map<String, String> staffInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// スタッフID、スタッフ名の設定
			StaffInfo st = it.next();
			staffInfoMap.put(st.getStaffId(), st.getStaffName());
		}
		
		logger.info("getStaffInfo:スタッフ情報取得　処理終了");
		return staffInfoMap;
	
	}
	
	/**
	 * 発注対象商品取得
	 * 
	 * 共通処理を呼び出し、発注対象商品を取得する。
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderId 発注ID
	 * @return Map 店舗コード・店舗名
	 */
	public void getPurchaseOrderProduct(SmarejiUser smarejiUser, OrderHistoryForm form) {
		logger.info("getPurchaseOrderProduct:発注対象商品取得　処理開始");
		
		List <OrderHistorySubForm> orderList = form.getDisplayList();
		
		for (int i=0; i<orderList.size(); i++) {
			List<PurchaseOrdersProductsInfo> orderProductList = new ArrayList<PurchaseOrdersProductsInfo>();
			
			// 発注対象商品を取得(API)
			ParamPurchaseOrderProduct paramPurchaseOrderProduct = new ParamPurchaseOrderProduct();
			
			// 発注対象商品取得のためのパラメータを設定
			List<String> getParam = new ArrayList<String>();
			getParam.add("storageInfoProductId");
			getParam.add("quantity");
			getParam.add("cost");
			// 取得パラメータ
			paramPurchaseOrderProduct.setFields(getParam);
			// ソート順
//			paramPurchaseOrderProduct.setSort("staffId");
			
			// 発注対象商品取得
			orderProductList = smarejiApiAccess.getPurchaseOrdersProductsInfo(smarejiUser.getContract().getId(), orderList.get(i).getOrderId(),paramPurchaseOrderProduct);
			
			// 発注点数(合計）計算
			BigDecimal totalQuantity = calcQuantity(orderProductList);
			
			//合計金額（外税）計算
			BigDecimal totalCost = calcCost(orderProductList);
			
			// 計算結果を設定
			orderList.get(i).setQuantity(totalQuantity.toString());
			orderList.get(i).setTotalPrice(totalCost.toString());
		}
		form.setDisplayList(orderList);
		
		logger.info("getPurchaseOrderProduct:発注対象商品取得　処理終了");
		return ;
	
	}
	
	/**
	 * 仕入先名設定
	 * 
	 * 仕入先名を設定する。
	 * 
	 * @param OrderHistoryForm 画面情報
	 * @param suppliersMap 仕入先Map
	 * @return OrderHistoryForm 画面情報
	 */
	public OrderHistoryForm setSupplierName(OrderHistoryForm form, Map<String, String> suppliersMap) {
		logger.info("setSupplierInfo:仕入先名設定　処理開始");
		
		List <OrderHistorySubForm> orderList =form.getDisplayList();
		
		for (int i=0; i<orderList.size(); i++) {
			// 仕入先名取得
			String suppliersName = suppliersMap.get(orderList.get(i).getSupplierId());
			// 仕入先名設定
			orderList.get(i).setSupplierName(suppliersName);
		}
		
		form.setDisplayList(orderList);
		logger.info("setSupplierInfo:仕入先名設定　処理終了");
		
		return form;
	
	}
	/**
	 * スタッフ名設定
	 * 
	 * スタッフ名を設定する。
	 * 
	 * @param orderList 発注情報
	 * @param staffMap スタッフMap
	 * @return List <OrderHistorySubForm> 表示用リスト
	 */
	public OrderHistoryForm setStaffName(OrderHistoryForm form, Map<String, String> staffMap) {
		logger.info("setStaffName:スタッフ名設定　処理開始");
		
		List <OrderHistorySubForm> orderList =form.getDisplayList();
		
		for (int i=0; i<orderList.size(); i++) {
			// スタッフ名取得
			String staffName = staffMap.get(orderList.get(i).getStaffId());
			// スタッフ名設定
			orderList.get(i).setStaffName(staffName);
		}
		
		form.setDisplayList(orderList);
		logger.info("setStaffName:スタッフ名設定　処理終了");
		
		return form;
	
	}
	
	/**
	 * 発注点数計算
	 * 
	 * 発注点数を計算する。
	 * 
	 * @param orderProductList 発注対象商品一覧
	 * @return int 発注点数合計
	 */
	private BigDecimal calcQuantity (List<PurchaseOrdersProductsInfo> orderProductList) {
		logger.info("calcQuantity:発注点数計算　処理開始");
		
		BigDecimal totalQuantity = new BigDecimal("0");
		
		for (PurchaseOrdersProductsInfo orderPuroduct : orderProductList) {
			BigDecimal quantity = new BigDecimal(orderPuroduct.getQuantity());
			// 加算
			totalQuantity = totalQuantity.add(quantity);
		}
		
		logger.info("calcQuantity:発注点数計算　処理終了");
		
		return totalQuantity;
	
	}

	/**
	 * 発注合計金額計算
	 * 
	 * 発注合計金額を計算する。
	 * 
	 * @param orderProductList 発注対象商品一覧
	 * @return int 発注合計金額
	 */
	private BigDecimal calcCost (List<PurchaseOrdersProductsInfo> orderProductList) {
		logger.info("calcCost:発注合計金額計算　処理開始");
		
		BigDecimal total = new BigDecimal("0");
		
		for (PurchaseOrdersProductsInfo orderPuroduct : orderProductList) {
			BigDecimal cost = new BigDecimal(orderPuroduct.getCost());
			BigDecimal quantity = new BigDecimal(orderPuroduct.getQuantity());
			// 小計計算（原価*点数）
			BigDecimal subTotal = cost.multiply(quantity);
			// 合計金額に加算
			total = total.add(subTotal);
		}
		
		// 端数処理（小数点第二位で四捨五入）
		BigDecimal result = total.setScale(1, RoundingMode.HALF_UP);
		
		logger.info("calcCost:発注合計金額計算　処理終了");
		
		return result;
	
	}
}
