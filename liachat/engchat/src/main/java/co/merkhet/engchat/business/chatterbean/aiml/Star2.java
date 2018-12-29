package co.merkhet.engchat.business.chatterbean.aiml;

import static co.merkhet.engchat.business.chatterbean.Match.Section.PATTERN;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import co.merkhet.engchat.business.chatterbean.Match;

public class Star2 extends TemplateElement {

	private static final Map<String, String> spanishBasicWords;
	
	static {
		Map<String, String> aMap = new HashMap<>();
		aMap.put("padre", "father");
		aMap.put("coche", "car");
		spanishBasicWords = Collections.unmodifiableMap(aMap);
	}
	
	private int index;

	public Star2(Attributes attributes) {
		String value = attributes.getValue(0);
		index = (value != null ? Integer.parseInt(value) : 1);
	}

	public Star2(int index) {
		this.index = index;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Star2))
			return false;
		else {
			Star2 star = (Star2) obj;
			return (index == star.index);
		}
	}

	public int hashCode() {
		return index;
	}

	public String toString() {
		return "<star2 index=\"" + index + "\"/>";
	}

	public String process(Match match) {
		String wildcard = match.wildcard(PATTERN, index);
		String translate = spanishBasicWords.get(wildcard != null ? wildcard.trim().toLowerCase() : "");
		
		return (translate != null ? translate : "");
	}
}
