package com.orderManage.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * OrderManageProject Utilクラス
 */
public class OrderManageUtil {

	// インスタンス化の禁止
	private OrderManageUtil() {
	}
	
	/**
	 * ユーザが作成したオブジェクトをJson形式に変換する
	 * @param o オブジェクト
	 * @return String Json文字列
	 *         ※json形式変換失敗時はnullが返却される
	 */
	public static String objectToJson(Object o) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = "";
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			jsonStr = objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return null;
		}
		return jsonStr;
	}
	
}
