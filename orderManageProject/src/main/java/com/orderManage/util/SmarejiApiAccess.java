package com.orderManage.util;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderManage.model.ApplicationPropertyModel;
import com.orderManage.model.api.AccessToken;
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
import com.orderManage.model.api.UserAccessToken;
import com.orderManage.model.param.ParamCategorieInfo;
import com.orderManage.model.param.ParamEntryPurchaseOrder;
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
import com.orderManage.model.param.ParamUpdatePurchaseOrder;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.service.OrderManageLoggingService;

/**
 * スマレジAPIを使用して情報を取得するクラス
 * 
 * （注意点）
 * APIの戻り値（json）をJavaオブジェクトに格納する場合は、
 * スマレジで設定しているレスポンスの項目名とクラスの変数定義名を
 * 同じにしなければならない
 * 
 * TODO ログ出力、エラーハンドリング時の返却値
 * 
 */
@RestController
public class SmarejiApiAccess {
	
	/* プロパティ値 20230118 add*/
	@Autowired
	public ApplicationPropertyModel app_properties; 
	
	/* ログ管理クラス定義 */
	@Autowired
	OrderManageLoggingService logger;
	
	/* Json変換に使用 */
	@Autowired
	private ObjectMapper objectMapper;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	// 認証関連
	/**
	 * getUserAccessToken
	 * 
	 * ユーザーアクセストークンを取得する(POST)
	 * 
	 * @param code 認可コード
	 */
	public UserAccessToken getUserAccessToken(String code) {
		
		// URL作成
		String url =  app_properties.getUrlId() + "authorize/token";
      
		// Basic認証のヘッダー情報を作成 ///////////////////////////////////////////
		String clientNmPass = app_properties.getClientId() + ":" + app_properties.getClientSecret();
		String userPassword = new String(Base64.getEncoder().encode(clientNmPass.getBytes()));
		StringBuilder basicAuth = new StringBuilder();
		basicAuth.append("Basic ").append(userPassword);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", basicAuth.toString());
		/////////////////////////////////////////////////////////////////
		
		// Body設定 /////////////////////////////////////////////////////
		// MultiValueMapでないと　「No HttpMessageConverter for com.orderManage.sumareji.api.object.AccessTokenBody and content type "application/x-www-form-urlencoded"」が発生
		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("grant_type", "authorization_code");
		bodyMap.add("code", code);	
		////////////////////////////////////////////////////////////////

		// スマレジAPIにアクセス ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();		
        RequestEntity<MultiValueMap> request = RequestEntity.post(URI.create(url))
              .contentType(MediaType.APPLICATION_FORM_URLENCODED)
              .headers(headers)
              .body(bodyMap);
        
        // API結果をUserAccessTokenクラスに格納している
        ResponseEntity<UserAccessToken> response = restTemplate.exchange(request, UserAccessToken.class);
		///////////////////////////////////////////////////////////////////
      
  		return response.getBody();
	}
	
	/**
	 * getSmarejiUserInfo
	 * 
	 * スマレジユーザ情報を取得(POST)
	 * 
	 * @param userAccessToken ユーザーアクセストークン
	 */
	public SmarejiUser getSmarejiUserInfo(String userAccessToken) {
		
		// URL作成
		String url = app_properties.getUrlId() + "userinfo";
		
		// ヘッダー情報を作成 ///////////////////////////////////////////
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", userAccessToken);
		/////////////////////////////////////////////////////////////////
		
		// スマレジAPIにアクセス ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();		
        RequestEntity<?> request = RequestEntity.post(URI.create(url))
              .contentType(MediaType.APPLICATION_FORM_URLENCODED)
              .headers(headers)
              // body設定は不要
              .body("");
        
        // API結果をUserAccessTokenクラスに格納している
        ResponseEntity<SmarejiUser> response = restTemplate.exchange(request, SmarejiUser.class);
		///////////////////////////////////////////////////////////////////
	
		return response.getBody();
	}

