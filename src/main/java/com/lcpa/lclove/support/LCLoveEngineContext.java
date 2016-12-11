package com.lcpa.lclove.support;

import java.util.Map;

import org.apache.velocity.VelocityContext;

public class LCLoveEngineContext extends VelocityContext {
	
	private ViewContextDebugger gvd;
		
	public LCLoveEngineContext(Map context) {
		super(context);
		if(ViewContextDebugger.isDebugEnabled()){
			if(gvd==null) {
				gvd = new ViewContextDebugger();
			}
			gvd.addProperty(context);
		}
	}

	@Override
	public Object get(String key) {
		Object result = super.get(key);
		if(gvd!=null) {
			gvd.remove(key);
		}
		return result;
	}
}
