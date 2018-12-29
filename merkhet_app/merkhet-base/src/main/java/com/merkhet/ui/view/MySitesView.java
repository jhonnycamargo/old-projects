package com.merkhet.ui.view;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.jain.addon.resource.DefaultI18NResourceProvider;
import com.jain.addon.resource.I18NProvider;
import com.jensjansson.pagedtable.ControlsLayout;
import com.jensjansson.pagedtable.PagedTable;
import com.merkhet.boundary.MySitesService;
import com.merkhet.domain.MySite;
import com.merkhet.ui.util.ShortStringToDateConverter;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@CDIView(MySitesView.NAME)
@SuppressWarnings("serial")
public class MySitesView extends CustomComponent implements View {

  public static final String NAME = "my-sites";

  @Inject
  MySitesService mySitesService;

  I18NProvider i18nProvider = DefaultI18NResourceProvider.instance();

  VerticalLayout root = new VerticalLayout();

  public MySitesView() {
    root.setSpacing(true);
    root.setMargin(true);
    Label title = new Label("my.sites");
    title.addStyleName("h1");
    root.addComponent(title);
    final FormLayout form = new FormLayout();
    form.setMargin(false);
    form.setWidth("800px");
    // form.addStyleName("light");
    root.addComponent(form);

    Label section = new Label("search.my.sites");
    section.addStyleName("h2");
    section.addStyleName("colored");
    form.addComponent(section);

    TextField siteId = new TextField("site.id");
    siteId.setWidth("50%");
    form.addComponent(siteId);

    TextField description = new TextField("description");
    description.setWidth("50%");
    form.addComponent(description);

    DateField startDate = new DateField("start.date");
    startDate.setWidth("50%");
    form.addComponent(startDate);

    DateField endDate = new DateField("end.date");
    endDate.setWidth("50%");
    form.addComponent(endDate);

    Button searchBtn = new Button("search");
    searchBtn.addStyleName("primary");

    HorizontalLayout footer = new HorizontalLayout();
    footer.setMargin(new MarginInfo(true, false, true, false));
    footer.setSpacing(true);
    footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    form.addComponent(footer);
    footer.addComponent(searchBtn);

    setCompositionRoot(root);
  }

  @Override
  public void enter(ViewChangeEvent event) {
    root.addComponent(buildPagedTable());
  }

  public Table buildTable() {
    Table table = new Table();
    table.setWidth("100%");
    table.addContainerProperty("site.id", String.class, null);
    table.addContainerProperty("description", String.class, null);
    table.addContainerProperty("start.date", Date.class, null);
    table.addContainerProperty("end.date", Date.class, null);
    table.addItem(new Object[] { "123456", "Descripcion ejemplo",
        Calendar.getInstance().getTime(),
        Calendar.getInstance().getTime() }, "123456");
    table.addItem(new Object[] { "123457", "Descripcion ejemplo",
        Calendar.getInstance().getTime(),
        Calendar.getInstance().getTime() }, "123457");
    table.addItem(new Object[] { "123458", "Descripcion ejemplo",
        Calendar.getInstance().getTime(),
        Calendar.getInstance().getTime() }, "123458");
    table.setFooterVisible(true);
    table.setPageLength(10);

    return table;
  }

  public VerticalLayout buildPagedTable() {
    final VerticalLayout layout = new VerticalLayout();
    layout.setMargin(true);
    PagedTable table = new PagedTable();
    table.setWidth("100%");

    LazyLoadedContainer container = new LazyLoadedContainer(MySite.class,
        mySitesService);
    table.setContainerDataSource(container);
    table.setColumnHeader("siteId", "site.id");
    table.setColumnHeader("descripcion", "description");
    table.setColumnHeader("startDate", "start.date");
    table.setColumnHeader("endDate", "end.date");
    table.setVisibleColumns(new Object[] { "siteId", "descripcion",
        "startDate", "endDate" });
    table.setColumnReorderingAllowed(true);
    table.setPageLength(50);
    table.setSelectable(true);
    table.setAlwaysRecalculateColumnWidths(true);
    table.setConverter("startDate", new ShortStringToDateConverter());
    table.setConverter("endDate", new ShortStringToDateConverter());
    table.addGeneratedColumn("", new ColumnGenerator() {

      @Override
      public Object generateCell(final Table source, final Object itemId,
          Object columnId) {
        Button button = new Button();
        button.setIcon(FontAwesome.SEARCH_PLUS);
        button.setDescription("view");
        button.addClickListener(new ClickListener() {

          @Override
          public void buttonClick(ClickEvent event) {
            Item item = source.getContainerDataSource().getItem(
                itemId);
            getUI().getNavigator().navigateTo(
                MySiteView.NAME + "/"
                    + item.getItemProperty("siteId"));
          }
        });
        return button;
      }
    });
    layout.addComponent(table);

    ControlsLayout controlsLayout = table.createControls();
    controlsLayout.setWidth("100%");
    ComboBox itemsPerPage = controlsLayout.getItemsPerPageSelect();
    itemsPerPage.removeItem("50"); // cuando se selecciona 50 solo muestra
                    // la mitad.
    controlsLayout.getItemsPerPageLabel().setValue(
        i18nProvider.getMessage(getLocale(), "items.per.page"));
    controlsLayout.getCurrentPageTextField().setWidth("80px");
    controlsLayout.getPageLabel().setValue(
        i18nProvider.getText(getLocale(), "page"));
    itemsPerPage.setWidth("80px");
    layout.addComponent(controlsLayout);

    return layout;
  }
}

@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
class LazyLoadedContainer extends BeanContainer {

  private MySitesService mySitesService;
  private int lastSize;

  public LazyLoadedContainer(Class type, MySitesService mySitesService) {
    super(type);
    this.mySitesService = mySitesService;
  }

  @Override
  public int size() {
    return mySitesService.size();
  }

  @Override
  public BeanItem getItem(Object itemId) {
    return new BeanItem((MySite) itemId);
  }

  @Override
  public List getItemIds(int startIndex, int numberOfIds) {
    int endIndex = startIndex + numberOfIds;
    System.out.println("startIndex: " + startIndex + ", endIndex: "
        + endIndex);
    List list = mySitesService.list(startIndex, endIndex);
    return list;
  }

  public int getLastSize() {
    return lastSize;
  }
}
