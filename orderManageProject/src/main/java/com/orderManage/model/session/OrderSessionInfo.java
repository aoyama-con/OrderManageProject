package com.orderManage.model.session;

import java.util.List;
import java.util.Map;

import com.orderManage.controller.object.OrderInputSubForm;
import com.orderManage.model.service.DisplayOrderInput;
import com.orderManage.model.service.Pagination;

/**
 * 発注入力画面　セッション情報
 */
public class OrderSessionInfo {

	/* ページ情報 */
	private Pagination pagenation;
	
	/* 発注管理番号 */
	private String OrderControlNumber;
	/* 発注ID */
	private List<String> storageInfoIdList;
	/* 商品情報一覧（表示） */
	private List<DisplayOrderInput> displayOrderInput;
	
	// TODO formと合わせてこっちを使ってみる
	/** */
	private List<OrderInputSubForm> displayList;
	
	/** */
	private Map<String, String[]> orderAmount;
	

	/* 検索条件 ****************************************/
	/* 部門ID */
	private String categoryId;
	/* 商品属性 */
	private String attribute;
	/* グループコード */
	private String groupCode;
	/* 品番 */
	private String supplierProductNo;
	/* 商品ID */
	private String productId;
	/* 商品CD */
	private String productCode;
	/* 商品名 */
	private String productName;
	/* PB区分 */
	private String pbDivision;
	/* 販売金額 週(プルダウン) */
	private String totalPriceWeek;
	/* 販売金額 */
	private String totalPrice;
	/* ***********************************************/
	
	public Pagination getPagenation() {
		return pagenation;
	}
	public void setPagenation(Pagination pagenation) {
		this.pagenation = pagenation;
	}
	public String getOrderControlNumber() {
		return OrderControlNumber;
	}
	public void setOrderControlNumber(String orderControlNumber) {
		OrderControlNumber = orderControlNumber;
	}
	public List<String> getStorageInfoIdList() {
		return storageInfoIdList;
	}
	public void setStorageInfoIdList(List<String> storageInfoIdList) {
		this.storageInfoIdList = storageInfoIdList;
	}
	public List<DisplayOrderInput> getDisplayOrderInput() {
		return displayOrderInput;
	}
	public void setDisplayOrderInput(List<DisplayOrderInput> displayOrderInput) {
		this.displayOrderInput = displayOrderInput;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getSupplierProductNo() {
		return supplierProductNo;
	}
	public void setSupplierProductNo(String supplierProductNo) {
		this.supplierProductNo = supplierProductNo;
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
	public String getPbDivision() {
		return pbDivision;
	}
	public void setPbDivision(String pbDivision) {
		this.pbDivision = pbDivision;
	}
	public String getTotalPriceWeek() {
		return totalPriceWeek;
	}
	public void setTotalPriceWeek(String totalPriceWeek) {
		this.totalPriceWeek = totalPriceWeek;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderInputSubForm> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<OrderInputSubForm> displayList) {
		this.displayList = displayList;
	}
	public Map<String, String[]> getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Map<String, String[]> orderAmount) {
		this.orderAmount = orderAmount;
	}
}
