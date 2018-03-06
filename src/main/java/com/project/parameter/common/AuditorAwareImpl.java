package com.project.parameter.common;

import org.springframework.data.domain.AuditorAware;
import org.vaadin.spring.security.VaadinSecurity;

import javax.inject.Inject;

/**
 * Created by p.ly on 3/1/2018.
 */
public class AuditorAwareImpl implements AuditorAware<String> {

  @Inject
  VaadinSecurity vaadinSecurity;

  @Override
  public String getCurrentAuditor() {
    return vaadinSecurity.isAuthenticated() ? vaadinSecurity.getAuthentication().getName() : "Anonymous";
  }
}

