package com.orderManage.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.PurchaseOrdersProductsInfo;
import com.orderManage.model.api.PurchaseOrdersStoresInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.api.StoreInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.api.SuppliersProductsInfo;

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
		PurchaseOrdersInfo purchaseOrdersInfo = new PurchaseOrdersInfo();

		return purchaseOrdersInfo;
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
	public List<ProductsInfo> getProductsInfo(String contractId) {
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
}
