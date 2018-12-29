package co.merkhet.business.logging.boundary;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.jboss.logging.Logger;

public class LoggerProducer {
	@Inject
	private boolean debug;

	@Produces
	public MerkhetLogger getLogger(InjectionPoint ip) {
		if (debug) {
			Class<?> aClass = ip.getMember().getDeclaringClass();
			Logger logger = Logger.getLogger(aClass.getName());
			return new DelegatingLogger(logger);
		} else {
			return new DevNullLogger();
		}
	}
}
