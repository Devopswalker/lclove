package com.lcpa.lclove.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ReqLogUtil {
	public static final String REQ_LOG_ENTRY_ATTR_KEY = "_REQ_LOG_ENTRY_KEY";
	public static final String REQ_LOG_ENTRY_KEY_PRE = "log_";
	private static HttpServletRequest getRequest(){
		ServletRequestAttributes holder = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(holder!=null){
			HttpServletRequest request = holder.getRequest();
			if(request!=null){
				return request;
			}
		}
		return null;
	}
	public static void addLogInfo(Map<String, String> info){
		if(info==null || info.isEmpty()){
			return;
		}
		HttpServletRequest req = getRequest();
		if(req!=null){
			Map<String, String> logEntry = (Map<String, String>) req.getAttribute(REQ_LOG_ENTRY_ATTR_KEY);
			if(logEntry==null){
				logEntry = new HashMap<>();
				req.setAttribute(REQ_LOG_ENTRY_ATTR_KEY, logEntry);
			}
			for(Map.Entry<String, String> entry: info.entrySet()){
				//Ö»½ØÈ¡128×Ö½Ú
				logEntry.put(REQ_LOG_ENTRY_KEY_PRE + StringUtils.substring(entry.getKey(), 0, 30), StringUtils.substring(entry.getValue(), 0, 128));
			}
		}
	}
	public static void addLogInfo(String key, String value){
		HttpServletRequest req = getRequest();
		if(req!=null){
			Map<String, String> logEntry = (Map<String, String>) req.getAttribute(REQ_LOG_ENTRY_ATTR_KEY);
			if(logEntry==null){
				logEntry = new HashMap<>();
				req.setAttribute(REQ_LOG_ENTRY_ATTR_KEY, logEntry);
			}
			logEntry.put(REQ_LOG_ENTRY_KEY_PRE + StringUtils.substring(key, 0, 30), StringUtils.substring(value, 0, 128));
		}
	}
}
