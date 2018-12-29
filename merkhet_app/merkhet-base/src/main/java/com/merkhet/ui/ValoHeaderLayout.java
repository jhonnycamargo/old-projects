package com.merkhet.ui;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

@SuppressWarnings("serial")
public class ValoHeaderLayout extends HorizontalLayout {

  CssLayout rightMenu = new CssLayout();

  public ValoHeaderLayout() {
    setSizeFull();
    MenuCommand menuCommand = new MenuCommand();
    MenuBar menuBar = new MenuBar();
    menuBar.setWidth(100.0F, Unit.PERCENTAGE);
    MenuItem child = menuBar.addItem("hola", menuCommand);
    child.setEnabled(true);
    rightMenu.addComponent(menuBar);

    addComponent(rightMenu);
  }

  public CssLayout getRightMenu() {
    return rightMenu;
  }

}
