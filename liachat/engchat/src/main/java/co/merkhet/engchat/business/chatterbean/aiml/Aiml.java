package co.merkhet.engchat.business.chatterbean.aiml;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;

public class Aiml implements AIMLElement {

	/**
	 * 
	 */
	private final Topic defaultTopic = new Topic("*");

	/**
	 * 
	 */
	private final List<Topic> topics = new LinkedList<Topic>(Arrays.asList(new Topic[] { defaultTopic }));

	/**
	 * 
	 */
	private final List<Category> categories = new LinkedList<Category>();

	/**
	 * 
	 */
	private String version;

	/**
	 * 
	 * @param attributes
	 */
	public Aiml(Attributes attributes) {
		version = attributes.getValue(0);
	}

	/**
	 * 
	 * @param categories
	 */
	public Aiml(Category... categories) {
		this.categories.addAll(Arrays.asList(categories));
	}

	/**
	 * 
	 */
	public void appendChild(AIMLElement child) {
		if (child instanceof Category) {
			Category category = (Category) child;
			category.setTopic(defaultTopic);
			defaultTopic.appendChild(category);
			categories.add(category);
		} else {
			Topic topic = (Topic) child;
			topics.add(topic);
			categories.addAll(topic.categories());
		}
	}

	/**
	 * 
	 */
	public void appendChildren(List<AIMLElement> children) {
		for (AIMLElement child : children)
			appendChild(child);
	}

	/**
	 * 
	 * @return
	 */
	public List<Category> children() {
		return categories;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Aiml))
			return false;
		else
			return categories.equals(((Aiml) obj).categories);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Category i : categories) {
			result.append(i);
			result.append('\n');
		}

		return result.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}
}
