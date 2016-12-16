package com.lcpa.lclove.vo;

import java.io.Serializable;

/**
 * ��Ӫ����-ͨ�����������
 * @Reference: 
 * @author: Aaron.Yuan(Devops.Aaron@gmail.com)
 * @since:   2016��12��16�� ����3:47:18
 */
public class TableReqVo implements Serializable {
	
	private static final long serialVersionUID = 7532828604262217599L;
	
	private String sort;		// �����ֶ�
	private String order;		// ����asc��or����desc��
	private Integer limit;		// ÿҳ������
	private Integer offset;		// �ڼ�����¼��ʼ
	private String search;		// �������ֶ�

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
