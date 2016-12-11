package com.lcpa.lclove.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lcpa.lclove.support.Config;
import com.lcpa.lclove.util.VmUtils;



/**
 * ������������/��־����
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016��12��11�� ����4:18:16
 */
public class StartupListener extends ContextLoaderListener{
	Logger logger = Logger.getLogger(StartupListener.class);
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//���ݻ������������ļ�
		String SPRING_CONFIG_KEY = "contextConfigLocation";
		String[] remoteConfig = new String[]{
			"classpath:config.remote.xml",
			"classpath:spring.xml"
		};
		String SPRING_CONFIG_VALUE = StringUtils.join(remoteConfig, ",");
		System.setProperty("LCCONFIG", "REMOTE");
		logger.warn("Config Using REMOTE-TEST:" + SPRING_CONFIG_VALUE);
		ServletContext context = event.getServletContext();
		context.setInitParameter(SPRING_CONFIG_KEY, SPRING_CONFIG_VALUE);
		super.contextInitialized(event);
		setupContext(context);
		
		//��ʼ��������Ϣ
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		Config config = ctx.getBean(Config.class);
		VmUtils utils = new VmUtils();
		config.replacePageTool("VmUtils", utils);
		logger.warn("INIT GLOCAL PAGE-TOOLS: " + Config.getPageTools());
	}
	
	public static void setupContext(ServletContext context) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}
}
