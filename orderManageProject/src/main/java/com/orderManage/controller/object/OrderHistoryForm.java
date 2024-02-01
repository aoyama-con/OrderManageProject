package com.orderManage.controller.object;
import java.util.List;
/**
 * 発注履歴Form
 * 
 * 発注履歴画面表示時に使用するForm
 * 
 * @author aocon-mac
 *
 */
public class OrderHistoryForm {
	/* 仕入先店舗コード */
    private String supplierCode;
	/* 発注日From */
    private String DateFrom;
	/* 発注日To */
    private String DateTo;
	/* ステータス */
    private String status;
    /* 1ページ表示数 */
    private String maxDisplayAmount;
    /* ページ番号 */
    private String currentPage;
    /* 発注履歴リスト */
    private List<OrderHistorySubForm> displayList;
    
    
    
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getDateFrom() {
		return DateFrom;
	}
	public void setDateFrom(String dateFrom) {
		DateFrom = dateFrom;
	}
	public String getDateTo() {
		return DateTo;
	}
	public void setDateTo(String dateTo) {
		DateTo = dateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMaxDisplayAmount() {
		return maxDisplayAmount;
	}
	public void setMaxDisplayAmount(String maxDisplayAmount) {
		this.maxDisplayAmount = maxDisplayAmount;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public List<OrderHistorySubForm> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<OrderHistorySubForm> displayList) {
		this.displayList = displayList;
	}




}
