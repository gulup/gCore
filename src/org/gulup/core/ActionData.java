package org.gulup.core;

import java.io.Serializable;
import java.util.Map;

/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午2:30:35
 * 类说明:view層與action層交互的數據全部封裝在Data中
 */

public class ActionData implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean isSuccess = false;
	
	private int requestType;
	private Map<String,Object> data;
	
	public ActionData(){}
	
	public ActionData(int requestType,Map<String,Object> data,boolean isSuccess){
		this.requestType = requestType;
		this.data = data;
		this.isSuccess = isSuccess;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
	
}