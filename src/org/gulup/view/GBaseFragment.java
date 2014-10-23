package org.gulup.view;

import java.util.Observable;
import java.util.Observer;

import org.gulup.annotation.ViewFinder;
import org.gulup.core.GBaseAction;
import org.gulup.core.GData;
import org.gulup.utils.Constant;
import org.gulup.utils.ScreenUtil;
import org.gulup.utils.ViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public abstract class GBaseFragment extends Fragment implements Observer{
	public int id;
	public View view;
	
	/*public ScreenUtil su;
	protected int screenHeight;
	protected int screenWidth;*/
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		ViewUtil.injectFragment(this, getActivity());
		view = inflater.inflate(id, container,false);
		//initScreen(getActivity());
		//ViewUtil.inject(this, view ,getActivity());
		ViewUtil.injectViewFiled(this, new ViewFinder(view, getActivity()));
		init();
		return view;
	}
	
	/**
	 * 初始化Screen参数
	 * 
	 */
	/*public void initScreen(Activity activity){
		su = new ScreenUtil();
		su.setWidthAndHighByActivity(activity);
		this.screenHeight = su.getScreenHeight();
		this.screenWidth = su.getScreenWidth();
	}*/

	/**
	 * 將action與view綁定起來的方法,交由註解綁定,不需要顯式調用
	 * @param action 需要與view綁定的action
	 * @return 返回綁定是否成功
	 */
	public boolean setAction(GBaseAction action){
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
	public abstract void requestSuccess(GData data);
	
	/**
	 * 請求失敗後的回調方法,需要重寫
	 * @param requestType 回調失敗後回傳的請求標識
	 */
	public abstract void requestFail(GData data);
	
	/**
	 * 需要在設置橫豎屏佈局之前初始化的動作,都寫在這裏
	 */
	public void init(){}
	
	/**
	 * 控件默认调用的点击事件
	 * @param view
	 */
	public void onClick(View view){}
	
	
	/**
	 * 數據回調處理,通知界面數據已經更新
	 * 
	 */
	@Override
	public void update(Observable observable, Object data) {
		GData resData = (GData)data;
		if(resData.isSuccess()){
			requestSuccess(resData);
		}else{
			requestFail(resData);
		}
	}

	
}