	/**
	 * getAccessToken
	 * 
	 * アクセストークンを取得する(POST)
	 * 
	 * TODO 使用するAPI毎にスコープを指定したほうが良いか？引数追加
	 * 
	 * @param contractId アプリ契約ID
	 */
	public AccessToken getAccessToken(String contractId) {

		// URL作成
		String url = app_properties.getUrlId() + "app/" + contractId + "/token";
      
		// Basic認証のヘッダー情報を作成 ///////////////////////////////////////////
		String clientNmPass = app_properties.getClientId() + ":" + app_properties.getClientSecret();
		String userPassword = new String(Base64.getEncoder().encode(clientNmPass.getBytes()));
		StringBuilder basicAuth = new StringBuilder();
		basicAuth.append("Basic ").append(userPassword);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", basicAuth.toString());
		/////////////////////////////////////////////////////////////////
		
		// Body設定 ////////////
		// MultiValueMapでないと　「No HttpMessageConverter for com.orderManage.sumareji.api.object.AccessTokenBody and content type "application/x-www-form-urlencoded"」が発生
		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("grant_type", "client_credentials");
		bodyMap.add("scope", app_properties.getAplScope());	
		///////////////////////

		// スマレジAPIにアクセス ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();
        RequestEntity<MultiValueMap> request = RequestEntity.post(URI.create(url))
              .contentType(MediaType.APPLICATION_FORM_URLENCODED)
              .headers(headers)
              .body(bodyMap);
        
        // API結果をAccessTokenクラスに格納している
        ResponseEntity<AccessToken> response = restTemplate.exchange(request, AccessToken.class);
		///////////////////////////////////////////////////////////////////
      
  		return response.getBody();
	}
    /////////////////////////////////////////////////////////////////////////////////////////////////
	
