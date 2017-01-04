package com.lcpa.lclove.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * Velocity页面工具类
 * @Reference:
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016年12月11日 下午11:18:49
 */
public class VmUtils{

	public static final VmUtils instance = new VmUtils();
	private static String jsVersion = DateUtil.format(new Date(), "yyyyMMddHH");
	public final static void setJsVersion(String jv) {
		jsVersion = jv;
	}
	public final static String getJsVersion() {
		return jsVersion;
	}

	public final static String escapeHtml(String str) {
		return HtmlUtils.htmlEscape(str);
	}

	/**
	 * 先截取后，再将所有的HTML符号如:<, >, 等替换为 &gt, &lt
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public final static String escabbr(String str, int length) {
		// 与htmlabbr的区别是不经过去除html标签处理
		String result = StringUtil.enabbr(str, length);
		return HtmlUtils.htmlEscape(result);
	}

	/**
	 * 将html中的文本提出出来，再截取length个，再将所有的HTML符号如:<, >, 等替换为 &gt, &lt
	 *
	 * @param html
	 * @param length
	 * @return
	 */
	public final static String htmlabbr(String html, int length) {
		return StringUtil.getEscapeText(html, length);
	}

	public final static String getText(String html) {
		return StringUtil.getEscapeText(html);
	}

	public final static String subLastText(String temp, String text) {
		if (StringUtils.isBlank(temp) || StringUtils.isBlank(text))
			return "";
		return StringUtils.substring(text, StringUtils.lastIndexOf(text, temp) + 1);
	}

	public final static String getHtmlText(String html, int length) {
		return StringUtil.getHtmlText(html, length);
	}

	public final static String encodeStr(String str, String encoding) {
		try {
			if(StringUtils.isBlank(str)){
				return str;
			}
			String result = URLEncoder.encode(str, encoding);
			return result;
		} catch (UnsupportedEncodingException e) {// ignore
		}
		return null;
	}

	public final static String escbr(String str) {
		return StringUtils.replace(escapeHtml(str), "\n", "<br />");
	}

