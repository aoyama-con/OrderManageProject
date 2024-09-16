package com.orderManage.controller.object;

import java.io.Serializable;

/**
 * 
 */
public class OrderInputSubForm implements Serializable {

    /** グループコード */
	private String groupCode;
    
    /** 商品画像 */
	private String productImage;
    
    /** 商品情報 */
    private String productInfo;
    
    /** 仕入先名 */
    private String supplierName;
    
    /** 部門名 */
    private String categoryName;
    
    /** 在庫点数 */
    private String stockAmount = "0";
    
    /** 発注点数 */
    private String orderAmount;

    /** 発注点 */
    private String orderPoint;
    
    /** 商品ID */
    private String productId;
    
    /** 商品コード */
    private String productCode;
    
    /** 商品名 */
    private String ProductName;
    
    /** 仕入先ID */
    private String supplierId;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderPoint() {
		return orderPoint;
	}

	public void setOrderPoint(String orderPoint) {
		this.orderPoint = orderPoint;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}   
}