    /////////////////////////////////////////////////////////////////////////////////////////////////
	// サービス関連
	/**
	 * getStoresInfo
	 * 
	 * 店舗一覧情報を取得する(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramStoresInfo 店舗取得パラメータ
	 * 
	 * @return List 店舗一覧情報
	 */
	public List<StoreInfo> getStoresInfo(String contractId, ParamStoreInfo paramStoresInfo) {

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 店舗一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + "/pos/stores");

		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		if (Objects.nonNull(paramStoresInfo.getFields())) {
			// 検索パラメータ
			paramMap.add("fields", String.join(",", paramStoresInfo.getFields()));
		}
		if (StringUtils.hasLength(paramStoresInfo.getSort())) {
			// ソート
			paramMap.add("sort", paramStoresInfo.getSort());
		}
		if (paramStoresInfo.getLimit() != 0) {
			// 上限
			paramMap.add("limit", String.valueOf(paramStoresInfo.getLimit()));
		}
		if (paramStoresInfo.getPage() != 0) {
			// ページ
			paramMap.add("page", String.valueOf(paramStoresInfo.getPage()));
		}
		if (StringUtils.hasLength(paramStoresInfo.getStore_code())) {
			// 店舗コード
			paramMap.add("store_code", paramStoresInfo.getStore_code());
		}
		if (StringUtils.hasLength(paramStoresInfo.getDivision())) {
			// 店舗区分
			paramMap.add("division", paramStoresInfo.getDivision());
		}
		if (StringUtils.hasLength(paramStoresInfo.getWith_point_condition())) {
			// ポイント情報を付加するか
			paramMap.add("with_point_condition", paramStoresInfo.getWith_point_condition());
		}
		if (StringUtils.hasLength(paramStoresInfo.getWith_receipt_print_info())) {
			// レシート印刷情報を付加するか
			paramMap.add("with_receipt_print_info", paramStoresInfo.getWith_receipt_print_info());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<StoreInfo[]> response = restTemplate.exchange(request, StoreInfo[].class);
		
		// 配列に格納して返却
		StoreInfo[] storesInfo = response.getBody();
		return Arrays.asList(storesInfo);
	}
	
	
	/**
	 * getStoreInfo
	 * 
	 * 店舗情報(個別)を取得する(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param storeId 店舗ID
	 * @param paramStoresInfo 店舗取得パラメータ
	 * 
	 * @return StoreInfo 店舗情報
	 */
	public StoreInfo getStoreInfo(String contractId, String storeId, ParamStoreInfo paramStoresInfo) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 店舗情報取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() 
				+ contractId 
				+ "/pos/stores/" + storeId);
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		if (Objects.nonNull(paramStoresInfo.getFields())) {
			// 検索パラメータ
			paramMap.add("fields", String.join(",", paramStoresInfo.getFields()));
		}
		if (StringUtils.hasLength(paramStoresInfo.getWith_point_condition())) {
			// ポイント情報を付加
			paramMap.add("with_point_condition", String.join(",", paramStoresInfo.getWith_point_condition()));
		}
		if (StringUtils.hasLength(paramStoresInfo.getWith_receipt_print_info())) {
			// レシート印刷情報を付加
			paramMap.add("with_point_condition", String.join(",", paramStoresInfo.getWith_receipt_print_info()));
		}
		
		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<StoreInfo> response = restTemplate.exchange(request, StoreInfo.class);

		return response.getBody();
	}
	
	/**
	 * getPurchaseOrdersInfo
	 * 
	 * 発注一覧情報を取得する(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramPurchaseOrdersInfo 発注パラメータ
	 * 
	 * @return List 発注一覧情報
	 */
	public List<PurchaseOrdersInfo> getPurchaseOrdersInfo(String contractId, 
			ParamPurchaseOrderInfo paramPurchaseOrdersInfo) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() 
				+ contractId + "/pos/purchase_orders");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		if (Objects.nonNull(paramPurchaseOrdersInfo.getFields())) {
			// 検索パラメータ
			paramMap.add("fields", String.join(",", paramPurchaseOrdersInfo.getFields()));
		}
		if (StringUtils.hasLength(paramPurchaseOrdersInfo.getSort())) {
			// ソート
			paramMap.add("sort", paramPurchaseOrdersInfo.getSort());
		}
		if (paramPurchaseOrdersInfo.getLimit() != 0) {
			// 上限
			paramMap.add("limit", String.valueOf(paramPurchaseOrdersInfo.getLimit()));
		}
		if (paramPurchaseOrdersInfo.getPage() != 0) {
			// ページ
			paramMap.add("page", String.valueOf(paramPurchaseOrdersInfo.getPage()));
		}
		if (StringUtils.hasLength(paramPurchaseOrdersInfo.getRecipient_order_id())) {
			// 発注先ID
			paramMap.add("recipient_order_id", paramPurchaseOrdersInfo.getRecipient_order_id());
		}
		if (StringUtils.hasLength(paramPurchaseOrdersInfo.getOrdered_date())) {
			// 発注日
			paramMap.add("ordered_date", paramPurchaseOrdersInfo.getOrdered_date());
		}
		if (StringUtils.hasLength(paramPurchaseOrdersInfo.getStatus())) {
			// ステータス
			paramMap.add("status", paramPurchaseOrdersInfo.getStatus());
		}
		
		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<PurchaseOrdersInfo[]> response = restTemplate.exchange(request, PurchaseOrdersInfo[].class);
		
		// 配列に格納して返却
		PurchaseOrdersInfo[] purchaseOrdersInfo = response.getBody();
		return Arrays.asList(purchaseOrdersInfo);
	}
	
	/**
	 * getStoreInfo
	 * 
	 * 発注情報(個別)を取得する(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param storageInfoId 発注ID
	 * @param paramPurchaseOrdersInfo 発注パラメータ
	 * 
	 * @return purchaseOrdersInfo 発注情報
	 */
	public PurchaseOrdersInfo getPurchaseOrderInfo(String contractId, 
			String storageInfoId, ParamPurchaseOrderInfo paramPurchaseOrdersInfo) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注情報取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() 
				+ contractId 
				+ "/pos/purchase_orders/" + storageInfoId);
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		if (Objects.nonNull(paramPurchaseOrdersInfo.getFields())) {
			// 検索パラメータ
			paramMap.add("fields", String.join(",", paramPurchaseOrdersInfo.getFields()));
		}
		if (StringUtils.hasLength(paramPurchaseOrdersInfo.getWith_products())) {
			// ポイント情報を付加
			paramMap.add("with_products", String.join(",", paramPurchaseOrdersInfo.getWith_products()));
		}
		if (StringUtils.hasLength(paramPurchaseOrdersInfo.getWith_stores())) {
			// レシート印刷情報を付加
			paramMap.add("with_stores", String.join(",", paramPurchaseOrdersInfo.getWith_stores()));
		}
		
		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<PurchaseOrdersInfo> response = restTemplate.exchange(request, PurchaseOrdersInfo.class);

		return response.getBody();
	}
	
	/**
	 * entryPurchaseOrder
	 * 
	 * 発注登録を行う(POST)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramEntryPurchaseOrder 発注登録パラメータ
	 * 
	 * @return purchaseOrdersInfo 発注情報
	 */
	public PurchaseOrdersInfo entryPurchaseOrder(String contractId, ParamEntryPurchaseOrder paramEntryPurchaseOrder) {
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注登録URL
		String url = app_properties.getUrlApi() + contractId + "/pos/purchase_orders/";
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());

		// 登録内容をJSON形式に変換
		String jsonStr = OrderManageUtil.objectToJson(paramEntryPurchaseOrder);
		if (Objects.isNull(jsonStr)) {
			// Json変換結果がnull
			logger.error("Json形式の変換に失敗しました。");
			return null;
		}
		
		// スマレジAPIにアクセス ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();
