package com.orderManage.model.param;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 発注更新APIにリクエストする際に設定するパラメータ情報クラス（店舗）
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamUpdatePurchaseOrderStore {

	/* 発注配送店舗ID 更新の場合は指定、新規追加の場合は設定しないこと */
	private String storageInfoDeliveryId;

	/* 配送店舗ID */
	private String storageStoreId;
	
	/* 入荷予定日From */
	private String storageExpectedDateFrom;
	
	/* 入荷予定日To */
	private String storageExpectedDateTo;

	public String getStorageInfoDeliveryId() {
		return storageInfoDeliveryId;
	}

	public void setStorageInfoDeliveryId(String storageInfoDeliveryId) {
		this.storageInfoDeliveryId = storageInfoDeliveryId;
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
}
