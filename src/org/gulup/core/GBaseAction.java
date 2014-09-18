package org.gulup.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.gulup.http.JsonObjectRequestHttp;
import org.gulup.http.StringRequestHttp;
import org.gulup.utils.GlobalUtil;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

/**
 * @author gulup
 * @version 创建时间：2014-5-11 下午12:37:51 类说明:Action基礎類
 */
public abstract class GBaseAction extends Observable {
	protected Context context;
	protected RequestQueue request;
	protected GData data;
	protected Map<String,Object> map = null;

	public GBaseAction(Context context) {
		this.context = context;
		map = new HashMap<String,Object>();
		data = new GData();
	}

	public void getJsonRequest(String url,int requestType,Map hander){
		JsonObjectRequestHttp.runJsonObjectRequest(GlobalUtil.getRequestQueue(context), url, new GJsonResponseListener(requestType), new GErrorResponseListener(requestType), hander);
		requestBefore();
	};
	
	public void getJsonRequest(String url,int requestType,Map hander,Map body){
		JsonObjectRequestHttp.runJsonObjectRequset(GlobalUtil.getRequestQueue(context), url, Request.Method.POST, new GJsonResponseListener(requestType), new GErrorResponseListener(requestType), hander, body);
		requestBefore();
	};
	
	public void getStringRequest(String url,int requestType,Map hander){
		StringRequestHttp.runStringRequest(GlobalUtil.getRequestQueue(context), url, new GStringResponseListener(requestType), new GErrorResponseListener(requestType), hander);
		requestBefore();
	};
	
	public void getStringRequest(String url,int requestType,Map hander,Map body){
		StringRequestHttp.runStringRequset(GlobalUtil.getRequestQueue(context), url, Request.Method.POST, new GStringResponseListener(requestType), new GErrorResponseListener(requestType), hander, body);
		requestBefore();
	};


	/**
	 * 发起数据请求之前的处理
	 */
	public abstract void requestBefore();

	/**
	 * json请求成功之后的处理
	 */
	public void jsonRequestSuccess(JSONObject response, int requestType) {
		data.setRequestType(requestType);
		data.setData(map);
		data.setSuccess(true);
	}
	/**
	 * string请求成功之后的处理
	 */
	public void stringRequestSuccess(String response, int requestType) {
		data.setRequestType(requestType);
		data.setData(map);
		data.setSuccess(true);
	}
	/**
	 * 请求失敗之后的处理
	 */
	public void requestFail(VolleyError error, int requestType) {
		data.setRequestType(requestType);
		data.setData(null);
		data.setSuccess(false);
	}
	
	/**
	 * JSON請求成功之後的回調Listener
	 * 
	 * @author gulup
	 */
	private class GJsonResponseListener implements Listener<JSONObject> {

		private int requestType;

		public GJsonResponseListener(int requestType) {
			this.requestType = requestType;
		}

		@Override
		public void onResponse(JSONObject response) {
			setChanged();
			jsonRequestSuccess(response, requestType);
		}

	}

	/**
	 * String請求成功之後的回調Listener
	 * 
	 * @author gulup
	 */
	private class GStringResponseListener implements Listener<String> {

		private int requestType;

		public GStringResponseListener(int requestType) {
			this.requestType = requestType;
		}

		@Override
		public void onResponse(String response) {
			setChanged();
			stringRequestSuccess(response, requestType);
		}

	}

	/**
	 * 請求失敗之後的回調Listener
	 * 
	 * @author gulup
	 */
	private class GErrorResponseListener implements ErrorListener {

		private int requestType;

		public GErrorResponseListener(int requestType) {
			this.requestType = requestType;
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			setChanged();
			requestFail(error, requestType);
		}
	}
	
	public Map<String, Object> getEmptyMap() {
		this.map.clear();
		return this.map;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public void changedData() {
		notifyObservers(data);
	}
}
