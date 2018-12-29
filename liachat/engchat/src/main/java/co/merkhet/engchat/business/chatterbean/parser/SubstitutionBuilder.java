package co.merkhet.engchat.business.chatterbean.parser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import static co.merkhet.engchat.business.chatterbean.util.Escaper.escapeRegex;

public class SubstitutionBuilder implements ReflectionBuilder {

	/**
	 * 
	 */
	private Map<String, Map<String, String>> substitutions;

	/**
	 * 
	 */
	private Map<String, String> section;

	/**
	 * 
	 */
	public SubstitutionBuilder() {
		substitutions = new HashMap<String, Map<String, String>>();
	}

	/**
	 * 
	 * @param substitutions
	 */
	public SubstitutionBuilder(Map<String, Map<String, String>> substitutions) {
		this.substitutions = substitutions;
	}

	/**
	 * 
	 */
	public void characters(char[] chars, int start, int length) {
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startAccentuation(Attributes attributes) {
		section = substitutions.get("accentuation");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startCorrection(Attributes attributes) {
		section = substitutions.get("correction");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startPerson(Attributes attributes) {
		section = substitutions.get("person");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startPerson2(Attributes attributes) {
		section = substitutions.get("person2");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startGender(Attributes attributes) {
		section = substitutions.get("gender");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startProtection(Attributes attributes) {
		section = substitutions.get("protection");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startPunctuation(Attributes attributes) {
		section = substitutions.get("punctuation");
	}

	/**
	 * 
	 * @param attributes
	 */
	public void startSubstitute(Attributes attributes) {
		String find = escapeRegex(attributes.getValue(0));
		String replace = attributes.getValue(1);
		section.put(find, replace);
	}

	/**
	 * 
	 */
	public void clear() {
		substitutions.clear();
		substitutions.put("correction", new LinkedHashMap<String, String>());
		substitutions.put("protection", new LinkedHashMap<String, String>());
		substitutions.put("accentuation", new LinkedHashMap<String, String>());
		substitutions.put("punctuation", new LinkedHashMap<String, String>());
		substitutions.put("person", new LinkedHashMap<String, String>());
		substitutions.put("person2", new LinkedHashMap<String, String>());
		substitutions.put("gender", new LinkedHashMap<String, String>());
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, Map<String, String>> parsed() {
		return new HashMap<String, Map<String, String>>(substitutions);
	}
}
