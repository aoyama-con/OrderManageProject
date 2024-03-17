package com.orderManage.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.OrderProducts;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.PurchaseOrdersProductsInfo;
import com.orderManage.model.api.PurchaseOrdersStoresInfo;
import com.orderManage.model.api.StaffInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.api.StoreInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.api.SuppliersProductsInfo;
import com.orderManage.model.param.ParamStaffInfo;

/**
 * スマレジAPIを使用して情報を取得するクラス(Mock)
 * 
 * （注意点）
 * APIの戻り値（json）をJavaオブジェクトに格納する場合は、
 * スマレジで設定しているレスポンスの項目名とクラスの変数定義名を
 * 同じにしなければならない
 * 
 * @author aocon-mac
 */
@RestController
public class SmarejiApiAccessMock {

	/**
	 * getStoresInfo
	 * 
	 * ダミーの店舗一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<StoreInfo> getStoresInfo(String contractId) {
		ArrayList<StoreInfo> list = new ArrayList<StoreInfo>();
		
		// テスト用の店舗一覧を設定
		// 1~5のテスト店舗情報を作成してリストに格納している
		for (int i=0; i < 5; i++ ) {
			StoreInfo si = new StoreInfo();
			si.setStoreId(String.valueOf(i+1));
			si.setStoreCode(String.valueOf(i+1));
			si.setStoreName("テスト店舗" + String.valueOf(i+1));
			list.add(si);
		}
		return list;
	}
	
	/**
	 * getStoreInfo
	 * 
	 * ダミーの店舗情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public StoreInfo getStoreInfo(String contractId) {
		StoreInfo storeInfo = new StoreInfo();

		return storeInfo;
	}
	
	/**
	 * getPurchaseOrdersInfo
	 * 
	 * ダミーの発注一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<PurchaseOrdersInfo> getPurchaseOrdersInfo(String contractId) {
		
		ArrayList<PurchaseOrdersInfo> list = new ArrayList<PurchaseOrdersInfo>();
				
		// テスト用の発注情報を設定
		for (int i=0; i < 5; i++ ) {
			PurchaseOrdersInfo oi = new PurchaseOrdersInfo();
			
			oi.setStorageInfoId(String.valueOf(i+1));			// 発注ID
			oi.setRecipientOrderId(String.valueOf(i+1));		// 発注先ID
			oi.setDivisionUnit("2");							// 分割単位（2：仕入先）
			oi.setCategoryGroupId(String.format("%03d", i));	// 部門グループID
			oi.setOrderedDate("2024-01-10");					// 発注日（YYYY-MM-DD）			
			oi.setMemo("メモ" + String.valueOf(i+1));			// メモ
			oi.setIdentificationNo("99999");					// 識別番号
			oi.setRoundingDivision("0");						// 税丸め
			oi.setStatus("5");									// ステータス（5：仮発注）
			oi.setStaffId("id_001");							// スタッフID
			oi.setInsDateTime("YYYY-MM-DDThh:mm:ssTZD");		// 作成日時
			oi.setUpdDateTime("YYYY-MM-DDThh:mm:ssTZD");		// 更新日時
			
			list.add(oi);
		}
		
		return list;
	}
	
	/**
	 * getPurchaseOrderInfo
	 * 
	 * ダミーの発注情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public PurchaseOrdersInfo getPurchaseOrderInfo(String contractId) {
		
		PurchaseOrdersInfo PurchaseOrdersInfo = new PurchaseOrdersInfo();
		ArrayList<OrderProducts> producList = new ArrayList<OrderProducts>();
		
		// テスト用の発注情報を設定
		PurchaseOrdersInfo.setStorageInfoId(String.valueOf(1));			// 発注ID
		PurchaseOrdersInfo.setRecipientOrderId(String.valueOf(1));		// 発注先ID
		PurchaseOrdersInfo.setDivisionUnit("2");						// 分割単位（2：仕入先）
		PurchaseOrdersInfo.setCategoryGroupId("001");					// 部門グループID
		PurchaseOrdersInfo.setOrderedDate("2024-01-10");				// 発注日（YYYY-MM-DD）			
		PurchaseOrdersInfo.setMemo("メモ");								// メモ
		PurchaseOrdersInfo.setIdentificationNo("99999");				// 識別番号
		PurchaseOrdersInfo.setRoundingDivision("0");					// 税丸め
		PurchaseOrdersInfo.setStatus("5");								// ステータス（5：仮発注）
		PurchaseOrdersInfo.setStaffId("1");						// スタッフID
		PurchaseOrdersInfo.setInsDateTime("YYYY-MM-DDThh:mm:ssTZD");	// 作成日時
		PurchaseOrdersInfo.setUpdDateTime("YYYY-MM-DDThh:mm:ssTZD");	// 更新日時
		
		// 発注対象商品情報
		for (int i=0; i < 5; i++ ) {
			OrderProducts oi = new OrderProducts();
			
			oi.setStorageInfoId(String.valueOf(i));		// 発注商品ID
			oi.setStorageInfoProductId(PurchaseOrdersInfo.getStorageInfoId());	// 発注ID
			oi.setProductId(String.valueOf(i));			// 商品ID
			oi.setCost(String.valueOf(i*150));			// 原価
			oi.setQuantity(String.valueOf(i*100));		// 発注数量 

			producList.add(oi);
		}
		PurchaseOrdersInfo.setProducts(producList);
		
	
		return PurchaseOrdersInfo;
	}
	
	/**
	 * entryPurchaseOrder
	 * 
	 * ダミーの発注登録を行う
	 * ※スマレジに発注登録を行うが、登録した内容がレスポンスとして返ってくる
	 * 
	 * @param contractId 契約ID
	 */
	public PurchaseOrdersInfo entryPurchaseOrder(String contractId) {
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();

		return purchaseOrdersInfo;
	}
	
