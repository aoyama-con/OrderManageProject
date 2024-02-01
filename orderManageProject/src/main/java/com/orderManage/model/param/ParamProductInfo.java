package com.orderManage.model.param;

import java.util.List;

/**
 * 商品一覧取得APIにリクエストする際に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamProductInfo {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;

	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;
	
	/* 部門ID */
	private int category_id;
	
	/* 商品コード */
	private String product_code;
	
	/* グループコード */
	private String group_code;
	
	/* 端末表示 (0：表示しない、1：表示する) */
	private String display_flag;
	
	/* 商品区分 (0：通常商品、1：回数券、2：オプション商品) */
	private String division;
	
	/* 売上区分 (0:売上対象、1:売上対象外) */
	private String sales_division;
	
	/* 在庫管理区分 (0:在庫管理対象、1:在庫管理対象外) */
	private String stock_control_division;
	
	/* 品番 */
	private String supplier_product_no;
	
	/* 更新日時(From)：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String upd_date_time_from;
	
	/* 更新日時(To)：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
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

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getDisplay_flag() {
		return display_flag;
	}

	public void setDisplay_flag(String display_flag) {
		this.display_flag = display_flag;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSales_division() {
		return sales_division;
	}

	public void setSales_division(String sales_division) {
		this.sales_division = sales_division;
	}

	public String getStock_control_division() {
		return stock_control_division;
	}

	public void setStock_control_division(String stock_control_division) {
		this.stock_control_division = stock_control_division;
	}

	public String getSupplier_product_no() {
		return supplier_product_no;
	}

	public void setSupplier_product_no(String supplier_product_no) {
		this.supplier_product_no = supplier_product_no;
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
