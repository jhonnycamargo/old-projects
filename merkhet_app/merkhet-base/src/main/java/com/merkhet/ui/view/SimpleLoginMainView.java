package com.merkhet.ui.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;

@CDIView(SimpleLoginMainView.NAME)
@SuppressWarnings("serial")
public class SimpleLoginMainView extends CustomComponent implements View {

  public static final String NAME = "main";

  public SimpleLoginMainView() {
    setCompositionRoot(new CssLayout());
  }

  @Override
  public void enter(ViewChangeEvent event) {

  }
}
