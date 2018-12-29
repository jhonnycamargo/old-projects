package co.merkhet.engchat.business.chat.entity;

import java.io.Serializable;
import java.util.Set;

public class Word implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -261402644811282559L;

	public enum WordType {
		VERB("[Verb]"), NOUN("[Noun]"), ADVERB("[Adverb]"), ADJECTIVE("[Adjective]"), ARTICLE("[Article]");

		private String wordType;

		WordType(String wordType) {
			this.wordType = wordType;
		}

		public String getWordType() {
			return wordType;
		}

		public static WordType fromString(String text) {
			for (WordType b : WordType.values()) {
				if (b.wordType.equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}

	}

	String original;

	WordType type;

	Set<String> translations;

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Set<String> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<String> translations) {
		this.translations = translations;
	}

	public WordType getType() {
		return type;
	}

	public void setType(WordType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((original == null) ? 0 : original.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (original == null) {
			if (other.original != null)
				return false;
		} else if (!original.equals(other.original))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Word [original=" + original + ", translations=" + translations + "]";
	}

}
