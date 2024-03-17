package com.orderManage.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orderManage.controller.object.CheckOrderConfirmForm;
import com.orderManage.controller.object.StoreChoiceForm;
import com.orderManage.model.ApplicationPropertyModel;
import com.orderManage.model.api.StoreInfo;
import com.orderManage.model.session.SmarejiUser;
import com.orderManage.service.CheckOrderConfirmService;
import com.orderManage.service.MenuService;
import com.orderManage.service.OrderManageLoggingService;
import com.orderManage.service.StoreChoiceService;
import com.orderManage.service.UtilTestService;
import com.orderManage.util.DateUtil;

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
	/* *********************************************************/
	
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
//		String redirectStr = "redirect:" + app_properties.getUrlId() + "authorize?response_type=code&client_id=" 
//			+ app_properties.getClientId() + "&scope=openid+email+offline_access";
//		return redirectStr;
//		return "redirect:https://id.smaregi.dev/authorize?response_type=code&client_id=9b05282c38cbcc8d2faa18e4c81cb53b&scope=openid+email+offline_access";
		/** *****************************************************************************************/
		
		/** テスト用 ローカルで動かす用mockを使用 *****************/ 
		model.addAttribute("message", "ログイン画面");
        return "login";
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
		
		/** テスト用　Exception発生 ****/
		//String str = null;
		//str.toString();
		/**************************/
		
//		/** 認証関連 スマレジアクセス時にコメントをはずす*******************************************************************/
//		logger.debug("認可コード:code:" + code);
//		// authorization_code（code）を使用してユーザーアクセストークンをリクエストする
//		UserAccessToken uat = menuService.getUserAccessToken(code);
//		logger.debug("ユーザアクセストークン：" + uat.getAccess_token());
//
//		// ユーザーアクセストークンを使用してユーザー情報取得する
//		smarejiUser = menuService.getSmarejiUser(uat.getAccess_token());
//		logger.debug("契約ID：" + smarejiUser.getContract().getId());
//
//		// ユーザ情報をセッションに格納する
//		smarejiSession.setAttribute("s_smarejiUser", smarejiUser);
//		/****************************************************************************************************/
		
		/* Util系テスト ******************************************************************/
//		SmarejiUser suser = (SmarejiUser)smarejiSession.getAttribute("smarejiUser");
//		// 店舗一覧取得
//		List<StoreInfo> storeInfoList = utilTestService.getStoresInfo(suser);
//		// セッションに格納
//		smarejiSession.setAttribute("storesInfoList", storeInfoList);
		
		// 店舗情報取得
//		StoreInfo si = utilTestService.getStoreInfo(smarejiUser);
		// 発注一覧取得
//		List<PurchaseOrdersInfo> poiList = utilTestService.getPurchaseOrdersInfo(smarejiUser);
		// 発注情報取得
//		PurchaseOrdersInfo poi = utilTestService.getPurchaseOrderInfo(smarejiUser);
		// 発注登録
//		PurchaseOrdersInfo poientry = utilTestService.entryPurchaseOrder(smarejiUser);
		// 発注更新
//		PurchaseOrdersInfo poiupdate = utilTestService.updatePurchaseOrder(smarejiUser, "18");
		// 発注削除
//		utilTestService.deletePurchaseOrder(smarejiUser, "12");
		// 発注対象商品取得
//		List<PurchaseOrdersProductsInfo> popiList = utilTestService.getPurchaseOrdersProductInfo(smarejiUser, "22");
		// 発注対象店舗取得
//		List<PurchaseOrdersStoresInfo> posiList = utilTestService.getPurchaseOrdersStoreInfo(smarejiUser, "15");
		// 仕入先一覧取得
//		List<SuppliersInfo> suppliesList = utilTestService.getSuppliersInfo(smarejiUser);
		// 仕入先商品一覧取得
//		List<SuppliersProductsInfo> suplierProductList = utilTestService.getSuppliersProductsInfo(smarejiUser, "1");
		// 商品一覧取得
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
		form.setOrderDate(sysDate);
		form.setSysDate(sysDate);
		model.addAttribute("storeChoiceForm", form);

		// 入力チェックエラーの場合にセッションに保持しておく
//		smarejiSession.setAttribute("storeInfos", storesMap);
		smarejiSession.setAttribute("storeChoiceForm", form);

		logger.info("店舗選択画面遷移処理　終了");

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
		model.addAttribute("storeselect", object.getStoreselect());
		model.addAttribute("orderDate", object.getOrderDate());
		model.addAttribute("sysDate", object.getSysDate());
		
		// バリデートチェック
        if (bindingResult.hasErrors()) {
        	// エラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
//            model.addAttribute("storeInfos", smarejiSession.getAttribute("storeInfos"));
            object.setStoreInfos((LinkedHashMap<String, String>)smarejiSession.getAttribute("storeInfos"));	// TODO objectの使い回しは微妙？
            model.addAttribute("storeChoiceForm", object);
            return "storeChoice";
        }
 
        // 選択した店舗情報をセッションに保持する	// TODO APIから取得
        StoreInfo storeInfo = storeChoiceService.getStoreInfo(smarejiUser);
        smarejiSession.setAttribute("storesInfo", storeInfo);
        
        // 発注入力　初期表示
        
        // 発注入力　検索
        
        
        
		logger.info("controller:発注入力画面表示処理 end");
		
		return "orderInput";
	}

	/**
	 * 発注確認画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return  発注確認画面
	 */
	@RequestMapping("/checkOrderConfirm")
    public String checkOrderConfirm(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
		
		logger.info("発注確認画面遷移処理　開始");
		
		// スマレジユーザ取得
		SmarejiUser suser = (SmarejiUser)smarejiSession.getAttribute("smarejiUser");
		
		// 画面表示情報取得
		CheckOrderConfirmForm form = checkOrderConfirmService.getDisplayInfo(suser);
	
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
    public String orderConfirm(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
        return "orderConfirm";
	}

	/**
	 * 発注履歴画面遷移時に制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　発注履歴画面
	 */
	@RequestMapping("/orderHistory")
    public String orderHistory(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
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
    		Model model) {
        return "checkOrderStatus";
	}

	/**
	 * 検索モーダルを制御するコントローラー
	 * 
	 * @param model　パラメータ受け渡し制御Model
	 * @return　検索モーダル画面
	 */
	@RequestMapping("/orderSerch")
	@ResponseBody
    public String serch(@RequestHeader(value = "referer", required = false) final String referer,
    		Model model) {
        return "";
	}
	/* ************************************************************************************/
}
