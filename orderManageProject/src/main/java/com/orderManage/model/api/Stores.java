package com.orderManage.model.api;

/**
 * 商品取扱店舗
 * 
 */
public class Stores {

	/* 商品ID */
	private String productId;
	/* 店舗ID */
	private String storeId;
	/* オプショングループID */
	private String productOptionGroupId;
	/* 取扱区分：0:販売する／1:販売しない */
	private String assignDivision;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getProductOptionGroupId() {
		return productOptionGroupId;
	}
	public void setProductOptionGroupId(String productOptionGroupId) {
		this.productOptionGroupId = productOptionGroupId;
	}
	public String getAssignDivision() {
		return assignDivision;
	}
	public void setAssignDivision(String assignDivision) {
		this.assignDivision = assignDivision;
	}
}
