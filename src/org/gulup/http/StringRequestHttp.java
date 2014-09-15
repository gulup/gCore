package org.gulup.http;

import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * 運行一個StringRequest
 * @author 李靜
 * @version 2014-5-16
 */
public class StringRequestHttp {
	
	/**
	 * 運行一個StringRequest
	 * @param queue  隊列
	 * @param url    地址
	 * @param listener			請求成功回調
	 * @param errorListener		請求錯誤回調
	 * @param header			header參數
	 */
	public static void runStringRequest(RequestQueue queue,String url,Response.Listener<String> listener,
			Response.ErrorListener errorListener ,final Map header){
		StringRequest request = new StringRequest(url,listener,errorListener){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return header == null ? super.getHeaders() : header;
			}
		};
		queue.add(request);
	}
	
	/**
	 * 運行一個StringRequest
	 * @param queue  隊列、
	 * @param method 請求方式
	 * @param url    地址
	 * @param listener			請求成功回調
	 * @param errorListener		請求錯誤回調
	 * @param header			header參數
	 */
	public static void runStringRequset(RequestQueue queue,String url,int method,
			Response.Listener<String> listener,Response.ErrorListener errorListener,
			final Map header,final Map body){
		StringRequest request = new StringRequest(method,url,listener,errorListener){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return header == null ? super.getHeaders() : header;
			}
			
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				return body == null ? super.getParams() : body;
			}
		};
		queue.add(request);
	}
}
