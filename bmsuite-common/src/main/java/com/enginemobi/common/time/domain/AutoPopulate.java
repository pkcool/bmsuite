package com.enginemobi.common.time.domain;

import java.lang.annotation.*;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoPopulate {
    boolean autoUpdateValue() default false;
}
