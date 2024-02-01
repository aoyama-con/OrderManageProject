package com.orderManage.model.param;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 発注更新APIにリクエストする際に設定するパラメータ情報クラス（発注配送商品）
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamUpdatePurchasOrderDeliveryStore {

	/* 発注配送商品ID 更新の場合は指定、新規追加の場合は設定しないこと */
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
