package com.merkhet.ui.view;

import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;

import com.merkhet.security.LoginController;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@CDIView(SimpleLoginView.NAME)
@SuppressWarnings("serial")
public class SimpleLoginView extends CustomComponent implements View {

  public static final String NAME = "login";

  @Inject
  LoginController loginController;
  @Inject
  Identity identity;
  @Inject
  transient DefaultLoginCredentials loginCredentials;

  final TextField username = new TextField("username");
  final PasswordField password = new PasswordField("password");

  private final VerticalLayout loginLayout;

  public SimpleLoginView() {
    setSizeFull();

    loginLayout = new VerticalLayout();
    loginLayout.setSizeFull();
    Component loginForm = buildLoginForm();
    loginLayout.addComponent(loginForm);
    loginLayout.setComponentAlignment(loginForm, Alignment.TOP_RIGHT);
    setCompositionRoot(loginLayout);
  }

  private Component buildLoginForm() {
    final VerticalLayout loginPanel = new VerticalLayout();
    loginPanel.setSizeUndefined();
    loginPanel.setSpacing(true);
    Responsive.makeResponsive(loginPanel);
    loginPanel.addStyleName("login-panel");

    loginPanel.addComponent(buildLabels());
    loginPanel.addComponent(buildFields());
    return loginPanel;
  }

  private Component buildLabels() {
    CssLayout labels = new CssLayout();
    labels.addStyleName("labels");

    Label welcome = new Label("welcome");
    welcome.setSizeUndefined();
    welcome.addStyleName(ValoTheme.LABEL_H4);
    welcome.addStyleName(ValoTheme.LABEL_COLORED);
    labels.addComponent(welcome);

    Label title = new Label("app.name");
    title.setSizeUndefined();
    title.addStyleName(ValoTheme.LABEL_H3);
    title.addStyleName(ValoTheme.LABEL_LIGHT);
    labels.addComponent(title);
    return labels;
  }

  private Component buildFields() {
    HorizontalLayout fields = new HorizontalLayout();
    fields.setSpacing(true);
    fields.addStyleName("fields");

    username.setIcon(FontAwesome.USER);
    username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

    password.setIcon(FontAwesome.LOCK);
    password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

    final Button signin = new Button("sign.in");
    signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
    signin.setClickShortcut(KeyCode.ENTER);
    signin.focus();

    fields.addComponents(username, password, signin);
    fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

    signin.addClickListener(new ClickListener() {
      @Override
      public void buttonClick(final ClickEvent event) {
        loginCredentials.setUserId(username.getValue());
        loginCredentials.setPassword(password.getValue());
        loginController.login();
        if (identity.isLoggedIn()) {
          getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);
        }
      }
    });
    return fields;
  }

  @Override
  public void enter(ViewChangeEvent event) {
    username.focus();
  }

}
