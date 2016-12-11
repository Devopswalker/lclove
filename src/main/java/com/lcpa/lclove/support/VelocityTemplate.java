package com.lcpa.lclove.support;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
/**
 * Velocity模板初始化
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016年12月11日 下午4:06:34
 */
public class VelocityTemplate {
		private VelocityEngine velocityEngine;
		private Config config;
		public VelocityTemplate(){
		}
		public String parseTemplate(String template, Map model){
			model.putAll(Config.getPageTools());
			LCLoveEngineContext context = new LCLoveEngineContext(model);
			Writer writer = new StringWriter();
			try {
				velocityEngine.mergeTemplate(template, "UTF-8", context, writer);
			} catch (Exception e) {
				//TODO:日志记录
			}
			return writer.toString();
		}
		public void parseTemplate(String template, Map model, Writer writer){
			model.putAll(Config.getPageTools());
			LCLoveEngineContext context = new LCLoveEngineContext(model);
			try {
				velocityEngine.mergeTemplate(template, "UTF-8", context, writer);
			} catch (Exception e) {
				//TODO:日志记录 
			}
		}
		public void parseTemplate(String template, Map model, OutputStream os){
			model.putAll(Config.getPageTools());
			LCLoveEngineContext context = new LCLoveEngineContext(model);
			Writer writer = new OutputStreamWriter(os);
			try {
				velocityEngine.mergeTemplate(template, "UTF-8", context, writer);
			} catch (Exception e) {
				//TODO:日志记录
			}
		}
		public void setVelocityEngine(VelocityEngine velocityEngine) {
			this.velocityEngine = velocityEngine;
		}
		public Config getConfig() {
			return config;
		}
		public void setConfig(Config config) {
			this.config = config;
		}
}
