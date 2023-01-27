package com.douzone.mysite.vo;

public class PagingVo {
	private final static int pageCount = 5;
	private int StartNum;
	private int LastNum;
	private int lastPageNum;
	   
	public int getStartNum() {
		return StartNum;
	}
	public void setStartNum(int startNum) {
		StartNum = startNum;
	}
	public int getLastNum() {
		return LastNum;
	}
	public void setLastNum(int lastNum) {
		LastNum = lastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	
	   
}
