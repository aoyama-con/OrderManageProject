package com.orderManage.model.param;

import java.util.List;

/**
 * 店舗一覧取得時（GET）に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamStoreInfo {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;
	
	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;
	
	/* 店舗コード */
	private String store_code;
	
	/* 店舗区分 */
	private String division;
	
	/* ポイント情報を付加するか */
	private String with_point_condition;
	
	/* レシート印刷情報を付加するか */
	private String with_receipt_print_info;

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

	public String getStore_code() {
		return store_code;
	}

	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getWith_point_condition() {
		return with_point_condition;
	}

	public void setWith_point_condition(String with_point_condition) {
		this.with_point_condition = with_point_condition;
	}

	public String getWith_receipt_print_info() {
		return with_receipt_print_info;
	}

	public void setWith_receipt_print_info(String with_receipt_print_info) {
		this.with_receipt_print_info = with_receipt_print_info;
	}

}
