package com.orderManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.ProductAttributeInfo;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.PurchaseOrdersProductsInfo;
import com.orderManage.model.api.PurchaseOrdersStoresInfo;
import com.orderManage.model.api.StaffInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.api.StoreInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.api.SuppliersProductsInfo;
import com.orderManage.model.api.TransactionsInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamEntryPurchaseOrder;
import com.orderManage.model.param.ParamEntryPurchaseOrderDeliveryStore;
import com.orderManage.model.param.ParamEntryPurchaseOrderProduct;
import com.orderManage.model.param.ParamEntryPurchaseOrderStore;
import com.orderManage.model.param.ParamProductAttributeInfo;
import com.orderManage.model.param.ParamProductImage;
import com.orderManage.model.param.ParamProductInfo;
import com.orderManage.model.param.ParamPurchaseOrderInfo;
import com.orderManage.model.param.ParamPurchaseOrderProduct;
import com.orderManage.model.param.ParamPurchaseOrderStore;
import com.orderManage.model.param.ParamStaffInfo;
import com.orderManage.model.param.ParamStockInfo;
import com.orderManage.model.param.ParamStoreInfo;
import com.orderManage.model.param.ParamSupplierInfo;
import com.orderManage.model.param.ParamSupplierProduct;
import com.orderManage.model.param.ParamTransactionInfo;
import com.orderManage.model.param.ParamUpdatePurchasOrderDeliveryStore;
import com.orderManage.model.param.ParamUpdatePurchaseOrder;
import com.orderManage.model.param.ParamUpdatePurchaseOrderProduct;
import com.orderManage.model.param.ParamUpdatePurchaseOrderStore;
import com.orderManage.model.session.SmarejiUser;

/**
 * 共通系のテスト用サービスクラス
 * スマレジAPIをテストするためのサービスクラス
 * 
 * 
 */
@Service
public class UtilTestService extends OrderManageService {

	// 店舗一覧情報を取得
	public List<StoreInfo> getStoresInfo(SmarejiUser smarejiUser) {
		
		logger.info("getStoreNames:店舗一覧取得　処理開始");
		
		List<StoreInfo> storeInfoList = new ArrayList<StoreInfo>();
		
		// 店舗一覧を取得(API)
		ParamStoreInfo paramStoresInfo = new ParamStoreInfo();
		
		// 店舗一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("storeId");
		getParam.add("storeCode");
		getParam.add("storeName");
		// 取得パラメータ
		paramStoresInfo.setFields(getParam);
		//　ソート順
		paramStoresInfo.setSort("storeId");
		// 取得上限数
		paramStoresInfo.setLimit(1);
		
		storeInfoList = smarejiApiAccess.getStoresInfo(smarejiUser.getContract().getId(), paramStoresInfo);

		logger.info("getStoreNames:店舗一覧取得　処理終了");
		
		return storeInfoList;
	}
	
	// 店舗情報取得
	public StoreInfo getStoreInfo(SmarejiUser smarejiUser) {
		StoreInfo storeInfo = new StoreInfo();
		
		// 店舗一情報を取得(API)
		ParamStoreInfo paramStoresInfo = new ParamStoreInfo();

		// 検索パラメータ
		// ポイント情報を付加
		paramStoresInfo.setWith_point_condition("none");
		// レシート印刷情報を付加
		paramStoresInfo.setWith_receipt_print_info("none");
		
		storeInfo = smarejiApiAccess.getStoreInfo(smarejiUser.getContract().getId(), "3", paramStoresInfo);
		
		return storeInfo;
	}

	// 発注一覧情報取得
	public List<PurchaseOrdersInfo> getPurchaseOrdersInfo(SmarejiUser smarejiUser) {

		// 発注一覧を取得(API)
		ParamPurchaseOrderInfo paramPurchaseOrdersInfo = new ParamPurchaseOrderInfo();
		
		List<PurchaseOrdersInfo> purchaseOrdersInfoList = new ArrayList<PurchaseOrdersInfo>();

		// 検索パラメータ
		// 上限設定
		paramPurchaseOrdersInfo.setLimit(5);

		purchaseOrdersInfoList = smarejiApiAccess.getPurchaseOrdersInfo(smarejiUser.getContract().getId(), paramPurchaseOrdersInfo);
		
		return purchaseOrdersInfoList;
	}
	
