package co.merkhet.business.logging.boundary;

import javax.enterprise.inject.Alternative;

import org.jboss.logging.Logger;

@Alternative
public class DelegatingLogger implements MerkhetLogger {

	private Logger logger;

	public DelegatingLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public void info(String message) {
		this.logger.info(message);
	}

	@Override
	public void errorThrowable(Throwable throwable) {
		this.logger.error(throwable.getMessage(), throwable);
	}

}
