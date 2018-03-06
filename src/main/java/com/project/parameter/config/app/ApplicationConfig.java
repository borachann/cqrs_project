package com.project.parameter.config.app;

import com.project.parameter.common.AuditorAwareImpl;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Created by p.ly on 3/1/2018.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ApplicationConfig {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasenames("i18n/messages");
    source.setDefaultEncoding("UTF-8");
    return source;
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(new Locale("en"));
    return slr;
  }

  @Bean
  @VaadinSessionScope
  public AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }
}
