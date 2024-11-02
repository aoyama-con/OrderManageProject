package com.orderManage.controller.object;

import java.util.List;

/**
 * 発注状況画面Form
 */
public class CheckOrderStatusForm {

	/* 発注情報リスト */
    private List<CheckOrderStatusSubForm> displayList;

    /* 引渡し項目 */
    /* 発注ID */
    private String orderId ;
    
	public List<CheckOrderStatusSubForm> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<CheckOrderStatusSubForm> displayList) {
		this.displayList = displayList;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
