package co.merkhet.engchat.business.chatterbean.parser;

public interface ReflectionBuilder {

	/**
	 * 
	 * @param chars
	 * @param start
	 * @param length
	 */
	public void characters(char[] chars, int start, int length);

	/**
	 * 
	 */
	public void clear();
}
