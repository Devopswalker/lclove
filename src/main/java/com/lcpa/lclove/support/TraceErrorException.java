package com.lcpa.lclove.support;

/**
 * 用于调试和问题定位
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016年12月11日 下午3:46:57
 */
public class TraceErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TraceErrorException(String msg){
		super(msg);
	}
	public TraceErrorException(String msg, Throwable e){
		super(msg, e);
	}

}
