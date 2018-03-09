package com.project.parameter.ui.component.confirmation;

import com.project.parameter.common.I18nMessage;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.springframework.context.MessageSource;

/**
 * Created by p.ly on 3/8/2018.
 */
public class DeleteConfirmationComponentDesign extends CssLayout {
  protected Label titleLabel;
  protected Label messageLabel;
  protected Button closeButton;
  protected Button yesButton;
  protected Button noButton;

  public DeleteConfirmationComponentDesign(I18nMessage localize) {
    this.titleLabel = new Label();
    this.messageLabel =  new Label();
    this.closeButton = new Button();
    this.noButton = new Button();
    this.yesButton = new Button();

    setProperties(localize);
    addComponent(generateFormLayout());
    setWidth(100, Unit.PERCENTAGE);
  }

  private void setProperties(I18nMessage localize) {
    setTitleLabelProperty(localize);
    setCloseButtonProperty();
    setNoButtonProperty(localize);
    setYesButtonProperty(localize);
    setMessageLabelProperty(localize);
  }

  private void setTitleLabelProperty(I18nMessage localize) {
    titleLabel.setValue(localize.getMessage("delete.confirmation.title"));
    titleLabel.setSizeFull();
    titleLabel.setStyleName("confirm-header-label");
  }

  private void setCloseButtonProperty() {
    closeButton.setWidth(-1, Unit.PERCENTAGE);
    closeButton.setHeight(-1, Unit.PERCENTAGE);
    closeButton.setIcon(VaadinIcons.CLOSE);
    closeButton.setStyleName("borderless confirm-close-header-button");
  }

  private void setYesButtonProperty(I18nMessage localize) {
    yesButton.setCaption(localize.getMessage("button.yes"));
    yesButton.setSizeFull();
    yesButton.setStyleName("large friendly");
  }

  private void setNoButtonProperty(I18nMessage localize) {
    noButton.setCaption(localize.getMessage("button.no"));
    noButton.setSizeFull();
    noButton.setStyleName("danger large");
  }

  private void setMessageLabelProperty(I18nMessage localize) {
    messageLabel.setValue(localize.getMessage("delete.confirmation.body"));
    messageLabel.setSizeFull();
    messageLabel.setStyleName("confirm-body-label");
  }

  private CssLayout generateFormLayout() {
    CssLayout components = new CssLayout();

    CssLayout headerLayout = new CssLayout();
    headerLayout.setWidth(100, Unit.PERCENTAGE);
    headerLayout.setHeight(60, Unit.PIXELS);
    headerLayout.setStyleName("confirm-header");
    headerLayout.addComponent(titleLabel);
    headerLayout.addComponent(closeButton);

    CssLayout bodyLayout = new CssLayout();
    bodyLayout.setSizeFull();
    bodyLayout.addComponent(messageLabel);

    HorizontalLayout footerLayout = new HorizontalLayout();
    footerLayout.setWidth(100, Unit.PERCENTAGE);
    footerLayout.setMargin(true);
    footerLayout.setSpacing(true);
    footerLayout.addComponent(yesButton);
    footerLayout.addComponent(noButton);

    components.addComponent(headerLayout);
    components.addComponent(bodyLayout);
    components.addComponent(footerLayout);
    return components;
  }
}
