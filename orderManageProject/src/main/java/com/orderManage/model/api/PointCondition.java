package com.orderManage.model.api;


/**
 * ポイント条件
 * @author aocon-mac
 *
 */
public class PointCondition {
	/* 店舗ID */
	private String storeId;
	/* ポイント使用区分 */
	private String pointUseDivision;
	/* ポイント還元率 */
	private String spendRate;
	/* ポイント付与単位(金額) */
	private String pointGivingUnitPrice;
	/* ポイント付与単位(ポイント) */
	private String pointGivingUnit;
	/* ポイント付与区分 */
	private String pointGivingDivision;
	/* ポイント利用単位 */
	private String pointUseUnit;
	/* ポイント利用区分 */
	private String pointSpendDivision;
	/* ポイント利用上限区分 */
	private String pointSpendLimitDivision;
	/* ポイント失効期限区分 */
	private String expireDivision;
	/* ポイント失効期限 */
	private String expireLimit;
	/* マイレージ利用区分 */
	private String mileageDivision;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPointUseDivision() {
		return pointUseDivision;
	}
	public void setPointUseDivision(String pointUseDivision) {
		this.pointUseDivision = pointUseDivision;
	}
	public String getSpendRate() {
		return spendRate;
	}
	public void setSpendRate(String spendRate) {
		this.spendRate = spendRate;
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
	public String getPointGivingDivision() {
		return pointGivingDivision;
	}
	public void setPointGivingDivision(String pointGivingDivision) {
		this.pointGivingDivision = pointGivingDivision;
	}
	public String getPointUseUnit() {
		return pointUseUnit;
	}
	public void setPointUseUnit(String pointUseUnit) {
		this.pointUseUnit = pointUseUnit;
	}
	public String getPointSpendDivision() {
		return pointSpendDivision;
	}
	public void setPointSpendDivision(String pointSpendDivision) {
		this.pointSpendDivision = pointSpendDivision;
	}
	public String getPointSpendLimitDivision() {
		return pointSpendLimitDivision;
	}
	public void setPointSpendLimitDivision(String pointSpendLimitDivision) {
		this.pointSpendLimitDivision = pointSpendLimitDivision;
	}
	public String getExpireDivision() {
		return expireDivision;
	}
	public void setExpireDivision(String expireDivision) {
		this.expireDivision = expireDivision;
	}
	public String getExpireLimit() {
		return expireLimit;
	}
	public void setExpireLimit(String expireLimit) {
		this.expireLimit = expireLimit;
	}
	public String getMileageDivision() {
		return mileageDivision;
	}
	public void setMileageDivision(String mileageDivision) {
		this.mileageDivision = mileageDivision;
	}
}
