package co.merkhet.event;

public abstract class DashboardEvent {

  private DashboardEvent() {
    super();
  }

  public static final class UserLoginRequestedEvent {
    private final String userName, password;

    public UserLoginRequestedEvent(final String userName,
        final String password) {
      this.userName = userName;
      this.password = password;
    }

    public String getUserName() {
      return userName;
    }

    public String getPassword() {
      return password;
    }
  }
}
