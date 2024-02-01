package com.orderManage.model.api;

import java.util.List;

/**
 * 発注設定
 * 
 */
public class OrderSetting {

	/* 商品ID */
	private String productId;
	/* 継続区分 */
	private String continuationDivision;
	/* 発注状態 (0:発注不可、1:発注可) */
	private String orderStatusDivision;
	/* 発注不可理由 */
	private String orderNoReasonDivision;
	/* 発注制限数 */
	private String orderLimitAmount;
	/* 発注時仕入先編集状態 */
	private String orderSupplierEditable;
	/* PB区分 */
	private String pbDivision;
	/* 発注表示 */
	private String displayFlag;
	/* 発注単位 */
	private OrderUnit orderUnit;
	/* 店舗別発注設定 */
	private List<OrderSettingStores> stores;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getContinuationDivision() {
		return continuationDivision;
	}
	public void setContinuationDivision(String continuationDivision) {
		this.continuationDivision = continuationDivision;
	}
	public String getOrderStatusDivision() {
		return orderStatusDivision;
	}
	public void setOrderStatusDivision(String orderStatusDivision) {
		this.orderStatusDivision = orderStatusDivision;
	}
	public String getOrderNoReasonDivision() {
		return orderNoReasonDivision;
	}
	public void setOrderNoReasonDivision(String orderNoReasonDivision) {
		this.orderNoReasonDivision = orderNoReasonDivision;
	}
	public String getOrderLimitAmount() {
		return orderLimitAmount;
	}
	public void setOrderLimitAmount(String orderLimitAmount) {
		this.orderLimitAmount = orderLimitAmount;
	}
	public String getOrderSupplierEditable() {
		return orderSupplierEditable;
	}
	public void setOrderSupplierEditable(String orderSupplierEditable) {
		this.orderSupplierEditable = orderSupplierEditable;
	}
	public String getPbDivision() {
		return pbDivision;
	}
	public void setPbDivision(String pbDivision) {
		this.pbDivision = pbDivision;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public OrderUnit getOrderUnit() {
		return orderUnit;
	}
	public void setOrderUnit(OrderUnit orderUnit) {
		this.orderUnit = orderUnit;
	}
	public List<OrderSettingStores> getStores() {
		return stores;
	}
	public void setStores(List<OrderSettingStores> stores) {
		this.stores = stores;
	}
}
