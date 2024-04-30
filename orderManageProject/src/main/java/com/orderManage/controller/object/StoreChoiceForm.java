package com.orderManage.controller.object;

import java.io.Serializable;
import java.util.Map;

import jakarta.validation.constraints.AssertTrue;

public class StoreChoiceForm implements Serializable {

	/** 店舗選択デフォルト値 */
	private static String DEFAULT_STORESELECT = "0";
	
	/** 店舗ID */
 	private String storeId;

	/** 発注日 */
    // @NotEmpty(message = "発注日が入力されていません。発注日を入力してください。") 20240430 発注日が削除されたためコメントアウト
	// private String orderDate;

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
    	if (DEFAULT_STORESELECT.equals(this.storeId)) {
    		result = false;
    		// TODO 20240430 バリデートエラー後に、セレクトボックスに値再設定が必要
    	}
    	return result;
    }

    /**
     * 
     * @return
     */
//20240430 発注日が削除されたためコメントアウト
//    @AssertTrue(message = "発注日付が正しくありません。")
//    public boolean isValidOrderDate() {
//    	boolean result = true;
//
//    	if (!StringUtil.isEmpty(orderDate) && !StringUtil.isEmpty(sysDate)) {
//        	// 過去日の場合
//    		if (sysDate.compareTo(orderDate) > 0) {
//    			result = false;
//        	}
//    	}
//	    	
//    	return result;
//    }

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

//	public String getOrderDate() {
//		return orderDate;
//	}

//	public void setOrderDate(String orderDate) {
//		this.orderDate = orderDate;
//	}

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
