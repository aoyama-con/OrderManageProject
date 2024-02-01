package com.orderManage.model.service;

/**
 * 発注入力画面　検索結果表示情報
 */
public class DisplayOrderInput {

	/* 状態区分 */
	private String statusClass;
	/* グループコード */
	private String groupCode;
	/* 商品ID */
	private String productId;
	/* 商品CD */
	private String productCode;
	/* 商品名 */
	private String productName;
	/* 仕入先名 */
	private String supplierName;
	/* 部門名 */
	private String categoryName;
	/* 販売点数（4週） */
	private String totalPriceWeek_4;
	/* 販売点数（3週） */
	private String totalPriceWeek_3;
	/* 販売点数（2週） */
	private String totalPriceWeek_2;
	/* 販売点数（当週） */
	private String totalPriceWeek_1;
	/* 在庫日数 */
	private String stockDays;
	/* 発注点数 */
    private String quantity;

	public String getStatusClass() {
		return statusClass;
	}
	public void setStatusClass(String statusClass) {
		this.statusClass = statusClass;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	public String getTotalPriceWeek_4() {
		return totalPriceWeek_4;
	}
	public void setTotalPriceWeek_4(String totalPriceWeek_4) {
		this.totalPriceWeek_4 = totalPriceWeek_4;
	}
	public String getTotalPriceWeek_3() {
		return totalPriceWeek_3;
	}
	public void setTotalPriceWeek_3(String totalPriceWeek_3) {
		this.totalPriceWeek_3 = totalPriceWeek_3;
	}
	public String getTotalPriceWeek_2() {
		return totalPriceWeek_2;
	}
	public void setTotalPriceWeek_2(String totalPriceWeek_2) {
		this.totalPriceWeek_2 = totalPriceWeek_2;
	}
	public String getTotalPriceWeek_1() {
		return totalPriceWeek_1;
	}
	public void setTotalPriceWeek_1(String totalPriceWeek_1) {
		this.totalPriceWeek_1 = totalPriceWeek_1;
	}
	public String getStockDays() {
		return stockDays;
	}
	public void setStockDays(String stockDays) {
		this.stockDays = stockDays;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
