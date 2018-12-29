package co.merkhet.engchat.business.chatterbean.text;

import java.util.Arrays;

public class Request {

	/**
	 * 
	 */
	private Sentence[] sentences;
	private String original;

	/**
	 * 
	 */
	public Request() {
	}

	/**
	 * 
	 * @param original
	 */
	public Request(String original) {
		this.original = original;
	}

	/**
	 * 
	 * @param original
	 * @param sentences
	 */
	public Request(String original, Sentence... sentences) {
		this.original = original;
		this.sentences = sentences;
	}

	/**
	 * 
	 * @return
	 */
	public boolean empty() {
		return (sentences == null || sentences.length == 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Request))
			return false;

		Request compared = (Request) obj;
		return original.equals(compared.original) && Arrays.equals(sentences, compared.sentences);
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Sentence lastSentence(int index) {
		return sentences[sentences.length - (1 + index)];
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return original;
	}

	/**
	 * 
	 * @return
	 */
	public String trimOriginal() {
		return original.trim();
	}

	/**
	 * 
	 * @return
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * 
	 * @param original
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * 
	 * @return
	 */
	public Sentence[] getSentences() {
		return sentences;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Sentence getSentences(int index) {
		return sentences[index];
	}

	/**
	 * 
	 * @param sentences
	 */
	public void setSentences(Sentence[] sentences) {
		this.sentences = sentences;
	}
}