	/**
	 * updatePurchaseOrder
	 * 
	 * ダミーの発注更新を行う
	 * ※スマレジに発注更新を行うが、更新した内容がレスポンスとして返ってくる
	 * 
	 * @param contractId 契約ID
	 */
	public PurchaseOrdersInfo updatePurchaseOrder(String contractId) {
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();

		return purchaseOrdersInfo;
	}
	
	/**
	 * deletePurchaseOrder
	 * 
	 * ダミーの発注削除を行う
	 * 
	 * @param contractId 契約ID
	 */
	public boolean deletePurchaseOrder(String contractId) {
		return true;
	}
	
	/**
	 * getPurchaseOrdersProductsInfo
	 * 
	 * ダミーの発注対象商品情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<PurchaseOrdersProductsInfo> getPurchaseOrdersProductsInfo(String contractId) {
		ArrayList<PurchaseOrdersProductsInfo> list = new ArrayList<PurchaseOrdersProductsInfo>();

		return list;
	}
	
	/**
	 * getPurchaseOrdersStoresInfo
	 * 
	 * ダミーの発注対象店舗情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<PurchaseOrdersStoresInfo> getPurchaseOrdersStoresInfo(String contractId) {
		ArrayList<PurchaseOrdersStoresInfo> list = new ArrayList<PurchaseOrdersStoresInfo>();

		return list;
	}

	/**
	 * getSuppliersInfo
	 * 
	 * ダミーの仕入先一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<SuppliersInfo> getSuppliersInfo(String contractId) {
		ArrayList<SuppliersInfo> list = new ArrayList<SuppliersInfo>();
		
		// テスト用の仕入先一覧を設定
		for (int i=0; i < 5; i++ ) {
			SuppliersInfo supi = new SuppliersInfo();
			
			supi.setSupplierId(String.valueOf(i+1));				// 仕入先ID
			supi.setSupplierCode(String.valueOf(i+1));				// 仕入先コード
			supi.setSupplierName("仕入先" + String.valueOf(i+1));	// 仕入先名

			list.add(supi);
		}
		return list;
	}
	
	/**
	 * getSuppliersProductsInfo
	 * 
	 * ダミーの仕入先商品一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<SuppliersProductsInfo> getSuppliersProductsInfo(String contractId) {
		ArrayList<SuppliersProductsInfo> list = new ArrayList<SuppliersProductsInfo>();

		return list;
	}
	
	/**
	 * getProductsInfo
	 * 
	 * ダミーの商品一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<ProductsInfo> getProductsInfo(String contractId, ParamStaffInfo paramStaffInfo) {
		ArrayList<ProductsInfo> list = new ArrayList<ProductsInfo>();

		return list;
	}
	
	/**
	 * getStockInfo
	 * 
	 * ダミーの在庫一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<StockInfo> getStockInfo(String contractId) {
		ArrayList<StockInfo> list = new ArrayList<StockInfo>();

		return list;
	}
	
	/**
	 * getCategoriesInfo
	 * 
	 * ダミーの部門一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<CategorieInfo> getCategoriesInfo(String contractId) {
		ArrayList<CategorieInfo> list = new ArrayList<CategorieInfo>();

		return list;
	}
	
	/**
	 * getCategorieInfo
	 * 
	 * ダミーの部門情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public CategorieInfo getCategorieInfo(String contractId) {
		CategorieInfo categorieInfo = new CategorieInfo();

		return categorieInfo;
	}

	/**
	 * getStaffInfo
	 * 
	 * ダミーのスタッフ一覧情報を取得する
	 * 
	 * @param contractId 契約ID
	 */
	public List<StaffInfo> getStaffInfo(String contractId) {
		ArrayList<StaffInfo> list = new ArrayList<StaffInfo>();
		
		// テスト用のスタッフ一覧を設定　TODO スタッフ情報のmodel
		for (int i=0; i < 5; i++ ) {
			StaffInfo staff = new StaffInfo();			
			staff.setStaffId(String.valueOf(i+1));  // スタッフID
			staff.setStaffName("スタッフ" + String.valueOf(i+1));	// スタッフ名

			list.add(staff);
		}
		return list;

	}
	
}
