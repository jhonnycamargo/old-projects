package co.merkhet.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@CDIView(MainView.VIEW_NAME)
@SuppressWarnings("serial")
public class MainView extends HorizontalLayout implements View {

  public static final String VIEW_NAME = "main-view";

  private ComponentContainer content = new CssLayout();

  public MainView() {
    setSizeFull();
    content.setSizeFull();
    addComponent(content);
    setExpandRatio(content, 1.0F);
  }

  @Override
  public void enter(ViewChangeEvent event) {
    // Modificar si es necesario.
  }

  public ComponentContainer getContentContainer() {
    return content;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    MainView other = (MainView) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj))
        .append(this.content, other.content).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode())
        .append(this.content).toHashCode();
  }

}
