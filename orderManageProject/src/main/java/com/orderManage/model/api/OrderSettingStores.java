package com.orderManage.model.api;

/**
 * 店舗別発注設定
 * 
 */
public class OrderSettingStores {

	/* 店舗ID */
	private String storeId;
	/* 発注制限数(null:発注制限なし、-1:全店舗設定、0~99999999:発注制限) */
	private String orderLimitAmount;
	/* 発注表示 (-1:全店舗設定、0:表示しない、1:表示する) */
	private String displayFlag;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderLimitAmount() {
		return orderLimitAmount;
	}
	public void setOrderLimitAmount(String orderLimitAmount) {
		this.orderLimitAmount = orderLimitAmount;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
}
