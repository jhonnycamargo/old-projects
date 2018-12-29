package co.merkhet.logging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;

@Alternative
public class DelegatingLogger implements HarlequinLogger {

  private Logger logger;

  public DelegatingLogger(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void log(Level level, String message, Object[] params) {
    this.logger.log(level, message, params);
  }

  @Override
  public Logger getLogger() {
    return logger;
  }

}
