package com.lcpa.lclove.support;

/**
 * ���ڵ��Ժ����ⶨλ
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016��12��11�� ����3:46:57
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
