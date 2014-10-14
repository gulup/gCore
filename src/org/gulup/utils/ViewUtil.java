package org.gulup.utils;

/**
 * @author 覃江鵬
 * @version 创建时间：2014-5-10 下午12:59:14 
 * 类说明:注解工具类.类中方法用于配合注解初始化注解中的参数
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.gulup.annotation.GAction;
import org.gulup.annotation.GContentView;
import org.gulup.annotation.EventListener;
import org.gulup.annotation.GFragment;
import org.gulup.annotation.GPreference;
import org.gulup.annotation.GRes;
import org.gulup.annotation.ResLoader;
import org.gulup.annotation.ViewFinder;
import org.gulup.annotation.GView;
import org.gulup.core.GBaseAction;

import android.app.Activity;
import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AbsListView;

public class ViewUtil {

	private ViewUtil() {
	}

	public static void inject(View view) {
		injectObject(view, new ViewFinder(view));
	}

	public static void inject(Object handler, View view, Context context) {
		injectObject(handler, new ViewFinder(view, context));
	}

	public static void inject(Activity activity) {
		injectObject(activity, new ViewFinder(activity));
	}

	public static void inject(PreferenceActivity preferenceActivity) {
		injectObject(preferenceActivity, new ViewFinder(preferenceActivity));
	}

	public static void inject(Object handler, View view) {
		injectObject(handler, new ViewFinder(view));
	}

	public static void inject(Object handler, Activity activity) {
		injectObject(handler, new ViewFinder(activity));
	}

	public static void inject(Object handler, PreferenceGroup preferenceGroup) {
		injectObject(handler, new ViewFinder(preferenceGroup));
	}

	public static void inject(Object handler,
			PreferenceActivity preferenceActivity) {
		injectObject(handler, new ViewFinder(preferenceActivity));
	}

	@SuppressWarnings("ConstantConditions")
	private static void injectObject(Object handler, ViewFinder finder) {
		Class<?> handlerType = handler.getClass();

		// 根據佈局註解,進行佈局綁定
		GContentView contentView = handlerType
				.getAnnotation(GContentView.class);
		if (contentView != null) {
			try {
				Method setContentViewMethod = handlerType.getMethod(
						"setContentView", int.class);
				if (contentView.value() != 0) {
					setContentViewMethod.invoke(handler, contentView.value());
				} else {
					setContentViewMethod.invoke(
							handler,
							finder.getContext()
									.getResources()
									.getIdentifier(
											contentView.name(),
											contentView.type(),
											finder.getContext()
													.getPackageName()));
				}
			} catch (Throwable e) {
				LogUtil.e(e.getMessage(), e);
			}
		}

		// 根據控件註解,進行控件初始化,資源初始化,監聽事件綁定
		Field[] fields = handlerType.getDeclaredFields();
		//Object screenUtil = null;
		Class<?> suClass = null;
		try {
			suClass = Class.forName("org.gulup.utils.ScreenUtil");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		/*try {
			Field su = handlerType.getField("su");
			screenUtil = su.get(handler);
			suClass = screenUtil.getClass();
			//System.out.println();
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}*/
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				GView viewInject = field.getAnnotation(GView.class);
				if (viewInject != null) {
					try {
						View view = null;
						if (viewInject.value() != 0) {
							view = finder.findViewById(viewInject.value(),
									viewInject.parentId());
						} else {
							view = finder.findViewById(viewInject.name(),
									viewInject.type());
						}
						if (view != null) {
							field.setAccessible(true);
							field.set(handler, view);
							setListener(handler, field, viewInject.onClick(),
									inMethod.Click);
							setListener(handler, field, viewInject.longClick(),
									inMethod.LongClick);
							setListener(handler, field, viewInject.itemClick(),
									inMethod.ItemClick);
							setListener(handler, field,
									viewInject.itemLongClick(),
									inMethod.itemLongClick);
							if (viewInject.width() != 0) {
								if (viewInject.height() != 0) {
									Method setViewSize = suClass.getMethod(
											"setViewSize", View.class,
											float.class, float.class);
									setViewSize.invoke(suClass, view,
											viewInject.width(),
											viewInject.height());
								} else {
									Method setViewWidth = suClass
											.getMethod("setViewWidth",
													View.class, float.class);
									setViewWidth.invoke(suClass, view,
											viewInject.width());
								}
							} else if (viewInject.height() != 0) {
								Method setViewHeight = suClass.getMethod(
										"setViewHeight", View.class,
										float.class);
								setViewHeight.invoke(suClass, view,
										viewInject.height());
							}
							Method setViewMargin = suClass.getMethod(
									"setViewMargin", View.class, float.class,
									float.class, float.class, float.class);
							setViewMargin.invoke(suClass, view,
									viewInject.top(), viewInject.bottom(),
									viewInject.left(), viewInject.right());
							if (viewInject.center() != 0) {
								Method setCenter = suClass.getMethod(
										"setCenter", View.class, int.class);
								setCenter.invoke(suClass, view,
										viewInject.center());
							}
						}
					} catch (Throwable e) {
						LogUtil.e(e.getMessage(), e);
					}
				} else {
					GAction actionInject = field.getAnnotation(GAction.class);
					if (actionInject != null) {
						try {
							Class clazz = field.getType();
							Constructor<? extends GBaseAction> c = clazz
									.getConstructor(Context.class);
							GBaseAction action = null;
							if (finder.getContext() != null) {
								action = c.newInstance(finder.getContext());
							} else {
								action = c.newInstance(handler);
							}
							field.setAccessible(true);
							field.set(handler, action);
							Method setAction = handlerType.getMethod(
									"setAction", GBaseAction.class);
							setAction.invoke(handler, action);
						} catch (Throwable e) {
							LogUtil.e(e.getMessage(), e);
						}
					} else {
						GFragment fragmentInject = field
								.getAnnotation(GFragment.class);
						if (fragmentInject != null) {
							try {
								Class clazz = field.getType();
								Fragment fragment = (Fragment) clazz.newInstance();
								field.setAccessible(true);
								field.set(handler, fragment);
							}  catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InstantiationException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						} else {
							GRes resInject = field.getAnnotation(GRes.class);
							if (resInject != null) {
								try {
									Object res = ResLoader
											.loadRes(resInject.type(),
													finder.getContext(),
													resInject.id());
									if (res != null) {
										field.setAccessible(true);
										field.set(handler, res);
									}
								} catch (Throwable e) {
									LogUtil.e(e.getMessage(), e);
								}
							} else {
								GPreference preferenceInject = field
										.getAnnotation(GPreference.class);
								if (preferenceInject != null) {
									try {
										Preference preference = finder
												.findPreference(preferenceInject
														.value());
										if (preference != null) {
											field.setAccessible(true);
											field.set(handler, preference);
										}
									} catch (Throwable e) {
										LogUtil.e(e.getMessage(), e);
									}
								}
							}
						}
					}
				}
			}
		}

	}
	
	
	public static void injectFragment(Object handler,Context context){
		Class<?> handlerType = handler.getClass();
		GContentView contentView = handlerType.getAnnotation(GContentView.class);
		if (contentView != null) {
			try {
				Field field = handlerType.getField("id");
				field.setAccessible(true);
				if (contentView.value() != 0) {
					field.set(handler, contentView.value());
				} else {
					field.set(handler,context.getResources().getIdentifier(contentView.name(),contentView.type(),context.getPackageName()));
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private static void setListener(Object injectedSource, Field field,
			String methodName, inMethod method) throws Exception {
		if (methodName == null || methodName.trim().length() == 0)
			return;

		Object obj = field.get(injectedSource);

		switch (method) {
		case Click:
			if (obj instanceof View) {
				((View) obj).setOnClickListener(new EventListener(
						injectedSource).click(methodName));
			}
			break;
		case ItemClick:
			if (obj instanceof AbsListView) {
				((AbsListView) obj).setOnItemClickListener(new EventListener(
						injectedSource).itemClick(methodName));
			}
			break;
		case LongClick:
			if (obj instanceof View) {
				((View) obj).setOnLongClickListener(new EventListener(
						injectedSource).longClick(methodName));
			}
			break;
		case itemLongClick:
			if (obj instanceof AbsListView) {
				((AbsListView) obj)
						.setOnItemLongClickListener(new EventListener(
								injectedSource).itemLongClick(methodName));
			}
			break;
		default:
			break;
		}
	}

	public enum inMethod {
		Click, LongClick, ItemClick, itemLongClick
	}
}
