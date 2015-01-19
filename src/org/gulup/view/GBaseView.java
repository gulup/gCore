package org.gulup.view;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

import org.gulup.core.GBaseAction;
import org.gulup.core.GData;
import org.gulup.utils.Constant;
import org.gulup.utils.GlobalUtil;
import org.gulup.utils.ScreenUtil;
import org.gulup.utils.ViewUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author gulup
 * @version 创建时间：2014-9-15 下午2:35:23 类说明
 */
public abstract class GBaseView extends FragmentActivity implements Observer {

    /*
     * public ScreenUtil su; protected int screenHeight; protected int
     * screenWidth;
     */

    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// initScreen(this);
	ViewUtil.inject(this);
	GlobalUtil.setCurrentView(this);
	init();
	if (ScreenUtil.getScreenHeight() > ScreenUtil.getScreenWidth()) {
	    setVertical();
	} else {
	    setLandScape();
	}

    }

    protected void onCreate(Bundle savedInstanceState, boolean isFull) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	if (isFull) {
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		    WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	// initScreen(this);
	ViewUtil.inject(this);
	GlobalUtil.setCurrentView(this);
	init();
	if (ScreenUtil.getScreenHeight() > ScreenUtil.getScreenWidth()) {
	    setVertical();
	} else {
	    setLandScape();
	}

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	ScreenUtil.setWidthAndHighByActivity(this);
	if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    setLandScape();
	} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
	    setVertical();
	}
    }

    /**
     * 橫屏佈局設置
     */
    public void setLandScape() {
    }

    /**
     * 豎屏佈局設置
     */
    public void setVertical() {
    }

    /**
     * 將action與view綁定起來的方法,交由註解綁定,不需要顯式調用
     * 
     * @param action
     *            需要與view綁定的action
     * @return 返回綁定是否成功
     */
    public boolean setAction(GBaseAction action) {
	if (action != null) {
	    action.addObserver(this);
	} else {
	    return false;
	}
	return true;
    }

    /**
     * action請求成功後的回調方法,需要重寫
     * 
     * @param form
     *            回調成功後返回的數據,裏面包含請求標識requestType和返回的數據data
     */
    public abstract void requestSuccess(GData data);

    /**
     * 請求失敗後的回調方法,需要重寫
     * 
     * @param requestType
     *            回調失敗後回傳的請求標識
     */
    public abstract void requestFail(GData data);

    /**
     * 需要在設置橫豎屏佈局之前初始化的動作,都寫在這裏
     */
    public void init() {
    }

    /**
     * 控件默认调用的点击事件
     * 
     * @param view
     */
    public void onClick(View view) {
    }

    /**
     * 數據回調處理,通知界面數據已經更新
     * 
     */
    @Override
    public void update(Observable observable, Object data) {
	GData resData = (GData) data;
	if (resData.isSuccess()) {
	    requestSuccess(resData);
	} else {
	    requestFail(resData);
	}
    }

    /**
     * 添加,替换指定Fragment到指定布局
     * 
     * @param id
     *            需要替换的布局id
     * @param fragment
     *            需要替换的Fragment
     */
    public void addFragment(int id, Fragment fragment) {
	FragmentManager fragmentManager = getSupportFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager
		.beginTransaction();
	fragmentTransaction.replace(id, fragment);
	fragmentTransaction.commit();
    }

}
