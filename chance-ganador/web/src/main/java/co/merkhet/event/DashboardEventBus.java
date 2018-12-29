package co.merkhet.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import co.merkhet.ui.ChanceGanadorUI;

public class DashboardEventBus implements SubscriberExceptionHandler {

  private final EventBus eventBus = new EventBus(this);

  public static void post(final Object event) {
    ChanceGanadorUI.getDashboardEventbus().eventBus.post(event);
  }

  public static void register(final Object object) {
    ChanceGanadorUI.getDashboardEventbus().eventBus.register(object);
  }

  public static void unregister(final Object object) {
    ChanceGanadorUI.getDashboardEventbus().eventBus.unregister(object);
  }

  @Override
  public final void handleException(final Throwable exception,
      final SubscriberExceptionContext context) {
    if(exception instanceof RuntimeException) { // personalizar
      Object obj = context.getEvent();// castear
      eventBus.post(obj);
    }
  }
}
