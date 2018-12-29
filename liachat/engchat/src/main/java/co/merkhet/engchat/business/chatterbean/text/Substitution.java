package co.merkhet.engchat.business.chatterbean.text;

import java.util.List;

public class Substitution {

	private interface FindReplaceOperation {
		/**
		 * 
		 * @param index
		 * @param input
		 * @return
		 */
		public boolean matches(int index, List<String> input);

		/**
		 * 
		 * @param index
		 * @param output
		 * @return
		 */
		public int replacement(int index, List<String> output);
	}

	private class FindReplaceFragment implements FindReplaceOperation {
		/**
		 * 
		 */
		private final List<String> replacement = tokenizer.tokenize(replace);
		/**
		 * 
		 */
		private final List<String> fragment;

		/**
		 * 
		 * @param fragment
		 */
		FindReplaceFragment(List<String> fragment) {
			this.fragment = fragment;
		}

		/**
		 * 
		 */
		public boolean matches(int index, List<String> input) {
			for (int i = 0, j = index, n = index + fragment.size(); j < n; i++, j++) {
				String token = fragment.get(i);
				String find = input.get(j);
				if (!find.equalsIgnoreCase(token))
					return false;
			}

			return true;
		}

		/**
		 * 
		 */
		public int replacement(int index, List<String> tokens) {
			for (int i = 0, n = fragment.size(); i < n; i++)
				tokens.remove(index);

			tokens.addAll(index, replacement);
			return replacement.size();
		}
	}

	private class FindReplacePrefix implements FindReplaceOperation {
		/**
		 * 
		 */
		private String token;
		/**
		 * 
		 */
		private String TOKEN;

		/**
		 * 
		 */
		public boolean matches(int index, List<String> input) {
			token = input.get(index);
			TOKEN = token.toUpperCase();
			return (TOKEN.indexOf(find) == 0);
		}

		/**
		 * 
		 */
		public int replacement(int index, List<String> tokens) {
			int beginIndex = find.length();
			List<String> replacement = tokenizer.tokenize(replace + token.substring(beginIndex));
			tokens.remove(index);
			tokens.addAll(index, replacement);
			return replacement.size();
		}
	}

	private class FindReplaceSuffix implements FindReplaceOperation {
		/**
		 * 
		 */
		private String token;
		/**
		 * 
		 */
		private String TOKEN;

		/**
		 * 
		 */
		public boolean matches(int index, List<String> input) {
			token = input.get(index);
			TOKEN = token.toUpperCase();
			return TOKEN.endsWith(find);
		}

		/**
		 * 
		 */
		public int replacement(int index, List<String> tokens) {
			int endIndex = TOKEN.lastIndexOf(find);
			List<String> replacement = tokenizer.tokenize(token.substring(0, endIndex) + replace);
			tokens.remove(index);
			tokens.addAll(index, replacement);
			return replacement.size();
		}
	}

	private class FindReplaceWord implements FindReplaceOperation {
		/**
		 * 
		 */
		private final List<String> replacement = tokenizer.tokenize(replace);

		/**
		 * 
		 */
		public boolean matches(int index, List<String> input) {
			String token = input.get(index);
			return find.equalsIgnoreCase(token);
		}

		/**
		 * 
		 */
		public int replacement(int index, List<String> tokens) {
			tokens.remove(index);
			tokens.addAll(index, replacement);
			return replacement.size();
		}
	}

	/**
	 * 
	 */
	private FindReplaceOperation operation;

	/**
	 * 
	 */
	private String find;
	/**
	 * 
	 */
	private String replace;
	/**
	 * 
	 */
	private Tokenizer tokenizer;

	/**
	 * 
	 */
	public Substitution() {
	}

	/**
	 * 
	 * @param find
	 * @param replace
	 * @param tokenizer
	 */
	public Substitution(String find, String replace, Tokenizer tokenizer) {
		setFind(find);
		setReplace(replace);
		setTokenizer(tokenizer);
	}

	/**
	 * 
	 */
	private void afterSetProperty() {
		if (find == null || tokenizer == null || replace == null)
			return;

		List<String> tokens = tokenizer.tokenize(find);
		if (tokens.size() > 1)
			operation = new FindReplaceFragment(tokens);
		else if (find.charAt(0) != ' ')
			operation = new FindReplaceSuffix();
		else if (find.charAt(find.length() - 1) != ' ')
			operation = new FindReplacePrefix();
		else
			operation = new FindReplaceWord();

		find = find.toUpperCase().trim();
	}

	/**
	 * 
	 * @param input
	 */
	public void substitute(List<String> input) {
		if (operation == null)
			throw new NullPointerException("Substitution state incomplete\n" + "Find: " + find + '\n' + "Replace: "
					+ replace + '\n' + "Tokenizer: " + tokenizer);

		for (int i = 0; i < input.size();) /*
											 * The input size can change due to
											 * the successive substitutions.
											 */
		{
			if (operation.matches(i, input))
				i += operation.replacement(i, input);
			else
				i++;
		}
	}

	/**
	 * 
	 * @param offset
	 * @param input
	 * @return
	 */
	public int substitute(int offset, List<String> input) {
		if (operation == null)
			throw new NullPointerException("Substitution state incomplete\n" + "Find: " + find + '\n' + "Replace: "
					+ replace + '\n' + "Tokenizer: " + tokenizer);

		if (operation.matches(offset, input))
			offset += operation.replacement(offset, input);

		return offset;
	}

	/**
	 * 
	 * @return
	 */
	public String getFind() {
		return find;
	}

	/**
	 * 
	 * @param find
	 */
	public void setFind(String find) {
		this.find = find;
		afterSetProperty();
	}

	/**
	 * 
	 * @return
	 */
	public String getReplace() {
		return replace;
	}

	/**
	 * 
	 * @param replace
	 */
	public void setReplace(String replace) {
		this.replace = replace;
		afterSetProperty();
	}

	/**
	 * 
	 * @return
	 */
	public Tokenizer getTokenizer() {
		return tokenizer;
	}

	/**
	 * 
	 * @param tokenizer
	 */
	public void setTokenizer(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
		afterSetProperty();
	}
}
