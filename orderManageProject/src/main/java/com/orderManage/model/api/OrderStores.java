package com.orderManage.model.api;

/**
 * 発注対象店舗
 * 
 */
public class OrderStores {

	/* 発注配送店舗ID 自動採番 */
	private String storageInfoDeliveryId;
	/* 発注ID 自動採番 */
	private String storageInfoId;
	/* 配送店舗ID */
	private String storageStoreId;
	/* 入荷予定日From 未設定の場合、登録日を設定します。 */
	private String storageExpectedDateFrom;
	/* 入荷予定日To 未設定の場合、登録日を設定します。 */
	private String storageExpectedDateTo;
	/* 作成日時：登録時の日時 */
	private String insDateTime;
	/* 更新日時：登録時・更新時の日時 */
	private String updDateTime;
	
	public String getStorageInfoDeliveryId() {
		return storageInfoDeliveryId;
	}
	public void setStorageInfoDeliveryId(String storageInfoDeliveryId) {
		this.storageInfoDeliveryId = storageInfoDeliveryId;
	}
	public String getStorageInfoId() {
		return storageInfoId;
	}
	public void setStorageInfoId(String storageInfoId) {
		this.storageInfoId = storageInfoId;
	}
	public String getStorageStoreId() {
		return storageStoreId;
	}
	public void setStorageStoreId(String storageStoreId) {
		this.storageStoreId = storageStoreId;
	}
	public String getStorageExpectedDateFrom() {
		return storageExpectedDateFrom;
	}
	public void setStorageExpectedDateFrom(String storageExpectedDateFrom) {
		this.storageExpectedDateFrom = storageExpectedDateFrom;
	}
	public String getStorageExpectedDateTo() {
		return storageExpectedDateTo;
	}
	public void setStorageExpectedDateTo(String storageExpectedDateTo) {
		this.storageExpectedDateTo = storageExpectedDateTo;
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
}
