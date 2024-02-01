package com.orderManage.model.api;

/**
 * 部門一覧取得
 * 
 * スマレジAPI　部門一覧取得、部門取得時に格納するmodel
 * 
 */
public class CategorieInfo {

	/* 部門ID */
	private String categoryId;
	/* 部門コード */
	private String categoryCode;
	/* 部門名 */
	private String categoryName;
	/* 部門名略称 */
	private String categoryAbbr;
	/* 表示順 */
	private String displaySequence;
	/* 端末表示：スマレジ端末に表示するか否かの判定。 (0:表示しない、1:表示する) */
	private String displayFlag;
	/* 税区分：商品価格の消費税の扱いに関する設定。 （0:内税、1:外税、2:非課税） */
	private String taxDivision;
	/* ポイント対象：ポイント対象にするか否かの設定。（0:ポイント対象、1:ポイント対象外） */
	private String pointNotApplicable;
	/* 免税区分：免税区分の設定。 (0:対象外、1:一般品、2:消耗品) */
	private String taxFreeDivision;
	/* 軽減税率ID（税設定）：軽減税率設定画面で設定した軽減税率ID、または、下記の軽減税率ID。標準税率の場合はnull。
	 * 軽減:10000001 (特定商品の軽減税率適用）
	 * 選択[標準]:10000002（状態による適用[適用しない]）
	 * 選択[軽減]:10000003（状態による適用[適用する]）
	 * 選択[選択]:10000004（状態による適用[都度選択する]） */
	private String reduceTaxId;
	/* color */
	private String color;
	/* categoryGroupId */
	private String categoryGroupId;
	/* parentCategoryId */
	private String parentCategoryId;
	/* level */
	private String level;
	/* tag */
	private String tag;
	/* insDateTime */
	private String insDateTime;
	/* updDateTime */
	private String updDateTime;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryAbbr() {
		return categoryAbbr;
	}
	public void setCategoryAbbr(String categoryAbbr) {
		this.categoryAbbr = categoryAbbr;
	}
	public String getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(String displaySequence) {
		this.displaySequence = displaySequence;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getTaxDivision() {
		return taxDivision;
	}
	public void setTaxDivision(String taxDivision) {
		this.taxDivision = taxDivision;
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
	public String getReduceTaxId() {
		return reduceTaxId;
	}
	public void setReduceTaxId(String reduceTaxId) {
		this.reduceTaxId = reduceTaxId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCategoryGroupId() {
		return categoryGroupId;
	}
	public void setCategoryGroupId(String categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
	}
	public String getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