	// 発注情報取得
	public PurchaseOrdersInfo getPurchaseOrderInfo(SmarejiUser smarejiUser) {
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();

		// 発注情報取得
		ParamPurchaseOrderInfo paramPurchaseOrdersInfo = new ParamPurchaseOrderInfo();
		paramPurchaseOrdersInfo.setWith_products("all");
		paramPurchaseOrdersInfo.setWith_stores("all");
		
		purchaseOrdersInfo = smarejiApiAccess.getPurchaseOrderInfo(smarejiUser.getContract().getId(), "4", paramPurchaseOrdersInfo);
		
		return purchaseOrdersInfo;
	}
	
	// 発注登録
	public PurchaseOrdersInfo entryPurchaseOrder(SmarejiUser smarejiUser) {
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();
		
		// 発注登録内容設定
		ParamEntryPurchaseOrder paramEntryPurchaseOrder = new ParamEntryPurchaseOrder();
		// 発注先ID
		paramEntryPurchaseOrder.setRecipientOrderId("2");
		// 発注日
		paramEntryPurchaseOrder.setOrderedDate("2023-12-16");
		// メモ
		paramEntryPurchaseOrder.setMemo("共通ライブラリテストメモ");
		// 識別番号 現在時刻（UNIXTIME）を使用
		Long datetime = System.currentTimeMillis();
		paramEntryPurchaseOrder.setIdentificationNo(datetime.toString());
		// 税丸め（0:四捨五入、1:切り捨て、2:切り上げ）
		paramEntryPurchaseOrder.setRoundingDivision("0");
		// ステータス
		paramEntryPurchaseOrder.setStatus("5");
		// 発注処理時のスタッフID
		paramEntryPurchaseOrder.setStaffId(smarejiUser.getContract().getUser_id());
		
		// 発注対象商品 array ///////////////////////////////////////////////////////////
		ArrayList<ParamEntryPurchaseOrderProduct> opList = new ArrayList<ParamEntryPurchaseOrderProduct>(); 
		ArrayList<ParamEntryPurchaseOrderDeliveryStore> dsList = new ArrayList<ParamEntryPurchaseOrderDeliveryStore>();
		
		// テスト商品12 設定
		ParamEntryPurchaseOrderProduct op = new ParamEntryPurchaseOrderProduct();
		op.setProductId("8000010");
		// コスト　NULL不可
//		op.setCost("400");
		ParamEntryPurchaseOrderDeliveryStore ds = new ParamEntryPurchaseOrderDeliveryStore();
		ds.setStoreId("3");
		// 発注数量
		ds.setQuantity("5");
//		ds.setQuantity("100");
		dsList.add(ds);
		op.setDeliveryStore(dsList);
		opList.add(op);
		
		// テスト商品１ 設定
		dsList = new ArrayList<ParamEntryPurchaseOrderDeliveryStore>();
		op = new ParamEntryPurchaseOrderProduct();
		op.setProductId("8000001");
		// コスト　NULL不可
//		op.setCost("50");
		ds = new ParamEntryPurchaseOrderDeliveryStore();
		ds.setStoreId("3");
		// 発注数量
		ds.setQuantity("0");
//		ds.setQuantity("100");
		dsList.add(ds);
		op.setDeliveryStore(dsList);
		opList.add(op);
		
		paramEntryPurchaseOrder.setProducts(opList);
		//////////////////////////////////////////////////////////////////////////////

		// 発注対象店舗 array ///////////////////////////////////////////////////////////
		ArrayList<ParamEntryPurchaseOrderStore> osList = new ArrayList<ParamEntryPurchaseOrderStore>();
		ParamEntryPurchaseOrderStore os = new ParamEntryPurchaseOrderStore();
		os.setStorageStoreId("3");
		osList.add(os);
		paramEntryPurchaseOrder.setStores(osList);
		//////////////////////////////////////////////////////////////////////////////
		
		purchaseOrdersInfo = smarejiApiAccess.entryPurchaseOrder(smarejiUser.getContract().getId(), paramEntryPurchaseOrder);

		return purchaseOrdersInfo;
	}

