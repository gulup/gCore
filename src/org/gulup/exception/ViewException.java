package org.gulup.exception;
/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午12:54:27
 * 类说明
 */
public class ViewException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String strMsg = null;
	public ViewException(String strExce) {
		strMsg = strExce;
	}
	
	public void printStackTrace() {
		if(strMsg!=null)
			System.err.println(strMsg);
		
		super.printStackTrace();
	}
}
