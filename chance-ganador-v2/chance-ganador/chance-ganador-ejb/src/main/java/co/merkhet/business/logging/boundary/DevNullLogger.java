package co.merkhet.business.logging.boundary;

import javax.enterprise.inject.Alternative;

import org.jboss.logging.Logger;

@Alternative
public class DevNullLogger implements MerkhetLogger {

	@Override
	public Logger getLogger() {
		return null;
	}

	@Override
	public void info(String message) {
		// ignore
	}

	@Override
	public void errorThrowable(Throwable throwable) {
		// ignore
	}

}
