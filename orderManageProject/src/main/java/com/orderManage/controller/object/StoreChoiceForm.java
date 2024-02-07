package com.orderManage.controller.object;

import java.io.Serializable;
import java.util.Map;

import com.orderManage.util.StringUtil;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;

public class StoreChoiceForm implements Serializable {

	/** 店舗選択デフォルト値 */
	private static String DEFAULT_STORESELECT = "0";
	
	/** 店舗 */
 	private String storeselect;

	/** 発注日 */
    @NotEmpty(message = "発注日付が正しくありません。")
	private String orderDate;

    /** システム日付 */
    private String sysDate;
    
    /** 店舗一覧 */
    Map<String, String> storeInfos;
    
	/**
     * 
     * @return
     */
    @AssertTrue(message = "店舗が選択されていません。店舗を選択してください。")
    public boolean isValidStoreselect() {
    	boolean result = true;
    	// 未指定（デフォルト値）の場合
    	if (DEFAULT_STORESELECT.equals(this.storeselect)) {
    		result = false;
    	}
    	return result;
    }

    /**
     * 
     * @return
     */
    @AssertTrue(message = "発注日付が正しくありません。")
    public boolean isValidOrderDate() {
    	boolean result = true;
    	
    	if (!StringUtil.isEmpty(orderDate) && !StringUtil.isEmpty(sysDate)) {
        	// 過去日の場合
    		if (sysDate.compareTo(orderDate) > 0) {
    			result = false;
        	}
    	}
    	return result;
    }

	public String getStoreselect() {
		return storeselect;
	}

	public void setStoreselect(String storeselect) {
		this.storeselect = storeselect;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public Map<String, String> getStoreInfos() {
		return storeInfos;
	}

	public void setStoreInfos(Map<String, String> storeInfos) {
		this.storeInfos = storeInfos;
	}
}