	// 発注更新
	public PurchaseOrdersInfo updatePurchaseOrder(SmarejiUser smarejiUser, String id) {
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();
		
		// 発注更新内容設定
		ParamUpdatePurchaseOrder paramUpdatePurchaseOrder = new ParamUpdatePurchaseOrder();
		// ステータス
		paramUpdatePurchaseOrder.setStatus("2");
		// 発注処理時のスタッフID
		paramUpdatePurchaseOrder.setStaffId(smarejiUser.getContract().getUser_id());
		// 税丸め（0:四捨五入、1:切り捨て、2:切り上げ） 更新時必須とエラーが発生のため
		paramUpdatePurchaseOrder.setRoundingDivision("0");
		
		// 発注対象商品 array ///////////////////////////////////////////////////////////
		ArrayList<ParamUpdatePurchaseOrderProduct> opList = new ArrayList<ParamUpdatePurchaseOrderProduct>(); 
		ArrayList<ParamUpdatePurchasOrderDeliveryStore> dsList = new ArrayList<ParamUpdatePurchasOrderDeliveryStore>();
		
		// テスト商品12 設定
		ParamUpdatePurchaseOrderProduct op = new ParamUpdatePurchaseOrderProduct();
		// 発注商品ID 更新の場合は指定　登録時自動採番した値
		op.setStorageInfoProductId("32");
		// 商品ID NULL設定不可
//		op.setProductId("8000010");
		// コスト NULL設定不可
//		op.setCost("400");
		ParamUpdatePurchasOrderDeliveryStore ds = new ParamUpdatePurchasOrderDeliveryStore();
		ds.setStorageInfoDeliveryProductId("32");
//		ds.setStoreId("3");
		ds.setQuantity("500");
		dsList.add(ds);
		op.setDeliveryStore(dsList);
		opList.add(op);
		
		// テスト商品１ 設定
		dsList = new ArrayList<ParamUpdatePurchasOrderDeliveryStore>();
		op = new ParamUpdatePurchaseOrderProduct();
		// 発注商品ID 更新の場合は指定　登録時自動採番した値
		op.setStorageInfoProductId("33");
		// 商品ID NULL設定不可
//		op.setProductId("8000001");
		// コスト NULL設定不可
//		op.setCost("50");
		ds = new ParamUpdatePurchasOrderDeliveryStore();
		ds.setStorageInfoDeliveryProductId("33");
//		ds.setStoreId("3");
		ds.setQuantity("600");
		dsList.add(ds);
		op.setDeliveryStore(dsList);
		opList.add(op);
		
		paramUpdatePurchaseOrder.setProducts(opList);
		//////////////////////////////////////////////////////////////////////////////

		// 発注対象店舗 array ///////////////////////////////////////////////////////////
		ArrayList<ParamUpdatePurchaseOrderStore> osList = new ArrayList<ParamUpdatePurchaseOrderStore>();
		ParamUpdatePurchaseOrderStore os = new ParamUpdatePurchaseOrderStore();
		// 発注配送店舗ID 更新の場合は指定　登録時に自動更新した値
		os.setStorageInfoDeliveryId("19");
		// 配送店舗ID NULL設定不可
//		os.setStorageStoreId("3");
//		ds.setQuantity("200");
		osList.add(os);
		paramUpdatePurchaseOrder.setStores(osList);
		//////////////////////////////////////////////////////////////////////////////
		
		purchaseOrdersInfo = smarejiApiAccess.updatePurchaseOrder(smarejiUser.getContract().getId(), id, 
				paramUpdatePurchaseOrder);

		return purchaseOrdersInfo;
	}
	
	// 発注削除
	public void deletePurchaseOrder(SmarejiUser smarejiUser, String id) {
		smarejiApiAccess.deletePurchaseOrder(smarejiUser.getContract().getId(), id);
	}
	
	// 発注対象商品取得
	public List<PurchaseOrdersProductsInfo> getPurchaseOrdersProductInfo(SmarejiUser smarejiUser, String id) {
		
		// 発注更新内容設定
		ParamPurchaseOrderProduct paramPurchaseOrderProduct = new ParamPurchaseOrderProduct();
		
		paramPurchaseOrderProduct.setLimit(10);
		paramPurchaseOrderProduct.setProduct_id("8000010");
		
		List<PurchaseOrdersProductsInfo> purchaseOrdersProductsInfo = smarejiApiAccess.getPurchaseOrdersProductsInfo(smarejiUser.getContract().getId(), id, 
				paramPurchaseOrderProduct);

		return purchaseOrdersProductsInfo;
	}
	
	// 発注対象店舗取得
	public List<PurchaseOrdersStoresInfo> getPurchaseOrdersStoreInfo(SmarejiUser smarejiUser, String id) {
		
		// 発注更新内容設定
		ParamPurchaseOrderStore paramPurchaseOrderStore = new ParamPurchaseOrderStore();
		
		paramPurchaseOrderStore.setLimit(10);
		paramPurchaseOrderStore.setStorage_store_id("3");
		
		List<PurchaseOrdersStoresInfo> purchaseOrdersStoresInfo = smarejiApiAccess.getPurchaseOrdersStoresInfo(smarejiUser.getContract().getId(), id, 
				paramPurchaseOrderStore);

		return purchaseOrdersStoresInfo;
	}
	
