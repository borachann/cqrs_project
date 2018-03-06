package com.project.parameter.config.security;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,
    ElementType.METHOD,
    ElementType.TYPE,
    ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Authenticated {

  With value();

  enum With {
    CLIENT_CREDENTIALS,
    USER_CREDENTIALS
  }
}
