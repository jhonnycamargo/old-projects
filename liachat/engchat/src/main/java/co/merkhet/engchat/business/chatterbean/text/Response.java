package co.merkhet.engchat.business.chatterbean.text;

public class Response extends Request {

	/**
	 * 
	 */
	public Response() {
		super();
	}

	/**
	 * 
	 * @param original
	 */
	public Response(String original) {
		super(original);
	}

	/**
	 * 
	 * @param original
	 * @param sentences
	 */
	public Response(String original, Sentence... sentences) {
		super(original, sentences);
	}

	/**
	 * 
	 * @param output
	 */
	public void append(String output) {
		StringBuilder builder = new StringBuilder();
		String original = getOriginal();
		if (original != null) {
			builder.append(original);
			if (builder.charAt(builder.length() - 1) != ' ')
				builder.append(' ');
		}

		builder.append(output);
		setOriginal(builder.toString());
	}
}
