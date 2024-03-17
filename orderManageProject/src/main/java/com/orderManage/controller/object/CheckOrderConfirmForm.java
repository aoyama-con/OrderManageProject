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

	public List<CheckOrderConfirmSubForm> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<CheckOrderConfirmSubForm> displayList) {
		this.displayList = displayList;
	}

}
