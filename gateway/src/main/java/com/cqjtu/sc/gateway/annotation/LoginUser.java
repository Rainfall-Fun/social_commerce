package com.cqjtu.sc.gateway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//scope of use for annotations
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
