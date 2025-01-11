package com.orderManage.model.session;

import com.orderManage.model.service.Pagination;

/**
 * 発注履歴画面　セッション情報
 */
public class OrderHistSessionInfo {

	/* 発注日 from */
	private String orderedDateFrom;
	/* 発注日 to */
	private String orderedDateTo;
	/* 仕入先ID */
	private String supplierId;
	/* 仕入先名 */
	private String supplierName;
	/* ステータス(2:発注済、3:入荷検品中、9:全て（発注済み、仮発注）) */
	private String status;
	/* ページ情報 */
	private Pagination pagination;
	
	public String getOrderedDateFrom() {
		return orderedDateFrom;
	}
	public void setOrderedDateFrom(String orderedDateFrom) {
		this.orderedDateFrom = orderedDateFrom;
	}
	public String getOrderedDateTo() {
		return orderedDateTo;
	}
	public void setOrderedDateTo(String orderedDateTo) {
		this.orderedDateTo = orderedDateTo;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagenation(Pagination pagination) {
		this.pagination = pagination;
	}

}
