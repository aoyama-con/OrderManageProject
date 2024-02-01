package com.orderManage.model.api;

import java.util.List;

/**
 * 発注情報 model
 * 
 * スマレジAPI　発注一覧、発注取得、発注登録、発注更新時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class PurchaseOrdersInfo {
	
	/* 発注ID */
	private String storageInfoId;
	/* 発注先ID(仕入先ID) */
	private String recipientOrderId;
	/* 分割単位：0:なし、1:部門グループ、2:仕入先 */
	private String divisionUnit;
	/* 部門グループID：nullの時は全部門グループ */
	private String categoryGroupId;
	/* 発注日：YYYY-MM-DD形式 */
	private String orderedDate;
	/* メモ */
	private String memo;
	/* 識別番号：発注情報を管理するための任意の文字列を設定出来る項目。 */
	private String identificationNo;
	/* 税丸め（0:四捨五入、1:切り捨て、2:切り上げ） */
	private String roundingDivision;
	/* ステータス(2:発注済、3:入荷検品中、4:入荷完了、5:仮発注) */
	private String status;
	/* スタッフID：発注処理時のスタッフID */
	private String staffId;
	/* 作成日時 */
	private String insDateTime;
	/* 更新日時 */
	private String updDateTime;
	/* 発注対象商品 */
	private List<OrderProducts> products;
	/* 発注対象店舗 */
	private List<OrderStores> stores;
	
	public String getStorageInfoId() {
		return storageInfoId;
	}
	public void setStorageInfoId(String storageInfoId) {
		this.storageInfoId = storageInfoId;
	}
	public String getRecipientOrderId() {
		return recipientOrderId;
	}
	public void setRecipientOrderId(String recipientOrderId) {
		this.recipientOrderId = recipientOrderId;
	}
	public String getDivisionUnit() {
		return divisionUnit;
	}
	public void setDivisionUnit(String divisionUnit) {
		this.divisionUnit = divisionUnit;
	}
	public String getCategoryGroupId() {
		return categoryGroupId;
	}
	public void setCategoryGroupId(String categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getInsDateTime() {
		return insDateTime;
	}
	public void setInsDateTime(String insDateTime) {
		this.insDateTime = insDateTime;
	}
	public String getUpdDateTime() {
		return updDateTime;
	}
	public void setUpdDateTime(String updDateTime) {
		this.updDateTime = updDateTime;
	}
	public List<OrderProducts> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProducts> products) {
		this.products = products;
	}
	public List<OrderStores> getStores() {
		return stores;
	}
	public void setStores(List<OrderStores> stores) {
		this.stores = stores;
	}
}
