package co.merkhet.logging.boundary;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

public class LoggerProducer {

  @Inject
  private boolean debug;

  @Produces
  public HarlequinLogger getLogger(InjectionPoint ip) {
    if (debug) {
      Class<?> aClass = ip.getMember().getDeclaringClass();
      Logger logger = Logger.getLogger(aClass.getName());

      return new DelegatingLogger(logger);
    } else {
      return new DevNullLogger();
    }
  }
}
