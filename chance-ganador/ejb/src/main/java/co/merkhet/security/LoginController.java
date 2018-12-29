package co.merkhet.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

@Named
public class LoginController {

  @Inject
  private Identity identity;

  public void login() {
    AuthenticationResult result = identity.login();
    if (AuthenticationResult.FAILED.equals(result)) {
    }
  }
}
