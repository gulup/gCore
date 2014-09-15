package org.gulup.view;

import java.util.Observable;
import java.util.Observer;

import org.gulup.core.ActionData;
import org.gulup.core.BaseAction;
import org.gulup.utils.ViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @author gulup
 * @version 创建时间：2014-9-15 下午2:35:23
 * 类说明
 */
public abstract class GBaseView extends Activity implements Observer {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtil.inject(this);
	}

	/**
	 * 將action與view綁定起來的方法,交由註解綁定,不需要顯式調用
	 * @param action 需要與view綁定的action
	 * @return 返回綁定是否成功
	 */
	public boolean setAction(BaseAction action){
		if(action!=null){
			action.addObserver(this);
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * action請求成功後的回調方法,需要重寫
	 * @param form 回調成功後返回的數據,裏面包含請求標識requestType和返回的數據data
	 */
	public abstract void requestSuccess(ActionData data);
	
	/**
	 * 請求失敗後的回調方法,需要重寫
	 * @param requestType 回調失敗後回傳的請求標識
	 */
	public abstract void requestFail(ActionData data);
	
	/**
	 * 需要在設置橫豎屏佈局之前初始化的動作,都寫在這裏
	 */
	public void init(){}
	
	/**
	 * 控件默认调用的点击事件
	 * @param view
	 */
	public void onClick(View view){}
	
	@Override
	public void update(Observable observable, Object data) {
		ActionData resData = (ActionData)data;
		if(resData.isSuccess()){
			requestSuccess(resData);
		}else{
			requestFail(resData);
		}
	}

}
