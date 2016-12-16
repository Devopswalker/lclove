package com.lcpa.lclove.vo;

import java.io.Serializable;

/**
 * 运营管理-通用请求参数绑定
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016年12月16日 下午3:47:18
 */
public class TableReqVo implements Serializable {
	
	private static final long serialVersionUID = 7532828604262217599L;
	
	private String sort;		// 排序字段
	private String order;		// 升序（asc）or降序（desc）
	private Integer limit;		// 每页多少条
	private Integer offset;		// 第几条纪录开始
	private String search;		// 搜索的字段

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
