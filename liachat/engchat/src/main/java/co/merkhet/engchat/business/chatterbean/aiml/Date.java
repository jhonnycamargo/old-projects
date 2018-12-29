package co.merkhet.engchat.business.chatterbean.aiml;

import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.Match;

public class Date extends TemplateElement {

	private final SimpleDateFormat format = new SimpleDateFormat();

	public Date() {
	}

	public Date(Attributes attributes) {
	}

	public int hashCode() {
		return 13;
	}

	public String process(Match match) {
		try {
			format.applyPattern((String) match.getCallback().getContext().property("predicate.dateFormat"));
			return format.format(new java.util.Date());
		} catch (NullPointerException e) {
			return "";
		}
	}
}
