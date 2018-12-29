package co.merkhet.engchat.business.chatterbean.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.merkhet.engchat.business.chatterbean.config.TokenizerConfig;

import static co.merkhet.engchat.business.chatterbean.util.Escaper.escapeRegex;

public class Tokenizer {

	/**
	 * 
	 */
	private Boolean ignoreWhitespace;

	/**
	 * 
	 */
	private String[] splitters;

	/**
	 * 
	 */
	private Pattern pattern;

	/**
	 * 
	 */
	public Tokenizer() {
	}

	/**
	 * 
	 * @param splitters
	 */
	public Tokenizer(String... splitters) {
		setIgnoreWhitespace(true);
		setSplitters(splitters);
	}

	/**
	 * 
	 * @param config
	 */
	public Tokenizer(TokenizerConfig config) {
		this(config.splitters());
	}

	/**
	 * 
	 */
	private void afterSetProperty() {
		if (splitters == null || ignoreWhitespace == null)
			return;

		String expression = "";
		for (int i = 0, n = splitters.length;;) {
			expression += escapeRegex(splitters[i]);
			if (++i >= n)
				break;
			expression += '|';
		}

		if (ignoreWhitespace)
			expression = "(" + expression + ")\\s*|\\s+";
		else
			expression = "(" + expression + "|\\s+)";

		pattern = Pattern.compile(expression);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public List<String> tokenize(String input) {
		List<String> output = new ArrayList<String>();
		Matcher matcher = pattern.matcher(input);
		int beginIndex = 0;

		while (matcher.find()) {
			int endIndex = matcher.start();
			String token = input.substring(beginIndex, endIndex);
			if (token.length() > 0)
				output.add(token);

			String symbol = matcher.group(1);
			if (symbol != null)
				output.add(symbol);

			String breaker = matcher.group();
			beginIndex = endIndex + breaker.length();
		}

		if (beginIndex < input.length()) {
			String token = input.substring(beginIndex);
			output.add(token);
		}

		return output;
	}

	/**
	 * 
	 * @param tokens
	 * @return
	 */
	public String toString(List<String> tokens) {
		String output = "";
		int i = 0, n = tokens.size();
		String next = tokens.get(0);

		for (;;) {
			output += next;
			if (++i >= n)
				break;
			next = tokens.get(i);
			Matcher matcher = pattern.matcher(next);
			if (!matcher.matches())
				output += ' ';
		}

		return output;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIgnoreWhitespace() {
		return ignoreWhitespace;
	}

	/**
	 * 
	 * @param ignore
	 */
	public void setIgnoreWhitespace(boolean ignore) {
		ignoreWhitespace = ignore;
		afterSetProperty();
	}

	/**
	 * 
	 * @return
	 */
	public String[] getSplitters() {
		return splitters;
	}

	/**
	 * 
	 * @param splitters
	 */
	public void setSplitters(String[] splitters) {
		this.splitters = splitters;
		afterSetProperty();
	}
}
