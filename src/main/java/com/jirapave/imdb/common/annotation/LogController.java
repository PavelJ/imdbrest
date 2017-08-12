package com.jirapave.imdb.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate Controller to start logging result of method (only for package com.jirapave.imdb.rest.controller) and when exceptions is not thrown.
 *
 * NOTE: For DEV profile ONLY
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogController {
}
