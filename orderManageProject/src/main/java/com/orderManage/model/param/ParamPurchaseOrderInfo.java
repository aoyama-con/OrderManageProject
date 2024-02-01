package com.orderManage.model.param;

import java.util.List;

/**
 * 発注情報取得APIにリクエストする際に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamPurchaseOrderInfo {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;

	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;

	/* 発注先ID */
	private String recipient_order_id;
	
	/* 発注日 */
	private String ordered_date;
	
	/* 発注対象商品、発注配送商品を付加するか(付加する場合はall, しない場合はnone) */
	private String with_products;
	
	/* 発注対象店舗を付加するか(付加する場合はall, しない場合はnone) */
	private String with_stores;

	/* ステータス */
	private String status;

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getRecipient_order_id() {
		return recipient_order_id;
	}

	public void setRecipient_order_id(String recipient_order_id) {
		this.recipient_order_id = recipient_order_id;
	}

	public String getOrdered_date() {
		return ordered_date;
	}

	public void setOrdered_date(String ordered_date) {
		this.ordered_date = ordered_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWith_products() {
		return with_products;
	}

	public void setWith_products(String with_products) {
		this.with_products = with_products;
	}

	public String getWith_stores() {
		return with_stores;
	}

	public void setWith_stores(String with_stores) {
		this.with_stores = with_stores;
	}

}
