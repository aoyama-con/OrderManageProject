package com.orderManage.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orderManage.controller.object.CheckOrderConfirmForm;
import com.orderManage.controller.object.CheckOrderConfirmSubForm;
import com.orderManage.controller.object.CheckOrderStatusForm;
import com.orderManage.controller.object.CheckOrderStatusSubForm;
import com.orderManage.controller.object.OrderConfirmForm;
import com.orderManage.controller.object.OrderConfirmSubForm;
import com.orderManage.controller.object.OrderHistoryForm;
import com.orderManage.controller.object.OrderHistorySubForm;
import com.orderManage.controller.object.OrderInputForm;
import com.orderManage.controller.object.OrderInputSubForm;
import com.orderManage.controller.object.StoreChoiceForm;
import com.orderManage.model.ApplicationPropertyModel;
import com.orderManage.model.api.PurchaseOrdersInfo;
import com.orderManage.model.api.StaffInfo;
import com.orderManage.model.api.StoreInfo;
import com.orderManage.model.api.UserAccessToken;
import com.orderManage.model.session.OrderHistSessionInfo;
import com.orderManage.model.session.OrderSessionInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.service.CheckOrderConfirmService;
import com.orderManage.service.CheckOrderStatusService;
import com.orderManage.service.MenuService;
import com.orderManage.service.OrderConfirmService;
import com.orderManage.service.OrderHistoryService;
import com.orderManage.service.OrderInputService;
import com.orderManage.service.OrderManageLoggingService;
import com.orderManage.service.StoreChoiceService;
import com.orderManage.service.UtilTestService;
import com.orderManage.util.StringUtil;

import jakarta.servlet.http.HttpSession;

/**
 * 発注管理アプリ　コントローラークラス
 * 
 * TODO ブラウザバック対応
 * TODO ログアウトの制御
 * 
 */
@Controller
public class OrderManageController {
		
	/* ログ管理クラス定義 */
	@Autowired
	OrderManageLoggingService logger;
	
	/* スマレジユーザークラス(セッション)定義 */
	@Autowired
	SmarejiUser smarejiUser;
	
	/* セッション管理定義 */
	@Autowired
    private HttpSession smarejiSession;
	
	/* プロパティ値 20230118 add*/
	@Autowired
	public ApplicationPropertyModel app_properties; 

	/* サービスクラスはここで定義する *************************************/
	/* 共通系テスト用サービスクラス */
    @Autowired
    UtilTestService utilTestService;
    
	/* メニュー画面サービスクラス */
	@Autowired
	MenuService menuService;

	/* 店舗選択画面サービスクラス */
	@Autowired
	StoreChoiceService storeChoiceService;

	/* 発注確認画面サービスクラス */
	@Autowired
	CheckOrderConfirmService checkOrderConfirmService;
	
	/* 発注確定画面サービスクラス */
	@Autowired
	OrderConfirmService orderConfirmService;
	
	/* 発注履歴画面サービスクラス */
	@Autowired
	OrderHistoryService orderHistoryService;
	
	/* 発注状況サービスクラス */
	@Autowired
	CheckOrderStatusService checkOrderStatusService;
	/* *********************************************************/
	
	/* 発注入力画面サービスクラス */
	@Autowired
	OrderInputService orderInputService;
	/**
	 * コンストラクタ
	 * 
	 * @param session
	 */
	public OrderManageController(HttpSession session) {
		// セッションに格納
		this.smarejiSession = session;
	}

