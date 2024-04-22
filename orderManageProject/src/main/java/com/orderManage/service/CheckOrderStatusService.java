package com.orderManage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orderManage.controller.object.CheckOrderStatusSubForm;
import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersProductsInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamProductImage;
import com.orderManage.model.param.ParamProductInfo;
import com.orderManage.model.param.ParamPurchaseOrderProduct;
import com.orderManage.model.session.SmarejiUser;

@Service
public class CheckOrderStatusService extends OrderManageService {
	
	public List<PurchaseOrdersProductsInfo> getPurchaseOrdersProductsInfo(SmarejiUser smarejiUser) {
	
	/**
	 * 発注状況画面 下部（商品一覧）の表示情報を取得する。
	 * 
	 * API:PurchaseOrdersProductsInfoを使用
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return List 商品ID, 発注数量
	 */
	
		logger.info("getPurchaseOrdersProductsInfo:発注済み商品情報取得 処理開始");
		
		//APIからの取得値を格納するmodelを準備しておく
		List<PurchaseOrdersProductsInfo> purchaseOrdersProductsInfoList = new ArrayList<PurchaseOrdersProductsInfo>();
		
		//発注済み商品一覧取得パラメータを設定
		ParamPurchaseOrderProduct param = new ParamPurchaseOrderProduct();
		
		//必要パラメーター String contractId, String orderId,ParamPurchaseOrderProduct paramPurchaseOrderProduct
		//orderIdは発注履歴から引継ぎ
		List<String> getParam = new ArrayList<String>();
		getParam.add("productId");
		getParam.add("quantity");
		param.setFields(getParam);
		
		//API接続
		//purchaseOrdersProductsInfoList = smarejiApiAccess.getPurchaseOrdersProductsInfo(smarejiUser.getContract().getId(), orderId, paramSuppliersInfo);
		purchaseOrdersProductsInfoList = smarejiApiAccessMock.getPurchaseOrdersProductsInfo("dummyid");
		
        logger.info("getPurchaseOrdersProductsInfo:発注済み商品情報取得 処理終了");
		
		return purchaseOrdersProductsInfoList;
	}
	
	
	
	public List<ProductsInfo> getProductsInfo(SmarejiUser smarejiUser) {
		
		/**
		 * 発注状況画面 下部（商品一覧）の表示情報を取得する。
		 * 
		 * API:ProductsInfoを使用
		 * 
		 * @param smarejiUser スマレジユーザ情報
		 * @return List  商品CD, 商品名, グループコード
		 */
		
			logger.info("getProductsInfo:商品詳細情報取得 処理開始");
			
			//APIからの取得値を格納するmodelを準備しておく
			List<ProductsInfo> productsInfoList = new ArrayList<ProductsInfo>();
			
			//商品詳細情報取得パラメータを設定
			ParamProductInfo param = new ParamProductInfo();
			
			//必要パラメーター String contractId, ParamProductInfo paramProductInfo
			List<String> getParam = new ArrayList<String>();
			getParam.add("ProductCode");
			getParam.add("ProductName");
			getParam.add("GroupCode");
			param.setFields(getParam);
			
			//API接続
			//productsInfoList = smarejiApiAccess.getProductsInfo(smarejiUser.getContract().getId(), paramSuppliersInfo);
			productsInfoList = smarejiApiAccessMock.getProductsInfo("dummyid");
			
	        logger.info("getSupplierInfo:仕入先情報取得　処理終了");
			
	        return productsInfoList;
		}
	
	public List<CategorieInfo> getCategoriesLnfo(SmarejiUser smarejiUser) {
		
		/**
		 * 発注状況画面 下部（商品一覧）の表示情報を取得する。
		 * 
		 * API:CategorieInfoを使用
		 * 
		 * @param smarejiUser スマレジユーザ情報
		 * @return List 部門ID, 部門CD, 部門名
		 */
		
			logger.info("getCategoriesInfo:部門情報一覧取得 処理開始");
			
			//APIからの取得値を格納するmodelを準備しておく
			List<CategorieInfo> categoriesInfoList = new ArrayList<CategorieInfo>();
			
			//部門情報一覧取得パラメータを設定
			ParamCategorieInfo param = new ParamCategorieInfo();
		
			//必要パラメーター String contractId, ParamCategorieInfo paramCategorieInfo
			//orderIdは発注履歴から引継ぎ
			List<String> getParam = new ArrayList<String>();
			getParam.add("categoryId");
			getParam.add("categoryCode");
			getParam.add("categoryName");
			param.setFields(getParam);
			
			//API接続
			//categoriesInfoList = smarejiApiAccess.getCategoriesInfo(smarejiUser.getContract().getId(), contractId, paramCategorieInfo);
			categoriesInfoList = smarejiApiAccessMock.getCategoriesInfo("dummyid");
			
	        logger.info("getCategoriesInfo:部門情報一覧取得 処理終了");
			
			return categoriesInfoList;
		}
	
