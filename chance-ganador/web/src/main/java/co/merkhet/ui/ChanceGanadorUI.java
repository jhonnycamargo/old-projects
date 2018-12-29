package co.merkhet.ui;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;

import com.google.common.eventbus.Subscribe;
import co.merkhet.event.DashboardEvent.UserLoginRequestedEvent;
import co.merkhet.event.DashboardEventBus;
import co.merkhet.security.LoginController;
import co.merkhet.view.LoginView;
import co.merkhet.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@CDIUI
@Theme("valo")
@Title("")
public class ChanceGanadorUI extends UI {
  private static final long serialVersionUID = -1017880090135342077L;

  private final transient DashboardEventBus dashboardEventbus = new DashboardEventBus();

  @Inject
  CDIViewProvider provider;

  @Inject
  Identity identity;
  @Inject
  transient DefaultLoginCredentials loginCredentials;
  @Inject
  transient LoginController loginController;

  @Override
  protected void init(final VaadinRequest request) {
    DashboardEventBus.register(this);
    Responsive.makeResponsive(this);

    updateContent();
  }

  private void updateContent() {
    if (identity.isLoggedIn()) {
      MainView mainView = new MainView();
      setContent(mainView);
      removeStyleName("loginview");
      Navigator navigator = new ChanceGanadorNavigator(
          mainView.getContentContainer());
      navigator.addProvider(provider);
      setNavigator(navigator);
      getNavigator().navigateTo(MainView.VIEW_NAME);
    } else {
      setContent(new LoginView());
      addStyleName("loginview");
    }
  }

  @Subscribe
  public void userLoginRequested(final UserLoginRequestedEvent event) {
    loginCredentials.setUserId(event.getUserName());
    loginCredentials.setPassword(event.getPassword());
    loginController.login();

    updateContent();
  }

  public static DashboardEventBus getDashboardEventbus() {
    return ((ChanceGanadorUI) getCurrent()).dashboardEventbus;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((identity == null) ? 0 : identity.hashCode());
    return result;
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
    ChanceGanadorUI other = (ChanceGanadorUI) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj))
        .append(this.identity, other.identity).isEquals();
  }

}
