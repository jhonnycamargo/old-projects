package co.merkhet.engchat.business.chatterbean;

import java.beans.PropertyChangeEvent;
import co.merkhet.engchat.business.chatterbean.text.Sentence;
import co.merkhet.engchat.business.chatterbean.text.Transformations;

import static co.merkhet.engchat.business.chatterbean.text.Sentence.ASTERISK;

/**
 * Property change listener for the <code>predicate.topic</code> property.
 * Updates the Context with the new Topic value.
 */
public class ContextTopicChangeListener extends ContextPropertyChangeListener {

	/**
	 * Default class constructor.
	 */
	public ContextTopicChangeListener() {
		super("predicate.topic");
	}

	/**
	 * Fired when the predicate.topic property changes.
	 */
	public void propertyChange(PropertyChangeEvent event) {
		Object oldTopic = event.getOldValue();
		Object newTopic = event.getNewValue();
		Context context = (Context) event.getSource();
		Transformations transformations = context.getTransformations();

		if (oldTopic == null ? newTopic == null : oldTopic.equals(newTopic))
			return;

		String input = newTopic.toString().trim();
		if ("".equals(input) || "*".equals(input))
			context.setTopic(ASTERISK);
		else {
			Sentence topic = new Sentence(input);
			transformations.normalization(topic);
			context.setTopic(topic);
		}
	}
}
