package com.orderManage.controller.object;

/**
 * 発注履歴画面　一覧項目設定用SubForm
 */
public class OrderHistorySubForm {

	/* 発注ID */
	private String orderId;
	/* ステータス */
	private String status;
	/* 発注日（YYYY/MM/DD） */
	private String orderDate;
	/* 仕入先コード */
	private String supplierCode;
	/* 仕入先名 */
	private String supplierName;
	/* 発注者名*/
	private String staffName;
	/* 発注点数 */
    private int quantity;
	/* 発注金額合計 */
    private int totalPrice;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
