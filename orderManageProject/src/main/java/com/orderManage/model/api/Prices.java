package com.orderManage.model.api;

/**
 * 商品価格
 * 
 */
public class Prices {

	/* 商品ID */
	private String productId;
	/* 店舗ID */
	private String storeId;
	/* 価格区分 */
	private String priceDivision;
	/* 適用開始日：適用開始日。[YYYY-MM-DD] */
	private String startDate;
	/* 適用終了日：適用終了日。[YYYY-MM-DD] */
	private String endDate;
	/* 商品単価 */
	private String price;
	
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
	public String getPriceDivision() {
		return priceDivision;
	}
	public void setPriceDivision(String priceDivision) {
		this.priceDivision = priceDivision;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
