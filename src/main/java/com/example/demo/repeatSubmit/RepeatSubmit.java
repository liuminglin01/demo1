package com.example.demo.repeatSubmit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RepeatSubmit {

    /**
     * 默认的间隔时间(ms)，小于此时间视为重复提交
     */
    int timeout() default 2000;

}
