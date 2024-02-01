package com.orderManage.model.api;

import java.util.List;

/**
 * 商品一覧情報
 * 
 * スマレジAPI　商品一覧取得、商品取得時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class ProductsInfo {
	
	/* 商品ID */
	private String productId ;
	/* 部門ID */
	private String categoryId ;
	/* 商品コード */
	private String productCode ;
	/* 商品名 */
	private String productName ;
	/* 商品カナ */
	private String productKana ;
	/* 税区分：商品価格の消費税の扱いに関する設定。 （0:内税、1:外税、2:非課税） */
	private String taxDivision ;
	/* 商品価格区分：商品をオープン価格販売するかどうか設定。未設定の場合1を設定。 （1:通常価格、2:オープン価格） */
	private String productPriceDivision ;
	/* 商品単価 */
	private String price ;
	/* 会員価格 */
	private String customerPrice ;
	/* 原価 */
	private String cost ;
	/* 規格 */
	private String attribute ;
	/* 説明 */
	private String description ;
	/* キャッチコピー */
	private String catchCopy ;
	/* サイズ */
	private String size ;
	/* カラー */
	private String color ;
	/* タグ */
	private String tag ;
	/* グループコード　関連商品として紐付ける為のグループコードを設定。 例えば、AとBの商品に同じグループコード001を設定すると、
	 * AとBは関連商品となります。サイズ、カラーが異なる場合などに使用してください。 */
	private String groupCode ;
	/* URL */
	private String url ;
	/* レシート印字商品名 */
	private String printReceiptProductName ;
	/* 表示順 */
	private String displaySequence ;
	/* 売上区分：取引時に売上計上するか否かの設定。 （0:売上対象、1:売上対象外） */
	private String salesDivision ;
	/* 在庫管理区分：棚卸対象商品とするか否かの設定。 （0:在庫管理対象、1:在庫管理対象外） */
	private String stockControlDivision ;
	/* 端末表示：スマレジ端末に表示するか否かの判定。未設定の場合1を設定。 (0：表示しない、1：表示する) */
	private String displayFlag ;
	/* 商品区分：商品の種類を設定。未設定の場合0を設定。 (0：通常商品、1：回数券(*1)、2：オプション商品) */
	private String division ;
	/* オプショングループID：オプショングループの設定方法が「全店舗共通」の場合、適用可能なオプショングループがあればそのIDがこちらに設定されます。無ければnullが設定されます。 */
	private String productOptionGroupId ;
	/* ポイント対象：ポイント対象にするか否かの設定（0:ポイント対象、1:ポイント対象外） */
	private String pointNotApplicable ;
	/* 免税区分：免税区分の設定。未設定の場合0を設定。 (0：対象外、1：一般品、2：消耗品) */
	private String taxFreeDivision ;
	/* 品番 */
	private String supplierProductNo ;
	/* 値引割引計算対象：小計値引／割引(クーポン値引、ポイント値引き含む)の対象かどうかを設定 （0:対象外、1:対象）未設定の場合、1:対象 を設定。 */
	private String calcDiscount ;
	/* 社員販売割引率 */
	private String staffDiscountRate ;
	/* 部門の税設定を使用：商品に設定した税設定を使用するか、その商品に設定した部門の税設定を使用するかを選択。
	 * （0:商品の税設定を使用、1:部門の税設定を使用） */
	private String useCategoryReduceTax ;
	/* 軽減税率ID（税設定）：軽減税率ID。 軽減税率設定画面で設定した軽減税率ID、または、下記の軽減税率ID。標準税率の場合はnull。
	 * 軽減：10000001 (特定商品の軽減税率適用）
	 * 選択[標準]：10000002（状態による適用[適用しない]）
	 * 選択[軽減]：10000003（状態による適用[適用する]）
	 * 選択[選択]：10000004（状態による適用[都度選択する]）
	 * ※部門の税設定を使用する場合、所属部門の軽減税率ID。 */
	private String reduceTaxId ;
	/* 軽減税率用商品単価 */
	private String reduceTaxPrice ;
	/* 軽減税率用商品会員単価 */
	private String reduceTaxCustomerPrice ;
	/* 発注点 */
	private String orderPoint ;
	/* 仕入原価： */
	private String purchaseCost ;
	/* 適用開始日時[YYYY-MM-DD] */
	private String appStartDateTime ;
	/* 作成日時：登録時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String insDateTime ;
	/* 更新日時：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String updDateTime ;
	/* 商品自由項目 (発注登録時使用) */
	private List<ReserveItems> reserveItems;
	/* 商品価格 (発注登録時使用) */
	private List<Prices> prices;
	/* 商品取扱店舗 (発注登録時使用) */
	private List<Stores> stores;
	/* 在庫引当商品 (発注登録時使用) */
	private List<InventoryReservations> inventoryReservations;
	/* 商品属性項目 (発注登録時使用) */
	private List<AttributeItems> attributeItems;
	/* 発注設定 (発注登録時使用) */
	private OrderSetting orderSetting;

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	public String getProductKana() {
		return productKana;
	}
	public void setProductKana(String productKana) {
		this.productKana = productKana;
	}
	public String getTaxDivision() {
		return taxDivision;
	}
	public void setTaxDivision(String taxDivision) {
		this.taxDivision = taxDivision;
	}
	public String getProductPriceDivision() {
		return productPriceDivision;
	}
	public void setProductPriceDivision(String productPriceDivision) {
		this.productPriceDivision = productPriceDivision;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCustomerPrice() {
		return customerPrice;
	}
	public void setCustomerPrice(String customerPrice) {
		this.customerPrice = customerPrice;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCatchCopy() {
		return catchCopy;
	}
	public void setCatchCopy(String catchCopy) {
		this.catchCopy = catchCopy;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrintReceiptProductName() {
		return printReceiptProductName;
	}
	public void setPrintReceiptProductName(String printReceiptProductName) {
		this.printReceiptProductName = printReceiptProductName;
	}
	public String getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(String displaySequence) {
		this.displaySequence = displaySequence;
	}
	public String getSalesDivision() {
		return salesDivision;
	}
	public void setSalesDivision(String salesDivision) {
		this.salesDivision = salesDivision;
	}
	public String getStockControlDivision() {
		return stockControlDivision;
	}
	public void setStockControlDivision(String stockControlDivision) {
		this.stockControlDivision = stockControlDivision;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getProductOptionGroupId() {
		return productOptionGroupId;
	}
	public void setProductOptionGroupId(String productOptionGroupId) {
		this.productOptionGroupId = productOptionGroupId;
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
	public String getSupplierProductNo() {
		return supplierProductNo;
	}
	public void setSupplierProductNo(String supplierProductNo) {
		this.supplierProductNo = supplierProductNo;
	}
	public String getCalcDiscount() {
		return calcDiscount;
	}
	public void setCalcDiscount(String calcDiscount) {
		this.calcDiscount = calcDiscount;
	}
	public String getStaffDiscountRate() {
		return staffDiscountRate;
	}
	public void setStaffDiscountRate(String staffDiscountRate) {
		this.staffDiscountRate = staffDiscountRate;
	}
	public String getUseCategoryReduceTax() {
		return useCategoryReduceTax;
	}
	public void setUseCategoryReduceTax(String useCategoryReduceTax) {
		this.useCategoryReduceTax = useCategoryReduceTax;
	}
	public String getReduceTaxId() {
		return reduceTaxId;
	}
	public void setReduceTaxId(String reduceTaxId) {
		this.reduceTaxId = reduceTaxId;
	}
	public String getReduceTaxPrice() {
		return reduceTaxPrice;
	}
	public void setReduceTaxPrice(String reduceTaxPrice) {
		this.reduceTaxPrice = reduceTaxPrice;
	}
	public String getReduceTaxCustomerPrice() {
		return reduceTaxCustomerPrice;
	}
	public void setReduceTaxCustomerPrice(String reduceTaxCustomerPrice) {
		this.reduceTaxCustomerPrice = reduceTaxCustomerPrice;
	}
	public String getOrderPoint() {
		return orderPoint;
	}
	public void setOrderPoint(String orderPoint) {
		this.orderPoint = orderPoint;
	}
	public String getPurchaseCost() {
		return purchaseCost;
	}
	public void setPurchaseCost(String purchaseCost) {
		this.purchaseCost = purchaseCost;
	}
	public String getAppStartDateTime() {
		return appStartDateTime;
	}
	public void setAppStartDateTime(String appStartDateTime) {
		this.appStartDateTime = appStartDateTime;
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
	public List<ReserveItems> getReserveItems() {
		return reserveItems;
	}
	public void setReserveItems(List<ReserveItems> reserveItems) {
		this.reserveItems = reserveItems;
	}
	public List<Prices> getPrices() {
		return prices;
	}
	public void setPrices(List<Prices> prices) {
		this.prices = prices;
	}
	public List<Stores> getStores() {
		return stores;
	}
	public void setStores(List<Stores> stores) {
		this.stores = stores;
	}
	public List<InventoryReservations> getInventoryReservations() {
		return inventoryReservations;
	}
	public void setInventoryReservations(List<InventoryReservations> inventoryReservations) {
		this.inventoryReservations = inventoryReservations;
	}
	public List<AttributeItems> getAttributeItems() {
		return attributeItems;
	}
	public void setAttributeItems(List<AttributeItems> attributeItems) {
		this.attributeItems = attributeItems;
	}
	public OrderSetting getOrderSetting() {
		return orderSetting;
	}
	public void setOrderSetting(OrderSetting orderSetting) {
		this.orderSetting = orderSetting;
	}
}
