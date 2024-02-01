package com.orderManage.model.param;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 発注登録APIにリクエストする際に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamEntryPurchaseOrder {

	/* 発注先ID */
	private String recipientOrderId;
	
	/* 発注日 */
	private String orderedDate;
	
	/* メモ */
	private String memo;
	
	/* 識別番号 */
	private String identificationNo;
	
	/* 税丸め（0:四捨五入、1:切り捨て、2:切り上げ） */
	private String roundingDivision;
	
	/* 発注処理時のスタッフID */
	private String staffId;

	/* ステータス 発注取得、登録、更新　共通*/
	private String status;

	/* 発注対象商品 */
	private List<ParamEntryPurchaseOrderProduct> products;

	/* 発注対象店舗 */
	private List<ParamEntryPurchaseOrderStore> stores;

	public String getRecipientOrderId() {
		return recipientOrderId;
	}

	public void setRecipientOrderId(String recipientOrderId) {
		this.recipientOrderId = recipientOrderId;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getRoundingDivision() {
		return roundingDivision;
	}

	public void setRoundingDivision(String roundingDivision) {
		this.roundingDivision = roundingDivision;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ParamEntryPurchaseOrderProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ParamEntryPurchaseOrderProduct> products) {
		this.products = products;
	}

	public List<ParamEntryPurchaseOrderStore> getStores() {
		return stores;
	}

	public void setStores(List<ParamEntryPurchaseOrderStore> stores) {
		this.stores = stores;
	}
}
