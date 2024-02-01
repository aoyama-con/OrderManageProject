package com.orderManage.model.api;

/**
 * 
 * 
 * @author aocon-mac
 *
 */
public class Contract {
	
	/* 契約ID */
	private String id;
	/* 契約ID内においてのユーザー識別子 */
	private String user_id;
	/* オーナーフラグ */
	private String is_owner;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIs_owner() {
		return is_owner;
	}
	public void setIs_owner(String is_owner) {
		this.is_owner = is_owner;
	}
}
