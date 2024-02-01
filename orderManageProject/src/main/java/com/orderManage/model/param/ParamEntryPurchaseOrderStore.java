package com.orderManage.model.param;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 発注登録APIにリクエストする際に設定するパラメータ情報クラス（店舗）
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamEntryPurchaseOrderStore {

	/* 配送店舗ID */
	private String storageStoreId;
	
	/* 入荷予定日From */
	private String storageExpectedDateFrom;
	
	/* 入荷予定日To */
	private String storageExpectedDateTo;

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
