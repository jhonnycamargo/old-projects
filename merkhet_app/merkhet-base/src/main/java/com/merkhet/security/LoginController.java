package com.merkhet.security;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * We control the authentication process from this bean, so that in the event of
 * a failed authentication we can add an appropriate FacesMessage to the
 * response.
 *
 * @author Shane Bryzak
 *
 */
@Named
public class LoginController implements Serializable {

  private static final long serialVersionUID = -8218975158091698952L;

  @Inject
  private Identity identity;

  public void login() {
    AuthenticationResult result = identity.login();
    if (AuthenticationResult.FAILED.equals(result)) {
      Notification.show(
          "Authentication was unsuccessful.  Please check your username and password "
              + "before trying again.", Type.ERROR_MESSAGE);
    }
  }
}
