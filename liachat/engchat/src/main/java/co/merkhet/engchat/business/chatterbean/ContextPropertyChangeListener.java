package co.merkhet.engchat.business.chatterbean;

import java.beans.PropertyChangeListener;

public abstract class ContextPropertyChangeListener implements PropertyChangeListener {

	/**
	 * Name of the property whose changes to listen for.
	 */
	private String name;

	/**
	 * Creates a new change listener for the named Context Property.
	 * 
	 * @param name
	 *            The name of the property whose changes this object listens
	 *            for.
	 */
	public ContextPropertyChangeListener(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the property whose changes this object listens for.
	 * 
	 * @return The name of the property whose changes this object listens for.
	 */
	public String name() {
		return name;
	}
}
