package com.merkhet.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;

import com.jain.addon.i18N.component.I18NUI;
import com.jain.addon.resource.DefaultI18NResourceProvider;
import com.jain.addon.resource.I18NProvider;
import com.merkhet.ui.util.StringGenerator;
import com.merkhet.ui.view.MySitesView;
import com.merkhet.ui.view.SimpleLoginMainView;
import com.merkhet.ui.view.SimpleLoginView;
import com.merkhet.ui.view.TestView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.Notification;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickListener;

import javax.inject.Inject;

import org.picketlink.Identity;

/**
 *
 * @author adam-bien.com
 */
@CDIUI("")
@Theme("tests-valo")
@Title("")
@SuppressWarnings("serial")
public class WelcomePage extends I18NUI {

  @Inject
  CDIViewProvider viewProvider;
  @Inject
  Identity identity;

  private static LinkedHashMap<String, String> themeVariants = new LinkedHashMap<String, String>();
  static {
    themeVariants.put("tests-valo", "Default");
    themeVariants.put("tests-valo-blueprint", "Blueprint");
    themeVariants.put("tests-valo-dark", "Dark");
    themeVariants.put("tests-valo-facebook", "Facebook");
    themeVariants.put("tests-valo-flatdark", "Flat dark");
    themeVariants.put("tests-valo-flat", "Flat");
    themeVariants.put("tests-valo-light", "Light");
    themeVariants.put("tests-valo-metro", "Metro");
  }

  ValoMenuLayout valoMenuLayout = new ValoMenuLayout();
  ComponentContainer viewDisplay = valoMenuLayout.getContentContainer();
  CssLayout menu = new CssLayout();
  CssLayout menuBarLayout = new CssLayout();
  CssLayout menuItemsLayout = new CssLayout();

  private Navigator navigator;
  private final LinkedHashMap<String, String> menuItems = new LinkedHashMap<String, String>();

  Component buildTestMenu() {
    final CssLayout menu = new CssLayout();
    menu.addStyleName("large-icons");

    final Label logo = new Label("Va");
    logo.setSizeUndefined();
    logo.setPrimaryStyleName("valo-menu-logo");
    menu.addComponent(logo);

    Button b = new Button(
        "Reference <span class=\"valo-menu-badge\">3</span>");
    b.setIcon(FontAwesome.TH_LIST);
    b.setPrimaryStyleName("valo-menu-item");
    b.addStyleName("selected");
    b.setHtmlContentAllowed(true);
    menu.addComponent(b);

    b = new Button("API");
    b.setIcon(FontAwesome.BOOK);
    b.setPrimaryStyleName("valo-menu-item");
    menu.addComponent(b);

    b = new Button("Examples <span class=\"valo-menu-badge\">12</span>");
    b.setIcon(FontAwesome.TABLE);
    b.setPrimaryStyleName("valo-menu-item");
    b.setHtmlContentAllowed(true);
    menu.addComponent(b);

    return menu;
  }

  CssLayout buildMenu() {
    final HorizontalLayout top = new HorizontalLayout();
    top.setWidth("100%");
    top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
    top.addStyleName("valo-menu-title");
    menu.addComponent(top);
    String i18nTitle = DefaultI18NResourceProvider.instance().getText(
        getLocale(), "app.name");
    final Label title = new Label("<h1><strong>" + i18nTitle
        + "</strong></h1>", ContentMode.HTML);
    title.setSizeUndefined();
    top.addComponent(title);
    top.setExpandRatio(title, 1);

    Accordion accordion = new Accordion();
    accordion.setCaption("<hr>");
    accordion.setCaptionAsHtml(true);
    accordion.setSelectedTab(-1);
    accordion.addStyleName("borderless");
    // accordion.setHeight(80.0F, Unit.PERCENTAGE);

    final CssLayout layout = buildHomeLayout();
    I18NProvider provider = DefaultI18NResourceProvider.instance();
    com.vaadin.ui.TabSheet.Tab tab = accordion.addTab(layout,
        provider.getText(getLocale(), "home")
            + "<span class=\"v-accordion-item-badge\">1</span>",
        FontAwesome.HOME);
    tab.setStyleName("color1");

    final CssLayout layout2 = buildGamesLayout();
    accordion.addTab(
        layout2,
        provider.getText(getLocale(), "games")
            + "<span class=\"v-accordion-item-badge\">1</span>",
        FontAwesome.GAMEPAD).setStyleName("color1");
    accordion.setTabCaptionsAsHtml(true);

    menu.addComponent(accordion);

    return menu;
  }

