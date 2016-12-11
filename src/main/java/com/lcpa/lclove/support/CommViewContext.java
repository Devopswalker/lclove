package com.lcpa.lclove.support;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.view.ViewToolContext;

public class CommViewContext  extends ViewToolContext {
	private ViewContextDebugger gvd;
	public CommViewContext(VelocityEngine velocity, HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		super(velocity, request, response, application);
	}
	
	@Override
	protected Object internalGet(String key) {
		Object result = super.internalGet(key);
		if(gvd!=null) {
			gvd.remove(key);
		}
		return result;
	}

	public void putModel(Map model) {
		super.putAll(model);
		if(ViewContextDebugger.isDebugEnabled()){
			if(gvd==null) gvd = new ViewContextDebugger();
			gvd.addProperty(model);
		}
	}
	public String getUnUsedProperty(){
		if(gvd==null) return null;
		return gvd.getUnUsedProperty();
	}
}
