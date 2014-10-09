package org.gulup.annotation;

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
public @interface GView {

	int value() default 0;
	/* parent view id */
	int parentId() default 0;
	
	String name() default "";
	
	String type() default "id";
	
	float width() default 0;
	float height() default 0;
	
	float top() default 0;
	float bottom() default 0;
	float left() default 0;
	float right() default 0;
	
	int center() default 0;
	
	public String onClick() default "onClick";
	public String longClick() default "";
	public String itemClick() default "";
	public String itemLongClick() default "";
}