package co.merkhet.engchat.business.chatterbean.aiml;

import java.util.LinkedList;
import java.util.List;

public class AIMLStack {

	/**
	 * 
	 */
	private final List<Object> stack = new LinkedList<Object>();

	/**
	 * 
	 * @return
	 */
	public Object peek() {
		return stack.get(0);
	}

	/**
	 * 
	 * @return
	 */
	public Object pop() {
		return (stack.size() > 0 ? stack.remove(0) : null);
	}

	/**
	 * 
	 * @param element
	 */
	public void push(Object element) {
		stack.add(0, element);
	}
}
