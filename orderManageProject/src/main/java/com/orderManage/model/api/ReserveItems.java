package com.orderManage.model.api;

/**
 * 商品自由項目
 * 
 */
public class ReserveItems {

	/* 商品ID */
	private String productId;
	/* 商品自由項目番号 */
	private String no;
	/* 値 */
	private String value;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
