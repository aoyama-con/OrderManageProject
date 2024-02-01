package com.orderManage.model.api;

/**
 * レシート印刷情報
 * @author aocon-mac
 *
 */
public class ReceiptPrintInfo {

	/* 店舗ID */
	private String storeId;
	/* レシートヘッダ */
	private String header;
	/* レシートフッタ */
	private String footer;
	/* レシート税務署印コメント */
	private String receiptTaxOfficeStampComment;
	/* 税務署名 */
	private String taxOfficeName;
	/* AirPrint用ロゴ */
	private String airPrintLogo;
	/* 広告画像 */
	private String advertisementImage;
	/* ギフトレシート用画像 */
	private String giftReceiptImage;
	/* ギフトレシート用注釈 */
	private String giftReceiptNote;
	/* 割引レシート控えヘッダ */
	private String discountReceiptHeader;
	/* 割引レシート控えフッダ */
	private String discountReceiptFooter;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getReceiptTaxOfficeStampComment() {
		return receiptTaxOfficeStampComment;
	}
	public void setReceiptTaxOfficeStampComment(String receiptTaxOfficeStampComment) {
		this.receiptTaxOfficeStampComment = receiptTaxOfficeStampComment;
	}
	public String getTaxOfficeName() {
		return taxOfficeName;
	}
	public void setTaxOfficeName(String taxOfficeName) {
		this.taxOfficeName = taxOfficeName;
	}
	public String getAirPrintLogo() {
		return airPrintLogo;
	}
	public void setAirPrintLogo(String airPrintLogo) {
		this.airPrintLogo = airPrintLogo;
	}
	public String getAdvertisementImage() {
		return advertisementImage;
	}
	public void setAdvertisementImage(String advertisementImage) {
		this.advertisementImage = advertisementImage;
	}
	public String getGiftReceiptImage() {
		return giftReceiptImage;
	}
	public void setGiftReceiptImage(String giftReceiptImage) {
		this.giftReceiptImage = giftReceiptImage;
	}
	public String getGiftReceiptNote() {
		return giftReceiptNote;
	}
	public void setGiftReceiptNote(String giftReceiptNote) {
		this.giftReceiptNote = giftReceiptNote;
	}
	public String getDiscountReceiptHeader() {
		return discountReceiptHeader;
	}
	public void setDiscountReceiptHeader(String discountReceiptHeader) {
		this.discountReceiptHeader = discountReceiptHeader;
	}
	public String getDiscountReceiptFooter() {
		return discountReceiptFooter;
	}
	public void setDiscountReceiptFooter(String discountReceiptFooter) {
		this.discountReceiptFooter = discountReceiptFooter;
	}
}
