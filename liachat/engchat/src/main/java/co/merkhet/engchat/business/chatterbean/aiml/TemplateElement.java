package co.merkhet.engchat.business.chatterbean.aiml;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import co.merkhet.engchat.business.chatterbean.Match;

public class TemplateElement implements AIMLElement {

	private static final TemplateElement[] TEMPLATE_ELEMENT_ARRAY = {};

	/**
	 * 
	 */
	private final List<TemplateElement> children = new LinkedList<TemplateElement>();

	/**
	 * 
	 * @param elements
	 */
	public TemplateElement(Object... elements) {
		for (Object child : elements) {
			if (child instanceof AIMLElement)
				children.add((TemplateElement) child);
			else
				children.add(new Text(child.toString()));
		}
	}

	/**
	 * 
	 */
	public void appendChild(AIMLElement element) {
		children.add((TemplateElement) element);
	}

	/**
	 * 
	 */
	public void appendChildren(List<AIMLElement> elements) {
		for (AIMLElement element : elements)
			children.add((TemplateElement) element);
	}

	/**
	 * 
	 * @return
	 */
	public List<TemplateElement> children() {
		return children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof TemplateElement))
			return false;

		TemplateElement that = (TemplateElement) object;
		return children.equals(that.children);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return children.hashCode();
	}

	/**
	 * 
	 * @param match
	 * @return
	 */
	public String process(Match match) {
		StringBuilder value = new StringBuilder();
		for (TemplateElement i : children)
			value.append(i.process(match));

		return value.toString();
	}

	/**
	 * 
	 * @return
	 */
	public TemplateElement[] getChildren() {
		return children.toArray(TEMPLATE_ELEMENT_ARRAY);
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public TemplateElement getChildren(int index) {
		return children.get(index);
	}

	/**
	 * 
	 * @param elements
	 */
	public void setChildren(TemplateElement[] elements) {
		children.clear();
		children.addAll(Arrays.asList(elements));
	}
}
