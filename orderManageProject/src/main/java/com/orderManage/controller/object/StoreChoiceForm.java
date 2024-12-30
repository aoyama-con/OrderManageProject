package com.orderManage.controller.object;

import java.io.Serializable;
import java.util.Map;

import com.orderManage.util.StringUtil;

import jakarta.validation.constraints.AssertTrue;

public class StoreChoiceForm implements Serializable {

	/** 店舗ID */
 	private String storeId;

	/** 店舗一覧 */
    Map<String, String> storeInfos;

	/**
     * 店舗が選択されているかチェックする。
     * 
     * @return チェック結果
     */
    @AssertTrue(message = "店舗が選択されていません。店舗を選択してください。")
    public boolean isValidStoreselect() {
    	boolean result = true;
    	// 未指定（デフォルト値）の場合
    	if (StringUtil.isEmpty(this.storeId)) {
    		result = false;
    	}
    	return result;
    }

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Map<String, String> getStoreInfos() {
		return storeInfos;
	}

	public void setStoreInfos(Map<String, String> storeInfos) {
		this.storeInfos = storeInfos;
	}
}
