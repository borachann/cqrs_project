package com.project.parameter.ui;

import com.project.parameter.ui.viewdisplay.MainViewDisplay;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Locale;

/**
 * Created by p.ly on 3/1/2018.
 */
@SpringUI
@Theme("parameter")
public class ParameterUI extends UI{
  /**
 *
 */
private static final long serialVersionUID = 8125033437325957759L;

  private final static Logger logger = LoggerFactory.getLogger(ParameterUI.class);
  private MainViewDisplay mainViewDisplay;
  private SpringViewProvider springViewProvider;

  @Inject
  public ParameterUI(MainViewDisplay mainViewDisplay, SpringViewProvider viewProvider) {

    this.mainViewDisplay = mainViewDisplay;
    this.springViewProvider = viewProvider;
  }

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    setupDefaultErrorHandler();
   // springViewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
    setContent(mainViewDisplay);
  }

  private void setupDefaultErrorHandler() {
    setErrorHandler(errorEvent -> {
      logger.error("An error occurred", errorEvent.getThrowable());
      Notification.show("An error occurred : please contact your support",
        Notification.Type.ERROR_MESSAGE);
    });
  }

  @Override
  public void setLocale(Locale locale) {
    super.setLocale(locale);
  }
}
