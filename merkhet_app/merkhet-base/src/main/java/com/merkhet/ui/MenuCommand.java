package com.merkhet.ui;

import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class MenuCommand implements Command {
  public static final String LOGOUT_MNU = "logout";

  @Override
  public void menuSelected(MenuItem selectedItem) {

    switch (selectedItem.getText()) {
    case LOGOUT_MNU:
      ((WelcomePage) WelcomePage.getCurrent()).logout();
      break;
    default:
      Notification.show("Action " + selectedItem.getText(),
          Type.TRAY_NOTIFICATION);
      break;
    }
  }

}
