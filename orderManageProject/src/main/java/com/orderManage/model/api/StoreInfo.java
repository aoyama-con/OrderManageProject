package com.orderManage.model.api;

/**
 * 店舗情報 model
 * 
 * スマレジAPI　店舗一覧、店舗情報取得時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class StoreInfo {

	/* 店舗ID */
    private String storeId;
	/* 店舗コード */
    private String storeCode;
	/* 店舗名 */
    private String storeName;
	/* 店舗名略称 */
    private String storeAbbr;
	/* レシート印字店舗名 */
    private String printReceiptStoreName;
	/* 在庫レシート印字店舗名 */
    private String printStockReceiptStoreName;
	/* 店舗区分 */
    private String division;
	/* 店舗郵便番号 */
    private String postCode;
	/* 店舗住所 */
    private String address;
	/* 店舗電話番号 */
    private String phoneNumber;
	/* 店舗FAX番号 */
    private String faxNumber;
	/* 店舗メールアドレス */
    private String mailAddress;
	/* 店舗ホームページ */
    private String homepage;
	/* 仮取引登録時に送信するメールアドレス */
    private String tempTranMailAddress;
	/* 端末価格変更許可フラグ */
    private String priceChangeFlag;
	/* 販売区分　0：内税販売、1：外税販売 */
    private String sellDivision;
	/* 締め方式区分　	0：手動締め処理、1：自動締め処理 */
    private String sumProcDivision;
	/* 締め日付変更時間　締め方式区分が1：自動締め処理の場合必須 */
    private String sumDateChangeTime;
	/* 締め参照時間項目　0：端末時間、1：サーバー時間 */
    private String sumRefColumn;
	/* ポイント対象区分　0:ポイントをつける、1:ポイントをつけない */
    private String pointNotApplicable;
	/* 免税区分　0：対象外、1：一般品、2：消耗品 */
    private String taxFreeDivision;
	/* 簡易バンドル商品登録最大件数 */
    private String maxBundleProductCount;
	/* 最大値引率 */
    private String maxDiscountRate;
	/* 送料・手数料表示フラグ　0：表示しない、1：表示する */
    private String carriageDisplayFlag;
	/* 端末精算時の現金管理フラグ　0：入力しない、1：入力する */
    private String terminalAdjustmentCashFlag;
	/* 端末検査時の現金管理フラグ　0：入力しない、1：入力する */
    private String terminalCheckCashFlag;
	/* ウェイター精算可能チェック区分　0：チェックしない、1：チェックする */
    private String waiterAdjustmentDivision;
	/* 銀行預入金自動入力区分　0:自動入力しない、1:自動入力す */
    private String savingAutoDivision;
	/* 銀行預入金自動入力金額 */
    private String savingAutoPrice;
	/* 取消設定区分　1:自動、2：消込のみ、3:選択 */
    private String cancelSettingDivision;
	/* 端数値引区分 */
    private String roundingDivision;
	/* 割引丸め区分 */
    private String discountRoundingDivision;
	/* カード会社選択区分 */
    private String cardCompanySelectDivision;
	/* ギフトレシート引換有効日数 */
    private String giftReceiptValidDays;
	/* 標準税率のボタンラベル */
    private String taxLabelNormal;
	/* 軽減税率のボタンラベル */
    private String taxLabelReduce;
	/* 有効無効フラグ */
    private String pauseFlag;
	/* 表示番号 */
    private String displaySequence;
	/* 顔認証決済利用区分 */
    private String facePaymentUseDivision;
	/* 登録事業者番号 */
    private String invoiceRegistrationNo;
	/* 作成日付 */
    private String insDateTime;
	/* 更新日付 */
    private String updDateTime;
	/* ポイント条件 */
	PointCondition pointCondition;
	/* レシート印刷情報 */
	ReceiptPrintInfo receiptPrintInfo;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAbbr() {
		return storeAbbr;
	}
	public void setStoreAbbr(String storeAbbr) {
		this.storeAbbr = storeAbbr;
	}
	public String getPrintReceiptStoreName() {
		return printReceiptStoreName;
	}
	public void setPrintReceiptStoreName(String printReceiptStoreName) {
		this.printReceiptStoreName = printReceiptStoreName;
	}
	public String getPrintStockReceiptStoreName() {
		return printStockReceiptStoreName;
	}
	public void setPrintStockReceiptStoreName(String printStockReceiptStoreName) {
		this.printStockReceiptStoreName = printStockReceiptStoreName;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getTempTranMailAddress() {
		return tempTranMailAddress;
	}
	public void setTempTranMailAddress(String tempTranMailAddress) {
		this.tempTranMailAddress = tempTranMailAddress;
	}
	public String getPriceChangeFlag() {
		return priceChangeFlag;
	}
	public void setPriceChangeFlag(String priceChangeFlag) {
		this.priceChangeFlag = priceChangeFlag;
	}
	public String getSellDivision() {
		return sellDivision;
	}
	public void setSellDivision(String sellDivision) {
		this.sellDivision = sellDivision;
	}
	public String getSumProcDivision() {
		return sumProcDivision;
	}
	public void setSumProcDivision(String sumProcDivision) {
		this.sumProcDivision = sumProcDivision;
	}
	public String getSumDateChangeTime() {
		return sumDateChangeTime;
	}
	public void setSumDateChangeTime(String sumDateChangeTime) {
		this.sumDateChangeTime = sumDateChangeTime;
	}
	public String getSumRefColumn() {
		return sumRefColumn;
	}
	public void setSumRefColumn(String sumRefColumn) {
		this.sumRefColumn = sumRefColumn;
	}
	public String getPointNotApplicable() {
		return pointNotApplicable;
	}
	public void setPointNotApplicable(String pointNotApplicable) {
		this.pointNotApplicable = pointNotApplicable;
	}
	public String getTaxFreeDivision() {
		return taxFreeDivision;
	}
	public void setTaxFreeDivision(String taxFreeDivision) {
		this.taxFreeDivision = taxFreeDivision;
	}
	public String getMaxBundleProductCount() {
		return maxBundleProductCount;
	}
	public void setMaxBundleProductCount(String maxBundleProductCount) {
		this.maxBundleProductCount = maxBundleProductCount;
	}
	public String getMaxDiscountRate() {
		return maxDiscountRate;
	}
	public void setMaxDiscountRate(String maxDiscountRate) {
		this.maxDiscountRate = maxDiscountRate;
	}
	public String getCarriageDisplayFlag() {
		return carriageDisplayFlag;
	}
	public void setCarriageDisplayFlag(String carriageDisplayFlag) {
		this.carriageDisplayFlag = carriageDisplayFlag;
	}
	public String getTerminalAdjustmentCashFlag() {
		return terminalAdjustmentCashFlag;
	}
	public void setTerminalAdjustmentCashFlag(String terminalAdjustmentCashFlag) {
		this.terminalAdjustmentCashFlag = terminalAdjustmentCashFlag;
	}
	public String getTerminalCheckCashFlag() {
		return terminalCheckCashFlag;
	}
	public void setTerminalCheckCashFlag(String terminalCheckCashFlag) {
		this.terminalCheckCashFlag = terminalCheckCashFlag;
	}
	public String getWaiterAdjustmentDivision() {
		return waiterAdjustmentDivision;
	}
	public void setWaiterAdjustmentDivision(String waiterAdjustmentDivision) {
		this.waiterAdjustmentDivision = waiterAdjustmentDivision;
	}
	public String getSavingAutoDivision() {
		return savingAutoDivision;
	}
	public void setSavingAutoDivision(String savingAutoDivision) {
		this.savingAutoDivision = savingAutoDivision;
	}
	public String getSavingAutoPrice() {
		return savingAutoPrice;
	}
	public void setSavingAutoPrice(String savingAutoPrice) {
		this.savingAutoPrice = savingAutoPrice;
	}
	public String getCancelSettingDivision() {
		return cancelSettingDivision;
	}
	public void setCancelSettingDivision(String cancelSettingDivision) {
		this.cancelSettingDivision = cancelSettingDivision;
	}
	public String getRoundingDivision() {
		return roundingDivision;
	}
	public void setRoundingDivision(String roundingDivision) {
		this.roundingDivision = roundingDivision;
	}
	public String getDiscountRoundingDivision() {
		return discountRoundingDivision;
	}
	public void setDiscountRoundingDivision(String discountRoundingDivision) {
		this.discountRoundingDivision = discountRoundingDivision;
	}
	public String getCardCompanySelectDivision() {
		return cardCompanySelectDivision;
	}
	public void setCardCompanySelectDivision(String cardCompanySelectDivision) {
		this.cardCompanySelectDivision = cardCompanySelectDivision;
	}
	public String getGiftReceiptValidDays() {
		return giftReceiptValidDays;
	}
	public void setGiftReceiptValidDays(String giftReceiptValidDays) {
		this.giftReceiptValidDays = giftReceiptValidDays;
	}
	public String getTaxLabelNormal() {
		return taxLabelNormal;
	}
	public void setTaxLabelNormal(String taxLabelNormal) {
		this.taxLabelNormal = taxLabelNormal;
	}
	public String getTaxLabelReduce() {
		return taxLabelReduce;
	}
	public void setTaxLabelReduce(String taxLabelReduce) {
		this.taxLabelReduce = taxLabelReduce;
	}
	public String getPauseFlag() {
		return pauseFlag;
	}
	public void setPauseFlag(String pauseFlag) {
		this.pauseFlag = pauseFlag;
	}
	public String getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(String displaySequence) {
		this.displaySequence = displaySequence;
	}
	public String getFacePaymentUseDivision() {
		return facePaymentUseDivision;
	}
	public void setFacePaymentUseDivision(String facePaymentUseDivision) {
		this.facePaymentUseDivision = facePaymentUseDivision;
	}
	public String getInvoiceRegistrationNo() {
		return invoiceRegistrationNo;
	}
	public void setInvoiceRegistrationNo(String invoiceRegistrationNo) {
		this.invoiceRegistrationNo = invoiceRegistrationNo;
	}
	public String getInsDateTime() {
		return insDateTime;
	}
	public void setInsDateTime(String insDateTime) {
		this.insDateTime = insDateTime;
	}
	public String getUpdDateTime() {
		return updDateTime;
	}
	public void setUpdDateTime(String updDateTime) {
		this.updDateTime = updDateTime;
	}
}
