package com.orderManage.controller;

import java.util.ArrayList;
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
import com.orderManage.controller.object.CheckOrderStatusSubForm;
import com.orderManage.controller.object.OrderConfirmForm;
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
import com.orderManage.model.service.DisplayOrderInput;
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
import com.orderManage.util.DateUtil;
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
	 * todo 引数をスマレジユーザクラスに変更する
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
//		model.addAttribute("storeInfos", storesMap);
		form.setStoreInfos(storesMap);
		
		// 発注日を設定
		// テスト用に日付設定、本来は現在日付とする、また戻り時は設定された値が入る
		String sysDate = DateUtil.getSysDateYyyyMmDd();
		
//		model.addAttribute("sysDate", sysDate);
//		model.addAttribute("orderDate", sysDate);

// 20240430 発注日が削除されたためコメントアウト
//		form.setOrderDate(sysDate);

		form.setSysDate(sysDate);
		model.addAttribute("storeChoiceForm", form);

		// 入力チェックエラーの場合にセッションに保持しておく
		smarejiSession.setAttribute("storeInfos", storesMap);
//		smarejiSession.setAttribute("storeChoiceForm", form);

		logger.info("店舗選択画面遷移処理　終了");

        return "storeChoice";
	}

	/**
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
        
		form.setStoreInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("storeInfos"));

		StoreInfo storeInfo = (StoreInfo) smarejiSession.getAttribute("s_StoresInfo");

		model.addAttribute("storeId", storeInfo.getStoreId());
		model.addAttribute("storeChoiceForm", form);

		// 発注入力のセッション削除 発注入力に遷移したら詰めなおすので削除しなくても問題ないかもしれないが
		smarejiSession.removeAttribute("s_OrderInfo");
		
		logger.info("controller:店舗選択画面表示処理_back end");

		return "storeChoice";
	}

	/**
	 * 発注入力画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return 発注入力画面 orderDate
	 */
	@RequestMapping("/orderInput")