//        RequestEntity<ParamEntryPurchaseOrder> request = RequestEntity.post(URI.create(url))
//        	.contentType(MediaType.APPLICATION_JSON)
//        	.headers(headers)
//        	.body(paramEntryPurchaseOrder);
        RequestEntity<String> request = RequestEntity.post(URI.create(url))
            	.contentType(MediaType.APPLICATION_JSON)
            	.headers(headers)
            	.body(jsonStr);
        
        // API結果をPurchaseOrdersInfoクラスに格納している
        ResponseEntity<PurchaseOrdersInfo> response = restTemplate.exchange(request, PurchaseOrdersInfo.class);
		///////////////////////////////////////////////////////////////////
	
        return response.getBody();
	}
	
	/**
	 * updatePurchaseOrder
	 * 
	 * 発注更新を行う(PATCH)
	 *
	 * @param contractId アプリ契約ID
	 * @param orderId 発注ID
	 * @param paramUpdatePurchaseOrder 発注更新パラメータ
	 * 
	 * @return purchaseOrdersInfo 発注情報
	 */
	public PurchaseOrdersInfo updatePurchaseOrder(String contractId, String orderId,
			ParamUpdatePurchaseOrder paramUpdatePurchaseOrder) {
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注更新URL
		String url = app_properties.getUrlApi() + contractId + "/pos/purchase_orders/" + orderId;
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());

		// 登録内容をJSON形式に変換
		String jsonStr = OrderManageUtil.objectToJson(paramUpdatePurchaseOrder);
		if (Objects.isNull(jsonStr)) {
			// Json変換結果がnull
			logger.error("Json形式の変換に失敗しました。");
			return null;
		}
		
		// スマレジAPIにアクセス ////////////////////////////////////////////
		
		// PATCHメソッドを使用するための定義
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);
		//

