package com.orderManage.model.api;

/**
 * 仕入先情報 model
 * 
 * スマレジAPI　仕入先一覧情報取得、仕入先登録、仕入先更新時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class SuppliersInfo {

	/* 仕入先ID */
	private String supplierId;
	/* 仕入先コード */
	private String supplierCode;
	/* 仕入先区分ID */
	private String supplierDivisionId;
	/* 仕入先名 */
	private String supplierName;
	/* 仕入先名略称 */
	private String supplierAbbr;
	/* 住所 */
	private String address;
	/* 電話番号 */
	private String phoneNumber;
	/* FAX */
	private String faxNumber;
	/* メールアドレス */
	private String mailAddress;
	/* 担当者名 */
	private String staffName;
	/* 発注先優先度 */
	private String orderPriority;
	/* 作成日時 */
	private String insDateTime;
	/* 更新日時 */
	private String updDateTime;
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierDivisionId() {
		return supplierDivisionId;
	}
	public void setSupplierDivisionId(String supplierDivisionId) {
		this.supplierDivisionId = supplierDivisionId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierAbbr() {
		return supplierAbbr;
	}
	public void setSupplierAbbr(String supplierAbbr) {
		this.supplierAbbr = supplierAbbr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getOrderPriority() {
		return orderPriority;
	}
	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
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

}
