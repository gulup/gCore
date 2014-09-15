package org.gulup.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author gulup
 * @version 创建时间：2014-5-17 下午5:35:32
 * 类说明
 */
public class GlobalUtil {
	private static RequestQueue request;
	
	public static RequestQueue getRequestQueue(Context context){
		if(request==null){
			request = Volley.newRequestQueue(context);
		}
		return request;
	}
}
