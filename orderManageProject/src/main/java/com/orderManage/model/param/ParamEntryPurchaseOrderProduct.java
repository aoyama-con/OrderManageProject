package com.orderManage.model.param;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 発注登録APIにリクエストする際に設定するパラメータ情報クラス（商品）
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamEntryPurchaseOrderProduct {
	
	/* 商品ID */
	private String productId;
	
	/* 税率 小数点3桁まで */
	private float taxRate;
	
	/* 原価(外税) 未設定の場合、商品マスタの原価を自動で設定する。 */
	private String cost;
	
	/* 発注配送商品 */
	private List<ParamEntryPurchaseOrderDeliveryStore> deliveryStore;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public List<ParamEntryPurchaseOrderDeliveryStore> getDeliveryStore() {
		return deliveryStore;
	}

	public void setDeliveryStore(List<ParamEntryPurchaseOrderDeliveryStore> deliveryStore) {
		this.deliveryStore = deliveryStore;
	}
}
