package co.merkhet.engchat.business.chatterbean;

/**
 * Basic exception class for exceptions thrown from ChatterBean's main class.
 */
public class ChatterBeanException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5075152235370093923L;

	public ChatterBeanException(String message) {
		super(message);
	}

	public ChatterBeanException(Exception cause) {
		super(cause);
	}
}
