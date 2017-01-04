package com.lcpa.lclove.vo;

import java.io.Serializable;

public class PagingJsonVo implements Serializable{

	private static final long serialVersionUID = 4736386459450872711L;

	private int currentPage;    //当前页
	private int rowsCount;      //记录数
	private int pageCount;      //分页数量
	private int rowsPerPage;    //每页显示数量

	public PagingJsonVo(int rowsCount, int rowsPerPage, int currentPage) {
		this.rowsCount = rowsCount;
		this.rowsPerPage = rowsPerPage;
		this.currentPage = currentPage;
		this.pageCount = (rowsCount-1)/rowsPerPage + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowsCount() {
		return rowsCount;
	}
	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

}
