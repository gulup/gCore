package org.gulup.utils;

import java.lang.reflect.Field;

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
    private static int screenWidth;
    private static int screenHeight;

    public final int MARGIN_TOP = 1;
    public final int MARGIN_BOTTON = 2;
    public final int MARGIN_LEFT = 3;
    public final int MARGIN_RIGHT = 4;

    /**
     * 根據Activity設置屏幕大小
     * 
     * @param activity
     */
    public static void initScreen(Activity activity, float verticalWidth,
	    float verticalHeight) {
	DisplayMetrics dm = new DisplayMetrics();
	activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	screenWidth = dm.widthPixels;
	screenHeight = dm.heightPixels;
	Constant.DEF_WIDTH = verticalWidth;
	Constant.DEF_HEIGHT = verticalHeight;
	//Constant.LANDSCAPE_WIDTH = landscapeWidth;
	//Constant.LANDSCAPE_HEIGHT = landscapeHeight;
    }

    /**
     * 根據Fragment設置屏幕大小
     * 
     * @param fragment
     */
    public static void setWidthAndHightByFragment(Fragment fragment) {
	DisplayMetrics dm = new DisplayMetrics();
	fragment.getActivity().getWindowManager().getDefaultDisplay()
		.getMetrics(dm);
	screenWidth = dm.widthPixels;
	screenHeight = dm.heightPixels;
    }

    /**
     * 根據Activity設置屏幕大小
     * 
     * @param activity
     */
    public static void setWidthAndHighByActivity(Activity activity) {
	DisplayMetrics dm = new DisplayMetrics();
	activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	screenWidth = dm.widthPixels;
	screenHeight = dm.heightPixels;
    }

    /**
     * 设置控件寬高
     * 
     * @param view
     * @param widthScale
     * @param highScale
     */
    public static void setViewSize(View view, float widthScale, float highScale) {
	float width = 0;
	float height = 0;
	if(GlobalUtil.getDirection().equals(Constant.PORTRAIT)){
	    width = widthScale / Constant.DEF_WIDTH;
	    height = highScale / Constant.DEF_HEIGHT;
	}else if(GlobalUtil.getDirection().equals(Constant.LANDSCAPE)){
	    width = widthScale / Constant.DEF_HEIGHT;
	    height = highScale / Constant.DEF_WIDTH;
	}
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
    public static void setViewWidth(View view, float widthScale) {
	float width = 0;
	if(GlobalUtil.getDirection().equals(Constant.PORTRAIT)){
	    width = widthScale / Constant.DEF_WIDTH;
	}else if(GlobalUtil.getDirection().equals(Constant.LANDSCAPE)){
	    width = widthScale / Constant.DEF_HEIGHT;
	}
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
    public static void setViewHeight(View view, float highScale) {
	float height = 0;
	if(GlobalUtil.getDirection().equals(Constant.PORTRAIT)){
	    height = highScale / Constant.DEF_HEIGHT;
	}else if(GlobalUtil.getDirection().equals(Constant.LANDSCAPE)){
	    height = highScale / Constant.DEF_WIDTH;
	}
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
    public static void setViewMargin(View view, float top, float bootom,
	    float left, float right) {
	if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
	    Field fWeight = null;
	    float f = 0;
	    try {
		fWeight = view.getLayoutParams().getClass().getField("weight");
		f = (Float) fWeight.get(view.getLayoutParams());
	    } catch (NoSuchFieldException e) {
		e.printStackTrace();
	    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    }
	    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		    view.getLayoutParams());
	    params.topMargin = (int) ((top / Constant.DEF_HEIGHT) * screenHeight);
	    params.bottomMargin = (int) ((bootom / Constant.DEF_HEIGHT) * screenHeight);
	    params.leftMargin = (int) ((left / Constant.DEF_WIDTH) * screenWidth);
	    params.rightMargin = (int) ((right / Constant.DEF_WIDTH) * screenWidth);
	    params.weight = f;
	    view.setLayoutParams(params);
	} else {
	    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
		    view.getLayoutParams());
	    int[] rules = params.getRules();
	    params.topMargin = (int) ((top / Constant.DEF_HEIGHT) * screenHeight);
	    params.bottomMargin = (int) ((bootom / Constant.DEF_HEIGHT) * screenHeight);
	    params.leftMargin = (int) ((left / Constant.DEF_WIDTH) * screenWidth);
	    params.rightMargin = (int) ((right / Constant.DEF_WIDTH) * screenWidth);
	    view.setLayoutParams(params);
	}
    }

    /**
     * 设置控件居中方法
     * 
     * @param view
     * @param parameter
     */
    public static void setCenter(View view, int parameter) {
	if (view.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
	    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
		    view.getLayoutParams());
	    params.addRule(parameter);
	    view.setLayoutParams(params);
	}
    }

    public static int getScreenWidth() {
	return screenWidth;
    }

    public static int getScreenHeight() {
	return screenHeight;
    }

    public static int getScreenWidth(Context context) {
	WindowManager wm = (WindowManager) context
		.getSystemService(Context.WINDOW_SERVICE);
	int width = wm.getDefaultDisplay().getWidth();
	return width;
    }

    public static int getScreenHeight(Context context) {
	WindowManager wm = (WindowManager) context
		.getSystemService(Context.WINDOW_SERVICE);
	int height = wm.getDefaultDisplay().getHeight();
	return height;
    }

}
