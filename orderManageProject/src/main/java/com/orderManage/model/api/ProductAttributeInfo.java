package com.orderManage.model.api;

/**
 * 商品属性情報 model
 * 
 * スマレジAPI　商品属性一覧取得時に格納するmodel
 * 
 * @author nakagawa
 *
 */
public class ProductAttributeInfo {

	/* 項目番号 */
	String no;
	
	/* 属性名 */
	String name;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
