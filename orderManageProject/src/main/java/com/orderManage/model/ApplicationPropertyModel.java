package com.orderManage.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * application.propertiesに設定されたスマレジ環境変数を定義するクラス
 * 
 */
@Component
public class ApplicationPropertyModel {

	/* clientid */
	@Value("${smareji.clientid}")
	private String clientId;
	
	/* clientsecret */
	@Value("${smareji.clientsecret}")
	private String clientSecret;
	
	/* url.id */
	@Value("${smareji.url.id}")
	private String urlId;
	
	/* url.api */
	@Value("${smareji.url.api}")
	private String urlApi;
	
	/* apl.scope */
	@Value("${smareji.apl.scope}")
	private String aplScope;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getUrlApi() {
		return urlApi;
	}

	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}

	public String getAplScope() {
		return aplScope;
	}

	public void setAplScope(String aplScope) {
		this.aplScope = aplScope;
	}
}
