package com.orderManage.controller.object;

import java.math.BigDecimal;

/**
 * 発注確定画面　一覧項目設定用SubForm
 */
public class OrderConfirmSubForm {

	/* 状態区分 */
	private String conditionSection;
	/* グループコード */
	private String groupCode;
	/* 画像URL */
	private String imgUrl;
	/* 商品ID */
	private String productId;
	/* 商品CD */
	private String productCd;	
	/* 商品名 */
	private String productName;
	/* 仕入先 */
	private String supplierName;
	/* 部門名 */
	private String categoryName;
	/* 販売点数 4週 */
	private BigDecimal fourWeekNumberSales;
	/* 販売点数 3週 */
	private BigDecimal threeWeekNumberSales;
	/* 販売点数 2週 */
	private BigDecimal towWeekNumberSales;
	/* 販売点数 前週 */
	private BigDecimal oneWeekNumberSales;
	/* 販売点数 当週 */
	private BigDecimal thisWeekNumberSales;
	/* 在庫点数 */
	private int stockAmount;
	/* 在庫日数 */
	private int stockDays;
	/* 発注点数 */
	private int orderingPoint;

	public String getConditionSection() {
		return conditionSection;
	}
	public void setImgUrl(String imgUrl) {
		this.conditionSection = imgUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setConditionSection(String conditionSection) {
		this.conditionSection = conditionSection;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public BigDecimal getFourWeekNumberSales() {
		return fourWeekNumberSales;
	}
	public void setFourWeekNumberSales(BigDecimal fourAmount) {
		this.fourWeekNumberSales = fourAmount;
	}
	public BigDecimal getThreeWeekNumberSales() {
		return threeWeekNumberSales;
	}
	public void setThreeWeekNumberSales(BigDecimal threeAmount) {
		this.threeWeekNumberSales = threeAmount;
	}
	public BigDecimal getTowWeekNumberSales() {
		return towWeekNumberSales;
	}
	public void setTowWeekNumberSales(BigDecimal twoAmount) {
		this.towWeekNumberSales = twoAmount;
	}
	public BigDecimal getOneWeekNumberSales() {
		return oneWeekNumberSales;
	}
	public void setOneWeekNumberSales(BigDecimal twoAmount) {
		this.oneWeekNumberSales = twoAmount;
	}
	public BigDecimal getThisWeekNumberSales() {
		return thisWeekNumberSales;
	}
	public void setThisWeekNumberSales(BigDecimal amount) {
		this.thisWeekNumberSales = amount;
	}
	public int getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}
	public int getStockDays() {
		return stockDays;
	}
	public void setStockDays(int stockDays) {
		this.stockDays = stockDays;
	}
	public int getOrderingPoint() {
		return orderingPoint;
	}
	public void setOrderingPoint(int orderingPoint) {
		this.orderingPoint = orderingPoint;
	}
}