  private CssLayout buildGamesLayout() {
    CssLayout layout = new CssLayout();
    layout.setPrimaryStyleName("valo-menuitems");
    final Button b = new Button("content.package", new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        navigator.navigateTo(TestView.NAME);

      }
    });
    b.setHtmlContentAllowed(true);
    b.setPrimaryStyleName("myitem");
    b.setIcon(FontAwesome.GIFT);
    b.setDescription("content.package");
    layout.addComponent(b);

    return layout;
  }

  private CssLayout buildHomeLayout() {
    CssLayout layout = new CssLayout();
    layout.setPrimaryStyleName("valo-menuitems");
    final Button b = new Button("my.sites", new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        navigator.navigateTo(MySitesView.NAME);
      }
    });
    b.setHtmlContentAllowed(true);
    b.setPrimaryStyleName("myitem");
    b.setIcon(FontAwesome.SITEMAP);
    b.setDescription("my.sites");
    layout.addComponent(b);

    return layout;
  }

  static Handler actionHandler = new Handler() {
    private final Action ACTION_ONE = new Action("Action One");
    private final Action ACTION_TWO = new Action("Action Two");
    private final Action ACTION_THREE = new Action("Action Three");
    private final Action[] ACTIONS = new Action[] { ACTION_ONE, ACTION_TWO,
        ACTION_THREE };

    @Override
    public void handleAction(final Action action, final Object sender,
        final Object target) {
      Notification.show(action.getCaption());
    }

    @Override
    public Action[] getActions(final Object target, final Object sender) {
      return ACTIONS;
    }
  };

  static Handler getActionHandler() {
    return actionHandler;
  }

  static final String CAPTION_PROPERTY = "caption";
  static final String DESCRIPTION_PROPERTY = "description";
  static final String ICON_PROPERTY = "icon";
  static final String INDEX_PROPERTY = "index";

  @SuppressWarnings("unchecked")
  static Container generateContainer(final int size,
      final boolean hierarchical) {
    final IndexedContainer container = hierarchical ? new HierarchicalContainer()
        : new IndexedContainer();
    final StringGenerator sg = new StringGenerator();
    container.addContainerProperty(CAPTION_PROPERTY, String.class, null);
    container.addContainerProperty(ICON_PROPERTY, Resource.class, null);
    container.addContainerProperty(INDEX_PROPERTY, Integer.class, null);
    container
        .addContainerProperty(DESCRIPTION_PROPERTY, String.class, null);
    for (int i = 1; i < size + 1; i++) {
      final Item item = container.addItem(i);
      item.getItemProperty(CAPTION_PROPERTY).setValue(
          sg.nextString(true) + " " + sg.nextString(false));
      item.getItemProperty(INDEX_PROPERTY).setValue(i);
      item.getItemProperty(DESCRIPTION_PROPERTY).setValue(
          sg.nextString(true) + " " + sg.nextString(false) + " "
              + sg.nextString(false));
    }

    if (hierarchical) {
      for (int i = 1; i < size + 1; i++) {
        for (int j = 1; j < 5; j++) {
          final String id = i + " -> " + j;
          Item child = container.addItem(id);
          child.getItemProperty(CAPTION_PROPERTY).setValue(
              sg.nextString(true) + " " + sg.nextString(false));
          // ((Hierarchical) container).setChildrenAllowed(id, false);
          ((Hierarchical) container).setParent(id, i);

          for (int k = 1; k < 6; k++) {
            final String id2 = id + " -> " + k;
            child = container.addItem(id2);
            child.getItemProperty(CAPTION_PROPERTY).setValue(
                sg.nextString(true) + " "
                    + sg.nextString(false));
            // ((Hierarchical) container)
            // .setChildrenAllowed(id, false);
            ((Hierarchical) container).setParent(id2, id);

            for (int l = 1; l < 5; l++) {
              final String id3 = id2 + " -> " + l;
              child = container.addItem(id3);
              child.getItemProperty(CAPTION_PROPERTY).setValue(
                  sg.nextString(true) + " "
                      + sg.nextString(false));
              // ((Hierarchical) container)
              // .setChildrenAllowed(id, false);
              ((Hierarchical) container).setParent(id3, id2);
            }
          }
        }
      }
    }
    return container;
  }

  @Override
  protected void initialize(VaadinRequest request) {
    if (getPage().getWebBrowser().isIE()
        && getPage().getWebBrowser().getBrowserMajorVersion() == 9) {
      menu.setWidth("320px");
    }
    Responsive.makeResponsive(this);
    I18NProvider provider = DefaultI18NResourceProvider.instance();
    getPage()
        .setTitle(
            provider.getText(Locale.getDefault(), "welcome",
                new Object[0]));
    setContent(valoMenuLayout);
    valoMenuLayout.setWidth("100%");

    valoMenuLayout.addMenu(buildMenu());
    valoMenuLayout.addHeader(buildHeader());

    addStyleName(ValoTheme.UI_WITH_MENU);

    navigator = new Navigator(this, viewDisplay);
    setNavigator(navigator);
    navigator.addProvider(viewProvider);
    navigator.addView(SimpleLoginView.NAME, SimpleLoginView.class);
    navigator.addView(SimpleLoginMainView.NAME, SimpleLoginMainView.class);
    navigator.addView(MySitesView.NAME, MySitesView.class);

    final String f = Page.getCurrent().getUriFragment();
    if (f == null || f.equals("")) {
      navigator.navigateTo(SimpleLoginMainView.NAME);
    }

    navigator.setErrorView(SimpleLoginMainView.class);

    navigator.addViewChangeListener(new ViewChangeListener() {

      @Override
      public boolean beforeViewChange(final ViewChangeEvent event) {
        // Check if a user has logged in
        boolean isLoggedIn = identity.isLoggedIn();
        boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

        if (!isLoggedIn && !isLoginView) {
          // Redirect to login view always if a user has not yet
          // logged in
          getNavigator().navigateTo(SimpleLoginView.NAME);
          return false;

        } else if (isLoggedIn && isLoginView) {
          // If someone tries to access to login view while logged in,
          // then cancel
          return false;
        }

        return true;
      }

      @Override
      public void afterViewChange(final ViewChangeEvent event) {
        menu.setVisible(identity.isLoggedIn());
        menuBarLayout.setVisible(identity.isLoggedIn());

        for (final Iterator<Component> it = menuItemsLayout.iterator(); it
            .hasNext();) {
          it.next().removeStyleName("selected");
        }
        for (final Entry<String, String> item : menuItems.entrySet()) {
          if (event.getViewName().equals(item.getKey())) {
            for (final Iterator<Component> it = menuItemsLayout
                .iterator(); it.hasNext();) {
              final Component c = it.next();
              if (c.getCaption() != null
                  && c.getCaption().startsWith(
                      item.getValue())) {
                c.addStyleName("selected");
                break;
              }
            }
            break;
          }
        }
        menu.removeStyleName("valo-menu-visible");
      }
    });
  }

  private Component buildHeader() {
    MenuCommand command = new MenuCommand();
    MenuBar menuBar = new MenuBar();
    menuBar.setId("themeSelect");
    menuBar.addStyleName("small");
    MenuItem menuItem = menuBar.addItem("profile", command);
    menuItem.setIcon(FontAwesome.USER);
    menuItem = menuBar.addItem("messages", command);
    menuItem.setCommand(null);
    menuItem.setIcon(FontAwesome.ENVELOPE);
    menuItem.addItem("new.message", command);
    menuItem.addItem("inbox", command);
    menuItem.addItem("outbox", command);
    menuItem.addItem("trash", command);

    menuItem = menuBar.addItem("settings", command);
    menuItem.setIcon(FontAwesome.GEAR);
    menuItem = menuBar.addItem("logout", new Command() {

      @Override
      public void menuSelected(MenuItem selectedItem) {
        logout();
      }
    });
    menuItem.setIcon(FontAwesome.MAIL_FORWARD);
    menuBarLayout.addComponent(menuBar);

    return menuBarLayout;
  }

  public void logout() {
    identity.logout();
    getUI().getNavigator().navigateTo("");
  }

}
