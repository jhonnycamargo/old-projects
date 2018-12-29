package co.merkhet.configuration.boundary;

import javax.enterprise.inject.Produces;

public class Configuration {

  @Produces
  public static final boolean debug = true;
}
