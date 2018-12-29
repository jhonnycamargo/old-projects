package co.merkhet.engchat.business.chatterbean.aiml;

import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.AliceBot;
import co.merkhet.engchat.business.chatterbean.Match;

public class Srai extends TemplateElement {

	public Srai(Attributes attributes) {
	}

	public Srai(Object... children) {
		super(children);
	}

	public Srai(int index) {
		super(new Star(index));
	}

	public String process(Match match) {
		String request = super.process(match);

		try {
			AliceBot bot = (match != null ? match.getCallback() : null);
			return (bot != null ? bot.respond(request) : "");
		} catch (Exception e) {
			throw new RuntimeException("While trying to respond \"" + request + "\"", e);
		}
	}

	public String toString() {
		return "<srai>" + super.toString() + "</srai>";
	}
}
