package org.gulup.exception;

/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午12:53:39 
 * 类说明:异常基础类
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BaseException() {
	}

	public BaseException(String detailMessage) {
		super(detailMessage);
	}

	public BaseException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}
}