//  public String orderInput(@RequestParam(required = false) String orderDate, String storeselect, Model model) {
  public String orderInput(@ModelAttribute @Validated StoreChoiceForm object, BindingResult bindingResult, 
		  @RequestHeader(value = "referer", required = false) final String referer, 
		  Model model) {

		logger.info("controller:発注入力画面表示処理 start");

		// TODO OrderInputFormに設定してそれをmodelにaddすることになると思う
		model.addAttribute("storeId", object.getStoreId());
// 20240430 発注日が削除されたためコメントアウト
//		model.addAttribute("orderDate", object.getOrderDate());

		model.addAttribute("sysDate", object.getSysDate());
		
		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            object.setStoreInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("storeInfos"));	// TODO objectの使い回しは微妙？
            model.addAttribute("storeChoiceForm", object);
            
            return "storeChoice";
        }
 
        // 選択した店舗情報をセッションに保持する	// TODO APIから取得
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

		model.addAttribute("orderInputForm", form);

		smarejiSession.setAttribute("categoryInfos", categoryMap);
        
		logger.info("controller:発注入力画面表示処理 end");
		
		return "orderInput";
	}


	/**
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

		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            object.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));	// TODO objectの使い回しは微妙？
            model.addAttribute("orderInputForm", object);
            return "orderInput";
        }
 
		StoreInfo storeInfo = (StoreInfo)smarejiSession.getAttribute("s_StoresInfo");
		
		storeInfo.setStoreId(storeInfo.getStoreId());
		logger.info("店舗ID:" + storeInfo.getStoreId());
		
		OrderInputForm form = orderInputService.getDisplayInfo(smarejiUser, object, storeInfo.getStoreId());
		form.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));

		form.setCategoryId(object.getCategoryId());
		form.setGroupCode(object.getGroupCode());
		form.setSupplierProductNo(object.getSupplierProductNo());
		form.setProductId(object.getProductId());
		form.setProductCode(object.getProductCode());
		form.setProductName(object.getProductName());
		
		model.addAttribute("categoryId", object.getCategoryId());	// TODO
		model.addAttribute("orderInputForm", form);
		
		OrderSessionInfo orderSessionInfo = new OrderSessionInfo();
		orderSessionInfo.setCategoryId(object.getCategoryId());
		orderSessionInfo.setGroupCode(object.getGroupCode());
		orderSessionInfo.setSupplierProductNo(object.getSupplierProductNo());
		orderSessionInfo.setProductId(object.getProductId());
		orderSessionInfo.setProductCode(object.getProductCode());
		orderSessionInfo.setProductName(object.getProductName());

		List<OrderInputSubForm> displayList = form.getDisplayList();
		List<DisplayOrderInput> displayOrderInputList = new ArrayList<DisplayOrderInput>();  
		for (int i = 0; i < displayList.size(); i++) {
			DisplayOrderInput displayOrderInput = new DisplayOrderInput();
			displayOrderInput.setGroupCode(displayList.get(i).getGroupCode());
			displayOrderInput.setProductImage(displayList.get(i).getProductImage());
			displayOrderInput.setProductInfo(displayList.get(i).getProductInfo());
			displayOrderInput.setProductId(displayList.get(i).getProductId());
			displayOrderInput.setProductCode(displayList.get(i).getProductCode());
			displayOrderInput.setProductName(displayList.get(i).getProductName());
			displayOrderInput.setSupplierName(displayList.get(i).getSupplierName());
			displayOrderInput.setCategoryName(displayList.get(i).getCategoryName());
			displayOrderInput.setStockAmount(displayList.get(i).getStockAmount());
			displayOrderInput.setOrderAmount(displayList.get(i).getOrderAmount());
			displayOrderInput.setOrderPoint(displayList.get(i).getOrderPoint());
			
			displayOrderInput.setSupplierId(displayList.get(i).getSupplierId());
			
			displayOrderInputList.add(displayOrderInput);
		}

		orderSessionInfo.setDisplayOrderInput(displayOrderInputList);
		
		smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);
		
		logger.info("controller:発注入力画面表示処理_self end");
		
		return "orderInput";
	}

	/**
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
        
		form.setCategoryInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("categoryInfos"));

		OrderSessionInfo orderSessionInfo = (OrderSessionInfo) smarejiSession.getAttribute("s_OrderInfo");
		form.setCategoryId(orderSessionInfo.getCategoryId());
		form.setGroupCode(orderSessionInfo.getGroupCode());
		form.setSupplierProductNo(orderSessionInfo.getSupplierProductNo());
		form.setProductId(orderSessionInfo.getProductId());
		form.setProductCode(orderSessionInfo.getProductCode());
		form.setProductName(orderSessionInfo.getProductName());
		
		List<DisplayOrderInput> displayOrderInputList = orderSessionInfo.getDisplayOrderInput();
		List<OrderInputSubForm> displayList = new ArrayList<OrderInputSubForm>();
		String[] orderAmount = (String[]) smarejiSession.getAttribute("orderAmount_");
		for (int i = 0; i < displayOrderInputList.size(); i++) {
			OrderInputSubForm display = new OrderInputSubForm();
			display.setGroupCode(displayOrderInputList.get(i).getGroupCode());
			display.setProductImage(displayOrderInputList.get(i).getProductImage());
			display.setProductInfo(displayOrderInputList.get(i).getProductInfo());
			display.setProductId(displayOrderInputList.get(i).getProductId());
			display.setProductCode(displayOrderInputList.get(i).getProductCode());
			display.setProductName(displayOrderInputList.get(i).getProductName());
			display.setSupplierName(displayOrderInputList.get(i).getSupplierName());
			display.setCategoryName(displayOrderInputList.get(i).getCategoryName());
			display.setStockAmount(displayOrderInputList.get(i).getStockAmount());
//			display.setOrderAmount(displayOrderInputList.get(i).getOrderAmount());
			display.setOrderPoint(displayOrderInputList.get(i).getOrderPoint());
			
			display.setSupplierId(displayOrderInputList.get(i).getSupplierId());

			display.setOrderAmount(orderAmount[i]);
			
			displayList.add(display);
		}
		
		form.setDisplayList(displayList);
		
		model.addAttribute("categoryId", orderSessionInfo.getCategoryId());	// TODO
		model.addAttribute("orderInputForm", form);
		
		logger.info("controller:発注入力画面表示処理_back end");

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
	
		StoreInfo storeInfo = (StoreInfo)smarejiSession.getAttribute("s_StoresInfo");
		OrderSessionInfo orderSessionInfo = (OrderSessionInfo)smarejiSession.getAttribute("s_OrderInfo");
		String identificationNo = null;
		List<String> storageInfoIdList = new ArrayList<String>();
		
		if (StringUtil.isEmpty(orderSessionInfo.getOrderControlNumber())) {
			Long datetime = System.currentTimeMillis();
			identificationNo = datetime.toString();

			// 仮発注登録
//			storageInfoIdList = orderInputService.entryPurchaseOrder(smarejiUser, object, storeInfo.getStoreId(), identificationNo);
			Map<String, String> orderMap = orderInputService.entryPurchaseOrder(smarejiUser, object, storeInfo.getStoreId(), identificationNo);

			Iterator<Map.Entry<String, String>> it = orderMap.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, String> order = it.next();
				storageInfoIdList.add(order.getValue());
			}
			
			orderSessionInfo.setOrderControlNumber(identificationNo);
			orderSessionInfo.setStorageInfoIdList(storageInfoIdList);
			// ここで検索条件をつめると検索後に入力された値が保持されてしまう
	//		orderSessionInfo.setCategoryId(object.getCategoryId());
	//		orderSessionInfo.setGroupCode(object.getGroupCode());
	//		orderSessionInfo.setSupplierProductNo(object.getSupplierProductNo());
	//		orderSessionInfo.setProductId(object.getProductId());
	//		orderSessionInfo.setProductCode(object.getProductCode());
	//		orderSessionInfo.setProductName(object.getProductName());
			smarejiSession.setAttribute("s_OrderInfo", orderSessionInfo);
			
			smarejiSession.setAttribute("orderAmount_", object.getOrderAmount_());
			smarejiSession.setAttribute("orderMap", orderMap);

		} else {
			identificationNo = orderSessionInfo.getOrderControlNumber();
			Map<String, String> map = (Map<String, String>) smarejiSession.getAttribute("orderMap");
			
			// 仮発注更新
			Map<String, String> orderMap = orderInputService.updatePurchaseOrder(smarejiUser, object, storeInfo.getStoreId(), identificationNo, map);

			smarejiSession.setAttribute("orderAmount_", object.getOrderAmount_());
		}

		// セッションから情報を取得
		// 発注入力画面で設定した情報 TODO 不要なら削除すること
		// OrderSessionInfo sOrderInfo = (OrderSessionInfo)smarejiSession.getAttribute("s_OrderInfo");
		// スタッフ名（ログインユーザ名＝発注者名）
		StaffInfo sStaffInfo = (StaffInfo)smarejiSession.getAttribute("s_StaffInfo");
		
		// 画面表示情報取得
		CheckOrderConfirmForm form = checkOrderConfirmService.getDisplayInfo(smarejiUser, identificationNo, sStaffInfo.getStaffName());

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
	
		// TODO 発注確認画面内のページングではデータを取得する必要はなし。checkOrderConfirmで取得したデータをセッションに格納するようにし、ここではそれに対してページングを行うようにする
		// セッションから情報を取得
		// 発注入力画面で設定した情報
		OrderSessionInfo sOrderInfo = (OrderSessionInfo)smarejiSession.getAttribute("s_OrderInfo");
		// スタッフ名（ログインユーザ名＝発注者名）
		StaffInfo sStaffInfo = (StaffInfo)smarejiSession.getAttribute("s_StaffInfo");
		//発注管理番号
		String orderControlNumber = sOrderInfo.getOrderControlNumber(); 
		
		// 画面表示情報取得
		CheckOrderConfirmForm form = checkOrderConfirmService.getDisplayInfo(smarejiUser, orderControlNumber, sStaffInfo.getStaffName());

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
    public String orderConfirm(@RequestParam(required = false) String orderId, @RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		// TODO テスト用
		orderId = "27";

		// 画面表示情報取得
		OrderConfirmForm form = orderConfirmService.getDisplayInfo(smarejiUser, orderId);

		// 画面に返す
		model.addAttribute("orderConfirmForm", form);
		
        return "orderConfirm";
	}

	/**
	 * 発注履歴画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注履歴画面
	 */
	@RequestMapping("/orderHistory")
    public String orderHistory(Model model,
    		@ModelAttribute @Validated OrderHistoryForm orderHistoryForm,BindingResult bindingResult,
    		@RequestParam(required = false) String orderId,
    		@RequestHeader(value = "referer", required = false) final String referer) {
		
		// セッション情報取得
		OrderHistoryForm session = (OrderHistoryForm)smarejiSession
				.getAttribute("orderHistorySession");
		
		// 発注IDが連携された場合、削除
		if(!Objects.isNull(orderId)&& orderId !="") {
			// 発注削除（API使用）
			orderHistoryService.deleteOrder(smarejiUser,orderId);
		}
		
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
		// 絞り込み条件設定
		orderHistoryForm = orderHistoryService.setCondition(orderHistoryForm,session);
		// セッションに画面情報を格納
		smarejiSession.setAttribute("orderHistorySession", orderHistoryForm);
		
		
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
		
		// 発注対象商品取得（API使用）、発注点数・発注金額合計設定
		orderHistoryService.getPurchaseOrderProduct(smarejiUser,orderHistoryForm);
		
		// 画面に設定する
		model.addAttribute("suppliers", suppliersMap);
		model.addAttribute("orderHistoryForm", orderHistoryForm);
        return "orderHistory";
	}

	/**
	 * 発注状況画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注状況確認画面
	 */
	@RequestMapping("/checkOrderStatus")
    public String checkOrderStatus(@RequestHeader(value = "referer", required = false) final String referer,
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

			
			List<CheckOrderStatusSubForm> matome = checkOrderStatusService.getMeisai(smarejiUser, orderId);
			model.addAttribute("meisai",matome);
			
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
