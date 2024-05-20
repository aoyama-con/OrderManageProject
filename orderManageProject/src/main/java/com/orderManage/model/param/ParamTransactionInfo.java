package com.orderManage.model.param;

import java.util.List;

/**
 * 取引一覧取得APIにリクエストする際に設定するパラメータ情報クラス
 * サービスクラスで設定され、APIに渡す
 * 
 */
public class ParamTransactionInfo {

	/* 検索パラメータ HTTPリクエスト時はカンマ区切りとする*/
	private List<String> fields;
	
	/* 並び順 */
	private String sort;
	
	/* 上限数 */
	private int limit;
	
	/* ページ */
	private int page;
	
	/* 取引ID(From) */
	private String transaction_head_id_from;
	
	/* 取引ID(To) */
	private String transaction_head_id_to;
	
	/* 取引日時(From)：サーバーに送信された日時。販売日時は端末取引日時を参照。[YYYY-MM-DDThh:mm:ssTZD] */
	private String transaction_date_time_from;
	
	/* 取引日時(To)：サーバーに送信された日時。販売日時は端末取引日時を参照。[YYYY-MM-DDThh:mm:ssTZD] */
	private String transaction_date_time_to;
	
	/* 取引区分 */
	private String transaction_head_division;
	
	/* 店舗ID */
	private int store_id;
	
	/* 端末取引日時(From)：端末で設定された取引日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String terminal_tran_date_time_from;
	
	/* 端末取引日時(To)：端末で設定された取引日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String terminal_tran_date_time_to;
	
	/* 精算日時 [YYYY-MM-DDThh:mm:ssTZD] */
	private String adjustment_date_time;
	
	/* 締め日 [YYYY-MM-DD] */
	private String sum_date;
	
	/* 締め日(From)[YYYY-MM-DD] */
	private String sum_date_from;
	
	/* 締め日(To)[YYYY-MM-DD] */
	private String sum_date_to;
	
	/* 会員コード */
	private String customer_code;
	
	/* レシート番号 */
	private String transaction_uuid;
	
	/* バーコード */
	private String barcode;
	
	/* 更新日時(From)：登録時・更新時の日時。締め日(From)[YYYY-MM-DD] */
	private String upd_date_time_from;
	
	/* 更新日時(To)：登録時・更新時の日時。[YYYY-MM-DDThh:mm:ssTZD] */
	private String upd_date_time_to;
	
	/* 取引明細情報を付加するか(全項目付加する場合はall, 一部項目を付加する場合はsummary, しない場合はnone) */
	private String with_details;
	
	/* その他支払い情報を付加するか(付加する場合はall, しない場合はnone) */
	private String with_deposit_others;
		
	/* 取置き情報を付加するか(付加する場合はall, しない場合はnone) */
	private String with_layaways;
	
	/* 取置き引取情報を付加するか(付加する場合はall, しない場合はnone) */
	private String with_layaway_pick_ups;
	
	/* 取引金操作情報を付加するか(付加する場合はall, しない場合はnone) */
	private String with_money_control;
	
	/* 販売時の商品属性情報を付加するか(付加する場合はall, しない場合はnone) */
	private String with_detail_product_attributes;

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getTransaction_head_id_from() {
		return transaction_head_id_from;
	}

	public void setTransaction_head_id_from(String transaction_head_id_from) {
		this.transaction_head_id_from = transaction_head_id_from;
	}

	public String getTransaction_head_id_to() {
		return transaction_head_id_to;
	}

	public void setTransaction_head_id_to(String transaction_head_id_to) {
		this.transaction_head_id_to = transaction_head_id_to;
	}

	public String getTransaction_date_time_from() {
		return transaction_date_time_from;
	}

	public void setTransaction_date_time_from(String transaction_date_time_from) {
		this.transaction_date_time_from = transaction_date_time_from;
	}

	public String getTransaction_date_time_to() {
		return transaction_date_time_to;
	}

	public void setTransaction_date_time_to(String transaction_date_time_to) {
		this.transaction_date_time_to = transaction_date_time_to;
	}

	public String getTransaction_head_division() {
		return transaction_head_division;
	}

	public void setTransaction_head_division(String transaction_head_division) {
		this.transaction_head_division = transaction_head_division;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getTerminal_tran_date_time_from() {
		return terminal_tran_date_time_from;
	}

	public void setTerminal_tran_date_time_from(String terminal_tran_date_time_from) {
		this.terminal_tran_date_time_from = terminal_tran_date_time_from;
	}

	public String getTerminal_tran_date_time_to() {
		return terminal_tran_date_time_to;
	}

	public void setTerminal_tran_date_time_to(String terminal_tran_date_time_to) {
		this.terminal_tran_date_time_to = terminal_tran_date_time_to;
	}

	public String getAdjustment_date_time() {
		return adjustment_date_time;
	}

	public void setAdjustment_date_time(String adjustment_date_time) {
		this.adjustment_date_time = adjustment_date_time;
	}

	public String getSum_date() {
		return sum_date;
	}

	public void setSum_date(String sum_date) {
		this.sum_date = sum_date;
	}

	public String getSum_date_from() {
		return sum_date_from;
	}

	public void setSum_date_from(String sum_date_from) {
		this.sum_date_from = sum_date_from;
	}

	public String getSum_date_to() {
		return sum_date_to;
	}

	public void setSum_date_to(String sum_date_to) {
		this.sum_date_to = sum_date_to;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getTransaction_uuid() {
		return transaction_uuid;
	}

	public void setTransaction_uuid(String transaction_uuid) {
		this.transaction_uuid = transaction_uuid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getUpd_date_time_from() {
		return upd_date_time_from;
	}

	public void setUpd_date_time_from(String upd_date_time_from) {
		this.upd_date_time_from = upd_date_time_from;
	}

	public String getUpd_date_time_to() {
		return upd_date_time_to;
	}

	public void setUpd_date_time_to(String upd_date_time_to) {
		this.upd_date_time_to = upd_date_time_to;
	}

	public String getWith_details() {
		return with_details;
	}

	public void setWith_details(String with_details) {
		this.with_details = with_details;
	}

	public String getWith_deposit_others() {
		return with_deposit_others;
	}

	public void setWith_deposit_others(String with_deposit_others) {
		this.with_deposit_others = with_deposit_others;
	}

	public String getWith_layaways() {
		return with_layaways;
	}

	public void setWith_layaways(String with_layaways) {
		this.with_layaways = with_layaways;
	}

	public String getWith_layaway_pick_ups() {
		return with_layaway_pick_ups;
	}

	public void setWith_layaway_pick_ups(String with_layaway_pick_ups) {
		this.with_layaway_pick_ups = with_layaway_pick_ups;
	}

	public String getWith_money_control() {
		return with_money_control;
	}

	public void setWith_money_control(String with_money_control) {
		this.with_money_control = with_money_control;
	}

	public String getWith_detail_product_attributes() {
		return with_detail_product_attributes;
	}

	public void setWith_detail_product_attributes(String with_detail_product_attributes) {
		this.with_detail_product_attributes = with_detail_product_attributes;
	}
}
