package co.merkhet.engchat.business.chatterbean.aiml;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.AliceBot;
import co.merkhet.engchat.business.chatterbean.Context;
import co.merkhet.engchat.business.chatterbean.Match;
import co.merkhet.engchat.business.chatterbean.text.Response;

public class That extends TemplateElement {

	/**
	 * 
	 */
	private static final String[] STRING_ARRAY = {};

	/**
	 * 
	 */
	private int responseIndex = 1, sentenceIndex = 1;

	/**
	 * 
	 * @param attributes
	 */
	public That(Attributes attributes) {
		String value = attributes.getValue(0);
		if (value == null)
			return;

		String[] indexes = value.split(",");
		responseIndex = Integer.parseInt(indexes[0].trim());
		if (indexes.length > 1)
			sentenceIndex = Integer.parseInt(indexes[1].trim());
	}

	/**
	 * 
	 * @param children
	 */
	public That(Object... children) {
		super(children);
	}

	/**
	 * 
	 * @param responseIndex
	 * @param sentenceIndex
	 */
	public That(int responseIndex, int sentenceIndex) {
		this.responseIndex = responseIndex;
		this.sentenceIndex = sentenceIndex;
	}

	/**
	 * 
	 * @return
	 */
	public String[] elements() {
		TemplateElement[] children = getChildren();
		List<String> elements = new LinkedList<String>();
		for (int i = 0, n = children.length; i < n; i++) {
			String text = children[i].toString();
			text = text.trim();
			elements.addAll(Arrays.asList(text.split(" ")));
		}

		return elements.toArray(STRING_ARRAY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof That))
			return false;

		That compared = (That) obj;

		return (responseIndex == compared.responseIndex && sentenceIndex == compared.sentenceIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return responseIndex + sentenceIndex;
	}

	/**
	 * 
	 */
	public String process(Match match) {
		if (match == null)
			return "";

		AliceBot bot = match.getCallback();
		Context context = bot.getContext();
		Response response = context.getResponses(responseIndex - 1);
		return response.getSentences(sentenceIndex - 1).trimOriginal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (children().size() == 0)
			return "<that index=\"" + responseIndex + ", " + sentenceIndex + "\"/>";
		else {
			StringBuilder builder = new StringBuilder("<that>");
			for (TemplateElement element : children())
				builder.append(element);
			builder.append("</that>");

			return builder.toString();
		}
	}
}
