package com.orderManage.controller.object;

/**
 * 発注確認画面SubForm
 * 
 * @author makabe
 *
 */
public class CheckOrderConfirmSubForm {

	/* 発注日 */
    private String orderedDate;
	/* 仕入先名 */
    private String supplierName;
    /* 発注者名 */
    private String orderStaffName;
    /* 発注点数 */
    private String orderCount;
    /* 発注金額合計 */
    private String orderAmountSum;
    
	public String getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOrderStaffName() {
		return orderStaffName;
	}
	public void setOrderStaffName(String orderStaffName) {
		this.orderStaffName = orderStaffName;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getOrderAmountSum() {
		return orderAmountSum;
	}
	public void setOrderAmountSum(String orderAmountSum) {
		this.orderAmountSum = orderAmountSum;
	}
    

}
