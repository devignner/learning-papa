package com.nnoco.golbang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
public @interface Request {
	public String value();
}
