package org.gulup.utils;

import java.util.ArrayList;
import java.util.List;

import org.gulup.view.GBaseView;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author gulup
 * @version 创建时间：2014-5-17 下午5:35:32 类说明
 */
public class GlobalUtil {
    private static GBaseView currentView;
    
    private static List<GBaseView> viewList;
    
    private static RequestQueue request;
    
    private static String direction;

    public static RequestQueue getRequestQueue(Context context) {
	if (request == null) {
	    request = Volley.newRequestQueue(context);
	}
	return request;
    }

    public static GBaseView getCurrentView() {
        return currentView;
    }

    public static void setCurrentView(GBaseView currentView) {
        GlobalUtil.currentView = currentView;
    }

    public static List<GBaseView> getViewList() {
	if(viewList==null){
	    viewList = new ArrayList<GBaseView>();
	}
        return viewList;
    }

    public static void setViewList(List<GBaseView> viewList) {
        GlobalUtil.viewList = viewList;
    }

    public static RequestQueue getRequest() {
        return request;
    }

    public static void setRequest(RequestQueue request) {
        GlobalUtil.request = request;
    }

    public static String getDirection() {
        return direction;
    }

    public static void setDirection(String direction) {
        GlobalUtil.direction = direction;
    }
    
    
}
