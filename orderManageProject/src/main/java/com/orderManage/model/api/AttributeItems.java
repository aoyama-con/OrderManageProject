package com.orderManage.model.api;

/**
 * 商品属性項目
 * 
 */
public class AttributeItems {

	/* 商品ID */
	private String productId;
	/* コード */
	private String code;
	/* 項目番号 */
	private String no;
	/* 名称 */
	private String name;

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