//        RequestEntity<ParamUpdatePurchaseOrder> request = RequestEntity.patch(URI.create(url))
//        	.contentType(MediaType.APPLICATION_JSON)
//        	.headers(headers)
//        	.body(paramUpdatePurchaseOrder);
        RequestEntity<String> request = RequestEntity.patch(URI.create(url))
            	.contentType(MediaType.APPLICATION_JSON)
            	.headers(headers)
            	.body(jsonStr);
        
        // API結果をPurchaseOrdersInfoクラスに格納している
        ResponseEntity<PurchaseOrdersInfo> response = restTemplate.exchange(request, PurchaseOrdersInfo.class);
		///////////////////////////////////////////////////////////////////
	
        return response.getBody();

	}
	
	/**
	 * deletePurchaseOrder
	 * 
	 * 発注削除を行う(PATCH)
	 *
	 * @param contractId アプリ契約ID
	 * @param orderId 発注ID
	 * 
	 * @return レスポンス結果
	 */
	public void deletePurchaseOrder(String contractId, String orderId) {
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注削除URL
		String url = app_properties.getUrlApi() + contractId + "/pos/purchase_orders/" + orderId;
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// スマレジAPIにアクセス ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<?> request = RequestEntity.delete(URI.create(url)).headers(headers).build();
		
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
	}
	
	/**
	 * getPurchaseOrdersProductsInfo
	 * 
	 * 発注対象商品取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param orderId 発注ID
	 * @param paramUpdatePurchaseOrder 発注対象商品パラメータ
	 * 
	 * @return List 発注対象商品一覧情報
	 */
	public List<PurchaseOrdersProductsInfo> getPurchaseOrdersProductsInfo(String contractId, String orderId,
			ParamPurchaseOrderProduct paramPurchaseOrderProduct) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注対象商品取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + 
				contractId + "/pos/purchase_orders/" + orderId + "/products");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();

		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramPurchaseOrderProduct.getFields())) {
			paramMap.add("fields", String.join(",", paramPurchaseOrderProduct.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramPurchaseOrderProduct.getSort())) {
			paramMap.add("sort", paramPurchaseOrderProduct.getSort());
		}
		// 上限
		if (paramPurchaseOrderProduct.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramPurchaseOrderProduct.getLimit()));
		}
		// ページ
		if (paramPurchaseOrderProduct.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramPurchaseOrderProduct.getPage()));
		}
		// 商品ID
		if (StringUtils.hasLength(paramPurchaseOrderProduct.getProduct_id())) {
			paramMap.add("product_id", paramPurchaseOrderProduct.getProduct_id());
		}
		// 配送先店舗付加フラグ
		if (StringUtils.hasLength(paramPurchaseOrderProduct.getWith_deliveries())) {
			paramMap.add("with_deliveries", paramPurchaseOrderProduct.getWith_deliveries());
		}
		
		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<PurchaseOrdersProductsInfo[]> response = restTemplate.exchange(request, PurchaseOrdersProductsInfo[].class);
		
		// 配列に格納して返却
		PurchaseOrdersProductsInfo[] purchaseOrdersProductsInfo = response.getBody();
		return Arrays.asList(purchaseOrdersProductsInfo);
		
	}
	
	/**
	 * getPurchaseOrdersStoresInfo
	 * 
	 * 発注対象店舗取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param orderId 発注ID
	 * @param paramUpdatePurchaseOrder 発注店舗商品パラメータ
	 * 
	 * @return List 発注対象店舗一覧情報
	 */
	public List<PurchaseOrdersStoresInfo> getPurchaseOrdersStoresInfo(String contractId, String orderId,
			ParamPurchaseOrderStore paramPurchaseOrderStore) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 発注対象商品取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + 
				contractId + "/pos/purchase_orders/" + orderId + "/stores");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();

		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramPurchaseOrderStore.getFields())) {
			paramMap.add("fields", String.join(",", paramPurchaseOrderStore.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramPurchaseOrderStore.getSort())) {
			paramMap.add("sort", paramPurchaseOrderStore.getSort());
		}
		// 上限
		if (paramPurchaseOrderStore.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramPurchaseOrderStore.getLimit()));
		}
		// ページ
		if (paramPurchaseOrderStore.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramPurchaseOrderStore.getPage()));
		}
		// 配送店舗ID
		if (StringUtils.hasLength(paramPurchaseOrderStore.getStorage_store_id())) {
			paramMap.add("storage_store_id", paramPurchaseOrderStore.getStorage_store_id());
		}
		
		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<PurchaseOrdersStoresInfo[]> response = restTemplate.exchange(request, PurchaseOrdersStoresInfo[].class);
		
		// 配列に格納して返却
		PurchaseOrdersStoresInfo[] purchaseOrdersStoresInfo = response.getBody();
		return Arrays.asList(purchaseOrdersStoresInfo);
	}
	
	/**
	 * getSuppliersInfo
	 * 
	 * 仕入先一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramSuppliersInfo 仕入先取得パラメータ
	 * 
	 * @return List 仕入先一覧情報
	 */
	public List<SuppliersInfo> getSuppliersInfo(String contractId, ParamSupplierInfo paramSuppliersInfo){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 仕入先一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/suppliers");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramSuppliersInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramSuppliersInfo.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramSuppliersInfo.getSort())) {
			paramMap.add("sort", paramSuppliersInfo.getSort());
		}
		// 上限
		if (paramSuppliersInfo.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramSuppliersInfo.getLimit()));
		}
		// ページ
		if (paramSuppliersInfo.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramSuppliersInfo.getPage()));
		}
		// 仕入先ID
		if (StringUtils.hasLength(paramSuppliersInfo.getSupplier_id())) {
			paramMap.add("supplier_id", paramSuppliersInfo.getSupplier_id());
		}
		// 仕入先コード
		if (StringUtils.hasLength(paramSuppliersInfo.getSupplier_code())) {
			paramMap.add("supplier_code", paramSuppliersInfo.getSupplier_code());
		}
		// 仕入先区分ID
		if (StringUtils.hasLength(paramSuppliersInfo.getSupplier_division_id())) {
			paramMap.add("supplier_division_id", paramSuppliersInfo.getSupplier_division_id());
		}
		
		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<SuppliersInfo[]> response = restTemplate.exchange(request, SuppliersInfo[].class);
		
		// 配列に格納して返却
		SuppliersInfo[] suppliersInfo = response.getBody();
		return Arrays.asList(suppliersInfo);

	}
	
	/**
	 * getSuppliersProductsInfo
	 * 
	 * 仕入先商品一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param supplierId 仕入先ID
	 * 
	 * @return List 仕入先商品一覧情報
	 */
	public List<SuppliersProductsInfo> getSuppliersProductsInfo(String contractId, String supplierId,
			ParamSupplierProduct paramSupplierProduct) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 仕入先商品一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + 
				contractId + "/pos/suppliers/" + supplierId + "/products");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramSupplierProduct.getFields())) {
			paramMap.add("fields", String.join(",", paramSupplierProduct.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramSupplierProduct.getSort())) {
			paramMap.add("sort", paramSupplierProduct.getSort());
		}
		// 上限
		if (paramSupplierProduct.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramSupplierProduct.getLimit()));
		}
		// ページ
		if (paramSupplierProduct.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramSupplierProduct.getPage()));
		}
		// 部門ID
		if (StringUtils.hasLength(paramSupplierProduct.getCategory_id())) {
			paramMap.add("category_id", paramSupplierProduct.getCategory_id());
		}
		// 商品ID
		if (StringUtils.hasLength(paramSupplierProduct.getProduct_id())) {
			paramMap.add("product_id", paramSupplierProduct.getProduct_id());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<SuppliersProductsInfo[]> response = restTemplate.exchange(request, SuppliersProductsInfo[].class);
		
		// 配列に格納して返却
		SuppliersProductsInfo[] suppliersProductsInfo = response.getBody();
		return Arrays.asList(suppliersProductsInfo);
	}
	
	/**
	 * getProductsInfo
	 * 
	 * 商品一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramProductInfo 商品一覧取得パラメータ
	 * 
	 * @return List 商品一覧情報
	 */
	public List<ProductsInfo> getProductsInfo(String contractId, ParamProductInfo paramProductInfo){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 商品一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/products");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramProductInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramProductInfo.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramProductInfo.getSort())) {
			paramMap.add("sort", paramProductInfo.getSort());
		}
		// 上限
		if (paramProductInfo.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramProductInfo.getLimit()));
		}
		// ページ
		if (paramProductInfo.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramProductInfo.getPage()));
		}
		// 部門ID
		if (paramProductInfo.getCategory_id() != 0) {
			paramMap.add("category_id", String.valueOf(paramProductInfo.getCategory_id()));
		}
		// 商品コード
		if (StringUtils.hasLength(paramProductInfo.getProduct_code())) {
			paramMap.add("product_code", paramProductInfo.getProduct_code());
		}
		// グループコード
		if (StringUtils.hasLength(paramProductInfo.getGroup_code())) {
			paramMap.add("group_code", paramProductInfo.getGroup_code());
		}
		// 端末表示 
		if (StringUtils.hasLength(paramProductInfo.getDisplay_flag())) {
			paramMap.add("display_flag", paramProductInfo.getDisplay_flag());
		}
		// 商品区分
		if (StringUtils.hasLength(paramProductInfo.getDivision())) {
			paramMap.add("division", paramProductInfo.getDivision());
		}
		// 売上区分
		if (StringUtils.hasLength(paramProductInfo.getSales_division())) {
			paramMap.add("sales_division", paramProductInfo.getSales_division());
		}
		// 在庫管理区分
		if (StringUtils.hasLength(paramProductInfo.getStock_control_division())) {
			paramMap.add("stock_control_division", paramProductInfo.getStock_control_division());
		}
		// 品番
		if (StringUtils.hasLength(paramProductInfo.getSupplier_product_no())) {
			paramMap.add("supplier_product_no", paramProductInfo.getSupplier_product_no());
		}
		// 更新日時(From)
		if (StringUtils.hasLength(paramProductInfo.getUpd_date_time_from())) {
			paramMap.add("upd_date_time-from", paramProductInfo.getUpd_date_time_from());
		}
		// 更新日時(To)
		if (StringUtils.hasLength(paramProductInfo.getUpd_date_time_to())) {
			paramMap.add("upd_date_time-to", paramProductInfo.getUpd_date_time_to());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<ProductsInfo[]> response = restTemplate.exchange(request, ProductsInfo[].class);
		
		// 配列に格納して返却
		ProductsInfo[] productsInfo = response.getBody();
		return Arrays.asList(productsInfo);

	}
	
	/**
	 * getStocksInfo
	 * 
	 * 在庫一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramStockInfo 在庫一覧取得パラメータ
	 * 
	 * @return List 在庫一覧情報
	 */
	public List<StockInfo> getStocksInfo(String contractId, ParamStockInfo paramStockInfo){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 在庫一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/stock");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramStockInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramStockInfo.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramStockInfo.getSort())) {
			paramMap.add("sort", paramStockInfo.getSort());
		}
		// 上限
		if (paramStockInfo.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramStockInfo.getLimit()));
		}
		// ページ
		if (paramStockInfo.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramStockInfo.getPage()));
		}
		// 店舗ID
		if (paramStockInfo.getStore_id() != 0) {
			paramMap.add("store_id", String.valueOf(paramStockInfo.getStore_id()));
		}
		// 商品ID
		if (StringUtils.hasLength(paramStockInfo.getProduct_id())) {
			paramMap.add("sort", paramStockInfo.getProduct_id());
		}
		// 更新日時(From)
		if (StringUtils.hasLength(paramStockInfo.getUpd_date_time_from())) {
			paramMap.add("upd_date_time-from", paramStockInfo.getUpd_date_time_from());
		}
		// 更新日時(To)
		if (StringUtils.hasLength(paramStockInfo.getUpd_date_time_to())) {
			paramMap.add("upd_date_time-to", paramStockInfo.getUpd_date_time_to());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<StockInfo[]> response = restTemplate.exchange(request, StockInfo[].class);
		
		// 配列に格納して返却
		StockInfo[] stockInfo = response.getBody();
		return Arrays.asList(stockInfo);

	}
	/**
	 * getCategoriesInfo
	 * 
	 * 部門一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramCategorieInfo 部門一覧取得パラメータ
	 * 
	 * @return List 部門一覧情報
	 */
	public List<CategorieInfo> getCategoriesInfo(String contractId, ParamCategorieInfo paramCategorieInfo){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 部門一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/categories");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramCategorieInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramCategorieInfo.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramCategorieInfo.getSort())) {
			paramMap.add("sort", paramCategorieInfo.getSort());
		}
		// 上限
		if (paramCategorieInfo.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramCategorieInfo.getLimit()));
		}
		// ページ
		if (paramCategorieInfo.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramCategorieInfo.getPage()));
		}
		// 部門コード
		if (StringUtils.hasLength(paramCategorieInfo.getCategory_code())) {
			paramMap.add("category_code", paramCategorieInfo.getCategory_code());
		}
		// 階層レベル
		if (StringUtils.hasLength(paramCategorieInfo.getLevel())) {
			paramMap.add("level", paramCategorieInfo.getLevel());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<CategorieInfo[]> response = restTemplate.exchange(request, CategorieInfo[].class);
		
		// 配列に格納して返却
		CategorieInfo[] categorieInfo = response.getBody();
		return Arrays.asList(categorieInfo);

	}
	/**
	 * getCategorieInfo
	 * 
	 * 部門情報(個別)を取得する(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param categorieId 部門ID
	 * @param paramCategorieInfo 部門取得パラメータ
	 * 
	 * @return StoreInfo 部門情報
	 */
	public CategorieInfo getCategorieInfo(String contractId, String categorieId, ParamCategorieInfo paramCategorieInfo) {
		
		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 部門情報取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() 
				+ contractId 
				+ "/pos/categories/" + categorieId);
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramCategorieInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramCategorieInfo.getFields()));
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<CategorieInfo> response = restTemplate.exchange(request, CategorieInfo.class);

		return response.getBody();
	}
	/**
	 * getStaffsInfo
	 * 
	 * スタッフ一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramStaffInfo スタッフ一覧取得パラメータ
	 * 
	 * @return List スタッフ一覧情報
	 */
	public List<StaffInfo> getStaffsInfo(String contractId, ParamStaffInfo paramStaffInfo){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// スタッフ一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/staffs");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramStaffInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramStaffInfo.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramStaffInfo.getSort())) {
			paramMap.add("sort", paramStaffInfo.getSort());
		}
		// 上限
		if (paramStaffInfo.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramStaffInfo.getLimit()));
		}
		// ページ
		if (paramStaffInfo.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramStaffInfo.getPage()));
		}
		// スタッフID
		if (paramStaffInfo.getStaff_id() != 0) {
			paramMap.add("staff_id", String.valueOf(paramStaffInfo.getStaff_id()));
		}
		// ユーザー識別子
		if (paramStaffInfo.getUser_id() != 0) {
			paramMap.add("user_id", String.valueOf(paramStaffInfo.getUser_id()));
		}
		// スタッフコード
		if (StringUtils.hasLength(paramStaffInfo.getStaff_code())) {
			paramMap.add("staff_code", paramStaffInfo.getStaff_code());
		}
		// 役割・役職ID
		if (StringUtils.hasLength(paramStaffInfo.getRole_id())) {
			paramMap.add("role_id", paramStaffInfo.getRole_id());
		}
		// ランク
		if (StringUtils.hasLength(paramStaffInfo.getRank())) {
			paramMap.add("rank", paramStaffInfo.getRank());
		}
		// ログインフラグ
		if (StringUtils.hasLength(paramStaffInfo.getLogin_staff_flag())) {
			paramMap.add("login_staff_flag", paramStaffInfo.getLogin_staff_flag());
		}
		// 表示フラグ
		if (StringUtils.hasLength(paramStaffInfo.getDisplay_flag())) {
			paramMap.add("display_flag", paramStaffInfo.getDisplay_flag());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<StaffInfo[]> response = restTemplate.exchange(request, StaffInfo[].class);
		
		// 配列に格納して返却
		StaffInfo[] staffInfo = response.getBody();
		return Arrays.asList(staffInfo);
	}
	
	/**
	 * getProductsImage
	 * 
	 * 商品画像一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramProductImage 商品画像一覧取得パラメータ
	 * 
	 * @return List 商品画像一覧情報
	 */
	public List<ProductImageInfo> getProductsImage(String contractId, ParamProductImage paramProductImage){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 商品画像一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/products/images");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramProductImage.getFields())) {
			paramMap.add("fields", String.join(",", paramProductImage.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramProductImage.getSort())) {
			paramMap.add("sort", paramProductImage.getSort());
		}
		// 上限
		if (paramProductImage.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramProductImage.getLimit()));
		}
		// ページ
		if (paramProductImage.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramProductImage.getPage()));
		}
		// 商品ID
		if (StringUtils.hasLength(paramProductImage.getProduct_id())) {
			paramMap.add("product_id", paramProductImage.getProduct_id());
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<ProductImageInfo[]> response = restTemplate.exchange(request, ProductImageInfo[].class);
		
		// 配列に格納して返却
		ProductImageInfo[] productImage = response.getBody();
		return Arrays.asList(productImage);
	}
	
	/**
	 * getProductAttributes
	 * 
	 * 商品属性一覧取得を行う(GET)
	 *
	 * @param contractId アプリ契約ID
	 * @param paramProductAttributeInfo 商品属性一覧取得パラメータ
	 * 
	 * @return List 商品画像一覧情報
	 */
	public List<ProductAttributeInfo> getProductAttributes(String contractId, 
			ParamProductAttributeInfo paramProductAttributeInfo){

		// アクセストークン取得 ※必須
		AccessToken at = getAccessToken(contractId);
		
		// 商品属性一覧取得URL
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(app_properties.getUrlApi() + contractId + 
				"/pos/products/attributes");
		
		// ヘッダー情報を作成
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + at.getAccess_token());
		
		// クエリパラメータ設定 設定されているものだけをパラメータに設定する
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		
		// パラメータをMapに設定
		// 検索パラメータ
		if (Objects.nonNull(paramProductAttributeInfo.getFields())) {
			paramMap.add("fields", String.join(",", paramProductAttributeInfo.getFields()));
		}
		// ソート
		if (StringUtils.hasLength(paramProductAttributeInfo.getSort())) {
			paramMap.add("sort", paramProductAttributeInfo.getSort());
		}
		// 上限
		if (paramProductAttributeInfo.getLimit() != 0) {
			paramMap.add("limit", String.valueOf(paramProductAttributeInfo.getLimit()));
		}
		// ページ
		if (paramProductAttributeInfo.getPage() != 0) {
			paramMap.add("page", String.valueOf(paramProductAttributeInfo.getPage()));
		}

		String uri = builder.queryParams(paramMap).toUriString();

		// スマレジAPIにアクセス(GET) ////////////////////////////////////////////
		RestTemplate restTemplate = new RestTemplate();	
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).headers(headers).build();

		ResponseEntity<ProductAttributeInfo[]> response = restTemplate.exchange(request, ProductAttributeInfo[].class);
		
		// 配列に格納して返却
		ProductAttributeInfo[] productAttribute = response.getBody();
		return Arrays.asList(productAttribute);
	}
}