	// 仕入先一覧取得
	public List<SuppliersInfo> getSuppliersInfo(SmarejiUser smarejiUser) {
		
		// 仕入先一覧を取得(API)
		ParamSupplierInfo paramSuppliersInfo = new ParamSupplierInfo();

		// 検索パラメータ
		// 上限設定
		paramSuppliersInfo.setLimit(10);

		List<SuppliersInfo> supplierList = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
		
		return supplierList;
	}

	// 仕入先商品一覧取得
	public List<SuppliersProductsInfo> getSuppliersProductsInfo(SmarejiUser smarejiUser, String id) {
		
		// 仕入先一覧を取得(API)
		ParamSupplierProduct paramSupplierProduct = new ParamSupplierProduct();

		// 検索パラメータ
		// 上限設定
		paramSupplierProduct.setLimit(10);

		List<SuppliersProductsInfo> supplierList = smarejiApiAccess.getSuppliersProductsInfo(smarejiUser.getContract().getId(), 
				id, paramSupplierProduct);
		
		return supplierList;
	}

	// 商品一覧取得取得
	public List<ProductsInfo> getProductsInfo(SmarejiUser smarejiUser) {
		
		// 商品一覧を取得(API)
		ParamProductInfo paramProductInfo = new ParamProductInfo();

		// 検索パラメータ
		// 上限設定
		paramProductInfo.setLimit(10);
		// 部門ID
		paramProductInfo.setCategory_id(8000002);
		// グループコード
		paramProductInfo.setGroup_code("grp001");
		// 更新日時(From)
//		paramProductInfo.setUpd_date_time_from("2023-12-10T11:12:33+09:00");

		List<ProductsInfo> productInfo = smarejiApiAccess.getProductsInfo(smarejiUser.getContract().getId(), 
				paramProductInfo);
		
		return productInfo;
	}
	
	// 在庫一覧取得
	public List<StockInfo> getStockInfo(SmarejiUser smarejiUser) {
		
		// 在庫一覧を取得(API)
		ParamStockInfo paramStockInfo = new ParamStockInfo();

		// 検索パラメータ
		// 上限設定
		paramStockInfo.setLimit(10);
//		// 店舗ID
//		paramStockInfo.setStore_id(3);
//		// 商品ID
//		paramStockInfo.setProduct_id("1");

		List<StockInfo> stockInfo = smarejiApiAccess.getStocksInfo(smarejiUser.getContract().getId(), 
				paramStockInfo);
		
		return stockInfo;
	}
	// 部門一覧取得
	public List<CategorieInfo> getCategoriesInfo(SmarejiUser smarejiUser) {
		
		// 部門一覧を取得(API)
		ParamCategorieInfo paramCategorieInfo = new ParamCategorieInfo();

		// 検索パラメータ
		// 上限設定
		paramCategorieInfo.setLimit(10);
//		// 部門コード
//		paramCategorieInfo.setCategory_code("");
//		// 階層レベル
//		paramCategorieInfo.setLevel("");

		List<CategorieInfo> stockInfo = smarejiApiAccess.getCategoriesInfo(smarejiUser.getContract().getId(), 
				paramCategorieInfo);
		
		return stockInfo;
	}
	// 部門情報取得
	public CategorieInfo getCategorieInfo(SmarejiUser smarejiUser) {
		CategorieInfo categorieInfo = new CategorieInfo();
		
		// 店舗情報を取得(API)
		ParamCategorieInfo paramCategorieInfo = new ParamCategorieInfo();
		
		List<String> fList = new ArrayList<String>();
		fList.add("categoryId");
		fList.add("categoryCode");
		fList.add("categoryName");
		fList.add("categoryAbbr");
		fList.add("level");

		// 検索パラメータ
		paramCategorieInfo.setFields(fList);
		
		categorieInfo = smarejiApiAccess.getCategorieInfo(smarejiUser.getContract().getId(), "8000001", paramCategorieInfo);
		
		return categorieInfo;
	}
	// スタッフ一覧取得
	public List<StaffInfo> getStaffsInfo(SmarejiUser smarejiUser) {
		
		// 部門一覧を取得(API)
		ParamStaffInfo paramStaffInfo = new ParamStaffInfo();

		// 検索パラメータ
		// 上限設定
		paramStaffInfo.setLimit(10);
//		// スタッフID
//		paramStaffInfo.setStaff_id(8);

		List<StaffInfo> StaffInfo = smarejiApiAccess.getStaffsInfo(smarejiUser.getContract().getId(), 
				paramStaffInfo);
		
		return StaffInfo;
	}
	
