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
	private String supplierId;
	/* 仕入先名 */
	private String supplierName;
	/* 発注者ID*/
	private String staffId;
	/* 発注者名*/
	private String staffName;
	/* 発注点数 */
    private String quantity;
	/* 発注金額合計(外税） */
    private String totalPrice;


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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
}
