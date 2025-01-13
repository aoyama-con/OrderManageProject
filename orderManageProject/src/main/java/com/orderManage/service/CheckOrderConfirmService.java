package com.orderManage.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orderManage.controller.object.CheckOrderConfirmForm;
import com.orderManage.controller.object.CheckOrderConfirmSubForm;
import com.orderManage.model.api.OrderProducts;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.param.ParamSupplierInfo;
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
		
		// 仕入先一覧取得パラメータを設定
		ParamSupplierInfo param = new ParamSupplierInfo();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("supplierId");
		getParam.add("supplierName");
		param.setFields(getParam);
		
		// 仕入先一覧を取得(API)
		supplierInfoList = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), param);
		
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
	 * 発注確認画面初期表示情報を取得
	 * 
	 * 発注確認画面に初期表示する情報を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @param orderControlNumber 発注管理番号
	 * @param staffName 発注者名（ログインユーザ名）
	 * @return CheckOrderConfirmForm 発注確認画面Formリスト
	 */
	public CheckOrderConfirmForm getDisplayInfo(SmarejiUser smarejiUser, String orderControlNumber, String staffName) {
		
		logger.info("getDisplayInfoList:発注確認画面初期表示情報取得　処理開始");
		
		CheckOrderConfirmForm checkOrderConfirmForm = new CheckOrderConfirmForm();
		List<CheckOrderConfirmSubForm> displayList = new ArrayList<CheckOrderConfirmSubForm>();
				
		// 仕入先一覧取得API
		Map<String, String> supplierMap = getSupplierInfo(smarejiUser);
		
		// 発注一覧取得API（取得条件：契約ID、ステータス"5：仮発注"、発注管理番号）
		List<PurchaseOrdersInfo> purchaseOrdersInfoList = getPurchaseOrdersInfoList(smarejiUser, orderControlNumber);
			
		// 発注一覧の取得件数分、以下の処理を実施
		// TODO ラムダ式とstreamAPIにする
		/*		purchaseOrdersInfoList.forEach(order -> {
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
					
					// 名称取得（仕入先）
					String supplierName = supplierMap.get(purchaseOrdersInfo.getRecipientOrderId());
					
					// 画面表示用Formに情報を設定
					DecimalFormat decimalFormat = new DecimalFormat("#,###.#");		
					CheckOrderConfirmSubForm subForm = new CheckOrderConfirmSubForm();
					subForm.setOrderId(purchaseOrdersInfo.getStorageInfoId());		// 発注ID
					subForm.setOrderedDate(purchaseOrdersInfo.getOrderedDate());	// 発注日		
					subForm.setSupplierName(supplierName);							// 仕入先名		
					subForm.setOrderStaffName(staffName);							// 発注者名（ログインユーザ名）
					subForm.setOrderCount(decimalFormat.format(countSum));			// 発注点数
					subForm.setOrderAmountSum(decimalFormat.format(amountSum.setScale(1, RoundingMode.HALF_UP))); // 発注金額合計（小数点以下第二位を四捨五入）
					displayList.add(subForm);
				});*/
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
			
			// 名称取得（仕入先）
			String supplierName = supplierMap.get(purchaseOrdersInfo.getRecipientOrderId());
			// String staffName = staffMap.get(purchaseOrdersInfo.getStaffId());
			
			// 画面表示用Formに情報を設定
			DecimalFormat decimalFormat = new DecimalFormat("#,###.#");		
			CheckOrderConfirmSubForm subForm = new CheckOrderConfirmSubForm();
			subForm.setOrderId(purchaseOrdersInfo.getStorageInfoId());		// 発注ID
			subForm.setOrderedDate(purchaseOrdersInfo.getOrderedDate());	// 発注日		
			subForm.setSupplierName(supplierName);							// 仕入先名		
			subForm.setOrderStaffName(staffName);							// 発注者名（ログインユーザ名）
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
	 * @param form 発注確認画面Form
	 * @return
	 */
	public Page<CheckOrderConfirmSubForm> paging(Pageable pageable, CheckOrderConfirmForm form) {

		List<CheckOrderConfirmSubForm> dispList = form.getDisplayList();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<CheckOrderConfirmSubForm> list;
	
		if(dispList.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize , dispList.size());
			list = dispList.subList(startItem, toIndex);
		}
		
		Page<CheckOrderConfirmSubForm> checkOrderConfirmPage = new PageImpl<CheckOrderConfirmSubForm>(list, PageRequest.of(currentPage, pageSize),dispList.size());
		return checkOrderConfirmPage;
		
	}
}
