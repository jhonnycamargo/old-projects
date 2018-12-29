package co.merkhet.engchat.business.chatterbean.aiml;

import java.lang.System;
import java.util.List;
import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.Match;

public class Category implements AIMLElement {

	/**
	 * 
	 */
	private Pattern pattern;

	/**
	 * 
	 */
	private Template template;

	/**
	 * 
	 */
	private That that;

	/**
	 * 
	 */
	private Topic topic;

	/**
	 * 
	 */
	public Category() {
	}

	/**
	 * 
	 * @param pattern
	 * @param children
	 */
	public Category(String pattern, Object... children) {
		this(new Pattern(pattern), new That("*"), new Topic("*"), new Template(children));
	}

	/**
	 * 
	 * @param pattern
	 * @param template
	 */
	public Category(Pattern pattern, Template template) {
		this(pattern, new That("*"), new Topic("*"), template);
	}

	/**
	 * 
	 * @param pattern
	 * @param that
	 * @param template
	 */
	public Category(Pattern pattern, That that, Template template) {
		this(pattern, that, new Topic("*"), template);
	}

	/**
	 * 
	 * @param pattern
	 * @param that
	 * @param topic
	 * @param template
	 */
	public Category(Pattern pattern, That that, Topic topic, Template template) {
		this.pattern = pattern;
		this.template = template;
		this.that = that;
		this.topic = topic;
	}

	/**
	 * 
	 * @param attributes
	 */
	public Category(Attributes attributes) {
	}

	/**
	 * 
	 */
	public void appendChild(AIMLElement child) {
		if (child instanceof Pattern)
			pattern = (Pattern) child;
		else if (child instanceof That)
			that = (That) child;
		else if (child instanceof Template)
			template = (Template) child;
		else
			throw new ClassCastException("Invalid element of type " + child.getClass().getName() + ": (" + child + ")");
	}

	/**
	 * 
	 */
	public void appendChildren(List<AIMLElement> children) {
		for (AIMLElement child : children)
			appendChild(child);

		if (that == null)
			that = new That("*");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Category))
			return false;
		Category compared = (Category) obj;

		return (pattern.equals(compared.pattern) && template.equals(compared.template) && that.equals(compared.that));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "[" + pattern.toString() + "][" + that.toString() + "][" + template.toString() + "]";
	}

	/**
	 * 
	 * @param match
	 * @return
	 */
	public String process(Match match) {
		return template.process(match);
	}

	/**
	 * 
	 * @return
	 */
	public String[] getMatchPath() {
		String[] pattPath = pattern.getElements();
		String[] thatPath = that.elements();
		String[] topicPath = topic.elements();
		int m = pattPath.length;
		int n = thatPath.length;
		int o = topicPath.length;
		String[] matchPath = new String[m + 1 + n + 1 + o];

		matchPath[m] = "<THAT>";
		matchPath[m + 1 + n] = "<TOPIC>";
		System.arraycopy(pattPath, 0, matchPath, 0, m);
		System.arraycopy(thatPath, 0, matchPath, m + 1, n);
		System.arraycopy(topicPath, 0, matchPath, m + 1 + n + 1, o);
		return matchPath;
	}

	/**
	 * 
	 * @return
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * 
	 * @param pattern
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * 
	 * @return
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * 
	 * @param template
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

	/**
	 * 
	 * @return
	 */
	public That getThat() {
		return that;
	}

	/**
	 * 
	 * @param that
	 */
	public void setThat(That that) {
		this.that = that;
	}

	/**
	 * 
	 * @return
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * 
	 * @param topic
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
