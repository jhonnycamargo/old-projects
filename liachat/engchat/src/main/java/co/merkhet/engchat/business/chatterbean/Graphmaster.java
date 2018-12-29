package co.merkhet.engchat.business.chatterbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.merkhet.engchat.business.chatterbean.aiml.Category;

public class Graphmaster {

	/**
	 * The children of this node.
	 */
	private final Map<String, Graphmaster> children = new HashMap<String, Graphmaster>();

	/**
	 * 
	 */
	private int size = 0;

	/**
	 * 
	 */
	private Graphmaster parent;

	/**
	 * 
	 */
	private Category category;

	/**
	 * The name of a node is the pattern element it represents.
	 */
	private String name;

	/**
	 * 
	 * @param name
	 */
	private Graphmaster(String name) {
		this.name = name;
	}

	/**
	 * Constructs a new root node.
	 */
	public Graphmaster() {
	}

	/**
	 * Constructs a new tree, with this node as the root.
	 */
	public Graphmaster(List<Category> categories) {
		append(categories);
	}

	/**
	 * 
	 * @param category
	 * @param elements
	 * @param index
	 */
	private void append(Category category, String[] elements, int index) {
		Graphmaster child = children.get(elements[index]);
		if (child == null)
			appendChild(child = new Graphmaster(elements[index]));

		int nextIndex = index + 1;
		if (elements.length <= nextIndex)
			child.category = category;
		else
			child.append(category, elements, nextIndex);
	}

	/**
	 * 
	 * @param child
	 */
	private void appendChild(Graphmaster child) {
		children.put(child.name, child);
		child.parent = this;
	}

	/**
	 * <p>
	 * Returns an array with three child nodes, in the following order:
	 * </p>
	 * <ul>
	 * <li>The "_" node;</li>
	 * <li>The node with the given name;</li>
	 * <li>The "*" node.</li>
	 * </ul>
	 * <p>
	 * If any of these nodes can not be found among this node's children, its
	 * position is filled by <code>null</code>.
	 * </p>
	 */
	private Graphmaster[] children(String name) {
		return new Graphmaster[] { children.get("_"), children.get(name), children.get("*") };
	}

	/**
	 * 
	 * @return
	 */
	private boolean isWildcard() {
		return ("_".equals(name) || "*".equals(name));
	}

	/**
	 * 
	 * @param match
	 * @param index
	 * @return
	 */
	private Category match(Match match, int index) {
		if (isWildcard())
			return matchWildcard(match, index);

		if (!name.equals(match.getMatchPath(index)))
			return null;

		int nextIndex = index + 1;
		if (match.getMatchPathLength() <= nextIndex)
			return category;

		return matchChildren(match, nextIndex);
	}

	/**
	 * 
	 * @param match
	 * @param nextIndex
	 * @return
	 */
	private Category matchChildren(Match match, int nextIndex) {
		Graphmaster[] nodes = children(match.getMatchPath(nextIndex));
		for (int i = 0, n = nodes.length; i < n; i++) {
			Category category = (nodes[i] != null ? nodes[i].match(match, nextIndex) : null);

			if (category != null)
				return category;
		}

		return null;
	}

	/**
	 * 
	 * @param match
	 * @param index
	 * @return
	 */
	private Category matchWildcard(Match match, int index) {
		int n = match.getMatchPathLength();
		for (int i = index; i < n; i++) {
			Category category = matchChildren(match, i);
			if (category != null) {
				match.appendWildcard(index, i);
				return category;
			}
		}

		if (category != null)
			match.appendWildcard(index, n);
		return category;
	}

	/**
	 * 
	 * @param categories
	 */
	public void append(List<Category> categories) {
		for (Category category : categories)
			append(category);
	}

	/**
	 * 
	 * @param category
	 */
	public void append(Category category) {
		String matchPath[] = category.getMatchPath();
		append(category, matchPath, 0);
		size++;
	}

	/**
	 * Returns the Catgeory which Pattern matches the given Sentence, or
	 * <code>null</code> if it cannot be found.
	 */
	public Category match(Match match) {
		return matchChildren(match, 0);
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}
}
