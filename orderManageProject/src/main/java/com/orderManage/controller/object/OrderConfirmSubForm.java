package com.orderManage.controller.object;

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
	private int fourWeekNumberSales;
	/* 販売点数 3週 */
	private int threeWeekNumberSales;
	/* 販売点数 2週 */
	private int towWeekNumberSales;
	/* 販売点数 前週 */
	private int oneWeekNumberSales;
	/* 販売点数 当週 */
	private int thisWeekNumberSales;
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
	public int getFourWeekNumberSales() {
		return fourWeekNumberSales;
	}
	public void setFourWeekNumberSales(int fourWeekNumberSales) {
		this.fourWeekNumberSales = fourWeekNumberSales;
	}
	public int getThreeWeekNumberSales() {
		return threeWeekNumberSales;
	}
	public void setThreeWeekNumberSales(int threeWeekNumberSales) {
		this.threeWeekNumberSales = threeWeekNumberSales;
	}
	public int getTowWeekNumberSales() {
		return towWeekNumberSales;
	}
	public void setTowWeekNumberSales(int towWeekNumberSales) {
		this.towWeekNumberSales = towWeekNumberSales;
	}
	public int getOneWeekNumberSales() {
		return oneWeekNumberSales;
	}
	public void setOneWeekNumberSales(int oneWeekNumberSales) {
		this.oneWeekNumberSales = oneWeekNumberSales;
	}
	public int getThisWeekNumberSales() {
		return thisWeekNumberSales;
	}
	public void setThisWeekNumberSales(int thisWeekNumberSales) {
		this.thisWeekNumberSales = thisWeekNumberSales;
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
