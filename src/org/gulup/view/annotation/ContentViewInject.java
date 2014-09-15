package org.gulup.view.annotation;

/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午12:09:15
 * 类说明:布局注解
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentViewInject {
	int value() default 0;
	String name() default "";
	String defType() default "";
}