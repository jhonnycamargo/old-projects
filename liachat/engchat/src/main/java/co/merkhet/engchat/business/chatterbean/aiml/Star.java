package co.merkhet.engchat.business.chatterbean.aiml;

import static co.merkhet.engchat.business.chatterbean.Match.Section.PATTERN;

import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.Match;

public class Star extends TemplateElement {

	private int index;

	public Star(Attributes attributes) {
		String value = attributes.getValue(0);
		index = (value != null ? Integer.parseInt(value) : 1);
	}

	public Star(int index) {
		this.index = index;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Star))
			return false;
		else {
			Star star = (Star) obj;
			return (index == star.index);
		}
	}

	public int hashCode() {
		return index;
	}

	public String toString() {
		return "<star index=\"" + index + "\"/>";
	}

	public String process(Match match) {
		String wildcard = match.wildcard(PATTERN, index);
		return (wildcard != null ? wildcard.trim() : "");
	}
}
