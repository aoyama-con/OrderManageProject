package com.orderManage.model.session;

import java.util.List;

import com.orderManage.model.service.DisplayOrderInput;
import com.orderManage.model.service.Pagination;

/**
 * 発注入力画面　セッション情報
 */
public class OrderSessionInfo {

	/* ページ情報 */
	private Pagination pagenation;
	
	/* 発注管理番号 */
	private String OrderControlNumber;
	/* 発注ID */
	private List<String> storageInfoIdList;
	/* 商品情報一覧（表示） */
	private List<DisplayOrderInput> displayOrderInput;

	/* 検索条件 ****************************************/
	/* 部門ID */
	private String categoryId;
	/* 商品属性 */
	private String attribute;
	/* グループコード */
	private String groupCode;
	/* 品番 */
	private String supplierProductNo;
	/* 商品ID */
	private String productId;
	/* 商品CD */
	private String productCode;
	/* 商品名 */
	private String productName;
	/* PB区分 */
	private String pbDivision;
	/* 販売金額 週(プルダウン) */
	private String totalPriceWeek;
	/* 販売金額 */
	private String totalPrice;
	/* ***********************************************/
	
	
}
