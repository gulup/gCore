package org.gulup.view.annotation;
/**
 * @author gulup
 * @version 创建时间：2014-5-10 下午12:04:02
 * 类说明:资源注解
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.gulup.view.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResInject {
    int id();

    ResType type();
}