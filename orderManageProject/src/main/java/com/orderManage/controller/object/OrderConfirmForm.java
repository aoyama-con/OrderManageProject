package com.orderManage.controller.object;

import java.io.Serializable;
import java.util.List;

public class OrderConfirmForm implements Serializable {

//	/** 発注点数0値 */
//	private static final String ZERO = "0";
//	
//	/** 数値パターン */
//	private static final String INTEGER_PATTERN = "^([1-9]\\d*|0)(\\.\\d+)?$";
//	
//	/** 小数点パターン */
//	private static final String DECIMAL_POINT_PATTERN = "^([1-9]\\d*|0)(\\.\\d+)?$";
//	
//	/** 発注点数 */
//	private String orderingPoint;

    /** 発注確定リスト */
    private List<OrderConfirmSubForm> displayList;
//    /**
//     * 
//     * @return
//     */
//    @AssertTrue(message = "発注点数を入力する場合は0以上の整数で入力してください。")
//    public boolean isValidOrderingPoint() {
//    	boolean result = true;
//    	boolean isInteger = true;
//    	boolean isDecimal = true;
//
//    	// 整数パターン
//    	Pattern intPattern = Pattern.compile(INTEGER_PATTERN);
//    	// 小数点パターン
//    	Pattern deciPattern = Pattern.compile(DECIMAL_POINT_PATTERN);
//    	
//    	// ブランク以外または0以外の場合チェックを行う
//    	if (!orderingPoint.isEmpty() || !orderingPoint.equals(ZERO)) {
//        	// カンマをリプレイスする
//        	orderingPoint.replace(",", "");
//    		// 整数が使用されているかチェック
//        	isInteger = intPattern.matcher(orderingPoint).matches();
//    		// 小数点が使用されているかチェック
//    		isDecimal = deciPattern.matcher(orderingPoint).matches();
//    		// 整数以外または小数点が使用されている場合エラーとする
//        	if (!isInteger || isDecimal) {
//        		result = false;
//        	}
//    	}
//    	return result;
//    }

	public List<OrderConfirmSubForm> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<OrderConfirmSubForm> displayList) {
		this.displayList = displayList;
	}

}
