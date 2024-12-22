package com.orderManage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orderManage.controller.object.CheckOrderStatusForm;
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
	    
	public List<PurchaseOrdersProductsInfo> getPurchaseOrdersProductsInfo(SmarejiUser smarejiUser, String orderId) {
	
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
		purchaseOrdersProductsInfoList = smarejiApiAccess.getPurchaseOrdersProductsInfo(smarejiUser.getContract().getId(), orderId, param);

        logger.info("getPurchaseOrdersProductsInfo:発注済み商品情報取得 処理終了");
		
		return purchaseOrdersProductsInfoList;
	}
	
	
	
	public List<ProductsInfo> getProductsInfo(SmarejiUser smarejiUser, List<String> ProductId1) {
		
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
			
			List<ProductsInfo> productsInfoListTrue = new ArrayList<ProductsInfo>();
			
			//商品詳細情報取得パラメータを設定
			ParamProductInfo param = new ParamProductInfo();
			
			//一覧取得系APIの取得件数上限を100以上に拡張する
			param.setLimit(1000);
			
			//必要パラメーター String contractId, ParamProductInfo paramProductInfo
			List<String> getParam = new ArrayList<String>();
			getParam.add("productCode");
			getParam.add("productName");
			getParam.add("groupCode");
			getParam.add("productId");
			getParam.add("categoryId");
			param.setFields(getParam);
						
			//API接続
			  productsInfoList = smarejiApiAccess.getProductsInfo(smarejiUser.getContract().getId(), param);
					 

			for (int n=0; n<ProductId1.size(); n++){
				for (int i=0; i<productsInfoList.size(); i++) {
					if(productsInfoList.get(i).getProductId().equals(ProductId1.get(n))){
						ProductsInfo PL = new ProductsInfo();
						PL.setProductCode(productsInfoList.get(i).getProductCode());
						PL.setProductName(productsInfoList.get(i).getProductName());
						PL.setProductId(productsInfoList.get(i).getProductId());
						PL.setGroupCode(productsInfoList.get(i).getGroupCode());
						PL.setCategoryId(productsInfoList.get(i).getCategoryId());
						productsInfoListTrue.add(PL);
					}
			     }
			 }
			
	        logger.info("getProductsInfo:商品詳細情報取得　処理終了");
	        
	        return productsInfoListTrue;
		}
	
	public List<CategorieInfo> getCategoriesLnfo(SmarejiUser smarejiUser, List<String> CategoryId1) {
		
		/**
		 * 発注状況画面 下部（部門一覧）の表示情報を取得する。
		 * 
		 * API:CategorieInfoを使用
		 * 
		 * @param smarejiUser スマレジユーザ情報
		 * @return List 部門ID, 部門CD, 部門名
		 */
		
			logger.info("getCategoriesInfo:部門情報一覧取得 処理開始");
			
			//APIからの取得値を格納するmodelを準備しておく
			List<CategorieInfo> categoriesInfoList = new ArrayList<CategorieInfo>();
			
			List<CategorieInfo> categoriesInfoListTrue = new ArrayList<CategorieInfo>();
			
			//部門情報一覧取得パラメータを設定
			ParamCategorieInfo param = new ParamCategorieInfo();
			
			//一覧取得系APIの取得件数上限を100以上に拡張する
			param.setLimit(1000);
		
			//必要パラメーター String contractId, ParamCategorieInfo paramCategorieInfo
			//orderIdは発注履歴から引継ぎ
			List<String> getParam = new ArrayList<String>();
			getParam.add("categoryId");
			getParam.add("categoryCode");
			getParam.add("categoryName");
			param.setFields(getParam);
			
			//API接続
			categoriesInfoList = smarejiApiAccess.getCategoriesInfo(smarejiUser.getContract().getId(), param);
			
			for (int n=0; n<CategoryId1.size(); n++){
				for (int i=0; i<categoriesInfoList.size(); i++) {
					if(categoriesInfoList.get(i).getCategoryId().equals(CategoryId1.get(n))){
						CategorieInfo CI = new CategorieInfo();
						CI.setCategoryId(categoriesInfoList.get(i).getCategoryId());
						CI.setCategoryCode(categoriesInfoList.get(i).getCategoryCode());
						CI.setCategoryName(categoriesInfoList.get(i).getCategoryName());
						categoriesInfoListTrue.add(CI);
					}
				}
			}
			
	        logger.info("getCategoriesInfo:部門情報一覧取得 処理終了");
	        
			return categoriesInfoListTrue;
		}
	
	 public List<ProductImageInfo> getProductImageInfo(SmarejiUser smarejiUser, int roop, List<String> ProductId1) {
			
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

			//商品画像は登録が任意のため、未登録を考慮して予め件数分の入れ物を用意しておく
			List<ProductImageInfo> productImageInfoListTrue = new ArrayList<ProductImageInfo>();
		    for (int i=0; i<ProductId1.size(); i++) {
		    	ProductImageInfo PID = new ProductImageInfo();
		    	PID.setProductId("");
	        	PID.setUrl("");
	        	productImageInfoListTrue.add(PID);
		    }
			
			//商品画像情報一覧取得パラメータを設定
			ParamProductImage param = new ParamProductImage();
			
			//一覧取得系APIの取得件数上限を100以上に拡張する
			param.setLimit(1000);
		
			//必要パラメーター String contractId, ParamProductImage paramProductImage
			//orderIdは発注履歴から引継ぎ
			List<String> getParam = new ArrayList<String>();
			getParam.add("url");
			getParam.add("productId");
		 	param.setFields(getParam);
				
			//API接続
		    productImageInfoList = smarejiApiAccess.getProductsImage(smarejiUser.getContract().getId(), param);

		    
		    for (int n=0; n<productImageInfoList.size(); n++){
			   for (int i=0; i<ProductId1.size(); i++) {
				  if(ProductId1.get(i).equals(productImageInfoList.get(n).getProductId())){
					  productImageInfoListTrue.get(i).setProductId(productImageInfoList.get(n).getProductId());
					  productImageInfoListTrue.get(i).setUrl(productImageInfoList.get(n).getUrl());
					 break;}
			   }
		    }
				 //画像が登録されていない商品にダミー画像を設定する。 
				 for (int u=0; u<productImageInfoListTrue.size(); u++){
					  if(productImageInfoListTrue.get(u).getUrl().equals("")){
						  productImageInfoListTrue.get(u).setUrl("http://localhost:8080/orderManageProject/img/no_image.png");
					  }
				  }
				  				  		    
		    logger.info("getProductsImageInfo:商品画像情報一覧取得 処理終了");
				
			return productImageInfoListTrue;
		}
		 
	 public CheckOrderStatusForm getMeisai(SmarejiUser smarejiUser, String orderId) {
		
		/**
		 * 発注状況画面 全メソッドを動かして明細部の情報を総取得する。
		 * 
		 * API:CategorieInfoを使用
		 * 
		 * @param smarejiUser スマレジユーザ情報
		 * @return List 部門ID, 部門CD, 部門名
		 */
		

			List<String> ProductId1 = new ArrayList<String>();
			List<String> CategoryId1 = new ArrayList<String>();
			int roop = 0;
		 
			
		    logger.info("getMeisai:発注状況画面明細部情報一覧取得 処理開始");
		    
		    //発注済み商品情報取得
		    List<PurchaseOrdersProductsInfo> PurchaseOrdersProductsInfo = getPurchaseOrdersProductsInfo(smarejiUser, orderId);
		    
		    //商品詳細、商品画像一覧取得のためキー項目をリストに抽出しておく
		    for (int i=0; i<PurchaseOrdersProductsInfo.size(); i++) {
			ProductId1.add(PurchaseOrdersProductsInfo.get(i).getProductId());
		    }
			
		    //商品詳細情報取得
		    List<ProductsInfo> productsInfo = getProductsInfo(smarejiUser, ProductId1);
		    
		    //部門一覧取得のためキー項目をリストに抽出しておく	
		    for (int i=0; i<productsInfo.size(); i++) {
			CategoryId1.add(productsInfo.get(i).getCategoryId());
			}
		   
		    //部門情報一覧取得
		    List<CategorieInfo> CategoriesLnfo = getCategoriesLnfo(smarejiUser, CategoryId1);
		    
		    roop = CategoriesLnfo.size();
		    
		    //商品画像情報一覧取得
		    List<ProductImageInfo> ProductImageInfo = getProductImageInfo(smarejiUser, roop, ProductId1);
		    
		    
			List<CheckOrderStatusSubForm> CheckOrderStatusSubForm = new ArrayList<CheckOrderStatusSubForm>();
			CheckOrderStatusForm CheckOrderStatusForm = new CheckOrderStatusForm();

			
			//取得件数分のデータを表示用サブフォームに詰め替える
			for (int i=0; i<roop; i++) {
			logger.info(String.valueOf(i)+"開始");
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
		    logger.info(String.valueOf(i)+"終了");
			}
			
			CheckOrderStatusForm.setDisplayList(CheckOrderStatusSubForm);
			CheckOrderStatusForm.setOrderId(orderId);
		     
			logger.info("getMeisai:発注状況画面明細部情報一覧取得 処理終了");
			
		    return CheckOrderStatusForm;	        
	}
	
	 /**
		 * ページング処理
		 * 
		 * @param pageable 
		 * @param form 発注状況画面Form
		 * @return
		 */
		public Page<CheckOrderStatusSubForm> paging(Pageable pageable, CheckOrderStatusForm form) {

			List<CheckOrderStatusSubForm> dispList = form.getDisplayList();

			int pageSize = pageable.getPageSize();
			int currentPage = pageable.getPageNumber();
			int startItem = currentPage * pageSize;
			
			List<CheckOrderStatusSubForm> list;
		
			if(dispList.size() < startItem) {
				list = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize , dispList.size());
				list = dispList.subList(startItem, toIndex);
			}
			
			Page<CheckOrderStatusSubForm> CheckOrderStatusPage = new PageImpl<CheckOrderStatusSubForm>(list, PageRequest.of(currentPage, pageSize),dispList.size());
			return CheckOrderStatusPage;
			
		}
}