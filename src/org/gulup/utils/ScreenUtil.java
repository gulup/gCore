package org.gulup.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ScreenUtil {
	private int screenWidth;
	private int screenHeight;

	public final int MARGIN_TOP = 1;
	public final int MARGIN_BOTTON = 2;
	public final int MARGIN_LEFT = 3;
	public final int MARGIN_RIGHT = 4;

	/**
	 *  根據Activity設置屏幕大小
	 * @param activity
	 */
	public void setWidthAndHighByActivity(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
	}
	
	/**
	 * 根據Fragment設置屏幕大小
	 * @param fragment
	 */
	public void setWidthAndHightByFragment(Fragment fragment){
		DisplayMetrics dm = new DisplayMetrics();
		fragment.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
	}
	
	/**
	 *  设置控件大小
	 * @param view
	 * @param widthScale
	 * @param highScale
	 */
	public void setViewLayoutParams(View view, float widthScale, float highScale) {
		LayoutParams params = view.getLayoutParams();
		params.height = (int) (highScale * screenHeight);
		params.width = (int) (widthScale * screenWidth);
		view.setLayoutParams(params);
	}
	/**
	 * 设置控件的宽（不設置高）
	 * 
	 * @param view
	 * @param widthScale
	 */
	public void setViewLayoutWidth(View view, float widthScale) {
		LayoutParams params = view.getLayoutParams();
		params.width = (int) (widthScale * screenWidth);
		view.setLayoutParams(params);
	}

	/**
	 * 设置控件的高度（不設寬）
	 * 
	 * @param view
	 * @param highScale
	 */
	public void setViewLayoutHeight(View view, float highScale) {
		LayoutParams params = view.getLayoutParams();
		params.height = (int) (highScale * screenHeight);
		view.setLayoutParams(params);
	}
	
	/**
	 * 通过宽来设置控件大小
	 * 
	 * @param view
	 * @param widthScale
	 * @param highScale
	 */
	public void setViewLayoutByWidth(View view, float widthScale,
			float highScale) {
		LayoutParams params = view.getLayoutParams();
		params.height = (int) (highScale * screenWidth);
		params.width = (int) (widthScale * screenWidth);
		view.setLayoutParams(params);
	}

	/**
	 * 设置控件宽高
	 * 
	 * @param view
	 * @param width
	 * @param high
	 */
	public void setViewLayoutParams(View view, int width, int height) {
		LayoutParams params = view.getLayoutParams();
		params.height = height;
		params.width = width;
		view.setLayoutParams(params);
	}
	
	/**
	 * 設置view之間的間距,前提view必須是在LinearLayout佈局或RelativeLayout布局中
	 * 
	 * @param view
	 * @param scale 比例（上下距離是高*比例，左右距離是寬*比例）
	 * @param direction 方向
	 */
	public void setViewLayoutMagin(View view, float scale, int direction) {

		if(view.getLayoutParams() instanceof LinearLayout.LayoutParams){
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(view.getLayoutParams());
			LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) view.getLayoutParams();
			switch (direction) {
			case MARGIN_TOP:
				params.topMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_BOTTON:
				params.bottomMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_LEFT:
				params.leftMargin = (int) (scale * screenWidth);
				break;
			case MARGIN_RIGHT:
				params.rightMargin = (int) (scale * screenWidth);
				break;
			default:
				break;
			}
			view.setLayoutParams(params);
		}else if(view.getLayoutParams() instanceof RelativeLayout.LayoutParams){
//			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getLayoutParams());
			RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
			switch (direction) {
			case MARGIN_TOP:
				params.topMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_BOTTON:
				params.bottomMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_LEFT:
				params.leftMargin = (int) (scale * screenWidth);
				break;
			case MARGIN_RIGHT:
				params.rightMargin = (int) (scale * screenWidth);
				break;
			default:
				break;
			}
		
			view.setLayoutParams(params);
		}
	}
	
	public void setViewLayoutMagin(View view, float left, float top,float right, float bottom) {

		if(view.getLayoutParams() instanceof LinearLayout.LayoutParams){
			LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) view.getLayoutParams();
			if(left > 0)
				params.leftMargin = (int) (left *screenWidth);
			if(top > 0)
				params.topMargin = (int) (top * screenHeight);
			if(right > 0)
				params.rightMargin = (int) (right * screenWidth);
			if(bottom > 0)
				params.bottomMargin = (int) (bottom * screenHeight);
			view.setLayoutParams(params);
		}else if(view.getLayoutParams() instanceof RelativeLayout.LayoutParams){
			RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
			if(left > 0)
				params.leftMargin = (int) (left *screenWidth);
			if(top > 0)
				params.topMargin = (int) (top * screenHeight);
			if(right > 0)
				params.rightMargin = (int) (right * screenWidth);
			if(bottom > 0)
				params.bottomMargin = (int) (bottom * screenHeight);
			view.setLayoutParams(params);
		}
	}
	
	public void setViewLayoutVerticalMagin(View view, float scale, int direction) {

		if(view.getLayoutParams() instanceof LinearLayout.LayoutParams){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(view.getLayoutParams());
			switch (direction) {
			case MARGIN_TOP:
				params.topMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_BOTTON:
				params.bottomMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_LEFT:
				params.leftMargin = (int) (scale * screenWidth);
				break;
			case MARGIN_RIGHT:
				params.rightMargin = (int) (scale * screenWidth);
				break;
			default:
				break;
			}
			view.setLayoutParams(params);
		}else if(view.getLayoutParams() instanceof RelativeLayout.LayoutParams){
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getLayoutParams());
			switch (direction) {
			case MARGIN_TOP:
				params.topMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_BOTTON:
				params.bottomMargin = (int) (scale * screenHeight);
				break;
			case MARGIN_LEFT:
				params.leftMargin = (int) (scale * screenWidth);
				break;
			case MARGIN_RIGHT:
				params.rightMargin = (int) (scale * screenWidth);
				break;
			default:
				break;
			}
			view.setLayoutParams(params);
		}
	}

	/**
	 * 设置控件宽高
	 * 
	 * @param view
	 * @param widthScale
	 * @param highScale
	 */
	public void setViewLayoutByHeight(View view, int height) {
		LayoutParams params = view.getLayoutParams();
		params.height = height;
		params.width = height;
		view.setLayoutParams(params);
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
	}

	public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
	}

}
