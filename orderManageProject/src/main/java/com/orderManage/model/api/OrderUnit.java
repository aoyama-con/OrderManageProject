package com.orderManage.model.api;

/**
 * 発注単位
 * 
 */
public class OrderUnit {

	/* 発注単位区分 (0:単位なし、1:単位あり) */
	private String division;
	/* 発注単位数 */
	private String num;
	/* 発注単位名 */
	private String name;
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
