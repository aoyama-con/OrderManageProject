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
	
	/* 画面表示用項目 */
	/* 仕入先店舗コード */
    private String supplierId;
	/* 発注日From */
    private String DateFrom;
	/* 発注日To */
    private String DateTo;
	/* ステータス */
    private String status;
    /* 1ページ表示数 */
    private int maxDisplayAmount;
    /* ページ番号 */
    private int currentPage;
    /* 発注履歴リスト */
    private List<OrderHistorySubForm> displayList;
    
    /* 引渡し項目 */
    /* 発注ID */
    private String orderId ;
 

	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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
	public int getMaxDisplayAmount() {
		return maxDisplayAmount;
	}
	public void setMaxDisplayAmount(int maxDisplayAmount) {
		this.maxDisplayAmount = maxDisplayAmount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<OrderHistorySubForm> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<OrderHistorySubForm> displayList) {
		this.displayList = displayList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




}
