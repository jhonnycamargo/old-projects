package co.merkhet.engchat.business.chatterbean.aiml;

import org.xml.sax.Attributes;

public class Sr extends Srai {

	public Sr(Attributes attributes) {
		super(attributes);
		setChildren(new TemplateElement[] { new Star(attributes) });
	}
}
