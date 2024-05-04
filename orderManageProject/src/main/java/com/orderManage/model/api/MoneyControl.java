package com.orderManage.model.api;

/**
 * 取引一覧取得.取引金操作情報 model
 * 
 * @author aocon-mac
 *
 */
public class MoneyControl {
	
	/* 取引ID */
	private String transactionHeadId;
	/* 属性区分 */
	private String attributeDivision;
	/* 属性区分名 */
	private String attributeDivisionName;

	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getAttributeDivision() {
		return attributeDivision;
	}
	public void setAttributeDivision(String attributeDivision) {
		this.attributeDivision = attributeDivision;
	}
	public String getAttributeDivisionName() {
		return attributeDivisionName;
	}
	public void setAttributeDivisionName(String attributeDivisionName) {
		this.attributeDivisionName = attributeDivisionName;
	}
}
