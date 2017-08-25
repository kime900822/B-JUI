package com.kime.model;

import java.util.List;

public class QueryResult {
	private String firstPage;
	private String lastPage;
	private List list;
	private String pageNumber;
	private String pageSize;
	private String totalPage;
	private String totalRow;
	public String getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	public String getLastPage() {
		return lastPage;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(String totalRow) {
		this.totalRow = totalRow;
	}
	
	
}
