package org.gulup.view.annotation;

/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午12:02:30
 * 类说明:控件註解
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {

	int value() default 0;
	/* parent view id */
	int parentId() default 0;
	
	String name() default "";
	
	String defType() default "id";
	
	public String onClick() default "";
	public String longClick() default "";
	public String itemClick() default "";
	public String itemLongClick() default "";
}