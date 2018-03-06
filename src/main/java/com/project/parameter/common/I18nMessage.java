package com.project.parameter.common;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import javax.inject.Inject;

/**
 * Created by p.ly on 3/1/2018.
 */
@Service
public class I18nMessage {

  private final MessageSource messageSource;

  @Inject
  public I18nMessage(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public String getMessage(String i18nCode) {
    return getMessage(i18nCode, null);
  }

  public String getMessage(String i18nCode, Object... args) {
    return messageSource.getMessage(i18nCode, args, LocaleContextHolder.getLocale());
  }

}
