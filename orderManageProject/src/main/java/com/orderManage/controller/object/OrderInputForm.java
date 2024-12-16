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
    private String categoryId;

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

    /** 商品ID */
    private String[] productId_;

    /** 仕入先ID */
    private String[] supplierId_;
    
    /** 発注点数 */
    private String[] orderAmount_;
    
    // その他処理制御用
    /** 処理種別 */
    private String type;
    
    /** 非表示のページに発注点が入力されているか */
    private String inputFlag;
    
    /**
     * 
     * @return
     */
    @AssertTrue(message = "検索条件を１つ以上指定してください")
    public boolean isValidSearchConditionEmpty() {
    	boolean result = true;

    	// 検索時の場合のみチェックする
    	if ("0".equals(type)) {
	    	if (StringUtil.isEmpty(categoryId)
	    			&& StringUtil.isEmpty(groupCode)
	    			&& StringUtil.isEmpty(supplierProductNo)
	    			&& StringUtil.isEmpty(productId)
	    			&& StringUtil.isEmpty(productCode)
	    			&& StringUtil.isEmpty(productName)
	    			) {
	    		result = false;
	    	}
    	}
    	
    	return result;
    }

    /**
     * 
     * @return
     */
	@AssertTrue(message = "発注する商品を１つ以上選択してから発注に進んでください。")
	public boolean isValidOrderAmount_required() {
		boolean result = false;

		// 発注確認に進む場合のみチェックする
		if ("1".equals(type)) {
	    	if (orderAmount_ != null && orderAmount_.length > 0) {
	    		for (int i = 0; i < orderAmount_.length; i++) {
	    			if (StringUtil.isNotEmpty(orderAmount_[i])) {
	    				result = true;
	    				break;
	    			}
	    		}
	    	}
	    	
	    	if ("true".equals(inputFlag)) {
	    		result = true;
	    	}
		} else {
			result = true;
		}
 
		return result;
	}

    /**
     * 
     * @return
     */
    @AssertTrue(message = "発注点数を入力する場合は１以上、かつ、99999以下の整数で入力してください。")
    public boolean isValidOrderAmount_numeric() {
    	boolean result = true;

    	// 発注確認に進む、または、ページングの場合のみチェックする
    	if ("1".equals(type) || "3".equals(type)) {
	    	if (orderAmount_ != null && orderAmount_.length > 0) {
	    		for (int i = 0; i < orderAmount_.length; i++) {
	    			if (StringUtil.isNotEmpty(orderAmount_[i])) {
		    			try {
		    				int amount = Integer.parseInt(orderAmount_[i]);
		    				if (amount < 1 || amount > 99999) {
		    					result = false;
		    					break;
		    				}
		    			} catch (Exception ex) {
		    				result = false;
		    				break;
		    			}
	    			}
	    		}
	    	}
    	}
    	
    	return result;
    }
    
	public Map<String, String> getCategoryInfos() {
		return categoryInfos;
	}

	public void setCategoryInfos(Map<String, String> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}
}
