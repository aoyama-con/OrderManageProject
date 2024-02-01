package com.orderManage.model.param;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 発注登録APIにリクエストする際に設定するパラメータ情報クラス（発注配送商品）
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamEntryPurchaseOrderDeliveryStore {
	
	/* 配送店舗ID */
	private String storeId;
	
	/* 発注数量 */
	private String quantity;

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
