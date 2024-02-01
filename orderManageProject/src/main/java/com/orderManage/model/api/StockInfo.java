package com.orderManage.model.api;


/**
 * 在庫一覧取得
 * 
 * スマレジAPI　在庫一覧情報取得時に格納するmodel
 */
public class StockInfo {

	/* 店舗ID */
	private String storeId;
	/* 商品ID */
	private String productId;
	/* 在庫数 */
	private String stockAmount;
	/* 取置き在庫数 */
	private String layawayStockAmount;
	/* 更新日時：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String updDateTime;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}
	public String getLayawayStockAmount() {
		return layawayStockAmount;
	}
	public void setLayawayStockAmount(String layawayStockAmount) {
		this.layawayStockAmount = layawayStockAmount;
	}
	public String getUpdDateTime() {
		return updDateTime;
	}
	public void setUpdDateTime(String updDateTime) {
		this.updDateTime = updDateTime;
	}
}
