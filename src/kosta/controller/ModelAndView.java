package kosta.controller;
/**
 * Ŭ���̾�Ʈ�� ��������
 * �̵��� url�ּҿ� �̵������ �����ϴ� Ŭ����
 * */
public class ModelAndView {
	private String path;//���� �� ������ �̸�
	private boolean isRedirect;//�̵����(true: redirect, false: forward)
	
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
