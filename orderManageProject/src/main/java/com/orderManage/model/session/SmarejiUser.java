package com.orderManage.model.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.orderManage.model.api.Contract;

/**
 * スマレジユーザー情報
 * 
 * セッションとして保持するクラス
 * セッションスコープBean(複数のコントローラーをまたぐ)
 * 
 * @author aocon-mac
 *
 */
//@SessionScope 複数のコントローラーにまたぐときに使用
@Component
public class SmarejiUser implements Serializable {

//	private static final long serialVersionUID = 1L;　@SessionScope使用時に使用
	
	/* 契約またはユーザー識別子 */
	private String sub;
	/* ログインユーザー名（フルネーム） */
	private String name;
	/* ログインユーザーメールアドレス */
	private String email;
	/* メール検証済みフラグ */
	private String email_verified;
	/* 契約情報 */
	private Contract contract;
	
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(String email_verified) {
		this.email_verified = email_verified;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
