package com.douzone.mysite.vo;

public class GalleryVo {
	private Long no;
	private String url;
	private String commnets;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCommnets() {
		return commnets;
	}
	public void setCommnets(String commnets) {
		this.commnets = commnets;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", url=" + url + ", commnets=" + commnets + "]";
	}
	
}
