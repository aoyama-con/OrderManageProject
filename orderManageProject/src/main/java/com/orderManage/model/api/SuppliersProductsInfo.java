package com.orderManage.model.api;

/**
 * 仕入先商品一覧取得 model
 * 
 * スマレジAPI　仕入先商品一覧取得時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class SuppliersProductsInfo {

	/* 仕入先ID */
	private String supplierId;
	/* 部門ID */
	private String categoryId;
	/* 商品ID */
	private String productId;
	/* 作成日時：登録時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String insDateTime;
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getInsDateTime() {
		return insDateTime;
	}
	public void setInsDateTime(String insDateTime) {
		this.insDateTime = insDateTime;
	}
}
