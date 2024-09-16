package com.orderManage.controller.object;

import java.util.List;

/**
 * 発注確認画面Form
 *  * 
 * @author makabe
 *
 */
public class CheckOrderConfirmForm {

	/* 発注情報リスト */
    private List<CheckOrderConfirmSubForm> displayList;

    /* 引渡し項目 */
    /* 発注ID */
    private String orderId ;
    
	public List<CheckOrderConfirmSubForm> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<CheckOrderConfirmSubForm> displayList) {
		this.displayList = displayList;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
