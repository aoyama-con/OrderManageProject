package com.orderManage.model.api;

/**
 * 取引一覧取得.取引詳細.取引時の商品属性情報 model
 * 
 * @author aocon-mac
 *
 */
public class ProductAttributes {

	/* 取引ID */
	private String transactionHeadId;
	/* 取引明細ID */
	private String transactionDetailId;
	/* コード */
	private String code;
	/* 名称 */
	private String name;
	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getTransactionDetailId() {
		return transactionDetailId;
	}
	public void setTransactionDetailId(String transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
