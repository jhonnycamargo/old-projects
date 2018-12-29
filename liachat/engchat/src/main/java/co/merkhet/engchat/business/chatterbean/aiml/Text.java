package co.merkhet.engchat.business.chatterbean.aiml;

import co.merkhet.engchat.business.chatterbean.Match;

public class Text extends TemplateElement {

	/**
	 * 
	 */
	private final String value;

	/**
	 * 
	 * @param value
	 */
	public Text(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		String text = obj.toString();
		return (text != null ? text.equals(value) : value == null);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (value == null ? 0 : value.hashCode());
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return value;
	}

	/**
	 * 
	 */
	public String process(Match match) {
		return value;
	}
}
