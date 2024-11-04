package com.orderManage.controller.object;

import java.io.Serializable;
import java.util.List;

import com.orderManage.util.StringUtil;

import jakarta.validation.constraints.AssertTrue;

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
    
    /** 発注ID */
    private String orderId;

	/** 発注点数 */
    private String[] orderingPoint_;

    /** 処理種別 */
    private String type;

    /**
     * 
     * @return
     */
    @AssertTrue(message = "発注点数を入力する場合は１以上、かつ、99999以下の整数で入力してください。")
    public boolean isValidOrderAmount_numeric() {
    	boolean result = true;

    	// 発注登録を行う場合のみチェックする
    	if ("0".equals(type)) {
	    	if (orderingPoint_ != null && orderingPoint_.length > 0) {
	    		for (int i = 0; i < orderingPoint_.length; i++) {
	    			if (StringUtil.isNotEmpty(orderingPoint_[i])) {
		    			try {
		    				int amount = Integer.parseInt(orderingPoint_[i]);
		    				if (amount < 1 || amount > 99999) {
		    					result = false;
		    					break;
		    				}
		    			} catch (Exception ex) {
		    				result = false;
		    				break;
		    			}
	    			}
	    		}
	    	}
    	}
    	
    	return result;
    }
   
	public List<OrderConfirmSubForm> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<OrderConfirmSubForm> displayList) {
		this.displayList = displayList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getOrderingPoint_() {
		return orderingPoint_;
	}
	public void setOrderingPoint_(String[] orderingPoint_) {
		this.orderingPoint_ = orderingPoint_;
	}

}
