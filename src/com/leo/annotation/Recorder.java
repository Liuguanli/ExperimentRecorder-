package com.leo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by apple on 2017/12/23.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Recorder {
    String fileName();
    int recordType();
}
