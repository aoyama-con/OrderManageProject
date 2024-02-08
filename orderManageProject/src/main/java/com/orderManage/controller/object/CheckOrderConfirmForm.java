package com.orderManage.controller.object;

/**
 * 発注確認画面Form
 * 
 * 発注確認画面表示時に使用するForm
 * 
 * @author makabe
 *
 */
public class CheckOrderConfirmForm {


	/* 発注日 */
    private String orderDate;
	/* 仕入先 */
    private String supplier;
    /* 発注者 */
    private String orderStaff;
    /* 発注点数 */
    private String orderCount;
    /* 発注金額合計 */
    private String orderAmountSum;
    
    
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getOrderStaff() {
		return orderStaff;
	}
	public void setOrderStaff(String orderStaff) {
		this.orderStaff = orderStaff;
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
