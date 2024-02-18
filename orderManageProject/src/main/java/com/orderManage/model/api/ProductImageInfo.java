package com.orderManage.model.api;

/**
 * 商品画像情報 model
 * 
 * スマレジAPI　商品画像一覧取得時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class ProductImageInfo {

	/* 商品ID */
	private String productId;
	/* 商品画像URL */
	private String url;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
