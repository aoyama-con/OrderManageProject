package com.orderManage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orderManage.controller.object.OrderInputForm;
import com.orderManage.controller.object.OrderInputSubForm;
import com.orderManage.model.api.CategorieInfo;
import com.orderManage.model.api.ProductImageInfo;
import com.orderManage.model.api.ProductsInfo;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StockInfo;
import com.orderManage.model.api.SuppliersInfo;
import com.orderManage.model.api.SuppliersProductsInfo;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamEntryPurchaseOrder;
import com.orderManage.model.param.ParamEntryPurchaseOrderDeliveryStore;
import com.orderManage.model.param.ParamEntryPurchaseOrderProduct;
import com.orderManage.model.param.ParamEntryPurchaseOrderStore;
import com.orderManage.model.param.ParamProductImage;
import com.orderManage.model.param.ParamProductInfo;
import com.orderManage.model.param.ParamStockInfo;
import com.orderManage.model.param.ParamSupplierInfo;
import com.orderManage.model.param.ParamSupplierProduct;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.util.DateUtil;
import com.orderManage.util.StringUtil;

/**
 * 発注入力画面サービスクラス
 * 
 * 発注入力画面での操作を行う
 * @author aocon-mac
 *
 */
@Service
public class OrderInputService extends OrderManageService {

	private static int API_LIMIT = 1000;
	private static int API_LIMIT_LOOP = API_LIMIT;
	
	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public Map<String, String> getProductsImageInfo(SmarejiUser smarejiUser) {
		
		logger.info("getProductsImageInfo:商品画像取得　処理開始");
		
		List<ProductImageInfo> productImageInfoList = new ArrayList<ProductImageInfo>();
		
		// 商品画像を取得(API)
		ParamProductImage paramProductImage = new ParamProductImage();
		
		// 商品画像取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("productId");
		getParam.add("url");
		// 取得パラメータ
		paramProductImage.setFields(getParam);
		//　ソート順
		paramProductImage.setSort("productId");
		// 取得上限数
		paramProductImage.setLimit(API_LIMIT_LOOP);

		for (int page = 1;; page++) {
			paramProductImage.setPage(page);
			
			List<ProductImageInfo> list = smarejiApiAccess.getProductsImage(smarejiUser.getContract().getId(), paramProductImage);
			/** テスト用 ローカルで動かす用mockを使用 *****************/ 
			// テスト用の部門一覧取得
	//		productImageInfoList = smarejiApiAccessMock.getProductsImageInfo("dummyid", paramproductImage);
			/***********************************************/

			if (list == null || list.size() == 0) {
				break;
			}
		
			productImageInfoList.addAll(list);
		}
		
		Iterator<ProductImageInfo> it = productImageInfoList.iterator();
		Map<String, String> productImageInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 部門コード、部門名の設定
			ProductImageInfo st = it.next();
			productImageInfoMap.put(st.getProductId(), st.getUrl());
		}
		
		logger.info("getProductsImageInfo:商品画像取得　処理終了");
		
