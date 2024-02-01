package com.orderManage.model.api;

/**
 * スタッフ情報 model
 * 
 * スマレジAPI　スタッフ一覧取得時に格納するmodel
 * 
 * @author aocon-mac
 *
 */
public class StaffInfo {
	
	/* スタッフID */
	private String staffId;
	/* スタッフコード */
	private String staffCode;
	/* スタッフ名 */
	private String staffName;
	/* スタッフ名カナ */
	private String staffNameKana;
	/* ランク */
	private String rank;
	/* 表示フラグ（0：表示しない、1：表示する） */
	private String displayFlag;
	/* 表示順 */
	private String displaySequence;
	/* ログインフラグ（0：ログイン不可、1：ログイン可、2：認証待ち、3：停止） */
	private String loginStaffFlag;
	/* メールアドレス */
	private String email;
	/* ログイン許可IPアドレス */
	private String loginIpAddress;
	/* 役割 */
	private String roleId;
	/* 作成日時 */
    private String insDateTime;
	/* 作成日時 */
    private String updDateTime;

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffNameKana() {
		return staffNameKana;
	}
	public void setStaffNameKana(String staffNameKana) {
		this.staffNameKana = staffNameKana;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(String displaySequence) {
		this.displaySequence = displaySequence;
	}
	public String getLoginStaffFlag() {
		return loginStaffFlag;
	}
	public void setLoginStaffFlag(String loginStaffFlag) {
		this.loginStaffFlag = loginStaffFlag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginIpAddress() {
		return loginIpAddress;
	}
	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getInsDateTime() {
		return insDateTime;
	}
	public void setInsDateTime(String insDateTime) {
		this.insDateTime = insDateTime;
	}
	public String getUpdDateTime() {
		return updDateTime;
	}
	public void setUpdDateTime(String updDateTime) {
		this.updDateTime = updDateTime;
	}
}
