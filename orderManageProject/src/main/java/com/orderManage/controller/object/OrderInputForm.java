package com.orderManage.controller.object;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.orderManage.util.StringUtil;

import jakarta.validation.constraints.AssertTrue;

public class OrderInputForm implements Serializable {

	// 検索条件
	/** 検索条件・部門一覧 */
    Map<String, String> categoryInfos;
    
    /** 検索条件・部門 */
    private String category;

    /** 検索条件・Gコード（グループコード） */
    private String groupCode;

    /** 検索条件・品番 */
    private String supplierProductNo;

    /** 検索条件・商品ID */
    private String productId;

    /** 検索条件・商品CD */
    private String productCode;

    /** 検索条件・商品名 */
    private String productName;

	// 検索結果
    /** 発注情報リスト */
    private List<OrderInputSubForm> displayList;

    // 商品ID
    private String[] productId_;

    //  仕入先ID
    private String[] supplierId_;
    
    // 発注点数
    private String[] orderAmount_;
    
	/**
     * 
     * @return
     */
    @AssertTrue(message = "検索条件を１つ以上指定してください")
    public boolean isValidSearchConditionEmpty() {
    	boolean result = true;

    	if (StringUtil.isEmpty(category)
    			&& StringUtil.isEmpty(groupCode)
    			&& StringUtil.isEmpty(supplierProductNo)
    			&& StringUtil.isEmpty(productId)
    			&& StringUtil.isEmpty(productCode)
    			&& StringUtil.isEmpty(productName)
    			) {
    		result = false;
    	}
    	
    	return result;
    }

	public Map<String, String> getCategoryInfos() {
		return categoryInfos;
	}

	public void setCategoryInfos(Map<String, String> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSupplierProductNo() {
		return supplierProductNo;
	}

	public void setSupplierProductNo(String supplierProductNo) {
		this.supplierProductNo = supplierProductNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<OrderInputSubForm> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<OrderInputSubForm> displayList) {
		this.displayList = displayList;
	}

	public String[] getProductId_() {
		return productId_;
	}

	public void setProductId_(String[] productId_) {
		this.productId_ = productId_;
	}

	public String[] getSupplierId_() {
		return supplierId_;
	}

	public void setSupplierId_(String[] supplierId_) {
		this.supplierId_ = supplierId_;
	}

	public String[] getOrderAmount_() {
		return orderAmount_;
	}

	public void setOrderAmount_(String[] orderAmount_) {
		this.orderAmount_ = orderAmount_;
	}
}
