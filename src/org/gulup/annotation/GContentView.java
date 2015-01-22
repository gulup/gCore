package org.gulup.annotation;

/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午12:09:15
 * 类说明:布局注解
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gulup.utils.Constant;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GContentView {
	int value() default 0;
	int viewType() default 0;
	String name() default "";
	String type() default "layout";
	//boolean useAnnotation() default true;
}