	// 商品画像一覧取得
	public List<ProductImageInfo> getProductsImage(SmarejiUser smarejiUser) {
		
		// 商品画像一覧を取得(API)
		ParamProductImage paramProductImage = new ParamProductImage();

		// 検索パラメータ
		// 上限設定
		paramProductImage.setLimit(10);
		// 商品ID
		paramProductImage.setProduct_id("8000010");

		List<ProductImageInfo> productImage = smarejiApiAccess.getProductsImage(smarejiUser.getContract().getId(), 
				paramProductImage);
		
		return productImage;
	}
	
	// 商品属性一覧取得
	public List<ProductAttributeInfo> getAttributesInfo(SmarejiUser smarejiUser) {
		
		// 商品画像一覧を取得(API)
		ParamProductAttributeInfo paramProductAttributeInfo = new ParamProductAttributeInfo();

		// 検索パラメータ
		// 属性一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("no");
		getParam.add("name");
		// 取得パラメータ
		paramProductAttributeInfo.setFields(getParam);
		// ソート
		paramProductAttributeInfo.setSort("no");
		// 上限設定
		paramProductAttributeInfo.setLimit(10);
		// ページ
		paramProductAttributeInfo.setPage(1);

		List<ProductAttributeInfo> productImage = smarejiApiAccess.getProductAttributes(smarejiUser.getContract().getId(), 
				paramProductAttributeInfo);
		
		return productImage;
	}
	
	// 商品取得
	public ProductsInfo getProductInfo(SmarejiUser smarejiUser, String id) {
		ProductsInfo productsInfo = new ProductsInfo();

		// 商品取得パラメータ設定
		ParamProductInfo paramProductInfo = new ParamProductInfo();
		paramProductInfo.setWith_prices("all");
		paramProductInfo.setWith_reserve_items("all");
		paramProductInfo.setWith_stores("all");
		paramProductInfo.setWith_inventory_reservations("all");
		paramProductInfo.setWith_attribute_items("all");
		paramProductInfo.setWith_order_setting("none");

		productsInfo = smarejiApiAccess.getProductInfo(smarejiUser.getContract().getId(), id, paramProductInfo);
		
		return productsInfo;
	}
	
	// 取引一覧取得
	public List<TransactionsInfo> getTransactionsInfo(SmarejiUser smarejiUser) {
		
		// 商品画像一覧を取得(API)
		ParamTransactionInfo paramTransactionsInfo = new ParamTransactionInfo();

		// 検索パラメータ
		// 取引一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("transactionHeadId");
		getParam.add("transactionDateTime");
		getParam.add("storeId");
		// 指定できない↓APIアクセスでエラー、もし詳細が取得できないようであれば、検索パラメータ指定しない。スマレジに確認する（20240504）
		//getParam.add("details");
		// [transaction_date_time-from,transaction_date_time-to],
		// [terminal_tran_date_time-from,terminal_tran_date_time-to],
		// [sum_date-from,sum_date-to],
		// [upd_date_time-from,upd_date_time-to]のうちいずれかが必須です。
		// 取得パラメータ
		paramTransactionsInfo.setFields(getParam);
		// ソート
		paramTransactionsInfo.setSort("transactionHeadId");
		// 上限設定
		paramTransactionsInfo.setLimit(10);
		// ページ
		paramTransactionsInfo.setPage(1);
		// 取引明細情報を付加
		paramTransactionsInfo.setWith_details("all");
		// 取引日時(From) フォーマット：[YYYY-MM-DDThh:mm:ssTZD]　「+」はエンコードが必要
		paramTransactionsInfo.setTransaction_date_time_from("2023-12-01T00:00:00+09:00");
		// 取引日時(To)　フォーマット：[YYYY-MM-DDThh:mm:ssTZD]　「+」はエンコードが必要
		paramTransactionsInfo.setTransaction_date_time_to("2023-12-31T00:00:00+09:00");

		List<TransactionsInfo> transactionsInfo = smarejiApiAccess.getTransactionsInfo(smarejiUser.getContract().getId(), 
				paramTransactionsInfo);
		
		return transactionsInfo;
	}

}
