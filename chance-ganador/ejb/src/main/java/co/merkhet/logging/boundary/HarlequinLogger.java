package co.merkhet.logging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface HarlequinLogger {

  void log(Level level, String message, Object[] params);

  Logger getLogger();
}
