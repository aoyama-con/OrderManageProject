package com.orderManage.model.api;

/**
 * 在庫引当商品
 * 
 */
public class InventoryReservations {

	/* 商品ID */
	private String productId;
	/* 引当商品商品ID */
	private String reservationProductId;
	/* 引当数 */
	private String reservationAmount;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getReservationProductId() {
		return reservationProductId;
	}
	public void setReservationProductId(String reservationProductId) {
		this.reservationProductId = reservationProductId;
	}
	public String getReservationAmount() {
		return reservationAmount;
	}
	public void setReservationAmount(String reservationAmount) {
		this.reservationAmount = reservationAmount;
	}
}