	 public List<ProductImageInfo> getProductImageInfo(SmarejiUser smarejiUser) {
			
		/**
		 * 発注状況画面 下部（商品一覧）の表示情報を取得する。
		 * 
		 * API:ProductImageInfoを使用
		 * 
		 * @param smarejiUser スマレジユーザ情報
		 * @return List url
		 */
		
			logger.info("getProductImageInfo:商品画像情報一覧取得 処理開始");
			
			//APIからの取得値を格納するmodelを準備しておく
			List<ProductImageInfo> productImageInfoList = new ArrayList<ProductImageInfo>();
			
			//商品画像情報一覧取得パラメータを設定
			ParamProductImage param = new ParamProductImage();
		
			//必要パラメーター String contractId, ParamProductImage paramProductImage
			//orderIdは発注履歴から引継ぎ
			List<String> getParam = new ArrayList<String>();
			getParam.add("url");
		 	param.setFields(getParam);
				
			//API接続
			//categoriesInfoList = smarejiApiAccess.getProductsImage(smarejiUser.getContract().getId(), contractId, paramProductImage);
			productImageInfoList = smarejiApiAccessMock.getProductImageInfo("dummyid");
				
		    logger.info("getCategoriesInfo:商品画像情報一覧取得 処理終了");
				
			return productImageInfoList;
		}
	
	 
	 public List<CheckOrderStatusSubForm> getMeisai(SmarejiUser smarejiUser) {
		
		/**
		 * 発注状況画面 全APIを動かして明細部の情報を総取得する。
		 * 
		 * API:CategorieInfoを使用
		 * 
		 * @param smarejiUser スマレジユーザ情報
		 * @return List 部門ID, 部門CD, 部門名
		 */
		
		    logger.info("getMeisai:発注状況画面明細部情報一覧取得 処理開始");
		    
		    //発注済み商品情報取得
		    List<PurchaseOrdersProductsInfo> PurchaseOrdersProductsInfo = getPurchaseOrdersProductsInfo(smarejiUser);
			
		    //商品詳細情報取得
		    List<ProductsInfo> productsInfo = getProductsInfo(smarejiUser);
		   
		    //部門情報一覧取得
		    List<CategorieInfo> CategoriesLnfo = getCategoriesLnfo(smarejiUser);
		    
		    //商品画像情報一覧取得
		    List<ProductImageInfo> ProductImageInfo = getProductImageInfo(smarejiUser);
			
			List<CheckOrderStatusSubForm> CheckOrderStatusSubForm = new ArrayList<CheckOrderStatusSubForm>();
			
			//取得件数分のデータを表示用サブフォームに詰め替える
			for (int i=0; i<PurchaseOrdersProductsInfo.size(); i++) {
			CheckOrderStatusSubForm COSF = new CheckOrderStatusSubForm();
		    COSF.setProductId(PurchaseOrdersProductsInfo.get(i).getProductId());
		    COSF.setQuantity(PurchaseOrdersProductsInfo.get(i).getQuantity());
		    COSF.setProductCode(productsInfo.get(i).getProductCode());
		    COSF.setProductName(productsInfo.get(i).getProductName()); 
		    COSF.setGroupCode(productsInfo.get(i).getGroupCode()); 
		    COSF.setCategoryId(CategoriesLnfo.get(i).getCategoryId());
		    COSF.setCategoryCode(CategoriesLnfo.get(i).getCategoryCode());
		    COSF.setCategoryName(CategoriesLnfo.get(i).getCategoryName());
		    COSF.setUrl(ProductImageInfo.get(i).getUrl());
		    CheckOrderStatusSubForm.add(COSF);
			}
		     
			logger.info("getMeisai:発注状況画面明細部情報一覧取得 処理終了");
			
		    return CheckOrderStatusSubForm;	        
	}
	
   
}