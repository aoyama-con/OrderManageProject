package com.orderManage.model.param;

import java.util.List;

/**
 * 在庫一覧取得時（GET）に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamStockInfo {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;
	
	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;
	
	/* 店舗ID */
	private int store_id;
	
	/* 商品ID */
	private String product_id;
	
	/* 更新日時(From)：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String upd_date_time_from;
	
	/* 更新日時(to)：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String upd_date_time_to;

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

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getUpd_date_time_from() {
		return upd_date_time_from;
	}

	public void setUpd_date_time_from(String upd_date_time_from) {
		this.upd_date_time_from = upd_date_time_from;
	}

	public String getUpd_date_time_to() {
		return upd_date_time_to;
	}

	public void setUpd_date_time_to(String upd_date_time_to) {
		this.upd_date_time_to = upd_date_time_to;
	}
}
