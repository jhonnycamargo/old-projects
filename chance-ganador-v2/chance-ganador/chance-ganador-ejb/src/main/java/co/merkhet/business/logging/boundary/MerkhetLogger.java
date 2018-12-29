package co.merkhet.business.logging.boundary;

import org.jboss.logging.Logger;

public interface MerkhetLogger {

	public void info(String message);

	public void errorThrowable(Throwable throwable);

	public Logger getLogger();
}
