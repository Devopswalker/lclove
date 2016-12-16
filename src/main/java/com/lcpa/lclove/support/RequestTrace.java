package com.lcpa.lclove.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lcpa.lclove.util.WebUtils;
import com.lcpa.lclove.util.LogTraceUtil.LogTrace;

public class RequestTrace  implements LogTrace{
	@Override
	public String getTrace() {
		HttpServletRequest request = getRequest();
		if(request!=null){
			String trace = request.getRequestURI();
			if(!ServletFileUpload.isMultipartContent(request)){
				trace += ":" + WebUtils.getParamStr(request);
			}
			return trace;
		}
		return null;
	}
	private HttpServletRequest getRequest(){
		ServletRequestAttributes holder = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(holder!=null){
			HttpServletRequest request = holder.getRequest();
			if(request!=null){
				return request;
			}
		}
		return null;
	}
}
