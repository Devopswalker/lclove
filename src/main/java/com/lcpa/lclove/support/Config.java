package com.lcpa.lclove.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.generic.MathTool;
import org.springframework.beans.factory.InitializingBean;

import com.lcpa.lclove.util.DateUtil;
/**
 * LC公共配置初始化
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016年12月11日 下午3:49:32
 */
public class Config  implements InitializingBean {
	
	public static final String SYSTEMID;
	public static final String SESSION_COOKIE_NAME;
	
	private boolean initedConfig = false;
	private boolean initedPage = false;
	private Map<String, String> configMap = new HashMap<String, String>();
	private Map<String, Object> pageMap = new HashMap<String, Object>();
	private static Map pageTools;
	
	private static final Properties props = new Properties();
	static{
		try {
			props.load(Config.class.getClassLoader().getResourceAsStream("lc-global.properties"));
		} catch (IOException e) {
			throw new TraceErrorException("", e);
		}
		SYSTEMID = props.getProperty("systemid");
		if(StringUtils.isNotBlank(props.getProperty("sessionCookieName"))){
			SESSION_COOKIE_NAME = props.getProperty("sessionCookieName");
		}else{
			SESSION_COOKIE_NAME = Config.SYSTEMID.toLowerCase() + "_uskey_";
		}
	}
	
	public static Map getPageTools() {
		return pageTools;
	}
	
	public String getGlobalProp(String key){
		return props.getProperty(key);
	}
	public String getString(String key){
		String result = configMap.get(key);
		if(StringUtils.isBlank(result)){
			result = pageTools.get(key)==null?null:""+pageTools.get(key);
		}
		return result;
	}
	public Long getLong(String key){
		String result = getString(key);
		if(StringUtils.isBlank(result)) return null;
		return Long.parseLong(result);
	}
	public void setConfigMap(Map<String, String> configMap) {
		if (!initedConfig) {
			this.configMap = configMap;
			this.initedConfig = true;
		} else{
			throw new IllegalStateException("不能多次调用！");
		}
	}
	public void setPageMap(Map<String, Object> pageMap) {
		if (!initedPage) {
			this.pageMap = pageMap;
			this.initedPage = true;
		} else{
			throw new IllegalStateException("不能多次调用！");
		}
	}
	public Map<String, Object> getPageMap(){
		return new HashMap<String, Object>(pageMap);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.configMap = UnmodifiableMap.decorate(this.configMap);
		initPageTools();
	}
	public void initPageTools(){
		Map tmp = new HashMap();
		tmp.put("math", new MathTool());
		tmp.put("DateUtil", new DateUtil());
		tmp.putAll(pageMap);
		pageTools = UnmodifiableMap.decorate(tmp);
	}
	public ErrorCode replacePageTool(String property, Object value){
		Object old = pageTools.get(property);
		if (value == null || old == null){
			return ErrorCode.getFailure("参数错误！");
		}
		if (!value.getClass().equals(old.getClass())){
			return ErrorCode.getFailure("参数类型不兼容！");
		}
		Map tmp = new HashMap(pageTools);
		tmp.put(property, value);
		pageTools = UnmodifiableMap.decorate(tmp);
		if (pageMap.containsKey(property)) {
			pageMap.put(property, value);
		}
		return ErrorCode.SUCCESS;
	}	

	public String getBasePath() {
		return (String) pageMap.get("basePath");
	}

	public String getCacheVersionKey(){
		return getString("cacheVersionKey");
	}
}
