package com.biboheart.ddatabase.jpa;

import javax.servlet.http.HttpServletRequest;

public class SystemRequest {
	private static int DEFAULT_PAGE_SIZE = 10;
	private HttpServletRequest request;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int pageOffset;
	private String sort;
	private String order;

	void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	int getPageSize() {
		return (0 >= pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
	}

	void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	int getPageOffset() {
		return Math.max(pageOffset, 1);
	}

	void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	String getSort() {
		return sort;
	}

	void setSort(String sort) {
		this.sort = sort;
	}

	String getOrder() {
		return order;
	}

	void setOrder(String order) {
		this.order = order;
	}

	static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

	@Override
	public String toString() {
		return "SystemRequest [request=" + request + ", pageSize=" + pageSize + ", pageOffset=" + pageOffset + ", sort="
				+ sort + ", order=" + order + "]";
	}
}
