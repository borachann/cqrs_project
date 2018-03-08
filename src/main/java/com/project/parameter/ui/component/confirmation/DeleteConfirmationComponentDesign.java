package com.project.parameter.ui.component.confirmation;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Created by p.ly on 3/8/2018.
 */
public class DeleteConfirmationComponentDesign extends CssLayout{
  protected Label titleLabel;
  protected Label messageLabel;
  protected Button closeButton;
  protected Button yesButton;
  protected Button noButton;

  public DeleteConfirmationComponentDesign(Label titleLabel, Label messageLabel, Button closeButton, Button noButton, Button yesButton) {
    this.titleLabel = titleLabel;
    this.messageLabel = messageLabel;
    this.closeButton = closeButton;
    this.noButton = noButton;
    this.yesButton = yesButton;

    setTitleLabelProperties();
    addComponent(generateFormLayout());
    setWidth(100, Unit.PERCENTAGE);
  }

  private void setTitleLabelProperties(){
    titleLabel.setSizeFull();
    titleLabel.setStyleName("confirm-header-label");
  }

  private CssLayout generateFormLayout(){
    CssLayout components = new CssLayout();

    CssLayout headerLayout = new CssLayout();
    headerLayout.setWidth(100, Unit.PERCENTAGE);
    headerLayout.setStyleName("confirm-header");
    headerLayout.addComponent(titleLabel);

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
