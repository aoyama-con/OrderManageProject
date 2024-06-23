package com.orderManage.controller.object;

import java.io.Serializable;

/**
 * 発注状況画面 発注情報格納Form
 */

public class CheckOrderStatusSubForm implements Serializable {
	
	/**
	 * 発注状況初期表示
	 */
    
	/* PurchaseOrdersProductsInfoからの取得項目*/
	/* 商品ID*/
	private String productId;
	/* 発注数量*/
	private String quantity;
	
	/* ProductsInfoからの取得項目*/
	/* 商品CD*/
	private String ProductCode;
	/* 商品名*/
	private String ProductName;
	/* グループコード*/
    private String GroupCode;
  
    /*CategoriesInfoからの取得項目*/
    /*部門ID*/
    private String categoryId;
    /*部門CD*/
    private String categoryCode;
    /*部門名*/
    private String categoryName;
    
    /*productImageInfoからの取得項目*/
    /* 商品画像URL */
	private String url;
	
    
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getGroupCode() {
		return GroupCode;
	}
	public void setGroupCode(String groupCode) {
		GroupCode = groupCode;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}   
}
    
    