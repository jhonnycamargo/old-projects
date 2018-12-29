package com.merkhet.ui.view;

import com.vaadin.ui.Label;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;

@CDIView(TestView.NAME)
@SuppressWarnings("serial")
public class TestView extends CustomComponent implements View {

  public static final String NAME = "testview";

  public TestView() {
    setCompositionRoot(new CssLayout(new Label("hola")));
  }

  @Override
  public void enter(ViewChangeEvent event) {

  }
}