	/**
	 * ログイン認証を制御するコントローラー　(変更しないでください)
	 * @param model　パラメータ受け渡し制御Model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/login")
    public String login(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		logger.info("ログイン画面に遷移");

		/** スマレジアクセス時にコメントをはずす*******************************************************************/
		// 認可エンドポイントURLにリダイレクト
		String redirectStr = "redirect:" + app_properties.getUrlId() + "authorize?response_type=code&client_id=" 
			+ app_properties.getClientId() + "&scope=openid+email+offline_access";
		return redirectStr;
		/** *****************************************************************************************/
		
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
//		model.addAttribute("message", "ログイン画面");
//        return "login";
		/***********************************************/
	}

	/**
	 * ログアウトを制御するコントローラー　(変更しないでください)
	 * @param model　パラメータ受け渡し制御Model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/logout")
    public String logout(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		logger.info("ログアウトを実行");

//		// 取得
//		List<StoreInfo> storeInfoList = (List<StoreInfo>)smarejiSession.getAttribute("storesInfoList");

		// TODO スマレジログイン状態の破棄
		// 削除個別
//		smarejiSession.removeAttribute("storesInfoList");
		// 削除ALL
		smarejiSession.invalidate();

		// 取得 削除されていることを確認する
//		List<StoreInfo> storeInfoList2 = (List<StoreInfo>)smarejiSession.getAttribute("storesInfoList");
		
        return "logout";
		/***********************************************/
	}

	/**
	 * メニュー画面遷移時に制御するコントローラー　 (変更しないでください)
	 * 
	 * TODO 引数をスマレジユーザクラスに変更する
	 * @param model　パラメータ受け渡し制御Model
	 * @param code　認可コード
	 * @param user_id ※ローカル確認用 ユーザID
	 * @param password ※ローカル確認用 パスワード
	 * 
	 * @return メニュー画面
	 */
	@RequestMapping("/menu")
    public String menu(@RequestParam(required = false) String code,
    		@RequestParam(required = false) String user_id,
    		@RequestParam(required = false) String password,
    		@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {

		logger.info("controller:メニュー画面表示処理 start");

		/** 認証関連 *******************************************************************/
		// 20240506 追加 スマレジユーザが取得されている場合は認証処理を行わない(メニューに戻る対応)
		if (Objects.isNull(smarejiUser.getSub())) {
			logger.debug("認可コード:code:" + code);
			// authorization_code（code）を使用してユーザーアクセストークンをリクエストする
			UserAccessToken uat = menuService.getUserAccessToken(code);
			logger.debug("ユーザアクセストークン：" + uat.getAccess_token());

			// ユーザーアクセストークンを使用してユーザー情報取得する
			smarejiUser = menuService.getSmarejiUser(uat.getAccess_token());
			logger.debug("契約ID：" + smarejiUser.getContract().getId());

			// ユーザ情報をセッションに格納する
			smarejiSession.setAttribute("s_smarejiUser", smarejiUser);
		}
		/******************************************************************************/
		
		// 取得したスタッフ情報はセッションに格納しておく　20240715 add 
		StaffInfo staffInfo = menuService.getStaffInfo(smarejiUser);
		smarejiSession.setAttribute("s_StaffInfo", staffInfo);
		
		/* Util系テスト ******************************************************************/
//		SmarejiUser suser = (SmarejiUser)smarejiSession.getAttribute("smarejiUser");
//		// 店舗一覧取得
//		List<StoreInfo> storeInfoList = utilTestService.getStoresInfo(suser);
//		// セッションに格納
//		smarejiSession.setAttribute("storesInfoList", storeInfoList);
//		// 店舗情報取得
//		StoreInfo si = utilTestService.getStoreInfo(smarejiUser);
//		// 発注一覧取得
//		List<PurchaseOrdersInfo> poiList = utilTestService.getPurchaseOrdersInfo(smarejiUser);
//		// 発注情報取得
//		PurchaseOrdersInfo poi = utilTestService.getPurchaseOrderInfo(smarejiUser);
//		// 発注登録
//		PurchaseOrdersInfo poientry = utilTestService.entryPurchaseOrder(smarejiUser);
		// 発注更新
//		PurchaseOrdersInfo poiupdate = utilTestService.updatePurchaseOrder(smarejiUser, "18");
//		// 発注削除
//		utilTestService.deletePurchaseOrder(smarejiUser, "12");
//		// 発注対象商品取得
//		List<PurchaseOrdersProductsInfo> popiList = utilTestService.getPurchaseOrdersProductInfo(smarejiUser, "22");
//		// 発注対象店舗取得
//		List<PurchaseOrdersStoresInfo> posiList = utilTestService.getPurchaseOrdersStoreInfo(smarejiUser, "15");
//		// 仕入先一覧取得
//		List<SuppliersInfo> suppliesList = utilTestService.getSuppliersInfo(smarejiUser);
//		// 仕入先商品一覧取得
//		List<SuppliersProductsInfo> suplierProductList = utilTestService.getSuppliersProductsInfo(smarejiUser, "1");
//		// 商品一覧取得
//		List<ProductsInfo> productsInfoList = utilTestService.getProductsInfo(smarejiUser);
//		// 在庫一覧取得
//		List<StockInfo> stockInfoList = utilTestService.getStockInfo(smarejiUser);
//		// 部門一覧取得
//		List<CategorieInfo> categorieInfoList = utilTestService.getCategoriesInfo(smarejiUser);
//		// 部門取得
//		CategorieInfo categorieInfo = utilTestService.getCategorieInfo(smarejiUser);
//		// スタッフ一覧取得
//		List<StaffInfo> staffInfoList = utilTestService.getStaffsInfo(smarejiUser);
//		// 商品画像一覧
//		List<ProductImageInfo> productsImageList = utilTestService.getProductsImage(smarejiUser);
//		model.addAttribute("image", productsImageList.get(0).getUrl());
//		// 商品属性一覧
//		List<ProductAttributeInfo> attributeList = utilTestService.getAttributesInfo(smarejiUser);
//		// 商品取得
//		ProductsInfo productsInfo = utilTestService.getProductInfo(smarejiUser, "8000010");
//		// 取引一覧取得
//		List<TransactionsInfo> transactionsInfoList = utilTestService.getTransactionsInfo(smarejiUser);
		
//		// no image画像の設定
//		model.addAttribute("image", "/img/no_image.png");
		/* ****************************************************************************/

		// ログに出力すべき情報も出力
//		logger.info("ユーザID：" + smarejiUser.getContract().getUser_id());
		
		logger.info("controller:メニュー画面表示処理 end");

        return "menu";
	}

	/* ************************************************************************************/
	/* 各画面を制御するコントローラー */
	
	/**
	 * 店舗選択画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return 店舗選択画面
	 */
	@RequestMapping("/storeChoice")
    public String storeChoice(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		logger.info("店舗選択画面遷移処理　開始");

		StoreChoiceForm form = new StoreChoiceForm();

		// 店舗一覧情報を取得する
		Map<String, String> storesMap = new LinkedHashMap<String, String>();

		// 商品名一覧取得
		storesMap = storeChoiceService.getStoresInfo(smarejiUser);

		// 店舗一覧を店舗選択画面に設定する
		form.setStoreInfos(storesMap);
		
		model.addAttribute("storeChoiceForm", form);

		// 入力チェックエラーの場合の為に店舗一覧をセッションに保持しておく
		smarejiSession.setAttribute("storeInfos", storesMap);

		logger.info("店舗選択画面遷移処理　終了");

        return "storeChoice";
	}

	/**
	 * 店舗選択画面遷移時に制御するコントローラー（他画面からの戻った時）
	 * 
	 * @param referer
	 * @param model
	 * @return
	 */
	@RequestMapping("/storeChoice_back")
    public String storeChoice_back(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		logger.info("controller:店舗選択画面表示処理_back start");

		StoreChoiceForm form = new StoreChoiceForm();
        
		// セッションから店舗一覧を取得し、設定する
		form.setStoreInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("storeInfos"));

		// セッションから店舗情報を取得する
		StoreInfo storeInfo = (StoreInfo) smarejiSession.getAttribute("s_StoresInfo");

		model.addAttribute("storeId", storeInfo.getStoreId());
		model.addAttribute("storeChoiceForm", form);

		// 発注入力のセッション削除 発注入力に遷移したら詰めなおすので削除しなくても問題ないかもしれないが
		smarejiSession.removeAttribute("s_OrderInfo");	// 発注入力情報
		smarejiSession.removeAttribute("categoryInfos");// 発注入力画面の部門一覧
		
		logger.info("controller:店舗選択画面表示処理_back end");

		return "storeChoice";
	}

	
	private static int pagesize_orderinput = 20;	// TODO debug
	
	/**
	 * 発注入力画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return 発注入力画面 orderDate
	 */
	@RequestMapping("/orderInput")
	public String orderInput(@ModelAttribute @Validated StoreChoiceForm object, BindingResult bindingResult, 
		  @RequestHeader(value = "referer", required = false) final String referer, 
		  Model model) {

		logger.info("controller:発注入力画面表示処理 start");

		// 選択された店舗を設定
		model.addAttribute("storeId", object.getStoreId());

		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            
            // セッションに保持した店舗一覧を取得し、設定する
            object.setStoreInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("storeInfos"));
            model.addAttribute("storeChoiceForm", object);
            
            return "storeChoice";
        }
 
        // 選択した店舗情報をセッションに保持する	
        StoreInfo storeInfo = storeChoiceService.getStoreInfo(smarejiUser, object.getStoreId());
        smarejiSession.setAttribute("s_StoresInfo", storeInfo);
        
        // 発注入力　初期表示
		OrderInputForm form = new OrderInputForm();
        
		// 部門一覧情報を取得する
		Map<String, String> categoryMap = new LinkedHashMap<String, String>();

		// 部門一覧取得
		categoryMap = orderInputService.getCategoriesInfo(smarejiUser);

		// 部門一覧を発注入力画面に設定する
		form.setCategoryInfos(categoryMap);

		// ページング処理でNULLだと落ちるので空のオブジェクト
		form.setDisplayList(new ArrayList<OrderInputSubForm>());
		
		// ページング処理
		int currentPage = 1;
		int pageSize= pagesize_orderinput;
		Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
		model.addAttribute("page", pageable);
		
		int totalPages = pageable.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("orderInputForm", form);

		// 入力チェックエラーの場合の為に部門一覧をセッションに保持しておく
		smarejiSession.setAttribute("categoryInfos", categoryMap);

		// TODO いきなり発注に進むとかやると落ちるので
		OrderSessionInfo orderSessionInfo = new OrderSessionInfo();
		orderSessionInfo.setOrderInputForm(form);

		// TODO いきなり発注に進むとかやると落ちるので
		smarejiSession.setAttribute("orderInputPage", "1");
		Map<String, String[]> orderAmountMap = new HashMap<String, String[]>();
		orderSessionInfo.setOrderAmountMap(orderAmountMap);
		
		smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);
		
		logger.info("controller:発注入力画面表示処理 end");
		
		return "orderInput";
	}


	/**
	 * 発注入力画面遷移時に制御するコントローラー（検索時）
	 * 
	 * @param object
	 * @param bindingResult
	 * @param referer
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderInput_self")
	public String orderInput_self(@ModelAttribute @Validated OrderInputForm object, BindingResult bindingResult, 
		  @RequestHeader(value = "referer", required = false) final String referer, 
		  Model model) {

		logger.info("controller:発注入力画面表示処理_self start");
		
		OrderInputForm form = new OrderInputForm();

		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            
            // セッションに保持した部門一覧を取得し、設定する
            object.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));
            model.addAttribute("orderInputForm", object);

    		// ページング処理でNULLだと落ちるので空のオブジェクト
    		form.setDisplayList(new ArrayList<OrderInputSubForm>());
            
    		// ページング処理
    		int currentPage = 1;
    		int pageSize= pagesize_orderinput;
    		Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
    		model.addAttribute("page", pageable);
    		
    		int totalPages = pageable.getTotalPages();
    		if(totalPages > 0) {
    			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
    			model.addAttribute("pageNumbers", pageNumbers);
    		}

             return "orderInput";
        }

        // セッションから店舗情報を取得
		StoreInfo storeInfo = (StoreInfo)smarejiSession.getAttribute("s_StoresInfo");
		
		storeInfo.setStoreId(storeInfo.getStoreId());
		logger.info("店舗ID:" + storeInfo.getStoreId());

		// 検索結果を取得
		form = orderInputService.getDisplayInfo(smarejiUser, object, storeInfo.getStoreId());

		// セッションから部門一覧を取得
		form.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));

		// 検索条件をformに設定
		form.setCategoryId(object.getCategoryId());
		form.setGroupCode(object.getGroupCode());
		form.setSupplierProductNo(object.getSupplierProductNo());
		form.setProductId(object.getProductId());
		form.setProductCode(object.getProductCode());
		form.setProductName(object.getProductName());
		
		// ページング処理
		int currentPage = 1;
		int pageSize= pagesize_orderinput;
		Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
		model.addAttribute("page", pageable);
		
		int totalPages = pageable.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		// 画面返却用にmodelに設定
		model.addAttribute("categoryId", object.getCategoryId());
		model.addAttribute("orderInputForm", form);
		
		// セッション用クラス
		OrderSessionInfo orderSessionInfo = (OrderSessionInfo) smarejiSession.getAttribute("s_OrderInfo");
		orderSessionInfo.setOrderInputForm(form);
		
		smarejiSession.setAttribute("orderInputPage", "1");	// TODO
		Map<String, String[]> orderAmountMap = new HashMap<String, String[]>();
		orderSessionInfo.setOrderAmountMap(orderAmountMap);
		
		// セッションに検索条件と検索結果を保持
		smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);
		
		logger.info("controller:発注入力画面表示処理_self end");
		
		return "orderInput";
	}

	/**
	 * 発注入力画面遷移時に制御するコントローラー（他画面から戻った時）
	 * 
	 * @param referer
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderInput_back")
    public String orderInput_back(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		logger.info("controller:発注入力画面表示処理_back start");

		OrderInputForm form = new OrderInputForm();

		// セッションから部門一覧を取得
		form.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));

		OrderSessionInfo orderSessionInfo = (OrderSessionInfo) smarejiSession.getAttribute("s_OrderInfo");
		model.addAttribute("orderInputForm", form);

		// ページング処理でNULLだと落ちるので空のオブジェクト
		form.setDisplayList(new ArrayList<OrderInputSubForm>());

		// ページング処理
		int currentPage = 1;
		int pageSize= pagesize_orderinput;
		Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
		model.addAttribute("page", pageable);
		
		int totalPages = pageable.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		// 発注削除
		List<String> storageInfoIdList = orderSessionInfo.getStorageInfoIdList();
		orderInputService.deletePurchaseOrder(smarejiUser, storageInfoIdList);

		// セッション削除
		smarejiSession.removeAttribute("s_OrderInfo");
		
		// TODO いきなり発注に進むとかやると落ちるので
		orderSessionInfo = new OrderSessionInfo();
		orderSessionInfo.setOrderInputForm(form);

		// TODO いきなり発注に進むとかやると落ちるので
		smarejiSession.setAttribute("orderInputPage", "1");
		Map<String, String[]> orderAmountMap = new HashMap<String, String[]>();
		orderSessionInfo.setOrderAmountMap(orderAmountMap);
		smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);

		logger.info("controller:発注入力画面表示処理_back end");

		return "orderInput";
	}

	/**
	 * 発注入力画面遷移時に制御するコントローラー（ページング時）
	 * 
	 * @param page
	 * @param size
	 * @param referer
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderInput_page")
	public String orderInput_page(@RequestParam("page") int page,
			@RequestParam("size") int size,
			@RequestHeader(value = "referer", required = false) final String referer,
			Model model, @ModelAttribute @Validated OrderInputForm object, BindingResult bindingResult) {	
	    
		logger.info("発注入力画面遷移処理　開始");
	
		// セッションから画面表示情報取得
		OrderSessionInfo orderSessionInfo = (OrderSessionInfo) smarejiSession.getAttribute("s_OrderInfo");
		String orderInputPage = (String) smarejiSession.getAttribute("orderInputPage");	// TODO

		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            
            // セッションに保持した部門一覧を取得し、設定する
            object.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));
    		model.addAttribute("categoryId", object.getCategoryId());

    		OrderInputForm form = orderSessionInfo.getOrderInputForm();

    		List<OrderInputSubForm> displayList = form.getDisplayList();
    		int pageNumber = Integer.parseInt(orderInputPage);
    		int startIdx = (pageNumber - 1) * pagesize_orderinput;
    		int endIdx = pageNumber * pagesize_orderinput - 1;
    		String[] orderAmount = object.getOrderAmount_();

    		for (int i = 0, j = 0; i < displayList.size(); i++) {
    			if (i < startIdx) {
    				continue;
    			}
    			if (i > endIdx) {
    				break;
    			}
    			displayList.get(i).setOrderAmount(orderAmount[j++]);
    		}
    		
    		object.setDisplayList(displayList);

    		model.addAttribute("orderInputForm", object);
            
    		// ページング処理
    		int currentPage = Integer.parseInt(orderInputPage);
    		int pageSize= pagesize_orderinput;
    		Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
    		model.addAttribute("page", pageable);
    		
    		int totalPages = pageable.getTotalPages();
    		if(totalPages > 0) {
    			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
    			model.addAttribute("pageNumbers", pageNumbers);
    		}

            return "orderInput";
        }
 
		OrderInputForm form = orderSessionInfo.getOrderInputForm();
		
		// ページング処理
		int currentPage = page;
		int pageSize= size;
		Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
		model.addAttribute("page", pageable);
		
		int totalPages = pageable.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		// TODO
		Map<String, String[]> orderAmountMap = orderSessionInfo.getOrderAmountMap();
		String orderAmount[] = orderAmountMap.get(String.valueOf(page));
		List<OrderInputSubForm> displayList = form.getDisplayList();
		for (int i = 0, j = 0; i < displayList.size(); i++) {
			if (i >= (page - 1) * pagesize_orderinput || page * pagesize_orderinput < i) {
				if (orderAmount != null && orderAmount.length != 0 && orderAmount.length > j) {
					displayList.get(i).setOrderAmount(orderAmount[j]);
					j++;
				}
			}
		}
		form.setDisplayList(displayList);
		
		
		// TODO 発注点の入力があるかチェックする
		for (int i = 0; i < displayList.size(); i++) {
			if (StringUtil.isNotEmpty(displayList.get(i).getOrderAmount())) {
				form.setInputFlag("true");
				break;
			}
		}
		if (object.getOrderAmount_() != null && object.getOrderAmount_().length > 0) {
			for (int i = 0; i < object.getOrderAmount_().length; i++) {
				if (StringUtil.isNotEmpty(object.getOrderAmount_()[i])) {
					form.setInputFlag("true");
					break;
				}
			}
		}
		
		// 画面に設定する
		model.addAttribute("categoryId", form.getCategoryId());
		model.addAttribute("orderInputForm", form);
		
		orderAmountMap.put(orderInputPage, object.getOrderAmount_());
		smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);
		smarejiSession.setAttribute("orderInputPage", String.valueOf(page));
		
		logger.info("発注入力画面遷移処理　終了");
		
        return "orderInput";
	}

	/**
	 * 発注確認画面遷移時に制御するコントローラー
	 * 
	 * @param object　発注入力画面Form
	 * @param bindingResult
	 * @param referer
	 * @param model　パラメータ受け渡し制御Model
	 * 
	 * @return  発注確認画面
	 */
	@RequestMapping("/checkOrderConfirm")
	public String checkOrderConfirm(@ModelAttribute @Validated OrderInputForm object, BindingResult bindingResult, 
			@RequestHeader(value = "referer", required = false) final String referer, 
			Model model) {  	
	    
		logger.info("発注確認画面遷移処理　開始");
	
		OrderSessionInfo orderSessionInfo = (OrderSessionInfo)smarejiSession.getAttribute("s_OrderInfo");
		String orderInputPage = (String) smarejiSession.getAttribute("orderInputPage");	// TODO

		// 遷移先判定、発注入力からの遷移と発注確定からの戻り時処理を分岐させる 20241230 中川
		if (referer.contains("orderInput")) {
			// 発注入力画面からの遷移　20241230
			// バリデートチェック
			if (bindingResult.hasErrors()) {
				// エラーの場合
				List<String> errorList = new ArrayList<String>();
				for (ObjectError error : bindingResult.getAllErrors()) {
					errorList.add(error.getDefaultMessage());
				}
				model.addAttribute("validationError", errorList);
            
				// セッションに保持した部門一覧を取得し、設定する
				object.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));
				model.addAttribute("categoryId", object.getCategoryId());

				OrderInputForm form = orderSessionInfo.getOrderInputForm();

				List<OrderInputSubForm> displayList = form.getDisplayList();
				int pageNumber = Integer.parseInt(orderInputPage);
				int startIdx = (pageNumber - 1) * pagesize_orderinput;
				int endIdx = pageNumber * pagesize_orderinput - 1;
				String[] orderAmount = object.getOrderAmount_();

				for (int i = 0, j = 0; i < displayList.size(); i++) {
					if (i < startIdx) {
						continue;
					}
					if (i > endIdx) {
						break;
					}
					displayList.get(i).setOrderAmount(orderAmount[j++]);
				}
    		
				object.setDisplayList(displayList);

				model.addAttribute("orderInputForm", object);
				
				// ページング処理
				int currentPage = Integer.parseInt(orderInputPage);
				int pageSize= pagesize_orderinput;
				Page<OrderInputSubForm> pageable = orderInputService.paging(PageRequest.of(currentPage - 1, pageSize), form);
				model.addAttribute("page", pageable);
    		
				int totalPages = pageable.getTotalPages();
				if(totalPages > 0) {
					List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
					model.addAttribute("pageNumbers", pageNumbers);
				}

				return "orderInput";
			}
			StoreInfo storeInfo = (StoreInfo)smarejiSession.getAttribute("s_StoresInfo");

			String identificationNo = null;
			List<String> storageInfoIdList = new ArrayList<String>();
			
			Long datetime = System.currentTimeMillis();
			identificationNo = datetime.toString();

			Map<String, String[]> orderAmountMap = orderSessionInfo.getOrderAmountMap();
			orderAmountMap.put(orderInputPage, object.getOrderAmount_());
			
			// 仮発注登録
			Map<String, String> orderMap = orderInputService.entryPurchaseOrder(smarejiUser, storeInfo.getStoreId(), identificationNo, orderSessionInfo.getOrderInputForm().getDisplayList(), orderAmountMap, pagesize_orderinput);

			Iterator<Map.Entry<String, String>> it = orderMap.entrySet().iterator();

			// 発注IDリスト
			while (it.hasNext()) {
				Map.Entry<String, String> order = it.next();
				storageInfoIdList.add(order.getValue());
			}
			
			orderSessionInfo.setOrderControlNumber(identificationNo);	// 発注管理番号
			orderSessionInfo.setStorageInfoIdList(storageInfoIdList);	// 発注ID
			smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);
			
			// セッションから情報を取得
			// 発注入力画面で設定した情報 TODO 不要なら削除すること
			// OrderSessionInfo sOrderInfo = (OrderSessionInfo)smarejiSession.getAttribute("s_OrderInfo");
			// スタッフ名（ログインユーザ名＝発注者名）
			StaffInfo sStaffInfo = (StaffInfo)smarejiSession.getAttribute("s_StaffInfo");
			
			// 画面表示情報をセッションから削除
			smarejiSession.removeAttribute("s_OrderConfirmDisplayInfo");		
			// 画面表示情報取得
			CheckOrderConfirmForm form = checkOrderConfirmService.getDisplayInfo(smarejiUser, identificationNo, sStaffInfo.getStaffName());
			// 画面表示情報をセッションに格納
			smarejiSession.setAttribute("s_OrderConfirmDisplayInfo", form);
			
			// ページング処理
			// TODO 定数化、発注確定からの戻りの場合はセッションからcurrentPageを取得する
			int currentPage = 1;
			int pageSize= 20;
			Page<CheckOrderConfirmSubForm> pageable = checkOrderConfirmService.paging(PageRequest.of(currentPage - 1, pageSize), form);
			model.addAttribute("page", pageable);
			
			int totalPages = pageable.getTotalPages();
			if(totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}
			
			// 画面に設定する
			model.addAttribute("checkOrderConfirmForm", form);
		} else if (referer.contains("orderConfirm")) {
			// 発注確定画面からの遷移　20241230
			// スタッフ名（ログインユーザ名＝発注者名）
			StaffInfo sStaffInfo = (StaffInfo)smarejiSession.getAttribute("s_StaffInfo");
			
			// セッションから発注管理番号を取得する
			String identificationNo = orderSessionInfo.getOrderControlNumber();

			// 画面表示情報をセッションから削除
			smarejiSession.removeAttribute("s_OrderConfirmDisplayInfo");
			// 画面表示情報取得
			CheckOrderConfirmForm form = checkOrderConfirmService.getDisplayInfo(smarejiUser, identificationNo, sStaffInfo.getStaffName());
			// 画面表示情報をセッションに格納
			smarejiSession.setAttribute("s_OrderConfirmDisplayInfo", form);

			// ページング処理
			// TODO 定数化、発注確定からの戻りの場合はセッションからcurrentPageを取得する
			int currentPage = 1;
			int pageSize= 20;
			Page<CheckOrderConfirmSubForm> pageable = checkOrderConfirmService.paging(PageRequest.of(currentPage - 1, pageSize), form);
			model.addAttribute("page", pageable);
			
			int totalPages = pageable.getTotalPages();
			if(totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}
			
			// 画面に設定する
			model.addAttribute("checkOrderConfirmForm", form);			
		} else {
			// 意図しない画面遷移のためエラー　TODO
        	return "error";
		}

		logger.info("発注確認画面遷移処理　終了");
		
        return "checkOrderConfirm";
	}
	
	/**
	 * 発注確認画面ページング時に制御するコントローラー
	 * 
	 * @param page　ページ
	 * @param size　サイズ
	 * @param referer
	 * @param model　パラメータ受け渡し制御Model
	 * 
	 * @return  発注確認画面
	 */
	@RequestMapping("/checkOrderConfirm_page")
	public String checkOrderConfirm_page(@RequestParam("page") int page,
			@RequestParam("size") int size,
			@RequestHeader(value = "referer", required = false) final String referer,
			Model model) {	
	    
		logger.info("発注確認画面遷移処理　開始");
	
		// セッションから画面表示情報取得
		CheckOrderConfirmForm form = (CheckOrderConfirmForm)smarejiSession.getAttribute("s_OrderConfirmDisplayInfo");
		
		// ページング処理
		int currentPage = page;
		int pageSize= size;
		Page<CheckOrderConfirmSubForm> pageable = checkOrderConfirmService.paging(PageRequest.of(currentPage - 1, pageSize), form);
		model.addAttribute("page", pageable);
		
		int totalPages = pageable.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		// 画面に設定する
		model.addAttribute("checkOrderConfirmForm", form);
		
		logger.info("発注確認画面遷移処理　終了");
		
        return "checkOrderConfirm";
	}

	/**
	 * 発注確定画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注確定画面
	 */
	@RequestMapping("/orderConfirm")
    public String orderConfirm(@RequestParam(required = false) String orderId, 
    		@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {

		/** TODO 発注確認 or 発注確定　どちらから来たか判定して処理を行う必要がある **/
		// 画面遷移情報が設定されていない場合、セッションに画面制御情報を設定(初回のみ)
		if (Objects.isNull(smarejiSession.getAttribute("s_OrderConfirm_Referer"))) {
			smarejiSession.setAttribute("s_OrderConfirm_Referer", referer);
		}

		// 遷移元を取得
		String transition_src = (String) smarejiSession.getAttribute("s_OrderConfirm_Referer");
		
		// 遷移先判定処理/** TODO 発注確認 or 発注確定　どちらから来たか判定して処理を行う必要がある **/
//		if (referer.contains("checkOrderConfirm") || referer.contains("orderConfirm_Order")) {
		if (transition_src.contains("checkOrderConfirm")) {
			// 発注確認画面からの遷移
			// 画面表示情報をセッションから削除
			smarejiSession.removeAttribute("s_OrderDisplayInfo");		

//			// 店舗情報を取得
//			StoreInfo storeInfo = (StoreInfo) smarejiSession.getAttribute("s_StoresInfo");
			
			// 画面表示情報取得
			OrderConfirmForm form = orderConfirmService.getDisplayInfo(smarejiUser, orderId);

			// セッションに画面内容を設定(発注IDを追加で設定)
			form.setOrderId(orderId);
			smarejiSession.setAttribute("s_OrderDisplayInfo", form);
			
			// 発注IDの引き渡し
			model.addAttribute("orderId", orderId);

			// 画面に返す
			model.addAttribute("orderConfirmForm", form);

//		} else if (referer.contains("orderHistory")) {
		} else if (transition_src.contains("orderHistory")) {	
			// 発注履歴画面からの遷移
			// 画面表示情報取得
			OrderConfirmForm form = orderConfirmService.getDisplayInfo(smarejiUser, orderId);

			// セッションに画面内容を設定(発注IDを追加で設定)
			form.setOrderId(orderId);
			smarejiSession.setAttribute("s_OrderDisplayInfo", form);
			
			// 発注IDの引き渡し
			model.addAttribute("orderId", orderId);

			// 画面に返す
			model.addAttribute("orderConfirmForm", form);
		} else {
			// 意図しない画面遷移のためエラー　TODO
        	return "error";
		}
		
        return "orderConfirm";
	}
	
	/**
	 * 発注確定処理を行う
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注確定画面
	 * @throws ParseException 
	 */
	@RequestMapping("/orderConfirm_Order")
    public String orderConfirm_Order(@ModelAttribute @Validated OrderConfirmForm object, BindingResult bindingResult, 
    		@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) throws ParseException {
		
		logger.info("controller:発注確定画面　登録処理_order start");
		
		// 遷移元を取得
		String transition_src = (String) smarejiSession.getAttribute("s_OrderConfirm_Referer");

		OrderConfirmForm form = new OrderConfirmForm();
		OrderConfirmForm updateForm = new OrderConfirmForm();
		List<OrderConfirmSubForm> updateList = new ArrayList<OrderConfirmSubForm>();

		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            //object.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));	// TODO objectの使い回しは微妙？
            model.addAttribute("orderConfirmForm", object);

    		// ページング処理でNULLだと落ちるので空のオブジェクト
            form.setDisplayList(new ArrayList<OrderConfirmSubForm>());

             return "orderConfirm";
        }

        // セッションから発注情報を取得
        form = (OrderConfirmForm)smarejiSession.getAttribute("s_OrderDisplayInfo");
//        // セッションから店舗情報を取得
//     	StoreInfo storeInfo = (StoreInfo) smarejiSession.getAttribute("s_StoresInfo");
        
		//　発注点数更新
        int i = 0;
        for (OrderConfirmSubForm orderConfirmSubForm : form.getDisplayList()) {
        	// 画面に表示されている発注点数に設定する
        	if (object.getOrderingPoint_()[i] == "") {
        		// 発注点数がブランクの場合は"0"とする
        		object.getOrderingPoint_()[i] = "0";
        	}
        	orderConfirmSubForm.setOrderingPoint(Integer.valueOf(object.getOrderingPoint_()[i]));
        	updateList.add(orderConfirmSubForm);
        	i++;
        }

        // セッション内容の更新(発注点数)
        form.setDisplayList(updateList);
     	smarejiSession.setAttribute("s_OrderDisplayInfo", form);

		// 発注更新処理(仮発注→発注済) 
        orderConfirmService.updatePurchaseOrder(smarejiUser, form.getOrderId(), updateList);
		
		// 仕入先にメールを飛ばす or メールが存在しない場合は発注書のテンプレートを出力する（FAX用） TODO
      
        logger.info("発注確定処理完了");
        
        /** TODO 発注確認 or 発注確定　どちらから来たか判定して処理を行う必要がある **/  
		// 遷移先判定処理
//        if (referer.contains("orderConfirm")) {
        if (transition_src.contains("checkOrderConfirm")) {
        	
            // 発注確認画面の画面表示情報を取得
         	CheckOrderConfirmForm checkOrderConfirmForm = (CheckOrderConfirmForm)smarejiSession.getAttribute("s_OrderConfirmDisplayInfo");
            List<CheckOrderConfirmSubForm> displayList = checkOrderConfirmForm.getDisplayList();
            //発注更新した発注情報は削除画面表示から削除する
            int listNo = 0;
            for (CheckOrderConfirmSubForm list : displayList) {
              	// 発注IDが同じ場合は削除する
               	if (list.getOrderId().equals(form.getOrderId())) {
               		displayList.remove(listNo);
               		break;
               	}
            listNo++;
            }

        	if (checkOrderConfirmForm.getDisplayList().size() >= 1) {
        		// 発注情報が残っている場合は発注確認画面に遷移
        		
                // 画面表示再設定
                checkOrderConfirmForm.setDisplayList(displayList);

                // 画面表示情報をセッションに格納
        		smarejiSession.setAttribute("s_OrderConfirmDisplayInfo", checkOrderConfirmForm);

        		// ページング処理
        		// TODO 定数化、発注確定からの戻りの場合はセッションからcurrentPageを取得する
        		int currentPage = 1;
        		int pageSize= 20;
        		Page<CheckOrderConfirmSubForm> pageable = checkOrderConfirmService.paging(PageRequest.of(currentPage - 1, pageSize), 
        				checkOrderConfirmForm);
        		model.addAttribute("page", pageable);
        		
        		int totalPages = pageable.getTotalPages();
        		if(totalPages > 0) {
        			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        			model.addAttribute("pageNumbers", pageNumbers);
        		}
             	// 画面に設定する
             	model.addAttribute("checkOrderConfirmForm", checkOrderConfirmForm);

             	logger.info("controller:発注確定画面　登録処理_order end");
        		return "checkOrderConfirm";
        	} else {
        		// 発注情報が残っていない場合、発注履歴画面に遷移　TODO 
        		// TODO 今回の発注だけ発注履歴画面にて表示させるようにするか？
        		OrderHistoryForm orderHistoryForm = new OrderHistoryForm();

        		// 仕入先一覧を取得(API使用）
        		Map<String, String> suppliersMap = new LinkedHashMap<String, String>();
        		suppliersMap = orderHistoryService.getSupplierInfo(smarejiUser);
        		
//        		// スタッフ一覧取得(API使用）
//        		Map<String, String> staffInfoMap = new LinkedHashMap<String, String>();
//        		staffInfoMap = orderHistoryService.getStaffInfo(smarejiUser);
        		
        		// 画面に設定する
        		model.addAttribute("suppliers", suppliersMap);
        		model.addAttribute("orderHistoryForm", orderHistoryForm);       
        		/***********************************************************************************/

        		// 遷移元セッションの削除
        		smarejiSession.removeAttribute("s_OrderConfirm_Referer");
        		
        		logger.info("controller:発注確定画面　登録処理_order end");
        		return "orderHistory";
        	}
//        } else if (referer.contains("orderHistory")) {
        } else if (transition_src.contains("orderHistory")) {
        	// 発注履歴画面から遷移してきた場合、発注履歴画面に遷移　TODO
 
        	/**** いったん発注履歴と同じプログラムを実装 ************************************************/   
        	OrderHistoryForm orderHistoryForm = new OrderHistoryForm();
    		// セッション情報取得
        	OrderHistSessionInfo session = (OrderHistSessionInfo)smarejiSession.
    				getAttribute("s_OrderHistInfo");

    		// 絞り込み条件設定
    		orderHistoryForm = orderHistoryService.setCondition(orderHistoryForm,session);
    		// セッションに画面情報を格納
    		session = orderHistoryService.setSession(orderHistoryForm,session);
    		smarejiSession.setAttribute("s_OrderHistInfo", session);

    		// 仕入先一覧を取得(API使用）
    		Map<String, String> suppliersMap = new LinkedHashMap<String, String>();
    		suppliersMap = orderHistoryService.getSupplierInfo(smarejiUser);
    		
    		// スタッフ一覧取得(API使用）
    		Map<String, String> staffInfoMap = new LinkedHashMap<String, String>();
    		staffInfoMap = orderHistoryService.getStaffInfo(smarejiUser);
    		
    		// 発注一覧取得処理(API使用）
    		List<PurchaseOrdersInfo> purchaseOrderInfoList =orderHistoryService
    				.getPurchaseOrderInfoList(smarejiUser,orderHistoryForm);

    		// 絞り込み処理
    		orderHistoryService.filter(smarejiUser,purchaseOrderInfoList,orderHistoryForm);

    		// 名称設定
    		// 仕入先名設定
    		orderHistoryService.setSupplierName(orderHistoryForm, suppliersMap);
    		// スタッフ名設定
    		orderHistoryService.setStaffName(orderHistoryForm, staffInfoMap);
    		
    		// 発注対象商品取得（API使用）、発注点数・発注金額合計設定 (TODO 時間がかかるため修正を検討予定)
    		orderHistoryService.getPurchaseOrderProduct(smarejiUser,orderHistoryForm);
    		
    		// 画面に設定する
    		model.addAttribute("suppliers", suppliersMap);
    		model.addAttribute("orderHistoryForm", orderHistoryForm);
    		model.addAttribute("shokihyojiFlg", "0");
    		/***********************************************************************************/
        	
    		// 遷移元セッションの削除
    		smarejiSession.removeAttribute("s_OrderConfirm_Referer");
    		
        	logger.info("controller:発注確定画面　登録処理_order end");
        	return "orderHistory";
        } else {
        	// 意図しない画面遷移のためエラー　TODO
        	return "error";
        }
	}

	/**
	 * 発注履歴画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注履歴画面
	 * @throws ParseException 
	 */
	@RequestMapping("/orderHistory")
    public String orderHistory(Model model,
    		@ModelAttribute @Validated OrderHistoryForm orderHistoryForm,BindingResult bindingResult,
    		@RequestParam(required = false) String orderId,
    		@RequestParam(required = false) String kensakuFlg,
    		@RequestHeader(value = "referer", required = false) final String referer) throws ParseException {
		
		
		// セッション情報取得
		OrderHistSessionInfo session = (OrderHistSessionInfo)smarejiSession.
				getAttribute("s_OrderHistInfo");
		
		// 初期表示時処理
//		if(Objects.isNull(session)) {
		if(!(Objects.nonNull(kensakuFlg)&&kensakuFlg.equals("1"))) {
			// 仕入先一覧を取得(API使用）
			Map<String, String> suppliersMap = new LinkedHashMap<String, String>();
			suppliersMap = orderHistoryService.getSupplierInfo(smarejiUser);
			
			// 絞り込み条件設定
			orderHistoryForm = orderHistoryService.setCondition(orderHistoryForm,session);
			
			// セッションに画面情報を格納
			session = orderHistoryService.setSession(orderHistoryForm,session);
			smarejiSession.setAttribute("s_OrderHistInfo", session);
			
			// 画面に設定する
			model.addAttribute("suppliers", suppliersMap);
			model.addAttribute("orderHistoryForm", orderHistoryForm);
			model.addAttribute("shokihyojiFlg", "1");
	        return "orderHistory";
		}
		
		// 削除処理
		// 発注IDが連携された場合、削除
		if(!Objects.isNull(orderId)&& orderId !="") {
			// 発注削除（API使用）
			orderHistoryService.deleteOrder(smarejiUser,orderId);
		}
		
		// 検索条件チェック処理
		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "orderHistory";
        }
        
        // 検索条件処理
		// 絞り込み条件設定
		orderHistoryForm = orderHistoryService.setCondition(orderHistoryForm,session);
		
		// セッションに画面情報を格納
		session = orderHistoryService.setSession(orderHistoryForm,session);
		smarejiSession.setAttribute("s_OrderHistInfo", session);
		
		// 仕入先一覧を取得(API使用）
		Map<String, String> suppliersMap = new LinkedHashMap<String, String>();
		suppliersMap = orderHistoryService.getSupplierInfo(smarejiUser);
		
		// スタッフ一覧取得(API使用）
		Map<String, String> staffInfoMap = new LinkedHashMap<String, String>();
		staffInfoMap = orderHistoryService.getStaffInfo(smarejiUser);
		
		// 発注一覧取得処理(API使用）
		List<PurchaseOrdersInfo> purchaseOrderInfoList =orderHistoryService
				.getPurchaseOrderInfoList(smarejiUser,orderHistoryForm);

		// 絞り込み処理
		orderHistoryService.filter(smarejiUser,purchaseOrderInfoList,orderHistoryForm);

		// 名称設定
		// 仕入先名設定
		orderHistoryService.setSupplierName(orderHistoryForm, suppliersMap);
		// スタッフ名設定
		orderHistoryService.setStaffName(orderHistoryForm, staffInfoMap);
		
		// 発注対象商品取得（API使用）、発注点数・発注金額合計設定 (TODO 時間がかかるため修正を検討予定)
		orderHistoryService.getPurchaseOrderProduct(smarejiUser,orderHistoryForm);
		
		// 画面に設定する
		model.addAttribute("suppliers", suppliersMap);
		model.addAttribute("orderHistoryForm", orderHistoryForm);
		model.addAttribute("shokihyojiFlg", "0");
        return "orderHistory";
	}


	/**
	 * 発注状況画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注状況確認画面
	 */
	@RequestMapping("/checkOrderStatus")
    public String checkOrderStatus(
    		@RequestHeader(value = "referer", required = false) final String referer,
    		@RequestParam(required = false) String user_id,
	        @RequestParam(required = false) String password,
	        @RequestParam(required = false) String orderId,
    		Model model) {
		
		    logger.info("発注状況画面表示");
		    
		    //セッション情報取得
		    OrderHistoryForm session1 = (OrderHistoryForm)smarejiSession
					.getAttribute("orderHistorySession");
		    
		    String orderId1 = orderId;
		  
		    List<OrderHistorySubForm> display = session1.getDisplayList();
		    
		    OrderHistorySubForm displayTrue = new OrderHistorySubForm();
		    
		    for (int i=0; i<display.size(); i++){
		    	if (display.get(i).getOrderId().equals(orderId1)) {
		    		displayTrue.setOrderId(display.get(i).getOrderId());
		    		displayTrue.setOrderDate(display.get(i).getOrderDate());
		    		displayTrue.setStaffName(display.get(i).getStaffName());
		    		displayTrue.setSupplierName(display.get(i).getSupplierName());
		    	}
		    }
		    
		    
			model.addAttribute("orderedDate", displayTrue.getOrderDate());
			model.addAttribute("hatyusha", displayTrue.getStaffName());
			model.addAttribute("supplierName",displayTrue.getSupplierName());

			
			/*List<CheckOrderStatusSubForm> matome = checkOrderStatusService.getMeisai(smarejiUser, orderId);
			model.addAttribute("meisai",matome);
			
			return "checkOrderStatus";*/
			
			//CheckOrderStatusForm matome = checkOrderStatusService.getMeisai(smarejiUser, orderId);
			CheckOrderStatusForm matome = checkOrderStatusService.getMeisai(smarejiUser, orderId1);
			
			// ページング処理
			int currentPage = 1;
			int pageSize= 2;
			Page<CheckOrderStatusSubForm> pageable = checkOrderStatusService.paging(PageRequest.of(currentPage - 1, pageSize), matome);
			model.addAttribute("page", pageable);
			
			// ページングレイアウト検討
			int startPage = currentPage;
			int lastPage = 0;
			int totalPages = pageable.getTotalPages();
			
			if(totalPages > 0 && totalPages < 10) {
			    lastPage = totalPages;
		    }else if(totalPages >= 10) {
		    	lastPage = 10;
		    }
	
			if(totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(startPage, lastPage).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}
			
			model.addAttribute("startPage", startPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("orderId", matome.getOrderId());
			model.addAttribute("meisai" ,pageable.getContent());
			
			return "checkOrderStatus";
					
					
	}
	
	@RequestMapping("/checkOrderStatus_page")
	public String checkOrderStatus_page(
    		@RequestHeader(value = "referer", required = false) final String referer,
 	        @RequestParam(required = false) String orderId,
	        @RequestParam("page") int page,
			@RequestParam("size") int size,
			Model model) {	
		
	   
		
		String orderId1 = orderId;
		
		 OrderHistoryForm session1 = (OrderHistoryForm)smarejiSession.getAttribute("orderHistorySession");
		
	    List<OrderHistorySubForm> display = session1.getDisplayList();
	    
	    OrderHistorySubForm displayTrue = new OrderHistorySubForm();
	    
	    for (int i=0; i<display.size(); i++){
	    	if (display.get(i).getOrderId().equals(orderId1)) {
	    		displayTrue.setOrderId(display.get(i).getOrderId());
	    		displayTrue.setOrderDate(display.get(i).getOrderDate());
	    		displayTrue.setStaffName(display.get(i).getStaffName());
	    		displayTrue.setSupplierName(display.get(i).getSupplierName());
	    	}
	    }
	    
	    
		model.addAttribute("orderedDate", displayTrue.getOrderDate());
		model.addAttribute("hatyusha", displayTrue.getStaffName());
		model.addAttribute("supplierName",displayTrue.getSupplierName());
		
		CheckOrderStatusForm matome = checkOrderStatusService.getMeisai(smarejiUser, orderId1);
		
		int currentPage = page;
		int pageSize= size;
		Page<CheckOrderStatusSubForm> pageable = checkOrderStatusService.paging(PageRequest.of(currentPage - 1, pageSize), matome);
		model.addAttribute("page", pageable);
		
		// ページングレイアウト検討
		int startPage = 0;
		int lastPage = 0;
		int totalPages = pageable.getTotalPages();
		
		if(totalPages > 0 && totalPages <= 10) {
			startPage = 1;
		    lastPage = totalPages;
		}else if(totalPages > 10) {
			if(currentPage <= 6) {
				startPage = 1;
			    lastPage = 10;
			}else if(currentPage > 6 && currentPage+4 <= totalPages) {
			    startPage = currentPage-5;
			    lastPage = Math.min(currentPage+4, totalPages);	
		    }else if(currentPage+3 == totalPages) {
		        startPage = currentPage-6;
		    	lastPage = totalPages;   
		    }else if(currentPage+2 == totalPages) {
		        startPage = currentPage-7;
		    	lastPage = totalPages;   	
		    }else if(currentPage+1 == totalPages) {
		        startPage = currentPage-8;
		    	lastPage = totalPages;
		    }else if(currentPage == totalPages) {
		    	startPage = currentPage-9;
		    	lastPage = totalPages;
		    }
		}

	    /*}else if(totalPages > 16) {
			if(currentPage <= 5) {
				startPage = currentPage;
			    lastPage = totalPages;
			}else if(currentPage > 5) 
			    startPage = currentPage-5;
			    lastPage = Math.min(totalPages+4, totalPages);	
		    }*/	
		
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(startPage, lastPage).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("orderId", matome.getOrderId());
		model.addAttribute("meisai" ,pageable.getContent());
		
		return "checkOrderStatus";
	}

//	/**
//	 * 検索モーダルを制御するコントローラー ※使用しないためコメントアウト
//	 * 
//	 * @param model　パラメータ受け渡し制御Model
//	 * @return　検索モーダル画面
//	 */
//	@RequestMapping("/orderSerch")
//	@ResponseBody
//    public String serch(@RequestHeader(value = "referer", required = false) final String referer,
//    		Model model) {
//        return "";
//	}
	/* ************************************************************************************/
}
