package org.gulup.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.gulup.http.JsonObjectRequestHttp;
import org.gulup.http.StringRequestHttp;
import org.gulup.utils.GlobalUtil;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

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
    protected Map<String, Object> map = null;

    public Handler mHandler = new Handler(Looper.getMainLooper());

    public class GActionRunnable implements Runnable {
	private boolean isSuccess;
	private int requestType;

	public GActionRunnable(int requestType, boolean isSuccess) {
	    this.isSuccess = isSuccess;
	    this.requestType = requestType;
	}

	@Override
	public void run() {
	    requestAfter(requestType, isSuccess);
	}
    }

    public GBaseAction(Context context) {
	this.context = context;
	map = new HashMap<String, Object>();
	data = new GData();
    }

    public void getJsonRequest(String url, int requestType, Map hander) {
	requestBefore();
	JsonObjectRequestHttp.runJsonObjectRequest(
		GlobalUtil.getRequestQueue(context), url,
		new GJsonResponseListener(requestType),
		new GErrorResponseListener(requestType), hander);
    };

    public void getJsonRequest(String url, int requestType, Map hander, Map body) {
	requestBefore();
	JsonObjectRequestHttp.runJsonObjectRequset(
		GlobalUtil.getRequestQueue(context), url, Request.Method.POST,
		new GJsonResponseListener(requestType),
		new GErrorResponseListener(requestType), hander, body);
    };

    public void getStringRequest(String url, int requestType, Map hander) {
	requestBefore();
	StringRequestHttp.runStringRequest(GlobalUtil.getRequestQueue(context),
		url, new GStringResponseListener(requestType),
		new GErrorResponseListener(requestType), hander);
    };

    public void getStringRequest(String url, int requestType, Map hander,
	    Map body) {
	requestBefore();
	StringRequestHttp.runStringRequset(GlobalUtil.getRequestQueue(context),
		url, Request.Method.POST, new GStringResponseListener(
			requestType), new GErrorResponseListener(requestType),
		hander, body);
    };

    /**
     * 发起数据请求之前的处理
     */
    public abstract void requestBefore();

    /**
     * 请求处理完成后,进行response数据处理.
     */
    public abstract void jsonRequestHandle(JSONObject response, int requestType);

    /**
     * 请求处理完成后,进行response数据处理.
     */
    public abstract void stringRequestHandle(String response, int requestType);

    public void requestAfter(int requestType, boolean isSuccess) {
	setChanged();
	data.setRequestType(requestType);
	data.setData(map);
	data.setSuccess(isSuccess);
	changedData();
    }

    /**
     * json请求成功之后的处理
     */
    public void jsonRequestSuccess(JSONObject response, int requestType) {
	jsonRequestHandle(response, requestType);
	requestAfter(requestType, true);
    }

    /**
     * string请求成功之后的处理
     */
    public void stringRequestSuccess(String response, int requestType) {
	stringRequestHandle(response, requestType);
	requestAfter(requestType, true);
    }

    /**
     * 请求失敗之后的处理
     */
    public void requestFail(VolleyError error, int requestType) {
	requestAfter(requestType, false);
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

    public void changedNativeData(int requestType, boolean isSuccess) {
	mHandler.post(new GActionRunnable(requestType, isSuccess));
    }

    public void changeView(Context context, Class targetView) {
	Intent intent = new Intent();
	intent.setClass(context, targetView);
	context.startActivity(intent);
    }
    
    public void changeView(Context context, Class targetView, Bundle bundle) {
	Intent intent = new Intent();
	intent.setClass(context, targetView);
	intent.putExtras(bundle);
	context.startActivity(intent);
    }
    
    public void changeView(Context context, Class targetView, boolean isFinish) {
	changeView(context, targetView);
	if (isFinish && GlobalUtil.getCurrentView() != null) {
	    GlobalUtil.getCurrentView().finish();
	}
    }
    
    public void changeView(Context context, Class targetView, Bundle bundle,boolean isFinish) {
	changeView(context, targetView,bundle);
	if (isFinish && GlobalUtil.getCurrentView() != null) {
	    GlobalUtil.getCurrentView().finish();
	}
    }
    
}
