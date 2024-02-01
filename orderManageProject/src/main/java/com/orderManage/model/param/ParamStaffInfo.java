package com.orderManage.model.param;

import java.util.List;

/**
 * スタッフ一覧取得時（GET）に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamStaffInfo {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;
	
	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;
	
	/* スタッフID */
	private int staff_id;
	
	/* ユーザー識別子 */
	private int user_id;
	
	/* スタッフコード */
	private String staff_code;
	
	/* 役割・役職ID */
	private String role_id;
	
	/* ランク */
	private String rank;
	
	/* ログインフラグ（0：ログイン不可、1：ログイン可、2：認証待ち、3：停止） */
	private String login_staff_flag;
	
	/* 表示フラグ（0：表示しない、1：表示する） */
	private String display_flag;

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

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStaff_code() {
		return staff_code;
	}

	public void setStaff_code(String staff_code) {
		this.staff_code = staff_code;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getLogin_staff_flag() {
		return login_staff_flag;
	}

	public void setLogin_staff_flag(String login_staff_flag) {
		this.login_staff_flag = login_staff_flag;
	}

	public String getDisplay_flag() {
		return display_flag;
	}

	public void setDisplay_flag(String display_flag) {
		this.display_flag = display_flag;
	}
}
