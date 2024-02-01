package com.orderManage.model.session;

import com.orderManage.model.service.Pagination;

/**
 * 発注確認画面　セッション情報
 */
public class OrderConfirmSessionInfo {
	
	/* ページ情報 */
	private Pagination pagenation;

	public Pagination getPagenation() {
		return pagenation;
	}

	public void setPagenation(Pagination pagenation) {
		this.pagenation = pagenation;
	}
}
