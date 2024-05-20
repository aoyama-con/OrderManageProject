package com.orderManage.model.api;

/**
 * 取引一覧取得.取置き引取時の詳細情報 model
 * 
 * @author aocon-mac
 *
 */
public class LayawayPickUps {

	/* 取引ID */
	private String transactionHeadId;
	/* 取置きの取引ID */
	private String layawayServerTransactionHeadId;
	/* 受領済金額:取置きの内金/手付金（現金） */
	private String receivedDepositCash;
	/* 受領済クレジット金額:取置きの内金/手付金（クレジット） */
	private String receivedDepositCredit;

	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getLayawayServerTransactionHeadId() {
		return layawayServerTransactionHeadId;
	}
	public void setLayawayServerTransactionHeadId(String layawayServerTransactionHeadId) {
		this.layawayServerTransactionHeadId = layawayServerTransactionHeadId;
	}
	public String getReceivedDepositCash() {
		return receivedDepositCash;
	}
	public void setReceivedDepositCash(String receivedDepositCash) {
		this.receivedDepositCash = receivedDepositCash;
	}
	public String getReceivedDepositCredit() {
		return receivedDepositCredit;
	}
	public void setReceivedDepositCredit(String receivedDepositCredit) {
		this.receivedDepositCredit = receivedDepositCredit;
	}
}
