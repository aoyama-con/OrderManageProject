package com.orderManage.model.api;

/**
 * 取引一覧取得.取置き時の詳細情報 model
 * 
 * @author aocon-mac
 *
 */
public class Layaways {

	/* 取引ID */
	private String transactionHeadId;
	/* 受取日 */
	private String pickUpDate;
	/* ステータス：取置取引のステータス（1：引取済み） */
	private String status;
	/* 前受金 */
	private String partPayment;
	/* 前受金区分（1：現金、2：クレジット） */
	private String partPaymentClass;
	/* 引取取引ヘッダーID */
	private String pickUpTransactionHeadId;
	/* 変更不可能設定:変更不可能設定(0:変更可、 1:変更不可) */
	private String disabledEdit;
	
	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPartPayment() {
		return partPayment;
	}
	public void setPartPayment(String partPayment) {
		this.partPayment = partPayment;
	}
	public String getPartPaymentClass() {
		return partPaymentClass;
	}
	public void setPartPaymentClass(String partPaymentClass) {
		this.partPaymentClass = partPaymentClass;
	}
	public String getPickUpTransactionHeadId() {
		return pickUpTransactionHeadId;
	}
	public void setPickUpTransactionHeadId(String pickUpTransactionHeadId) {
		this.pickUpTransactionHeadId = pickUpTransactionHeadId;
	}
	public String getDisabledEdit() {
		return disabledEdit;
	}
	public void setDisabledEdit(String disabledEdit) {
		this.disabledEdit = disabledEdit;
	}
}
