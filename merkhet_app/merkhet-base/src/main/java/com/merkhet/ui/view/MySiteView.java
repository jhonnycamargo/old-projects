package com.merkhet.ui.view;

import javax.inject.Inject;

import com.merkhet.boundary.MySitesService;
import com.merkhet.domain.MySite;
import com.merkhet.ui.util.ShortStringToDateConverter;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@CDIView(MySiteView.NAME)
@SuppressWarnings("serial")
public class MySiteView extends CustomComponent implements View {

  public static final String NAME = "my-site";

  @Inject
  MySitesService mySitesService;

  MySite mySite;
  BeanFieldGroup<MySite> binder = new BeanFieldGroup<MySite>(MySite.class);
  TextField siteId;
  TextField description;
  DateField startDate;
  DateField endDate;

  public MySiteView() {
    VerticalLayout root = new VerticalLayout();
    root.setSpacing(true);
    root.setMargin(true);

    Label title = new Label("my.site");
    title.addStyleName("h1");
    root.addComponent(title);

    final FormLayout form = new FormLayout();
    form.setMargin(false);
    form.setWidth("800px");
    form.addStyleName("light");
    root.addComponent(form);

    Label setup = new Label("set.up");
    setup.addStyleName("h2");
    setup.addStyleName("colored");
    form.addComponent(setup);

    siteId = new TextField("site.id");
    siteId.setWidth("50%");
    form.addComponent(siteId);

    description = new TextField("description");
    description.setWidth("50%");
    form.addComponent(description);

    startDate = new DateField("start.date");
    startDate.setDateFormat(ShortStringToDateConverter.FORMAT);
    form.addComponent(startDate);

    endDate = new DateField("end.date");
    form.addComponent(endDate);

    form.setReadOnly(true);

    final Button save = new Button("save");
    save.addStyleName("primary");
    final Button edit = new Button("edit");
    save.addClickListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        form.setReadOnly(true);
        form.addStyleName("light");
        edit.setVisible(true);
        event.getButton().setVisible(false);
      }
    });
    edit.addClickListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        boolean readOnly = form.isReadOnly();
        if (readOnly) {
          form.setReadOnly(false);
          form.removeStyleName("light");
          save.setVisible(true);
          event.getButton().setVisible(false);
        }
      }
    });
    save.setVisible(false);

    HorizontalLayout footer = new HorizontalLayout();
    footer.setMargin(new MarginInfo(true, false, true, false));
    footer.setSpacing(true);
    footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    form.addComponent(footer);
    footer.addComponent(edit);
    footer.addComponent(save);

    setCompositionRoot(root);
  }

  @Override
  public void enter(ViewChangeEvent event) {
    if (event.getParameters() == null || event.getParameters().isEmpty()) {
      mySite = new MySite();
    } else {
      String param = event.getParameters();
      mySite = mySitesService.findById(param);
    }
    binder.setItemDataSource(mySite);
    binder.setBuffered(true);

    siteId.setNullRepresentation("");
    binder.bind(siteId, "siteId");

    description.setNullRepresentation("");
    binder.bind(description, "descripcion");

    binder.bind(startDate, "startDate");
    binder.bind(endDate, "endDate");
  }
}
