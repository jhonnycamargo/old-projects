package co.merkhet.engchat.business.chatterbean.aiml;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;

public class Topic implements AIMLElement {

	/**
	 * 
	 */
	private List<Category> categories = new LinkedList<Category>();

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 * @param attributes
	 */
	public Topic(Attributes attributes) {
		name(attributes.getValue(0));
	}

	/**
	 * 
	 * @param name
	 * @param children
	 */
	public Topic(String name, Category... children) {
		name(name);
		categories.addAll(Arrays.asList(children));
	}

	/**
	 * 
	 */
	public void appendChild(AIMLElement child) {
		Category category = (Category) child;
		category.setTopic(this);
		categories.add(category);
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
	public List<Category> categories() {
		return categories;
	}

	/**
	 * 
	 * @return
	 */
	public String[] elements() {
		return name.split(" ");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Topic))
			return false;
		else {
			Topic compared = (Topic) obj;
			return (name.equals(compared.name) && categories.equals(compared.categories));
		}
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
	 * @param name
	 */
	private void name(String name) {
		this.name = name.trim();
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
