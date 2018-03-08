package com.project.parameter.ui.component.util;

import com.project.parameter.ui.viewdeclaration.UIScopeParameterViews;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import org.vaadin.spring.security.VaadinSecurity;
//import org.vaadin.spring.security.VaadinSecurity;

import javax.inject.Inject;

@UIScope
@SpringComponent
public class MainMenuBar extends MainMenuBarDesign {

  @Inject
  public MainMenuBar(VaadinSecurity vaadinSecurity) {
    //set picture logo
    ThemeResource resource = new ThemeResource("image/logo.svg");
    logo.setSource(resource);
    logo.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(UIScopeParameterViews.PARAMETER));

    MenuBar.MenuItem item = systemMenu.addItem("System configuration", null);
    //item.addItem("", createNavigationCommand(UIScopeParameterViews.PARAMETER));

    //Contract
    addressMenu.addItem("Address configuration", createNavigationCommand(UIScopeParameterViews.PARAMETER));

    MenuBar.MenuItem logout = profileLoggedInMenu.addItem("Welcome, "  + vaadinSecurity.getAuthentication().getName().toUpperCase(), null);
    logout.addItem("Log out", menuItem -> vaadinSecurity.logout() );
  }

  private MenuBar.Command createNavigationCommand(final String viewName) {
    return menuItem -> getUI().getNavigator().navigateTo(viewName);
  }
}
