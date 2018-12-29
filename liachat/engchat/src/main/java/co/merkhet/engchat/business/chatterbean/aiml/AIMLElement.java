package co.merkhet.engchat.business.chatterbean.aiml;

import java.util.List;

public interface AIMLElement {

	/**
	 * 
	 * @param child
	 */
	public void appendChild(AIMLElement child);

	/**
	 * 
	 * @param children
	 */
	public void appendChildren(List<AIMLElement> children);
}
