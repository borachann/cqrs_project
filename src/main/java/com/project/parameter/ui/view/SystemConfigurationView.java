package com.project.parameter.ui.view;

import com.project.parameter.common.I18nMessage;
import com.project.parameter.dataprovider.BankDataProvider;
import com.project.parameter.ui.component.bank.BankComponent;
import com.project.parameter.ui.viewdeclaration.UIScopeParameterViews;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import javax.inject.Inject;
/**
 * Created by p.ly on 3/1/2018.
 */

@SpringComponent
@UIScope
@SpringView(name = UIScopeParameterViews.PARAMETER)
public class SystemConfigurationView extends SystemConfigurationViewDesign implements View {

  @Inject
  SystemConfigurationView(BankDataProvider bankDataProvider, I18nMessage localizer) {
    initializeSystemComponent(bankDataProvider, localizer);
  }

  private Component initializeSystemComponent(BankDataProvider bankDataProvider, I18nMessage localizer){
    componentLayout.addComponent(new BankComponent(bankDataProvider, localizer));
    return componentLayout;
  }
}
