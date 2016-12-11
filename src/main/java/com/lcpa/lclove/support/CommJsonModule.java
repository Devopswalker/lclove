package com.lcpa.lclove.support;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CommJsonModule  extends SimpleModule{
	private static final long serialVersionUID = 4093223126017721944L;
	public static CommJsonModule GEWA_MODULE = new CommJsonModule();
	private CommJsonModule(){
		addDeserializer(Timestamp.class, CommDateSerializers.timestampDeserializer);
		addDeserializer(Date.class, CommDateSerializers.dateDeserializer);
		addDeserializer(java.sql.Date.class, CommDateSerializers.sqlDateDeserializer);
		addSerializer(Timestamp.class, new CommDateSerializer());
		addSerializer(Date.class, new CommDateSerializer());
		addSerializer(java.sql.Date.class, new CommDateSerializer());
	}
	public static class UpperCasePropertyNamingStrategy extends PropertyNamingStrategyBase {
		
		private static final long serialVersionUID = -6510034237130928673L;
		@Override
		/**
		 * this method call back by PropertyNamingStrategy
		 */
		public String translate(String propertyName) {
			if ("objectName".equals(propertyName)) {
				return propertyName;
			}
			String name = propertyName.replaceAll("^\\w", propertyName.toUpperCase().substring(0, 1));
			
			return name;
		}

	}
}
