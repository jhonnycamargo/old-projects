package co.merkhet.engchat.business.chatterbean;

import java.beans.PropertyChangeEvent;

/**
 * Property change listener for the <code>bot.randomSeed</code> property.
 * Updates the Context's internal {@link java.util.Random} object with the new
 * random seed.
 */
public class ContextRandomSeedChangeListener extends ContextPropertyChangeListener {

	/**
	 * Default class constructor.
	 */
	public ContextRandomSeedChangeListener() {
		super("bot.randomSeed");
	}

	/**
	 * Fired when the bot.randomSeed property changes.
	 */
	public void propertyChange(PropertyChangeEvent event) {
		Context context = (Context) event.getSource();
		Object oldSeed = event.getOldValue();
		Object newSeed = event.getNewValue();

		if (oldSeed == null ? newSeed == null : oldSeed.equals(newSeed))
			return;

		long seed = Long.parseLong(newSeed.toString());
		context.random(seed);
	}
}
