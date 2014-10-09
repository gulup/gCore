package org.gulup.view;

import java.util.Observable;
import java.util.Observer;

import org.gulup.core.GBaseAction;
import org.gulup.core.GData;
import org.gulup.utils.Constant;
import org.gulup.utils.ScreenUtil;
import org.gulup.utils.ViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author gulup
 * @version 创建时间：2014-9-15 下午2:35:23
 * 类说明
 */
public abstract class GBaseView extends Activity implements Observer {
	
	private ScreenUtil su;
	protected int screenHeight;
	protected int screenWidth;
	
	protected void onCreate(Bundle savedInstanceState,boolean isFull) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(isFull){
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		initScreen(this);
		ViewUtil.inject(this);
		init();
	}
	
	/**
	 * 初始化Screen参数
	 * 
	 */
	public void initScreen(Activity activity){
		su = new ScreenUtil();
		su.setWidthAndHighByActivity(activity);
		this.screenHeight = su.getScreenHeight();
		this.screenWidth = su.getScreenWidth();
	}

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

	/**
	 *  设置控件寬高
	 * @param view
	 * @param widthScale
	 * @param highScale
	 */
	public void setViewSize(View view, float widthScale, float highScale) {
		float width = widthScale/Constant.DEF_WIDTH;
		float height = widthScale/Constant.DEF_HEIGHT;
		LayoutParams params = view.getLayoutParams();
		params.height = (int) (height * screenHeight);
		params.width = (int) (width * screenWidth);
		view.setLayoutParams(params);
	}
	
	/**
	 * 设置控件的宽（不設置高）
	 * 
	 * @param view
	 * @param widthScale
	 */
	public void setViewWidth(View view, float widthScale) {
		float width = widthScale/Constant.DEF_WIDTH;
		LayoutParams params = view.getLayoutParams();
		params.width = (int) (width * screenWidth);
		view.setLayoutParams(params);
	}
	
	/**
	 * 设置控件的高度（不設寬）
	 * 
	 * @param view
	 * @param highScale
	 */
	public void setViewHeight(View view, float highScale) {
		float height = highScale/Constant.DEF_HEIGHT;
		LayoutParams params = view.getLayoutParams();
		params.height = (int) (height * screenHeight);
		view.setLayoutParams(params);
	}
	
	/**
	 * 設置控件的邊距
	 * 
	 * @param view
	 * @param top
	 * @param bootom
	 * @param left
	 * @param right
	 */
	public void setViewMargin(View view,float top,float bootom,float left,float right){
		if(view.getLayoutParams() instanceof LinearLayout.LayoutParams){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(view.getLayoutParams());
			params.topMargin = (int) ((top / Constant.DEF_HEIGHT) * screenHeight);
			params.bottomMargin = (int) ((bootom / Constant.DEF_HEIGHT) * screenHeight);
			params.leftMargin = (int) ((left / Constant.DEF_WIDTH) * screenWidth);
			params.rightMargin = (int) ((right / Constant.DEF_WIDTH) * screenWidth);
			view.setLayoutParams(params);
		}else{
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getLayoutParams());
			int[] rules = params.getRules();
			params.topMargin = (int) ((top / Constant.DEF_HEIGHT) * screenHeight);
			params.bottomMargin = (int) ((bootom / Constant.DEF_HEIGHT) * screenHeight);
			params.leftMargin = (int) ((left / Constant.DEF_WIDTH) * screenWidth);
			params.rightMargin = (int) ((right / Constant.DEF_WIDTH) * screenWidth);
			view.setLayoutParams(params);
		}
	}
	
	public void setCenter(View view,int parameter){
		if(view.getLayoutParams() instanceof RelativeLayout.LayoutParams){
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getLayoutParams());
			params.addRule(parameter);
			view.setLayoutParams(params);
		}
	}
}
