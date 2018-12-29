package co.merkhet.logging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;

@Alternative
public class DevNullLogger implements HarlequinLogger {

  @Override
  public void log(Level level, String message, Object[] params) {
    // ignorar 
  }

  @Override
  public Logger getLogger() {
    return null;
  }

}