		return productImageInfoMap;
	}

	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public Map<String, String> getCategoriesInfo(SmarejiUser smarejiUser) {
		
		logger.info("getCategoriesInfo:部門名取得　処理開始");
		
		List<CategorieInfo> categoryInfoList = new ArrayList<CategorieInfo>();
		
		// 部門一覧を取得(API)
		ParamCategorieInfo paramCategorieInfo = new ParamCategorieInfo();
		
		// 部門一覧取得のためのパラメータを設定
		List<String> getParam = new ArrayList<String>();
		getParam.add("categoryId");
		getParam.add("categoryCode");
		getParam.add("categoryName");
		// 取得パラメータ
		paramCategorieInfo.setFields(getParam);
		//　ソート順
		paramCategorieInfo.setSort("categoryId");
		// 取得上限数
		paramCategorieInfo.setLimit(API_LIMIT);
		
		categoryInfoList = smarejiApiAccess.getCategoriesInfo(smarejiUser.getContract().getId(), paramCategorieInfo);
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		// テスト用の部門一覧取得
//		categoryInfoList = smarejiApiAccessMock.getCategoriesInfo("dummyid", paramCategorieInfo);
		/***********************************************/
		
		Iterator<CategorieInfo> it = categoryInfoList.iterator();
		Map<String, String> categoryInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 部門コード、部門名の設定
			CategorieInfo st = it.next();
			categoryInfoMap.put(st.getCategoryId(), st.getCategoryName());
		}
		
		logger.info("getCategoriesInfo:部門名取得　処理終了");
		
		return categoryInfoMap;
	}

	/**
	 * 仕入先情報を取得
	 * 
	 * 仕入先一覧取得APIを使用して仕入先コード・仕入先名を取得する
	 * 
	 * @param smarejiUser スマレジユーザ情報
	 * @return Map 仕入先コード・仕入先名
	 */
	public List<SuppliersInfo> getSupplierInfo(SmarejiUser smarejiUser) {
		
		logger.info("getSupplierInfo:仕入先情報取得　処理開始");
		
		List<SuppliersInfo> supplierInfoList = new ArrayList<SuppliersInfo>();
		
		// 仕入先一覧取得パラメータを設定
		ParamSupplierInfo param = new ParamSupplierInfo();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("supplierId");
		getParam.add("supplierCode");
		getParam.add("supplierName");
		getParam.add("supplierAbbr");
		param.setFields(getParam);
		param.setLimit(API_LIMIT_LOOP);
		
		for (int page = 1;; page++) {
			param.setPage(page);
			
			// 仕入先一覧を取得(API)
			List<SuppliersInfo> list = smarejiApiAccess.getSuppliersInfo(smarejiUser.getContract().getId(), param);
			/** テスト用 ローカルで動かす用mockを使用 *****************/ 
			// テスト用の仕入先一覧取得
	//		supplierInfoList = smarejiApiAccessMock.getSuppliersInfo("dummyid");
			/***********************************************/

			if (list == null || list.size() == 0) {
				break;
			}
			
			supplierInfoList.addAll(list);
		}
		
		logger.info("getSupplierInfo:仕入先情報取得　処理終了");
		
		return supplierInfoList;
	}
	
	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public List<SuppliersProductsInfo> getSuppliersProductsInfo(SmarejiUser smarejiUser, String supplierId, String categoryId, String productId) {
		
		logger.info("getSuppliersProductsInfo:仕入先商品一覧取得　処理開始");
		
		List<SuppliersProductsInfo> suppliersProductsInfoList = new ArrayList<SuppliersProductsInfo>();
		
		// 仕入先商品一覧取得パラメータを設定
		ParamSupplierProduct param = new ParamSupplierProduct();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("supplierId");
		getParam.add("categoryId");
		getParam.add("productId");
		param.setFields(getParam);
		param.setLimit(API_LIMIT_LOOP);
		
		// AND検索はできないので条件は指定しない
//		if (StringUtil.isNotEmpty(categoryId)) {
//			param.setCategory_id(categoryId);
//		}
//		
		if (StringUtil.isNotEmpty(productId)) {
			param.setProduct_id(productId);
		}

		for (int page = 1;; page++) {
			param.setPage(page);
			
			// 仕入先商品一覧を取得(API)
			List<SuppliersProductsInfo> list = smarejiApiAccess.getSuppliersProductsInfo(smarejiUser.getContract().getId(), supplierId, param);
			/** テスト用 ローカルで動かす用mockを使用 *****************/ 
			// テスト用の仕入先商品一覧取得
	//		suppliersProductsInfoList = smarejiApiAccessMock.getSuppliersProductsInfo("dummyid", supplierId, param);
			/***********************************************/
			
			if (list == null || list.size() == 0) {
				break;
			}
			
			suppliersProductsInfoList.addAll(list);
		}
		
		logger.info("getSuppliersProductsInfo:仕入先商品一覧取得　処理終了");
		
		return suppliersProductsInfoList;
	}
	
