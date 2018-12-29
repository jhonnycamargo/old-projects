package com.merkhet.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @since
 * @author Vaadin Ltd
 */
@SuppressWarnings("serial")
public class ValoMenuLayout extends HorizontalLayout {

  private VerticalLayout upperSection = new VerticalLayout();
  private CssLayout headerArea = new CssLayout();
  private CssLayout contentArea = new CssLayout();
  private CssLayout menuArea = new CssLayout();

  public ValoMenuLayout() {
    setSizeFull();
    menuArea.setPrimaryStyleName("valo-menu");

    headerArea.setPrimaryStyleName("valo-menu-title");

    contentArea.setPrimaryStyleName("valo-content");
    contentArea.addStyleName("v-scrollable");
    contentArea.setSizeFull();

    addComponent(menuArea);
    addComponent(upperSection);

    upperSection.addComponent(headerArea);
    upperSection.addComponent(contentArea);
    upperSection.setSizeFull();

    setExpandRatio(upperSection, 1);
    upperSection.setExpandRatio(contentArea, 1);
    setMargin(false);
    setSpacing(false);
    upperSection.setMargin(false);
    upperSection.setSpacing(false);
  }

  public ComponentContainer getContentContainer() {
    return contentArea;
  }

  public void addMenu(Component menu) {
    menu.addStyleName("valo-menu-part");
    menuArea.addComponent(menu);
  }

  public void addHeader(Component header) {
    headerArea.addComponent(header);
  }

}
