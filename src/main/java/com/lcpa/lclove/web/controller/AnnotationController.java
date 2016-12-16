package com.lcpa.lclove.web.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lcpa.lclove.util.DateUtil;
import com.lcpa.lclove.util.JsonUtils;

/**
 * ��������
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016��12��16�� ����3:57:53
 */
public abstract class AnnotationController{
	public static final String ERROR_MESSAGES_KEY = "errmsg";
	
	protected final String showError(ModelMap model, String errMsg){
		model.put(ERROR_MESSAGES_KEY, errMsg);
		return "redirect:/showResult.xhtml";
	}
	protected final String showError_NOT_LOGIN(ModelMap model){
		model.put(ERROR_MESSAGES_KEY, "���ȵ�¼!");
		return "redirect:/showResult.xhtml";
	}
	
	protected final String showJsonError_CAPTCHA_ERROR(ModelMap model){
		return showJsonError(model, "��֤�����");
	}
	protected final String showJsonError_NOT_LOGIN(ModelMap model){
		return showJsonError(model, "����û�е�¼�����ȵ�¼��");
	}
	protected final String showJsonError_NORIGHTS(ModelMap model){
		return showJsonError(model, "��û��Ȩ�ޣ�");
	}
	protected final String showJsonError_REPEATED(ModelMap model){
		return showJsonError(model, "�����ظ�������");
	}
	protected final String showJsonError_NOT_FOUND(ModelMap model){
		return showJsonError(model, "δ�ҵ�������ݣ�");
	}
	protected final String showJsonError_DATAERROR(ModelMap model){
		return showJsonError(model, "�����д�����ˢ�����ԣ�");
	}
	protected final String showJsonError_PARAMSERROR(ModelMap model){
		return showJsonError(model, "��������");
	}
	protected final String showJsonError_SOFAST(ModelMap model){
		return showJsonError(model, "�ύ����Ƶ�ʲ���̫�죡");
	}
	
	protected final String showJsonSuccess(ModelMap model){
		return showJsonSuccess(model, "");
	}
	protected final String showJsonSuccess(ModelMap model, String retval){
		return showJsonSuccess(model, retval);
	}
	protected final String showJsonSuccess(ModelMap model, Map jsonMap){
		return showJsonSuccess(model, jsonMap);
	}
	protected final String showJsonSuccess(ModelMap model, Object object){
		Map jsonMap = new HashMap();
		jsonMap.put("success", true);
		if(object instanceof String){
			jsonMap.put("data", object);
		}else{
			jsonMap.put("data", JsonUtils.writeObjectToJson(object));
		}
		model.put("jsonMap", jsonMap);
		return "common/json.vm";
	}
	protected final String showJsonError(ModelMap model, String msg){
		Map jsonMap = new HashMap();
		jsonMap.put("success", false);
		jsonMap.put("msg", msg);
		model.put("jsonMap", jsonMap);
		return "common/json.vm";
	}
	
	protected final String show404(ModelMap model, String msg){
		model.put("msg", msg);
		return "error/404.vm";
	}
	
	protected void download(String downloadType, HttpServletResponse response){
		if(StringUtils.equals(downloadType, "xls")){
			response.setContentType("application/xls");
		}else if (StringUtils.equals(downloadType, "jpg")) {
			response.setContentType("image/jpeg");
		}else{
			response.setContentType("application/x-download");
		}
		response.addHeader("Content-Disposition", "attachment;filename=LcLove"+DateUtil.format(new Date(), "yyMMdd_hhmmss")+ "." + downloadType);
	}
	
	protected HttpServletRequest getRequest(){
		ServletRequestAttributes holder = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(holder!=null){
			HttpServletRequest request = holder.getRequest();
			if(request!=null){
				return request;
			}
		}
		return null;
	}
	protected HttpServletResponse getResponse(){
		ServletRequestAttributes holder = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(holder!=null){
			HttpServletResponse response = holder.getResponse();
			if(response!=null){
				return response;
			}
		}
		return null;
	}	
	
	/**
	 * ֻ����Get�ύ�Ĳ�����ת
	 * @param targetUrl
	 * @param request
	 * @param model
	 * @return
	 */
	protected String gotoLogin(String targetUrl, HttpServletRequest request, ModelMap model){
		try {
			if(StringUtils.isNotBlank(targetUrl)){
				String queryStr = request.getQueryString();//ֻ����Get����
				String paramStr = ""; 
				if(StringUtils.isNotBlank(queryStr) && StringUtils.length(queryStr) < 300){//300���µ�ת��
					paramStr = URLDecoder.decode(queryStr, "utf-8");
				}
				targetUrl += targetUrl.indexOf('?')>0?"&" + paramStr:"?" + paramStr;
				
				return showRedirect("adminLogin.xhtml?TARGETURL=" + URLEncoder.encode(targetUrl, "utf-8"), model);
			}
		} catch (UnsupportedEncodingException e) {//ignore
		}
		return showRedirect("adminLogin.xhtml", model);
	}
	
	protected final String showRedirect(String path, ModelMap model){
		if(StringUtils.startsWith(path, "/")) path = path.substring(1);
		StringBuilder targetUrl = new StringBuilder(path);
		appendQueryProperties(targetUrl, model, "utf-8");
		model.put("redirectUrl", targetUrl.toString());
		return "common/tempRedirect.vm";
	}
	private void appendQueryProperties(StringBuilder targetUrl, ModelMap model, String encoding) {
		boolean first = (targetUrl.indexOf("?") < 0);
		for (Map.Entry<String, Object> entry : queryProperties(model).entrySet()) {
			Object rawValue = entry.getValue();
			Iterator valueIter = null;
			if (rawValue != null && rawValue.getClass().isArray()) {
				valueIter = Arrays.asList(ObjectUtils.toObjectArray(rawValue)).iterator();
			}
			else if (rawValue instanceof Collection) {
				valueIter = ((Collection) rawValue).iterator();
			}
			else {
				valueIter = Collections.singleton(rawValue).iterator();
			}
			while (valueIter.hasNext()) {
				Object value = valueIter.next();
				if (first) {
					targetUrl.append('?');
					first = false;
				}
				else {
					targetUrl.append('&');
				}
				String encodedKey = urlEncode(entry.getKey(), encoding);
				String encodedValue = (value != null ? urlEncode(value.toString(), encoding) : "");
				targetUrl.append(encodedKey).append('=').append(encodedValue);
			}
		}
	}
	private Map<String, Object> queryProperties(Map<String, Object> model) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			if (isEligibleProperty(entry.getValue())) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
	private boolean isEligibleProperty(Object value) {
		if (value == null) return false;
		if (isEligibleValue(value)) return true;

		if (value.getClass().isArray()) {
			int length = Array.getLength(value);
			if (length == 0) {
				return false;
			}
			for (int i = 0; i < length; i++) {
				Object element = Array.get(value, i);
				if (!isEligibleValue(element)) {
					return false;
				}
			}
			return true;
		}

		if (value instanceof Collection) {
			Collection coll = (Collection) value;
			if (coll.isEmpty()) {
				return false;
			}
			for (Object element : coll) {
				if (!isEligibleValue(element)) {
					return false;
				}
			}
			return true;
		}

		return false;
	}
	private String urlEncode(String input, String charsetName) {
		try {
			return (input != null ? URLEncoder.encode(input, charsetName) : null);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	private boolean isEligibleValue(Object value) {
		return (value != null && BeanUtils.isSimpleValueType(value.getClass()));
	}
	

}
