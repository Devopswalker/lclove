package com.lcpa.lclove.util;


import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lcpa.lclove.support.CommJsonModule;

public class JsonUtils {
	private static final transient Logger dbLogger = Logger.getLogger(JsonUtils.class);
	public static <T> T readJsonToObject(Class<T> clazz, String json) {
		if (StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		
		try {
			T result = mapper.readValue(json, clazz);
			return result;
		} catch (Exception e) {
			dbLogger.error(StringUtils.substring(json, 0, 500), e);
		}
		return null;
	}
	public static <T> T readJsonToObject(TypeReference<T> type, String json){
		if (StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		try {
			T result = mapper.readValue(json, type);
			return result;
		} catch (Exception e) {
			dbLogger.error("json:" + StringUtils.substring(json, 0, 500), e);
		}
		return null;
	}
	public static <T> List<T> readJsonToObjectList(Class<T> clazz, String json) {
		if (StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(CommJsonModule.GEWA_MODULE);

		try {
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			List<T> result = mapper.readValue(json, type);
			return result;
		} catch (Exception e) {
			dbLogger.error("json:" + StringUtils.substring(json, 0, 500), e);
		}
		return null;
	}

	public static Map readJsonToMap(String json) {
		if (StringUtils.isBlank(json)){
			return new HashMap<>();
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		try {
			Map result = mapper.readValue(json, Map.class);
			if (result == null) result = new HashMap();
			return result;
		} catch (Exception e) {
			dbLogger.error("json:" + StringUtils.substring(json, 0, 500) ,e);
			return new HashMap();
		}

	}
	//-----------------------------------------------------------------
	public static <T> T readJsonToObject(Class<T> clazz, String json, PropertyNamingStrategy pns) {
		if (StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		if(pns!=null){
			mapper.setPropertyNamingStrategy(pns);
		}
		try {
			T result = mapper.readValue(json, clazz);
			return result;
		} catch (Exception e) {
			dbLogger.error(StringUtils.substring(json, 0, 500), e);
		}
		return null;
	}
	public static <T> T readJsonToObject(TypeReference<T> type, String json, PropertyNamingStrategy pns){
		if (StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		if(pns!=null){
			mapper.setPropertyNamingStrategy(pns);
		}
		try {
			T result = mapper.readValue(json, type);
			return result;
		} catch (Exception e) {
			dbLogger.error("json:" + StringUtils.substring(json, 0, 500), e);
		}
		return null;
	}
	public static <T> List<T> readJsonToObjectList(Class<T> clazz, String json, PropertyNamingStrategy pns) {
		if (StringUtils.isBlank(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		if(pns!=null){
			mapper.setPropertyNamingStrategy(pns);
		}

		try {
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			List<T> result = mapper.readValue(json, type);
			return result;
		} catch (Exception e) {
			dbLogger.error("json:" + StringUtils.substring(json, 0, 500), e);
		}
		return null;
	}

	public static Map readJsonToMap(String json, PropertyNamingStrategy pns) {
		if (StringUtils.isBlank(json)){
			return new HashMap();
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(CommJsonModule.GEWA_MODULE);
		if(pns!=null){
			mapper.setPropertyNamingStrategy(pns);
		}
		try {
			Map result = mapper.readValue(json, Map.class);
			if (result == null) result = new HashMap();
			return result;
		} catch (Exception e) {
			dbLogger.error("json:" + StringUtils.substring(json, 0, 500) , e);
			return new HashMap();
		}
	}
	
	public static String writeObjectToJson(Object object) {
		return writeObjectToJson(object, false);
	}
	public static void writeObjectToStream(OutputStream os, Object object, boolean excludeNull) {
		writeObject(object, os, null, excludeNull);
	}
	public static void writeObjectToWriter(Writer writer, Object object, boolean excludeNull) {
		writeObject(object, null, writer, excludeNull);
	}
	public static String writeObjectToJson(Object object, boolean excludeNull) {
		return writeObject(object, null, null, excludeNull);
	}
	private static String writeObject(Object object, OutputStream os, Writer writer, boolean excludeNull) {
		if (object == null)	return null;
		if(object instanceof Map){
			try{((Map) object).remove(null);}catch(Exception e){}
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		//不自动关闭流
		mapper.getFactory().disable(Feature.AUTO_CLOSE_TARGET);
		if(excludeNull) {
			//TODO: 未实现
		}

		try {
			mapper.registerModule(CommJsonModule.GEWA_MODULE);
			if(os!=null){
				mapper.writeValue(os, object);
			}else if(writer!=null){
				mapper.writeValue(writer, object);
			}else{
				String data = mapper.writeValueAsString(object);
				return data;
			}
		} catch (Exception e) {
			dbLogger.error("object:" + object + "\n" , e);
		}
		return null;
	}
	
	public static String writeMapToJson(Map<String, String> dataMap){
		if(dataMap==null) return null;
		if(dataMap instanceof HashMap){
			try{dataMap.remove(null);}catch(Exception e){}
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		try {
			String data = mapper.writeValueAsString(dataMap);
			return data;
		} catch (Exception e) {
			dbLogger.error("", e);
		}
		return null;
	}

	public static String addJsonKeyValue(String json, String key, String value){
		Map info = readJsonToMap(json);
		info.put(key, value);
		return writeMapToJson(info);
	}
	public static String removeJsonKeyValue(String json, String key){
		Map info = readJsonToMap(json);
		info.remove(key);
		return writeMapToJson(info);
	}
	public static String getJsonValueByKey(String json, String key){
		Map<String, String> info = readJsonToMap(json);
		return info.get(key);
	}
}