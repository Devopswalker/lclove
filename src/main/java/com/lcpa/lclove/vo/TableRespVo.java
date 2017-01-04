package com.lcpa.lclove.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 运营管理-通用列表响应集合
 * @Reference:
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016年12月16日 下午3:49:13
 */
public class TableRespVo<T> implements Serializable {

	private static final long serialVersionUID = 116815093942050920L;

	private boolean success;	// 查询结果
	private String errmsg;		// 错误消息
	private List<T> rows;		// 数据列表
	private Integer total;		// 查询总数

	public TableRespVo() {

	}

	public TableRespVo(boolean success) {
		this.success = success;
	}

	public TableRespVo(boolean success, String errmsg) {
		this.success = success;
		this.errmsg = errmsg;
	}

	public TableRespVo(boolean success, String errmsg, List<T> rows, Integer total) {
		this.success = success;
		this.errmsg = errmsg;
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
