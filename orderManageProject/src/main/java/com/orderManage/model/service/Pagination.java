package com.orderManage.model.service;

/**
 * ページネーション情報
 */
public class Pagination {

	/* 1ページ表示数 */
	private int limit;
	
	/* ページ番号 */
	private int page;
	
	/* ソート順 (カンマ区切りでソート順指定可 desc)*/
	private String sort;

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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
