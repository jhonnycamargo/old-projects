package co.merkhet.engchat.business.chatterbean.aiml;

import org.xml.sax.Attributes;

public class Template extends TemplateElement {

	/**
	 * 
	 * @param attributes
	 */
	public Template(Attributes attributes) {
		super();
	}

	/**
	 * 
	 * @param children
	 */
	public Template(Object... children) {
		super(children);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		value.append("<template>");
		for (TemplateElement i : children())
			value.append(i.toString());
		value.append("</template>");

		return value.toString();
	}
}
