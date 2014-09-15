package org.gulup.http;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * 運行一個JsonRequest
 * 
 * @author 李靜
 * @version 2014-5-16
 */
public class JsonObjectRequestHttp {

	/**
	 * 運行一個JsonObjectRequest
	 * 
	 * @param queue
	 *            隊列
	 * @param url
	 *            地址
	 * @param listener
	 *            請求成功回調
	 * @param errorListener
	 *            請求錯誤回調
	 * @param header
	 *            header參數
	 */
	public static void runJsonObjectRequest(RequestQueue queue, String url,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener, final Map header) {
		JsonObjectRequest request = new JsonObjectRequest(url, null, listener,
				errorListener) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return header == null ? super.getHeaders() : header;
			}
		};
		queue.add(request);
	}

	/**
	 * 運行一個JsonObjectRequest
	 * 
	 * @param queue
	 *            隊列、
	 * @param method
	 *            請求方式
	 * @param url
	 *            地址
	 * @param listener
	 *            請求成功回調
	 * @param errorListener
	 *            請求錯誤回調
	 * @param header
	 *            header參數
	 */
	public static void runJsonObjectRequset(RequestQueue queue, String url,
			int method, Listener<JSONObject> listener,
			Response.ErrorListener errorListener, final Map header,
			final Map body) {
		JsonObjectRequest request = new JsonObjectRequest(method, url, null,listener,
				errorListener,body) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				return header == null ? super.getHeaders() : header;
			}
		};
		queue.add(request);
	}

}
