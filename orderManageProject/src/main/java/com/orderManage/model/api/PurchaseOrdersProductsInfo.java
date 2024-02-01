package com.orderManage.model.api;

import java.util.List;

/**
 * 発注対象商品 model
 * 
 * スマレジAPI　発注対象商品取得時に格納するmodel
 * 
 */
public class PurchaseOrdersProductsInfo {

	/* 発注商品ID */
	private String storageInfoProductId;
	/* 発注ID */
	private String storageInfoId;
	/* 商品ID */
	private String productId;
	/* 税率 */
	private String taxRate;
	/* 原価(外税) */
	private String cost;
	/* 税区分：(0：税込、1：税抜、2：非課税) */
	private String taxDivision;
	/* 発注数量 */
	private String quantity;
	/* 作成日時：登録時の日時 */
	private String insDateTime;
	/* 更新日時：登録時・更新時の日時 */
	private String updDateTime;
	/* 発注配送商品 */
	private List<DeliveryStore> deliveryStore;
	
	public String getStorageInfoProductId() {
		return storageInfoProductId;
	}
	public void setStorageInfoProductId(String storageInfoProductId) {
		this.storageInfoProductId = storageInfoProductId;
	}
	public String getStorageInfoId() {
		return storageInfoId;
	}
	public void setStorageInfoId(String storageInfoId) {
		this.storageInfoId = storageInfoId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getTaxDivision() {
		return taxDivision;
	}
	public void setTaxDivision(String taxDivision) {
		this.taxDivision = taxDivision;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	public List<DeliveryStore> getDeliveryStore() {
		return deliveryStore;
	}
	public void setDeliveryStore(List<DeliveryStore> deliveryStore) {
		this.deliveryStore = deliveryStore;
	}
}
