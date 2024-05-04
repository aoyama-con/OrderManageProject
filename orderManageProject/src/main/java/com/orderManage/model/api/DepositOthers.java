package com.orderManage.model.api;

/**
 * 取引一覧取得.その他支払方法 model
 * 
 * @author aocon-mac
 *
 */
public class DepositOthers {

	/* 取引ID */
	private String transactionHeadId;
	/* 項番 */
	private String no;
	/* その他支払方法ID */
	private String paymentMethodId;
	/* 支払方法コード */
	private String paymentMethodCode;
	/* 支払方法名 */
	private String paymentMethodName;
	/* 預り金その他 */
	private String depositOthers;
	/* 単価 */
	private String paymentUnitPrice;
	/* 釣銭フラグ */
	private String paymentChangeFlag;
	/* 支払方法マスタの支払方法分類を自動設定 */
	private String paymentDivision;
	/* 有価証券フラグ */
	private String paymentSecuritiesFlag;
	/* 支払金種コード */
	private String denominationCode;
	/* 支払金種名 */
	private String denominationName;
	/* クレジットカード会社名 */
	private String cardCompanyName;
	/* 伝票番号 */
	private String slipNumber;
	/* 取消伝票番号 */
	private String cancelSlipNumber;
	/* ポイント付与単位(金額) */
	private String pointGivingUnitPrice;
	/* ポイント付与単位(ポイント) */
	private String pointGivingUnit;
	
	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public String getPaymentMethodName() {
		return paymentMethodName;
	}
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}
	public String getDepositOthers() {
		return depositOthers;
	}
	public void setDepositOthers(String depositOthers) {
		this.depositOthers = depositOthers;
	}
	public String getPaymentUnitPrice() {
		return paymentUnitPrice;
	}
	public void setPaymentUnitPrice(String paymentUnitPrice) {
		this.paymentUnitPrice = paymentUnitPrice;
	}
	public String getPaymentChangeFlag() {
		return paymentChangeFlag;
	}
	public void setPaymentChangeFlag(String paymentChangeFlag) {
		this.paymentChangeFlag = paymentChangeFlag;
	}
	public String getPaymentDivision() {
		return paymentDivision;
	}
	public void setPaymentDivision(String paymentDivision) {
		this.paymentDivision = paymentDivision;
	}
	public String getPaymentSecuritiesFlag() {
		return paymentSecuritiesFlag;
	}
	public void setPaymentSecuritiesFlag(String paymentSecuritiesFlag) {
		this.paymentSecuritiesFlag = paymentSecuritiesFlag;
	}
	public String getDenominationCode() {
		return denominationCode;
	}
	public void setDenominationCode(String denominationCode) {
		this.denominationCode = denominationCode;
	}
	public String getDenominationName() {
		return denominationName;
	}
	public void setDenominationName(String denominationName) {
		this.denominationName = denominationName;
	}
	public String getCardCompanyName() {
		return cardCompanyName;
	}
	public void setCardCompanyName(String cardCompanyName) {
		this.cardCompanyName = cardCompanyName;
	}
	public String getSlipNumber() {
		return slipNumber;
	}
	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}
	public String getCancelSlipNumber() {
		return cancelSlipNumber;
	}
	public void setCancelSlipNumber(String cancelSlipNumber) {
		this.cancelSlipNumber = cancelSlipNumber;
	}
	public String getPointGivingUnitPrice() {
		return pointGivingUnitPrice;
	}
	public void setPointGivingUnitPrice(String pointGivingUnitPrice) {
		this.pointGivingUnitPrice = pointGivingUnitPrice;
	}
	public String getPointGivingUnit() {
		return pointGivingUnit;
	}
	public void setPointGivingUnit(String pointGivingUnit) {
		this.pointGivingUnit = pointGivingUnit;
	}
}
