package com.orderManage.model.api;

/**
 * 発注配送商品
 * 
 */
public class DeliveryStore {

	/* 発注配送商品ID 自働採番 */
	private String storageInfoDeliveryProductId;
	/* 配送店舗ID */
	private String storeId;
	/* 発注数量 */
	private String quantity;
	
	public String getStorageInfoDeliveryProductId() {
		return storageInfoDeliveryProductId;
	}
	public void setStorageInfoDeliveryProductId(String storageInfoDeliveryProductId) {
		this.storageInfoDeliveryProductId = storageInfoDeliveryProductId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
