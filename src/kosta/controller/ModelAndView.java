package kosta.controller;
/**
 * 클라이언트에 응답전에
 * 이동할 url주소와 이동방식을 설정하는 클래스
 * */
public class ModelAndView {
	private String path;//최종 뷰 페이지 이름
	private boolean isRedirect;//이동방식(true: redirect, false: forward)
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
}
