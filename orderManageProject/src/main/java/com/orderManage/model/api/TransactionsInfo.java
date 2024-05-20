package com.orderManage.model.api;

import java.util.List;

/**
 * 取引一覧取得 model
 * 
 * スマレジAPI　取引一覧取得時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class TransactionsInfo {

	/* 取引ID */
	private String transactionHeadId;
	/* 取引日時：取引日時。サーバーに送信された日時。販売日時は端末取引日時を参照。[YYYY-MM-DDThh:mm:ssTZD] */
	private String transactionDateTime;
	/* 取引区分 */
	private String transactionHeadDivision;
	/* 取消区分 */
	private String cancelDivision;
	/* 単品値引き前小計 */
	private String unitNonDiscountsubtotal;
	/* 単品値引き小計 */
	private String unitDiscountsubtotal;
	/* 単品社員販売値引き計 */
	private String unitStaffDiscountsubtotal;
	/* 単品セール販売値引き計 */
	private String unitBargainDiscountsubtotal;
	/* 小計 */
	private String subtotal;
	/* 値引対象小計 */
	private String subtotalForDiscount;
	/* 小計値引き */
	private String subtotalDiscountPrice;
	/* 小計割引率 */
	private String subtotalDiscountRate;
	/* 小計値引き/割引区分 */
	private String subtotalDiscountDivision;
	/* ポイント値引き */
	private String pointDiscount;
	/* クーポン値引き */
	private String couponDiscount;
	/* 合計 */
	private String total;
	/* 内税額 */
	private String taxInclude;
	/* 外税額 */
	private String taxExclude;
	/* 端数値引区分 */
	private String roundingDivision;
	/* 端数値引額 */
	private String roundingPrice;
	/* 内現金支払金額 */
	private String cashTotal;
	/* 内クレジット支払金額 */
	private String creditTotal;
	/* 預かり金 */
	private String deposit;
	/* 預かり金現金 */
	private String depositCash;
	/* 預かり金クレジット */
	private String depositCredit;
	/* 釣銭 */
	private String change;
	/* 現金チップ */
	private String tipCash;
	/* クレジットチップ */
	private String tipCredit;
	/* 数量合計 */
	private String amount;
	/* 返品数量合計 */
	private String returnAmount;
	/* 原価合計 */
	private String costTotal;
	/* 売上ヘッダ区分 */
	private String salesHeadDivision;
	/* 内税対象額 */
	private String inTaxSalesTotal;
	/* 外税対象額 */
	private String outTaxSalesTotal;
	/* 非課税対象額 */
	private String nonTaxSalesTotal;
	/* 売上対象外合計 */
	private String nonSalesTargetTotal;
	/* 売上対象外外税合計 */
	private String nonSalesTargetOutTaxTotal;
	/* 売上対象外内税合計 */
	private String nonSalesTargetInTaxTotal;
	/* 売上対象外免税額合計 */
	private String nonSalesTargetTaxFreeTotal;
	/* 売上対象外原価合計 */
	private String nonSalesTargetCostTotal;
	/* 売上対象外数量合計 */
	private String nonSalesTargetAmount;
	/* 売上対象外返品数量合計 */
	private String nonSalesTargetReturnAmount;
	/* 付与ポイント */
	private String newPoint;
	/* 使用ポイント */
	private String spendPoint;
	/* 現在ポイント */
	private String point;
	/* 合計ポイント */
	private String totalPoint;
	/* 現在マイル */
	private String currentMile;
	/* 獲得マイル */
	private String earnMile;
	/* 合計マイル */
	private String totalMile;
	/* 調整マイル */
	private String adjustmentMile;
	/* 調整マイル区分 */
	private String adjustmentMileDivision;
	/* 調整マイル区分値 */
	private String adjustmentMileValue;
	/* 店舗ID */
	private String storeId;
	/* 店舗コード */
	private String storeCode;
	/* 端末ID */
	private String terminalId;
	/* 会員ID */
	private String customerId;
	/* 会員コード */
	private String customerCode;
	/* 端末取引ID */
	private String terminalTranId;
	/* 端末取引日時 */
	private String terminalTranDateTime;
	/* 締め区分 */
	private String sumDivision;
	/* 精算日時 */
	private String adjustmentDateTime;
	/* 締め日 */
	private String sumDate;
	/* 会員ランク */
	private String customerRank;
	/* 客層ID */
	private String customerGroupId;
	/* 客層ID2 */
	private String customerGroupId2;
	/* 客層ID3 */
	private String customerGroupId3;
	/* 客層ID4 */
	private String customerGroupId4;
	/* 客層ID5 */
	private String customerGroupId5;
	/* 販売員ID */
	private String staffId;
	/* 販売員コード */
	private String staffCode;
	/* 販売員名 */
	private String staffName;
	/* クレジット区分 */
	private String creditDivision;
	/* 支払回数 */
	private String paymentCount;
	/* 伝票番号 */
	private String slipNumber;
	/* 取消伝票番号 */
	private String cancelSlipNumber;
	/* 承認番号 */
	private String authNumber;
	/* センター処理日 */
	private String authDate;
	/* 取扱カード会社 */
	private String cardCompany;
	/* 金種 */
	private String denomination;
	/* メモ */
	private String memo;
	/* レシートメモ */
	private String receiptMemo;
	/* 送料 */
	private String carriage;
	/* 手数料 */
	private String commission;
	/* 客数 */
	private String guestNumbers;
	/* 客数（男） */
	private String guestNumbersMale;
	/* 客数（女） */
	private String guestNumbersFemale;
	/* 客数（不明） */
	private String guestNumbersUnknown;
	/* 入店日時：[YYYY-MM-DDThh:mm:ssTZD] */
	private String enterDateTime;
	/* 免税販売区分 */
	private String taxFreeSalesDivision;
	/* 内税商品の一般品免税額 */
	private String netTaxFreeGeneralTaxInclude;
	/* 外税商品の一般品免税額 */
	private String netTaxFreeGeneralTaxExclude;
	/* 内税商品の消耗品免税額 */
	private String netTaxFreeConsumableTaxInclude;
	/* 外税商品の消耗品免税額 */
	private String netTaxFreeConsumableTaxExclude;
	/* 取引タグ */
	private String tags;
	/* ポイント付与区分 */
	private String pointGivingDivision;
	/* ポイント付与単位(金額) */
	private String pointGivingUnitPrice;
	/* ポイント付与単位(ポイント) */
	private String pointGivingUnit;
	/* ポイント利用区分 */
	private String pointSpendDivision;
	/* マイレージ利用区分 */
	private String mileageDivision;
	/* マイレージ名称 */
	private String mileageLabel;
	/* PINコード */
	private String customerPinCode;
	/* 返品取引区分 */
	private String returnSales;
	/* 打消区分 */
	private String disposeDivision;
	/* 打消元取引ID */
	private String disposeServerTransactionHeadId;
	/* 取消日時 */
	private String cancelDateTime;
	/* 販売区分 */
	private String sellDivision;
	/* 税率 */
	private String taxRate;
	/* 税の丸め方式 */
	private String taxRounding;
	/* 割引丸め区分 */
	private String discountRoundingDivision;
	/* レシート番号 */
	private String transactionUuid;
	/* 引換券番号 */
	private String exchangeTicketNo;
	/* ギフトレシート有効日数 */
	private String giftReceiptValidDays;
	/* 仮販売のバーコード */
	private String barcode;
	/* 更新日時 */
	private String updDateTime;
	/* 取引詳細 */
	private List<Details> details;
	/* その他支払方法 */
	private List<DepositOthers> depositOthers; 
	/* 取置き時の詳細情報 */
	private List<Layaways> layaways; 
	/* 取置き引取時の詳細情報 */
	private List<LayawayPickUps> layawayPickUps; 
	/* 取引金操作情報 */
	private MoneyControl moneyControl;

	public String getTransactionHeadId() {
		return transactionHeadId;
	}
	public void setTransactionHeadId(String transactionHeadId) {
		this.transactionHeadId = transactionHeadId;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getTransactionHeadDivision() {
		return transactionHeadDivision;
	}
	public void setTransactionHeadDivision(String transactionHeadDivision) {
		this.transactionHeadDivision = transactionHeadDivision;
	}
	public String getCancelDivision() {
		return cancelDivision;
	}
	public void setCancelDivision(String cancelDivision) {
		this.cancelDivision = cancelDivision;
	}
	public String getUnitNonDiscountsubtotal() {
		return unitNonDiscountsubtotal;
	}
	public void setUnitNonDiscountsubtotal(String unitNonDiscountsubtotal) {
		this.unitNonDiscountsubtotal = unitNonDiscountsubtotal;
	}
	public String getUnitDiscountsubtotal() {
		return unitDiscountsubtotal;
	}
	public void setUnitDiscountsubtotal(String unitDiscountsubtotal) {
		this.unitDiscountsubtotal = unitDiscountsubtotal;
	}
	public String getUnitStaffDiscountsubtotal() {
		return unitStaffDiscountsubtotal;
	}
	public void setUnitStaffDiscountsubtotal(String unitStaffDiscountsubtotal) {
		this.unitStaffDiscountsubtotal = unitStaffDiscountsubtotal;
	}
	public String getUnitBargainDiscountsubtotal() {
		return unitBargainDiscountsubtotal;
	}
	public void setUnitBargainDiscountsubtotal(String unitBargainDiscountsubtotal) {
		this.unitBargainDiscountsubtotal = unitBargainDiscountsubtotal;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getSubtotalForDiscount() {
		return subtotalForDiscount;
	}
	public void setSubtotalForDiscount(String subtotalForDiscount) {
		this.subtotalForDiscount = subtotalForDiscount;
	}
	public String getSubtotalDiscountPrice() {
		return subtotalDiscountPrice;
	}
	public void setSubtotalDiscountPrice(String subtotalDiscountPrice) {
		this.subtotalDiscountPrice = subtotalDiscountPrice;
	}
	public String getSubtotalDiscountRate() {
		return subtotalDiscountRate;
	}
	public void setSubtotalDiscountRate(String subtotalDiscountRate) {
		this.subtotalDiscountRate = subtotalDiscountRate;
	}
	public String getSubtotalDiscountDivision() {
		return subtotalDiscountDivision;
	}
	public void setSubtotalDiscountDivision(String subtotalDiscountDivision) {
		this.subtotalDiscountDivision = subtotalDiscountDivision;
	}
	public String getPointDiscount() {
		return pointDiscount;
	}
	public void setPointDiscount(String pointDiscount) {
		this.pointDiscount = pointDiscount;
	}
	public String getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(String couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTaxInclude() {
		return taxInclude;
	}
	public void setTaxInclude(String taxInclude) {
		this.taxInclude = taxInclude;
	}
	public String getTaxExclude() {
		return taxExclude;
	}
	public void setTaxExclude(String taxExclude) {
		this.taxExclude = taxExclude;
	}
	public String getRoundingDivision() {
		return roundingDivision;
	}
	public void setRoundingDivision(String roundingDivision) {
		this.roundingDivision = roundingDivision;
	}
	public String getRoundingPrice() {
		return roundingPrice;
	}
	public void setRoundingPrice(String roundingPrice) {
		this.roundingPrice = roundingPrice;
	}
	public String getCashTotal() {
		return cashTotal;
	}
	public void setCashTotal(String cashTotal) {
		this.cashTotal = cashTotal;
	}
	public String getCreditTotal() {
		return creditTotal;
	}
	public void setCreditTotal(String creditTotal) {
		this.creditTotal = creditTotal;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getDepositCash() {
		return depositCash;
	}
	public void setDepositCash(String depositCash) {
		this.depositCash = depositCash;
	}
	public String getDepositCredit() {
		return depositCredit;
	}
	public void setDepositCredit(String depositCredit) {
		this.depositCredit = depositCredit;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getTipCash() {
		return tipCash;
	}
	public void setTipCash(String tipCash) {
		this.tipCash = tipCash;
	}
	public String getTipCredit() {
		return tipCredit;
	}
	public void setTipCredit(String tipCredit) {
		this.tipCredit = tipCredit;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}
	public String getCostTotal() {
		return costTotal;
	}
	public void setCostTotal(String costTotal) {
		this.costTotal = costTotal;
	}
	public String getSalesHeadDivision() {
		return salesHeadDivision;
	}
	public void setSalesHeadDivision(String salesHeadDivision) {
		this.salesHeadDivision = salesHeadDivision;
	}
	public String getInTaxSalesTotal() {
		return inTaxSalesTotal;
	}
	public void setInTaxSalesTotal(String inTaxSalesTotal) {
		this.inTaxSalesTotal = inTaxSalesTotal;
	}
	public String getOutTaxSalesTotal() {
		return outTaxSalesTotal;
	}
	public void setOutTaxSalesTotal(String outTaxSalesTotal) {
		this.outTaxSalesTotal = outTaxSalesTotal;
	}
	public String getNonTaxSalesTotal() {
		return nonTaxSalesTotal;
	}
	public void setNonTaxSalesTotal(String nonTaxSalesTotal) {
		this.nonTaxSalesTotal = nonTaxSalesTotal;
	}
	public String getNonSalesTargetTotal() {
		return nonSalesTargetTotal;
	}
	public void setNonSalesTargetTotal(String nonSalesTargetTotal) {
		this.nonSalesTargetTotal = nonSalesTargetTotal;
	}
	public String getNonSalesTargetOutTaxTotal() {
		return nonSalesTargetOutTaxTotal;
	}
	public void setNonSalesTargetOutTaxTotal(String nonSalesTargetOutTaxTotal) {
		this.nonSalesTargetOutTaxTotal = nonSalesTargetOutTaxTotal;
	}
	public String getNonSalesTargetInTaxTotal() {
		return nonSalesTargetInTaxTotal;
	}
	public void setNonSalesTargetInTaxTotal(String nonSalesTargetInTaxTotal) {
		this.nonSalesTargetInTaxTotal = nonSalesTargetInTaxTotal;
	}
	public String getNonSalesTargetTaxFreeTotal() {
		return nonSalesTargetTaxFreeTotal;
	}
	public void setNonSalesTargetTaxFreeTotal(String nonSalesTargetTaxFreeTotal) {
		this.nonSalesTargetTaxFreeTotal = nonSalesTargetTaxFreeTotal;
	}
	public String getNonSalesTargetCostTotal() {
		return nonSalesTargetCostTotal;
	}
	public void setNonSalesTargetCostTotal(String nonSalesTargetCostTotal) {
		this.nonSalesTargetCostTotal = nonSalesTargetCostTotal;
	}
	public String getNonSalesTargetAmount() {
		return nonSalesTargetAmount;
	}
	public void setNonSalesTargetAmount(String nonSalesTargetAmount) {
		this.nonSalesTargetAmount = nonSalesTargetAmount;
	}
	public String getNonSalesTargetReturnAmount() {
		return nonSalesTargetReturnAmount;
	}
	public void setNonSalesTargetReturnAmount(String nonSalesTargetReturnAmount) {
		this.nonSalesTargetReturnAmount = nonSalesTargetReturnAmount;
	}
	public String getNewPoint() {
		return newPoint;
	}
	public void setNewPoint(String newPoint) {
		this.newPoint = newPoint;
	}
	public String getSpendPoint() {
		return spendPoint;
	}
	public void setSpendPoint(String spendPoint) {
		this.spendPoint = spendPoint;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
	public String getCurrentMile() {
		return currentMile;
	}
	public void setCurrentMile(String currentMile) {
		this.currentMile = currentMile;
	}
	public String getEarnMile() {
		return earnMile;
	}
	public void setEarnMile(String earnMile) {
		this.earnMile = earnMile;
	}
	public String getTotalMile() {
		return totalMile;
	}
	public void setTotalMile(String totalMile) {
		this.totalMile = totalMile;
	}
	public String getAdjustmentMile() {
		return adjustmentMile;
	}
	public void setAdjustmentMile(String adjustmentMile) {
		this.adjustmentMile = adjustmentMile;
	}
	public String getAdjustmentMileDivision() {
		return adjustmentMileDivision;
	}
	public void setAdjustmentMileDivision(String adjustmentMileDivision) {
		this.adjustmentMileDivision = adjustmentMileDivision;
	}
	public String getAdjustmentMileValue() {
		return adjustmentMileValue;
	}
	public void setAdjustmentMileValue(String adjustmentMileValue) {
		this.adjustmentMileValue = adjustmentMileValue;
	}
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
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getTerminalTranId() {
		return terminalTranId;
	}
	public void setTerminalTranId(String terminalTranId) {
		this.terminalTranId = terminalTranId;
	}
	public String getTerminalTranDateTime() {
		return terminalTranDateTime;
	}
	public void setTerminalTranDateTime(String terminalTranDateTime) {
		this.terminalTranDateTime = terminalTranDateTime;
	}
	public String getSumDivision() {
		return sumDivision;
	}
	public void setSumDivision(String sumDivision) {
		this.sumDivision = sumDivision;
	}
	public String getAdjustmentDateTime() {
		return adjustmentDateTime;
	}
	public void setAdjustmentDateTime(String adjustmentDateTime) {
		this.adjustmentDateTime = adjustmentDateTime;
	}
	public String getSumDate() {
		return sumDate;
	}
	public void setSumDate(String sumDate) {
		this.sumDate = sumDate;
	}
	public String getCustomerRank() {
		return customerRank;
	}
	public void setCustomerRank(String customerRank) {
		this.customerRank = customerRank;
	}
	public String getCustomerGroupId() {
		return customerGroupId;
	}
	public void setCustomerGroupId(String customerGroupId) {
		this.customerGroupId = customerGroupId;
	}
	public String getCustomerGroupId2() {
		return customerGroupId2;
	}
	public void setCustomerGroupId2(String customerGroupId2) {
		this.customerGroupId2 = customerGroupId2;
	}
	public String getCustomerGroupId3() {
		return customerGroupId3;
	}
	public void setCustomerGroupId3(String customerGroupId3) {
		this.customerGroupId3 = customerGroupId3;
	}
	public String getCustomerGroupId4() {
		return customerGroupId4;
	}
	public void setCustomerGroupId4(String customerGroupId4) {
		this.customerGroupId4 = customerGroupId4;
	}
	public String getCustomerGroupId5() {
		return customerGroupId5;
	}
	public void setCustomerGroupId5(String customerGroupId5) {
		this.customerGroupId5 = customerGroupId5;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getCreditDivision() {
		return creditDivision;
	}
	public void setCreditDivision(String creditDivision) {
		this.creditDivision = creditDivision;
	}
	public String getPaymentCount() {
		return paymentCount;
	}
	public void setPaymentCount(String paymentCount) {
		this.paymentCount = paymentCount;
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
	public String getAuthNumber() {
		return authNumber;
	}
	public void setAuthNumber(String authNumber) {
		this.authNumber = authNumber;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getCardCompany() {
		return cardCompany;
	}
	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReceiptMemo() {
		return receiptMemo;
	}
	public void setReceiptMemo(String receiptMemo) {
		this.receiptMemo = receiptMemo;
	}
	public String getCarriage() {
		return carriage;
	}
	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getGuestNumbers() {
		return guestNumbers;
	}
	public void setGuestNumbers(String guestNumbers) {
		this.guestNumbers = guestNumbers;
	}
	public String getGuestNumbersMale() {
		return guestNumbersMale;
	}
	public void setGuestNumbersMale(String guestNumbersMale) {
		this.guestNumbersMale = guestNumbersMale;
	}
	public String getGuestNumbersFemale() {
		return guestNumbersFemale;
	}
	public void setGuestNumbersFemale(String guestNumbersFemale) {
		this.guestNumbersFemale = guestNumbersFemale;
	}
	public String getGuestNumbersUnknown() {
		return guestNumbersUnknown;
	}
	public void setGuestNumbersUnknown(String guestNumbersUnknown) {
		this.guestNumbersUnknown = guestNumbersUnknown;
	}
	public String getEnterDateTime() {
		return enterDateTime;
	}
	public void setEnterDateTime(String enterDateTime) {
		this.enterDateTime = enterDateTime;
	}
	public String getTaxFreeSalesDivision() {
		return taxFreeSalesDivision;
	}
	public void setTaxFreeSalesDivision(String taxFreeSalesDivision) {
		this.taxFreeSalesDivision = taxFreeSalesDivision;
	}
	public String getNetTaxFreeGeneralTaxInclude() {
		return netTaxFreeGeneralTaxInclude;
	}
	public void setNetTaxFreeGeneralTaxInclude(String netTaxFreeGeneralTaxInclude) {
		this.netTaxFreeGeneralTaxInclude = netTaxFreeGeneralTaxInclude;
	}
	public String getNetTaxFreeGeneralTaxExclude() {
		return netTaxFreeGeneralTaxExclude;
	}
	public void setNetTaxFreeGeneralTaxExclude(String netTaxFreeGeneralTaxExclude) {
		this.netTaxFreeGeneralTaxExclude = netTaxFreeGeneralTaxExclude;
	}
	public String getNetTaxFreeConsumableTaxInclude() {
		return netTaxFreeConsumableTaxInclude;
	}
	public void setNetTaxFreeConsumableTaxInclude(String netTaxFreeConsumableTaxInclude) {
		this.netTaxFreeConsumableTaxInclude = netTaxFreeConsumableTaxInclude;
	}
	public String getNetTaxFreeConsumableTaxExclude() {
		return netTaxFreeConsumableTaxExclude;
	}
	public void setNetTaxFreeConsumableTaxExclude(String netTaxFreeConsumableTaxExclude) {
		this.netTaxFreeConsumableTaxExclude = netTaxFreeConsumableTaxExclude;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getPointGivingDivision() {
		return pointGivingDivision;
	}
	public void setPointGivingDivision(String pointGivingDivision) {
		this.pointGivingDivision = pointGivingDivision;
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
	public String getPointSpendDivision() {
		return pointSpendDivision;
	}
	public void setPointSpendDivision(String pointSpendDivision) {
		this.pointSpendDivision = pointSpendDivision;
	}
	public String getMileageDivision() {
		return mileageDivision;
	}
	public void setMileageDivision(String mileageDivision) {
		this.mileageDivision = mileageDivision;
	}
	public String getMileageLabel() {
		return mileageLabel;
	}
	public void setMileageLabel(String mileageLabel) {
		this.mileageLabel = mileageLabel;
	}
	public String getCustomerPinCode() {
		return customerPinCode;
	}
	public void setCustomerPinCode(String customerPinCode) {
		this.customerPinCode = customerPinCode;
	}
	public String getReturnSales() {
		return returnSales;
	}
	public void setReturnSales(String returnSales) {
		this.returnSales = returnSales;
	}
	public String getDisposeDivision() {
		return disposeDivision;
	}
	public void setDisposeDivision(String disposeDivision) {
		this.disposeDivision = disposeDivision;
	}
	public String getDisposeServerTransactionHeadId() {
		return disposeServerTransactionHeadId;
	}
	public void setDisposeServerTransactionHeadId(String disposeServerTransactionHeadId) {
		this.disposeServerTransactionHeadId = disposeServerTransactionHeadId;
	}
	public String getCancelDateTime() {
		return cancelDateTime;
	}
	public void setCancelDateTime(String cancelDateTime) {
		this.cancelDateTime = cancelDateTime;
	}
	public String getSellDivision() {
		return sellDivision;
	}
	public void setSellDivision(String sellDivision) {
		this.sellDivision = sellDivision;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getTaxRounding() {
		return taxRounding;
	}
	public void setTaxRounding(String taxRounding) {
		this.taxRounding = taxRounding;
	}
	public String getDiscountRoundingDivision() {
		return discountRoundingDivision;
	}
	public void setDiscountRoundingDivision(String discountRoundingDivision) {
		this.discountRoundingDivision = discountRoundingDivision;
	}
	public String getTransactionUuid() {
		return transactionUuid;
	}
	public void setTransactionUuid(String transactionUuid) {
		this.transactionUuid = transactionUuid;
	}
	public String getExchangeTicketNo() {
		return exchangeTicketNo;
	}
	public void setExchangeTicketNo(String exchangeTicketNo) {
		this.exchangeTicketNo = exchangeTicketNo;
	}
	public String getGiftReceiptValidDays() {
		return giftReceiptValidDays;
	}
	public void setGiftReceiptValidDays(String giftReceiptValidDays) {
		this.giftReceiptValidDays = giftReceiptValidDays;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getUpdDateTime() {
		return updDateTime;
	}
	public void setUpdDateTime(String updDateTime) {
		this.updDateTime = updDateTime;
	}
	public List<Details> getDetails() {
		return details;
	}
	public void setDetails(List<Details> details) {
		this.details = details;
	}
	public List<DepositOthers> getDepositOthers() {
		return depositOthers;
	}
	public void setDepositOthers(List<DepositOthers> depositOthers) {
		this.depositOthers = depositOthers;
	}
	public List<Layaways> getLayaways() {
		return layaways;
	}
	public void setLayaways(List<Layaways> layaways) {
		this.layaways = layaways;
	}
	public List<LayawayPickUps> getLayawayPickUps() {
		return layawayPickUps;
	}
	public void setLayawayPickUps(List<LayawayPickUps> layawayPickUps) {
		this.layawayPickUps = layawayPickUps;
	}
	public MoneyControl getMoneyControl() {
		return moneyControl;
	}
	public void setMoneyControl(MoneyControl moneyControl) {
		this.moneyControl = moneyControl;
	} 
}