//	/**
//	 * 
//	 * @param smarejiUser
//	 * @return
//	 */
//	public ProductsInfo getProductInfo(SmarejiUser smarejiUser, String productId) {
//		
//		logger.info("getProductInfo:商品情報取得　処理開始");
//		
//		// 仕入先一覧取得パラメータを設定
//		ParamProductInfo param = new ParamProductInfo();
//
//		// 取得項目
//		List<String> getParam = new ArrayList<String>();
//		getParam.add("productId");
//		getParam.add("categoryId");
//		getParam.add("productCode");
//		getParam.add("productName");
//		getParam.add("groupCode");
//		getParam.add("orderPoint");
//		param.setFields(getParam);
////		param.setLimit(1000);
//		
//		// 商品情報を取得(API)
//		ProductsInfo productsInfo = smarejiApiAccess.getProductInfo(smarejiUser.getContract().getId(), productId, param);
//		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
//		// テスト用の商品情報取得
////		ProductsInfo productsInfo = smarejiApiAccessMock.getProductInfo("dummyid", productId, param);
//		/***********************************************/
//		
//		return productsInfo;
//	}
	
	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public Map<String, ProductsInfo> getProductsInfoMap(SmarejiUser smarejiUser, String categoryId, String productCode, String groupCode, String supplierProductNo) {
		
		logger.info("getProductsInfo:商品一覧取得　処理開始");
		
		List<ProductsInfo> productsInfoList = new ArrayList<ProductsInfo>();
		
		// 仕入先一覧取得パラメータを設定
		ParamProductInfo param = new ParamProductInfo();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("productId");
		getParam.add("categoryId");
		getParam.add("productCode");
		getParam.add("productName");
		getParam.add("groupCode");
		getParam.add("orderPoint");
		param.setFields(getParam);
		
		// 取得上限数
		param.setLimit(API_LIMIT_LOOP);

		if (StringUtil.isNotEmpty(categoryId)) {
			param.setCategory_id(Integer.parseInt(categoryId));
		}
		
		if (StringUtil.isNotEmpty(productCode)) {
			param.setProduct_code(productCode);
		}
		
		if (StringUtil.isNotEmpty(groupCode)) {
			param.setGroup_code(groupCode);
		}
		
		if (StringUtil.isNotEmpty(supplierProductNo)) {
			param.setSupplier_product_no(supplierProductNo);
		}
		
		for (int page = 1;; page++) {
			param.setPage(page);
			
			// 商品一覧を取得(API)
			List<ProductsInfo> list = smarejiApiAccess.getProductsInfo(smarejiUser.getContract().getId(), param);
			/** テスト用 ローカルで動かす用mockを使用 *****************/ 
			// テスト用の商品情報取得
//			productsInfoList = smarejiApiAccessMock.getProductInfo("dummyid", param);
			/***********************************************/

			if (list == null || list.size() == 0) {
				break;
			}
			
			productsInfoList.addAll(list);
		}
		
		Iterator<ProductsInfo> it = productsInfoList.iterator();
		Map<String, ProductsInfo> productsInfoMap = new LinkedHashMap<String, ProductsInfo>();
		while (it.hasNext()) {
			// 仕入先ID、商品IDの設定
			ProductsInfo sup = it.next();
			productsInfoMap.put(sup.getProductId(), sup);
		}
		
		logger.info("getProductsInfo:商品一覧取得　処理終了");
		
		return productsInfoMap;
	}
	
	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public Map<String, String> getStocksInfoMap(SmarejiUser smarejiUser, String storeId) {
	
		logger.info("getStocksInfo:在庫一覧取得　処理開始");
		
		List<StockInfo> stocksInfoList = new ArrayList<StockInfo>();
		
		// 仕入先一覧取得パラメータを設定
		ParamStockInfo param = new ParamStockInfo();

		// 取得項目
		List<String> getParam = new ArrayList<String>();
		getParam.add("storeId");
		getParam.add("productId");
		getParam.add("stockAmount");
		param.setFields(getParam);
		param.setLimit(API_LIMIT_LOOP);

		param.setStore_id(Integer.parseInt(storeId));

		for (int page = 1;; page++) {
			param.setPage(page);
		
			// 在庫一覧を取得(API)
			List<StockInfo> list = smarejiApiAccess.getStocksInfo(smarejiUser.getContract().getId(), param);
			/** テスト用 ローカルで動かす用mockを使用 *****************/ 
			// テスト用の在庫一覧取得
	//		stocksInfoList = smarejiApiAccessMock.getStockInfo("dummyid");
			/***********************************************/

			if (list == null || list.size() == 0) {
				break;
			}
			
			stocksInfoList.addAll(list);
		}
		
		Iterator<StockInfo> it = stocksInfoList.iterator();
		Map<String, String> stocksInfoMap = new LinkedHashMap<String, String>();
		while (it.hasNext()) {
			// 仕入先ID、商品IDの設定
			StockInfo sup = it.next();
			stocksInfoMap.put(sup.getProductId(), sup.getStockAmount());
		}
		
		logger.info("getStocksInfo:在庫一覧取得　処理終了");
		
		return stocksInfoMap;
	}
	
	/**
	 * 
	 * @param smarejiUser
	 * @return
	 */
	public OrderInputForm getDisplayInfo(SmarejiUser smarejiUser, OrderInputForm object, String storeId) {

		OrderInputForm orderInputForm = new OrderInputForm();
		List<OrderInputSubForm> displayList = new ArrayList<OrderInputSubForm>();
		
		logger.info("*************************************************");

		// API
		// (0)商品画像一覧取得
		logger.info("(0)商品画像一覧取得");

		Map<String, String> productImageMap = getProductsImageInfo(smarejiUser);
		
		logger.info("商品画像一覧取得件数：" + productImageMap.size());
		
		logger.info("---------------------------------------");

		// API
		// (0)部門一覧取得
		logger.info("(0)部門一覧取得");

		Map<String, String> categoriesMap = getCategoriesInfo(smarejiUser);
		
		logger.info("部門一覧取得件数：" + categoriesMap.size());
		
		logger.info("---------------------------------------");

		// API
		// (3)商品一覧取得
		logger.info("(3)商品一覧取得");

		Map<String, ProductsInfo> productsInfoMap= getProductsInfoMap(smarejiUser, object.getCategoryId(), object.getProductCode(), object.getGroupCode(), object.getSupplierProductNo());

		logger.info("商品一覧取得件数：" + productsInfoMap.size());
		
		logger.info("---------------------------------------");

		// API
		// (1)仕入先一覧取得
		logger.info("(1)仕入先一覧取得");
		List<SuppliersInfo> supplierList = getSupplierInfo(smarejiUser);

		logger.info("仕入先一覧取得件数：" + supplierList.size());

		Iterator<SuppliersInfo> supplierIt = supplierList.iterator();

		while (supplierIt.hasNext()) {

			SuppliersInfo supplier = supplierIt.next();

			logger.info("---------------------------------------");

			logger.info("仕入先ID：" + supplier.getSupplierId());
			logger.info("仕入先名：" + supplier.getSupplierName());
			logger.info("部門ID：" + object.getCategoryId());
			logger.info("商品ID：" + object.getProductId());
			
			// API
			// (2)仕入先商品一覧取得
			logger.info("(2)仕入先商品一覧取得");
			List<SuppliersProductsInfo> suppliersProductsList = getSuppliersProductsInfo(smarejiUser, supplier.getSupplierId(), "", object.getProductId());

			logger.info("仕入先商品一覧取得件数：" + suppliersProductsList.size());
			
			Iterator<SuppliersProductsInfo> suppliersProductsIt = suppliersProductsList.iterator();

			
			while (suppliersProductsIt.hasNext()) {
			
				SuppliersProductsInfo suppliersProducts = suppliersProductsIt.next();

//				logger.info("商品ID：" + suppliersProducts.getProductId());
//				logger.info("部門ID：" + suppliersProducts.getCategoryId());
				
				OrderInputSubForm subForm = new OrderInputSubForm();

				// 商品情報が取得できない場合は表示対象としない
				if (!productsInfoMap.containsKey(suppliersProducts.getProductId())) {
					continue;
				}

				ProductsInfo productsInfo = productsInfoMap.get(suppliersProducts.getProductId());

				// 商品名が指定されている場合
				if (StringUtil.isNotEmpty(object.getProductName())) {
					// 一致しない場合は表示対象としない
					if (!object.getProductName().equals(productsInfo.getProductName())) {
						continue;
					}
				}
				
				subForm.setGroupCode(productsInfo.getGroupCode());	// グループコード
				String url = productImageMap.get(productsInfo.getProductId());
				subForm.setProductImage(StringUtil.isNotEmpty(url) ? url : "/img/no_image.png");	// 商品画像
				subForm.setProductId(productsInfo.getProductId());	// 商品ID
				subForm.setProductCode(productsInfo.getProductCode());	// 商品コード
				subForm.setProductName(productsInfo.getProductName());	// 商品名
				subForm.setProductInfo(productsInfo.getProductId() + " - " + productsInfo.getProductCode());	// 商品情報
				subForm.setSupplierName(supplier.getSupplierName());	// 仕入先名
				subForm.setSupplierId(supplier.getSupplierId());	// 仕入先ID

				// 部門が取得できない場合は表示対象としない
				if (!categoriesMap.containsKey(productsInfoMap.get(suppliersProducts.getProductId()).getCategoryId())) {
					continue;
				}
				
				subForm.setCategoryName(categoriesMap.get(productsInfoMap.get(suppliersProducts.getProductId()).getCategoryId()));	// 部門名
				subForm.setOrderPoint(StringUtils.isEmpty(productsInfo.getOrderPoint()) ? "0": productsInfo.getOrderPoint());	// 発注点
				
				displayList.add(subForm);
			}
		}

		logger.info("---------------------------------------");

		// API
		// (4)在庫一覧取得
		logger.info("(4)在庫一覧取得");
		Map<String, String> stocksMap = getStocksInfoMap(smarejiUser, storeId);

		logger.info("在庫一覧取得件数：" + stocksMap.size());
		
		for (int i = 0; i < displayList.size(); i++) {
			OrderInputSubForm subForm = displayList.get(i);
			
			if (StringUtil.isEmpty(subForm.getProductId())) {	// TODO
				continue;
			}
			
			String stockAmount = stocksMap.get(subForm.getProductId());
			subForm.setStockAmount(StringUtil.isEmpty(stockAmount) ? "0": stockAmount);
		}
		
		logger.info("---------------------------------------");

		orderInputForm.setDisplayList(displayList);
		
		logger.info("***表示件数：" + displayList.size());
		
		return orderInputForm;
	}

	/**
	 * 
	 * @param smarejiUser
	 * @param storeId
	 * @param identificationNo
	 * @param displayList
	 * @param orderAmountMap
	 * @param pagesize
	 * @return
	 */
	public Map<String, String> entryPurchaseOrder(SmarejiUser smarejiUser, String storeId, String identificationNo , List<OrderInputSubForm> displayList, Map<String, String[]> orderAmountMap, int pagesize) {

		Map<String, String> resultMap = new HashMap<String, String>();	// 返却用のオブジェクト
		List<String> storageInfoIdList = new ArrayList<String>();	// 発注IDリスト
		Map<String, List<String[]>> orderMap = new HashMap<String, List<String[]>>();	// 

		int preidx = 0;
		String[] orderAmount = null;
		for (int i = 0, j = 0; i < displayList.size(); i++, j++) {

			// 
			int idx = i / pagesize + 1;

			if (preidx != idx) {
				orderAmount = orderAmountMap.get(String.valueOf(idx));
				j = 0;
				preidx = idx;
			}

			// 発注点が存在しない場合はスキップ
			if (orderAmount == null || orderAmount.length == 0) {
				continue;
			}

			// 発注点が0の場合はスキップ
			if (StringUtils.isEmpty(orderAmount[j]) || "0".equals(orderAmount[j])) {
				continue;
			}
			
			String supplierId = displayList.get(i).getSupplierId();
			String productId = displayList.get(i).getProductId();
			// 仕入先が存在する場合
			if (orderMap.containsKey(supplierId)) {
				List<String[]> orderList = orderMap.get(supplierId);
				orderList.add(new String[] { productId, orderAmount[j] });
				orderMap.put(supplierId, orderList);
			// 仕入先が存在しない場合
			} else {
				List<String[]> orderList = new ArrayList<String[]>();
				orderList.add(new String[] { productId, orderAmount[j] });
				orderMap.put(supplierId, orderList);
			}

			if (j == pagesize - 1) {
				orderAmount = null;
			}
		}
		
		Iterator<Map.Entry<String, List<String[]>>> it = orderMap.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Map.Entry<String, List<String[]>> order = it.next();
			
			// 発注登録内容設定
			ParamEntryPurchaseOrder paramEntryPurchaseOrder = new ParamEntryPurchaseOrder();
			// 発注先ID
			paramEntryPurchaseOrder.setRecipientOrderId(order.getKey());	// 仕入先
			// 発注日
			paramEntryPurchaseOrder.setOrderedDate(DateUtil.getSysDateYyyyMmDd());	// システム日付
			// 識別番号
			paramEntryPurchaseOrder.setIdentificationNo(identificationNo);
			// 税丸め（0:四捨五入、1:切り捨て、2:切り上げ）
			// ステータス
			paramEntryPurchaseOrder.setStatus("5");
			// 発注処理時のスタッフID
			paramEntryPurchaseOrder.setStaffId(smarejiUser.getContract().getUser_id());
			
			// 発注対象商品 array ///////////////////////////////////////////////////////////
			ArrayList<ParamEntryPurchaseOrderProduct> opList = new ArrayList<ParamEntryPurchaseOrderProduct>(); 

			List<String[]> valueList = order.getValue();

			for (int j = 0; j < valueList.size(); j++) {
				String[] value = valueList.get(j);
				
				ParamEntryPurchaseOrderProduct op = new ParamEntryPurchaseOrderProduct();
				op.setProductId(value[0]);
				ArrayList<ParamEntryPurchaseOrderDeliveryStore> dsList = new ArrayList<ParamEntryPurchaseOrderDeliveryStore>();
				ParamEntryPurchaseOrderDeliveryStore ds = new ParamEntryPurchaseOrderDeliveryStore();
				ds.setStoreId(storeId);
				// 発注数量
				ds.setQuantity(value[1]);
				dsList.add(ds);
				op.setDeliveryStore(dsList);
				opList.add(op);
			}
			
			paramEntryPurchaseOrder.setProducts(opList);
			//////////////////////////////////////////////////////////////////////////////

			// 発注対象店舗 array ///////////////////////////////////////////////////////////
			ArrayList<ParamEntryPurchaseOrderStore> osList = new ArrayList<ParamEntryPurchaseOrderStore>();
			ParamEntryPurchaseOrderStore os = new ParamEntryPurchaseOrderStore();
			os.setStorageStoreId(storeId);
			osList.add(os);
			paramEntryPurchaseOrder.setStores(osList);
			//////////////////////////////////////////////////////////////////////////////
			

			PurchaseOrdersInfo purchaseOrdersInfo = smarejiApiAccess.entryPurchaseOrder(smarejiUser.getContract().getId(), paramEntryPurchaseOrder);
			
			logger.info("発注ID：" + purchaseOrdersInfo.getStorageInfoId());
			
			storageInfoIdList.add(purchaseOrdersInfo.getStorageInfoId());
			
			resultMap.put(order.getKey(), purchaseOrdersInfo.getStorageInfoId());
		}
		
		return resultMap;
	}

	/**
	 * 
	 * @param smarejiUser
	 * @param storageInfoIdList
	 */
	public void deletePurchaseOrder(SmarejiUser smarejiUser, List<String> storageInfoIdList) {
		for (int i = 0; i < storageInfoIdList.size(); i++) {
			smarejiApiAccess.deletePurchaseOrder(smarejiUser.getContract().getId(), storageInfoIdList.get(i));
		}
	}

	
	/**
	 * ページング処理
	 * 
	 * @param pageable 
	 * @param form 発注入力画面Form
	 * @return
	 */
	public Page<OrderInputSubForm> paging(Pageable pageable, OrderInputForm form) {

		List<OrderInputSubForm> dispList = form.getDisplayList();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<OrderInputSubForm> list;
	
		if(dispList.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize , dispList.size());
			list = dispList.subList(startItem, toIndex);
		}
		
		Page<OrderInputSubForm> orderInputPage = new PageImpl<OrderInputSubForm>(list, PageRequest.of(currentPage, pageSize),dispList.size());
		return orderInputPage;
		
	}
}
