package co.merkhet.engchat.business.chatterbean.aiml;

public class AIMLParserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3423993226883788474L;

	/**
	 * 
	 * @param e
	 */
	public AIMLParserException(Exception e) {
		super(e);
	}

	/**
	 * 
	 * @param message
	 */
	public AIMLParserException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public AIMLParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
