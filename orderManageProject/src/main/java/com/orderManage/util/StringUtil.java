package com.orderManage.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * StringUtilsクラス
 * 
 * @author 
 * @version
 */
public class StringUtil {

	/**
	 * <p>文字列が空文字かnullかを判定します</p>
	 *
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs チェック対象文字列
	 * @return 空文字、もしくはnullの場合は{@code true}を返します
	 */
	public static boolean isEmpty(CharSequence cs) {
		return StringUtils.isEmpty(cs);
	}

	/**
	 * <p>同じ文字列かを判定します</p>
	 * 
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, "abc")  = false
	 * StringUtils.equals("abc", null)  = false
	 * StringUtils.equals("abc", "abc") = true
	 * StringUtils.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @param cs1 文字列1
	 * @param cs2 文字列2
	 * @return 同じ文字列の場合は{@code true}を返します
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return StringUtils.equals(cs1, cs2);
	}

	/**
	 * <p>文字列が空文字以外、null以外かを判定します</p>
	 *
	 * <pre>
	 * StringUtil.isNotEmpty(null)      = false
	 * StringUtil.isNotEmpty("")        = false
	 * StringUtil.isNotEmpty(" ")       = true
	 * StringUtil.isNotEmpty("bob")     = true
	 * StringUtil.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs チェック対象文字列
	 * @return 空文字以外、null以外の場合は{@code true}を返します
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return StringUtils.isNotEmpty(cs);
	}

	/**
	 * <p>文字列がアルファベットのみかを判定します</p>
	 *
	 * <pre>
	 * StringUtil.isAlpha(null)   = false
	 * StringUtil.isAlpha("")     = false
	 * StringUtil.isAlpha("  ")   = false
	 * StringUtil.isAlpha("abc")  = true
	 * StringUtil.isAlpha("ab2c") = false
	 * StringUtil.isAlpha("ab-c") = false
	 * </pre>
	 * 
	 * @param cs チェック対象文字列
	 * @return アルファベットのみの場合は{@code true}を返します
	 */
	public static boolean isAlphabet(CharSequence cs) {
		return StringUtils.isAlpha(cs);
	}

	/**
	 * <p>文字列が半角数字のみかを判定します</p>
	 *
	 * <pre>
	 * StringUtil.isHalfNumeric(null)   = false
	 * StringUtil.isHalfNumeric("")     = false
	 * StringUtil.isHalfNumeric("  ")   = false
	 * StringUtil.isHalfNumeric("123")  = true
	 * StringUtil.isHalfNumeric("ab2c") = false
	 * StringUtil.isHalfNumeric("12-3") = false
	 * StringUtil.isHalfNumeric("１２") = false
	 * </pre>
	 * 
	 * @param cs チェック対象文字列
	 * @return 数字のみの場合は{@code true}を返します
	 */
	public static boolean isHalfNumeric(CharSequence cs) {
		if (isEmpty(cs)) {
			return false;
		}
		String regex_num = "^[0-9]+$";
		Pattern p1 = Pattern.compile(regex_num);
		Matcher m1 = p1.matcher(cs);
		boolean result = m1.matches();
		return result;
	}

	/**
	 * <p>文字列が半角英数のみかを判定します</p>
	 *
	 * <pre>
	 * StringUtil.isAlphanumeric(null)   = false
	 * StringUtil.isAlphanumeric("")     = false
	 * StringUtil.isAlphanumeric("  ")   = false
	 * StringUtil.isAlphanumeric("abc")  = true
	 * StringUtil.isAlphanumeric("ab c") = false
	 * StringUtil.isAlphanumeric("ab2c") = true
	 * StringUtil.isAlphanumeric("ab-c") = false
	 * </pre>
	 * 
	 * @param cs チェック対象文字列
	 * @return 半角英数のみの場合は{@code true}を返します
	 */
	public static boolean isAlphanumeric(CharSequence cs) {
		if (isEmpty(cs)) {
			return false;
		}
		String regex_num = "^[A-Za-z0-9]+$";
		Pattern p1 = Pattern.compile(regex_num);
		Matcher m1 = p1.matcher(cs);
		boolean result = m1.matches();
		return result;

	}

	/**
	 * <p>文字列が日付として妥当かを判定します</p>
	 *
	 * <pre>
	 * StringUtil.isDate("") = false
	 * StringUtil.isDate(null)= false
	 * StringUtil.isDate("2023/12/24")= true
	 * StringUtil.isDate("2023-12-24")= true
	 * StringUtil.isDate("20231231")= true
	 * StringUtil.isDate("20231232")= false
	 * </pre>
	 * 
	 * @param cs チェック対象文字列
	 * @return 半角英数のみの場合は{@code true}を返します
	 */
	public static boolean isDate(CharSequence cs) {
		boolean ret = false;
		if (isEmpty(cs)) {
			return ret;
		}
		try {
			if (cs != null) {
				String yyyMMdd = ((String) cs).replace("-", "").replace("/", "");
				DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT).parse(yyyMMdd,
						LocalDate::from);
				ret = true;
			}
		} catch (DateTimeParseException e) {
			return ret;
		}
		return ret;
	}

	/**
	 * <p>文字列が指定した長さ未満かを判定します</p>
	 * 
	 * @param cs チェック対象文字列
	 * @param length 長さ
	 * @return 文字列の長さが指定した長さ未満の場合は{@code true}を返します
	 */
	public static boolean isLessThan(CharSequence cs, int length) {
		if (cs.length() < length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>文字列が指定した長さ以下かを判定します</p>
	 * 
	 * @param cs チェック対象文字列
	 * @param length 長さ
	 * @return 文字列の長さが指定した長さ以下の場合は{@code true}を返します
	 */
	public static boolean isOrLess(CharSequence cs, int length) {
		if (cs.length() <= length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>文字列が指定した長さを超えているかを判定します</p>
	 * 
	 * @param cs チェック対象文字列
	 * @param length 長さ
	 * @return 文字列の長さが指定した長さを超えている場合は{@code true}を返します
	 */
	public static boolean isMoreThan(CharSequence cs, int length) {
		if (cs.length() > length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>文字列が指定した長さ以上かを判定します</p>
	 * 
	 * @param cs チェック対象文字列
	 * @param length 長さ
	 * @return 文字列の長さが指定した長さ以上の場合は{@code true}を返します
	 */
	public static boolean isOrMore(CharSequence cs, int length) {
		if (cs.length() >= length) {
			return true;
		} else {
			return false;
		}
	}
}
