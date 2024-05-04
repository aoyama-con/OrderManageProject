package com.orderManage.model.api;

import java.util.List;

/**
 * 取引一覧取得.取引詳細 model
 * 
 * @author aocon-mac
 *
 */
public class Details {
	
	/* 取引ID */
	private String transactionHeadId;
	/* 取引明細ID */
	private String transactionDetailId;
	/* 親取引明細ID */
	private String parentTransactionDetailId;
	/* 取引明細区分 */
	private String transactionDetailDivision;
	/* 商品ID */
	private String productId;
	/* 商品コード */
	private String productCode;
	/* 商品名 */
	private String productName;
	/* レシート印字商品名 */
	private String printReceiptProductName;
	/* カラー */
	private String color;
	/* サイズ */
	private String size;
	/* グループコード */
	private String groupCode;
	/* 税区分 */
	private String taxDivision;
	/* 商品単価 */
	private String price;
	/* 販売単価 */
	private String salesPrice;
	/* 単品値引 */
	private String unitDiscountPrice;
	/* 単品割引 */
	private String unitDiscountRate;
	/* 単品値引き/割引区分 */
	private String unitDiscountDivision;
	/* 原価 */
	private String cost;
	/* 数量 */
	private String quantity;
	/* 値引き前計 */
	private String unitNonDiscountSum;
	/* 単品値引き計 */
	private String unitDiscountSum;
	/* 値引き後計 */
	private String unitDiscountedSum;
	/* 原価計 */
	private String costSum;
	/* 部門ID */
	private String categoryId;
	/* 部門名 */
	private String categoryName;
	/* 識別番号 */
	private String discriminationNo;
	/* 売上区分 */
	private String salesDivision;
	/* 商品区分 */
	private String productDivision;
	/* 在庫引当区分 */
	private String inventoryReservationDivision;
	/* ポイント対象 */
	private String pointNotApplicable;
	/* 値引割引計算対象 */
	private String calcDiscount;
	/* 免税区分 */
	private String taxFreeDivision;
	/* 免税対象額 */
	private String taxFreeCommodityPrice;
	/* 免税額 */
	private String taxFree;
	/* 商品バンドルグループID */
	private String productBundleGroupId;
	/* 小計値引き按分 */
	private String discountPriceProportional;
	/* ポイント値引き按分 */
	private String discountPointProportional;
	/* クーポン値引き按分 */
	private String discountCouponProportional;
	/* 内税按分 */
	private String taxIncludeProportional;
	/* 外税按分 */
	private String taxExcludeProportional;
	/* 商品バンドル値引按分 */
	private String productBundleProportional;
	/* 社員値引き按分 */
	private String staffDiscountProportional;
	/* セール値引き按分 */
	private String bargainDiscountProportional;
	/* 端数値引額按分 */
	private String roundingPriceProportional;
	/* 商品毎の社員割引率 */
	private String productStaffDiscountRate;
	/* 社員ランクコード */
	private String staffRank;
	/* 社員ランク名 */
	private String staffRankName;
	/* 社員販売割引率 */
	private String staffDiscountRate;
	/* 社員販売割引区分 */
	private String staffDiscountDivision;
	/* 適用社員販売割引率 */
	private String applyStaffDiscountRate;
	/* 適用社員値引き額 */
	private String applyStaffDiscountPrice;
	/* セールID */
	private String bargainId;
	/* セール名称 */
	private String bargainName;
	/* セール区分 */
	private String bargainDivision;
	/* セール値 */
	private String bargainValue;
	/* 適用セール値 */
	private String applyBargainValue;
	/* 適用セール値引き額 */
	private String applyBargainDiscountPrice;
	/* 適用税率 */
	private String taxRate;
	/* 標準税率 */
	private String standardTaxRate;
	/* 修正税率 */
	private String modifiedTaxRate;
	/* 軽減税率ID（税設定） */
	private String reduceTaxId;
	/* 軽減税率名 */
	private String reduceTaxName;
	/* 軽減税率 */
	private String reduceTaxRate;
	/* 軽減税率用商品単価 */
	private String reduceTaxPrice;
	/* 軽減税率用会員商品単価 */
	private String reduceTaxMemberPrice;
	/* 返品販売時などのメモなどを設定 */
	private String memo;
	/* 取引時の商品属性情報 */
	private List<ProductAttributes> productAttributes; 
	/* RFIDタグリスト */
	private List<String> rfidTags;
	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getTransactionDetailId() {
		return transactionDetailId;
	}
	public void setTransactionDetailId(String transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
	}
	public String getParentTransactionDetailId() {
		return parentTransactionDetailId;
	}
	public void setParentTransactionDetailId(String parentTransactionDetailId) {
		this.parentTransactionDetailId = parentTransactionDetailId;
	}
	public String getTransactionDetailDivision() {
		return transactionDetailDivision;
	}
	public void setTransactionDetailDivision(String transactionDetailDivision) {
		this.transactionDetailDivision = transactionDetailDivision;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrintReceiptProductName() {
		return printReceiptProductName;
	}
	public void setPrintReceiptProductName(String printReceiptProductName) {
		this.printReceiptProductName = printReceiptProductName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getTaxDivision() {
		return taxDivision;
	}
	public void setTaxDivision(String taxDivision) {
		this.taxDivision = taxDivision;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}
	public String getUnitDiscountPrice() {
		return unitDiscountPrice;
	}
	public void setUnitDiscountPrice(String unitDiscountPrice) {
		this.unitDiscountPrice = unitDiscountPrice;
	}
	public String getUnitDiscountRate() {
		return unitDiscountRate;
	}
	public void setUnitDiscountRate(String unitDiscountRate) {
		this.unitDiscountRate = unitDiscountRate;
	}
	public String getUnitDiscountDivision() {
		return unitDiscountDivision;
	}
	public void setUnitDiscountDivision(String unitDiscountDivision) {
		this.unitDiscountDivision = unitDiscountDivision;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitNonDiscountSum() {
		return unitNonDiscountSum;
	}
	public void setUnitNonDiscountSum(String unitNonDiscountSum) {
		this.unitNonDiscountSum = unitNonDiscountSum;
	}
	public String getUnitDiscountSum() {
		return unitDiscountSum;
	}
	public void setUnitDiscountSum(String unitDiscountSum) {
		this.unitDiscountSum = unitDiscountSum;
	}
	public String getUnitDiscountedSum() {
		return unitDiscountedSum;
	}
	public void setUnitDiscountedSum(String unitDiscountedSum) {
		this.unitDiscountedSum = unitDiscountedSum;
	}
	public String getCostSum() {
		return costSum;
	}
	public void setCostSum(String costSum) {
		this.costSum = costSum;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDiscriminationNo() {
		return discriminationNo;
	}
	public void setDiscriminationNo(String discriminationNo) {
		this.discriminationNo = discriminationNo;
	}
	public String getSalesDivision() {
		return salesDivision;
	}
	public void setSalesDivision(String salesDivision) {
		this.salesDivision = salesDivision;
	}
	public String getProductDivision() {
		return productDivision;
	}
	public void setProductDivision(String productDivision) {
		this.productDivision = productDivision;
	}
	public String getInventoryReservationDivision() {
		return inventoryReservationDivision;
	}
	public void setInventoryReservationDivision(String inventoryReservationDivision) {
		this.inventoryReservationDivision = inventoryReservationDivision;
	}
	public String getPointNotApplicable() {
		return pointNotApplicable;
	}
	public void setPointNotApplicable(String pointNotApplicable) {
		this.pointNotApplicable = pointNotApplicable;
	}
	public String getCalcDiscount() {
		return calcDiscount;
	}
	public void setCalcDiscount(String calcDiscount) {
		this.calcDiscount = calcDiscount;
	}
	public String getTaxFreeDivision() {
		return taxFreeDivision;
	}
	public void setTaxFreeDivision(String taxFreeDivision) {
		this.taxFreeDivision = taxFreeDivision;
	}
	public String getTaxFreeCommodityPrice() {
		return taxFreeCommodityPrice;
	}
	public void setTaxFreeCommodityPrice(String taxFreeCommodityPrice) {
		this.taxFreeCommodityPrice = taxFreeCommodityPrice;
	}
	public String getTaxFree() {
		return taxFree;
	}
	public void setTaxFree(String taxFree) {
		this.taxFree = taxFree;
	}
	public String getProductBundleGroupId() {
		return productBundleGroupId;
	}
	public void setProductBundleGroupId(String productBundleGroupId) {
		this.productBundleGroupId = productBundleGroupId;
	}
	public String getDiscountPriceProportional() {
		return discountPriceProportional;
	}
	public void setDiscountPriceProportional(String discountPriceProportional) {
		this.discountPriceProportional = discountPriceProportional;
	}
	public String getDiscountPointProportional() {
		return discountPointProportional;
	}
	public void setDiscountPointProportional(String discountPointProportional) {
		this.discountPointProportional = discountPointProportional;
	}
	public String getDiscountCouponProportional() {
		return discountCouponProportional;
	}
	public void setDiscountCouponProportional(String discountCouponProportional) {
		this.discountCouponProportional = discountCouponProportional;
	}
	public String getTaxIncludeProportional() {
		return taxIncludeProportional;
	}
	public void setTaxIncludeProportional(String taxIncludeProportional) {
		this.taxIncludeProportional = taxIncludeProportional;
	}
	public String getTaxExcludeProportional() {
		return taxExcludeProportional;
	}
	public void setTaxExcludeProportional(String taxExcludeProportional) {
		this.taxExcludeProportional = taxExcludeProportional;
	}
	public String getProductBundleProportional() {
		return productBundleProportional;
	}
	public void setProductBundleProportional(String productBundleProportional) {
		this.productBundleProportional = productBundleProportional;
	}
	public String getStaffDiscountProportional() {
		return staffDiscountProportional;
	}
	public void setStaffDiscountProportional(String staffDiscountProportional) {
		this.staffDiscountProportional = staffDiscountProportional;
	}
	public String getBargainDiscountProportional() {
		return bargainDiscountProportional;
	}
	public void setBargainDiscountProportional(String bargainDiscountProportional) {
		this.bargainDiscountProportional = bargainDiscountProportional;
	}
	public String getRoundingPriceProportional() {
		return roundingPriceProportional;
	}
	public void setRoundingPriceProportional(String roundingPriceProportional) {
		this.roundingPriceProportional = roundingPriceProportional;
	}
	public String getProductStaffDiscountRate() {
		return productStaffDiscountRate;
	}
	public void setProductStaffDiscountRate(String productStaffDiscountRate) {
		this.productStaffDiscountRate = productStaffDiscountRate;
	}
	public String getStaffRank() {
		return staffRank;
	}
	public void setStaffRank(String staffRank) {
		this.staffRank = staffRank;
	}
	public String getStaffRankName() {
		return staffRankName;
	}
	public void setStaffRankName(String staffRankName) {
		this.staffRankName = staffRankName;
	}
	public String getStaffDiscountRate() {
		return staffDiscountRate;
	}
	public void setStaffDiscountRate(String staffDiscountRate) {
		this.staffDiscountRate = staffDiscountRate;
	}
	public String getStaffDiscountDivision() {
		return staffDiscountDivision;
	}
	public void setStaffDiscountDivision(String staffDiscountDivision) {
		this.staffDiscountDivision = staffDiscountDivision;
	}
	public String getApplyStaffDiscountRate() {
		return applyStaffDiscountRate;
	}
	public void setApplyStaffDiscountRate(String applyStaffDiscountRate) {
		this.applyStaffDiscountRate = applyStaffDiscountRate;
	}
	public String getApplyStaffDiscountPrice() {
		return applyStaffDiscountPrice;
	}
	public void setApplyStaffDiscountPrice(String applyStaffDiscountPrice) {
		this.applyStaffDiscountPrice = applyStaffDiscountPrice;
	}
	public String getBargainId() {
		return bargainId;
	}
	public void setBargainId(String bargainId) {
		this.bargainId = bargainId;
	}
	public String getBargainName() {
		return bargainName;
	}
	public void setBargainName(String bargainName) {
		this.bargainName = bargainName;
	}
	public String getBargainDivision() {
		return bargainDivision;
	}
	public void setBargainDivision(String bargainDivision) {
		this.bargainDivision = bargainDivision;
	}
	public String getBargainValue() {
		return bargainValue;
	}
	public void setBargainValue(String bargainValue) {
		this.bargainValue = bargainValue;
	}
	public String getApplyBargainValue() {
		return applyBargainValue;
	}
	public void setApplyBargainValue(String applyBargainValue) {
		this.applyBargainValue = applyBargainValue;
	}
	public String getApplyBargainDiscountPrice() {
		return applyBargainDiscountPrice;
	}
	public void setApplyBargainDiscountPrice(String applyBargainDiscountPrice) {
		this.applyBargainDiscountPrice = applyBargainDiscountPrice;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getStandardTaxRate() {
		return standardTaxRate;
	}
	public void setStandardTaxRate(String standardTaxRate) {
		this.standardTaxRate = standardTaxRate;
	}
	public String getModifiedTaxRate() {
		return modifiedTaxRate;
	}
	public void setModifiedTaxRate(String modifiedTaxRate) {
		this.modifiedTaxRate = modifiedTaxRate;
	}
	public String getReduceTaxId() {
		return reduceTaxId;
	}
	public void setReduceTaxId(String reduceTaxId) {
		this.reduceTaxId = reduceTaxId;
	}
	public String getReduceTaxName() {
		return reduceTaxName;
	}
	public void setReduceTaxName(String reduceTaxName) {
		this.reduceTaxName = reduceTaxName;
	}
	public String getReduceTaxRate() {
		return reduceTaxRate;
	}
	public void setReduceTaxRate(String reduceTaxRate) {
		this.reduceTaxRate = reduceTaxRate;
	}
	public String getReduceTaxPrice() {
		return reduceTaxPrice;
	}
	public void setReduceTaxPrice(String reduceTaxPrice) {
		this.reduceTaxPrice = reduceTaxPrice;
	}
	public String getReduceTaxMemberPrice() {
		return reduceTaxMemberPrice;
	}
	public void setReduceTaxMemberPrice(String reduceTaxMemberPrice) {
		this.reduceTaxMemberPrice = reduceTaxMemberPrice;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<ProductAttributes> getProductAttributes() {
		return productAttributes;
	}
	public void setProductAttributes(List<ProductAttributes> productAttributes) {
		this.productAttributes = productAttributes;
	}
	public List<String> getRfidTags() {
		return rfidTags;
	}
	public void setRfidTags(List<String> rfidTags) {
		this.rfidTags = rfidTags;
	} 
}
