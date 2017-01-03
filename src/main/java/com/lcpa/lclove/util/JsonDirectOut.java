package com.lcpa.lclove.util;

import java.io.Writer;

import com.lcpa.lclove.support.DirectOut;

/**
 * 直接输出json流，少一次大的String转换
 * @param req
 * @param obj
 */
public class JsonDirectOut implements DirectOut{
	private Writer writer;
	private Object json;
	public JsonDirectOut(Object json){
		this.json = json;
	}
	@Override
	public void write(){
		JsonUtils.writeObjectToWriter(writer, json, false);
	}
	@Override
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
}