package co.merkhet.engchat.business.chatterbean.aiml;

import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.AliceBot;
import co.merkhet.engchat.business.chatterbean.Match;
import co.merkhet.engchat.business.chatterbean.text.Transformations;

public class Gender extends TemplateElement {

	public Gender(Attributes attributes) {
	}

	public Gender(Object... children) {
		super(children);
	}

	public String process(Match match) {
		String input = super.process(match);
		AliceBot bot = match.getCallback();
		Transformations transformations = bot.transformations();
		return transformations.gender(input);
	}
}
