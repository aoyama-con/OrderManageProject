package com.orderManage.model.param;

import java.util.List;

/**
 * 発注対象商品取得APIにリクエストする際に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamPurchaseOrderProduct {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;

	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;
	
	/* 商品ID */
	private String product_id;
	
	/* 配送先店舗付加フラグ：(none：付加しない、all：付加する) */
	private String with_deliveries;

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

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getWith_deliveries() {
		return with_deliveries;
	}

	public void setWith_deliveries(String with_deliveries) {
		this.with_deliveries = with_deliveries;
	}
}