	public final static boolean isEmpObj(Object obj) {
		if (obj == null)
			return true;
		if (obj.getClass().isArray()) {
			// Thanks to Eric Fixler for this refactor.
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).size() == 0;
		}
		if (obj instanceof Map) {
			return ((Map) obj).size() == 0;
		}
		return false;
	}

	public final static boolean isNotEmpObj(Object obj) {
		return !isEmpObj(obj);
	}

	public final static Integer size(Object list) {
		if (list == null)
			return 0;
		if (list.getClass().isArray()) {
			// Thanks to Eric Fixler for this refactor.
			return new Integer(Array.getLength(list));
		}
		if (list instanceof Collection) {
			return ((Collection) list).size();
		}
		if (list instanceof Map) {
			return ((Map) list).size();
		}
		return 0;
	}

	public final static String printList(String[] list) {
		return StringUtils.join(list, ",");
	}

	public final static String printList(List<String> list) {
		return printList(list, ",");
	}

	public final static String printList(List<String> list, String separatorChars) {
		if (isEmpObj(list))
			return null;
		String[] a = list.toArray(new String[list.size()]);
		return StringUtils.join(a, separatorChars);
	}

	public final static boolean isNull(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			return StringUtils.isBlank((String) obj);
		}
		return false;
	}

	public final static boolean eq(Object obj1, Object obj2) {
		if (obj1 == null)
			return obj2 == null;
		if (obj2 == null)
			return false;
		if (obj1.equals(obj2))
			return true;
		if (obj1.getClass().equals(obj2.getClass()))
			return false;
		return StringUtils.equals(obj1.toString(), obj2.toString());
	}

	public final static boolean gt(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null)
			return true;
		if (obj2 == null)
			return true;
		if (obj1 == null)
			return false;
		if (obj1.getClass().equals(obj2.getClass())) {
			try {
				int i = ((Comparable) obj1).compareTo(obj2);
				return i > 0;
			} catch (Exception e) {
				return false;
			}
		} else {
			try {
				return Double.parseDouble("" + obj1) - Double.parseDouble("" + obj2) > 0;
			} catch (Exception e) {
				return ("" + obj1).compareTo(obj2.toString()) > 0;
			}
		}
	}

	public final static Object dft(Object original, Object value) {
		if (original == null)
			return value;
		if (original instanceof String && isBlank("" + original))
			return value;
		return original;
	}

	public final static boolean contains(Object list, Object element) {
		if (list == null)
			return false;
		if (list.getClass().isArray()) {
			return arrayContains(list, element);
		}
		if (!(list instanceof Collection)) {
			return false;
		}
		return ((Collection) list).contains(element);
	}

	private final static boolean arrayContains(Object array, Object element) {
		int size = size(array).intValue();
		try {
			for (int index = 0; index < size; ++index) {
				if (eq(element, Array.get(array, index))) {
					return true;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (Exception e) {
		}
		return false;
	}

	public final static boolean isAscii(String s) {
		if (s == null)
			return true;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > 256)
				return false;
		}
		return true;
	}

	public final static String dft(String src, String dft) {
		if (isBlank(src))
			return dft;
		return src;
	}

	public final static int getByteLength(String input) {
		if (input == null)
			return 0;
		int length = input.length();
		int byteLength = length;
		for (int i = 0; i < length; i++)
			if (input.charAt(i) > 128)
				byteLength++;
		return byteLength;
	}

	public final static boolean isBlank(Object obj) {
		if (obj == null)
			return true;
		return StringUtils.isBlank(obj + "");
	}

	public final static String replaceName(String name, String replaceOldName, String repalceNewName) {
		if (StringUtils.isNotBlank(name) && name.indexOf(replaceOldName) != -1) {
			return name.replace(replaceOldName, repalceNewName);
		} else {
			return name;
		}
	}

	public final static String formatPercent(Integer num1, Integer num2) {
		return formatPercent(num1, num2, "0.00%");// 设置百分率的输出形式，形如00%,根据需要设定。
	}

	public final static String formatPercent(Integer num1, Integer num2, String format) {
		if (num1 == null || num2 == null || num2 == 0)
			return "?";
		double div = (double) num1 / num2;
		DecimalFormat myformat = (DecimalFormat) NumberFormat.getPercentInstance();
		myformat.applyPattern(format);
		return myformat.format(div);
	}

	public final static String formatPercent(Double num1, Double num2) {
		if (num1 == null || num2 == null || num2 == 0)
			return "";
		String format = "0.00%";
		if (num1.intValue() == num1.doubleValue() && num2.intValue() == num2.doubleValue()) {
			format = "0%";
		}
		return formatPercent(num1, num2, format);// 设置百分率的输出形式，形如00%,根据需要设定。
	}

	public final static String formatPercent(Double num1, Double num2, String format) {
		if (num1 == null || num2 == null || num2 == 0)
			return "";
		double div = num1 / num2;
		DecimalFormat myformat = (DecimalFormat) NumberFormat.getPercentInstance();
		myformat.applyPattern(format);
		return myformat.format(div);
	}

	public final static String getJsonValueByKey(String json, String key) {
		return JsonUtils.getJsonValueByKey(json, key);
	}

	public String getScriptObject(Object modelObject){
		return JsonUtils.writeObjectToJson(modelObject);
	}

	public String getScriptString(String str){
		str = JsonUtils.writeObjectToJson(str);
		return str;
	}

	public static Map<String, String> readJsonToMap(String json){
		return JsonUtils.readJsonToMap(json);
	}

	public static String getTextContent(String html){
		if(StringUtils.isBlank(html)) return "";
		String text = StringUtil.getHtmlText(html);
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(text)) {
			for (int i = 0; i < text.length(); i++) {// 过滤空白字符
				if (text.charAt(i) >= 48)
					sb.append(text.charAt(i));
			}
		}
		text = sb.toString();
		return text;
	}
}
