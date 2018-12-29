package co.merkhet.engchat.business.chatterbean;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import co.merkhet.engchat.business.chatterbean.script.BeanshellInterpreter;
import co.merkhet.engchat.business.chatterbean.text.Request;
import co.merkhet.engchat.business.chatterbean.text.Response;
import co.merkhet.engchat.business.chatterbean.text.Sentence;
import co.merkhet.engchat.business.chatterbean.text.Transformations;

import static co.merkhet.engchat.business.chatterbean.text.Sentence.ASTERISK;

/**
 * A conversational context. This class stores information such as the history
 * of a conversation and predicate values, which the Alice Bot can refer to
 * while responding user requests.
 * 
 * @author merkhet
 *
 */
public class Context {

	/**
	 * Map of context properties.
	 */
	private final Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * Map of property change listeners.
	 */
	private final Map<String, ContextPropertyChangeListener> listeners = new HashMap<String, ContextPropertyChangeListener>();

	/**
	 * 
	 */
	private final List<Request> requests = new LinkedList<Request>();
	/**
	 * 
	 */
	private final List<Response> responses = new LinkedList<Response>();

	/**
	 * 
	 */
	private final Random random = new Random();

	/**
	 * 
	 */
	private OutputStream output;

	/**
	 * 
	 */
	private Sentence that;

	/**
	 * 
	 */
	private Sentence topic;

	/**
	 * Set of normalizing transformations applied to unstructured text.
	 */
	private Transformations transformations;

	/**
	 * Default Constructor.
	 */
	public Context() {
		property("beanshell.interpreter", new BeanshellInterpreter());

		addContextPropertyChangeListener(new ContextRandomSeedChangeListener());
		addContextPropertyChangeListener(new ContextTopicChangeListener());
	}

	/**
	 * Creates a new Context object with the given set of normalizing
	 * transformations.
	 * 
	 * @param transformations
	 *            A set of normalizing transformations.
	 */
	public Context(Transformations transformations) {
		this();
		this.transformations = transformations;
	}

	/**
	 * Adds a property change listener to this context object.
	 * 
	 * @param listener
	 *            A property change listener. If there already is a listener
	 *            with the same name of this one, the old listener will be
	 *            discarded.
	 */
	public void addContextPropertyChangeListener(ContextPropertyChangeListener listener) {
		listeners.put(listener.name(), listener);
	}

	/**
	 * Removes a property change listener to this context object. Although
	 * listeners are stored by name, for the removing to actually occur it is
	 * not enough to simply pass a listener with the same name; the same
	 * <i>object</i> must be passed, otherwise this method does nothing.
	 * 
	 * @param listener
	 *            A property change listener.
	 */
	public void removeContextPropertyChangeListener(ContextPropertyChangeListener listener) {
		ContextPropertyChangeListener listening = listeners.get(listener.name());
		if (listening == listener)
			listeners.remove(listener.name());
	}

	/**
	 * 
	 * @param request
	 */
	public void appendRequest(Request request) {
		requests.add(0, request);
	}

	/**
	 * 
	 * @param response
	 */
	public void appendResponse(Response response) {
		transformations.normalization(response);
		responses.add(0, response);

		that = response.lastSentence(0);
		transformations.normalization(that);
	}

	/**
	 * 
	 * @param output
	 * @throws IOException
	 */
	public void print(String output) throws IOException {
		outputStream().write(output.getBytes());
		outputStream().write('\n');
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Object property(String name) {
		return properties.get(name);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void property(String name, Object value) {
		ContextPropertyChangeListener listener = listeners.get(name);
		if (listener != null) {
			Object oldValue = properties.get(name);
			PropertyChangeEvent event = new PropertyChangeEvent(this, name, oldValue, value);
			listener.propertyChange(event);
		}

		properties.put(name, value);
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public OutputStream outputStream() throws IOException {
		if (output == null) {
			String path = (String) property("bot.output");
			File file = new File(path);
			if (file.isDirectory())
				path = file.getPath() + "/gossip-" + id() + ".txt";

			outputStream(new FileOutputStream(path));
		}

		return output;
	}

	/**
	 * 
	 * @param output
	 */
	public void outputStream(OutputStream output) {
		this.output = output;
	}

	/**
	 * 
	 * @return
	 */
	public String id() {
		String id = (String) property("bot.id");
		if ("".equals(id))
			return Integer.toString(hashCode());
		else
			return id;
	}

	/**
	 * 
	 * @return
	 */
	public Random random() {
		return random;
	}

	/**
	 * Sets the value of the seed used by the internal random number generator.
	 * 
	 * @param seed
	 *            The seed used by the internal random number generator.
	 */
	public void random(long seed) {
		random.setSeed(seed);
	}

	/**
	 * 
	 * @return
	 */
	public Sentence getThat() {
		if (that == null)
			that = ASTERISK;
		return that;
	}

	/**
	 * 
	 * @return
	 */
	public Sentence getTopic() {
		if (topic == null)
			topic = ASTERISK;
		return topic;
	}

	/**
	 * 
	 * @param topic
	 */
	public void setTopic(Sentence topic) {
		if (topic == null)
			this.topic = ASTERISK;
		this.topic = topic;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Request getRequests(int index) {
		return requests.get(index);
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Response getResponses(int index) {
		return responses.get(index);
	}

	/**
	 * 
	 * @return
	 */
	public Transformations getTransformations() {
		return transformations;
	}

	/**
	 * 
	 * @param transformations
	 */
	public void setTransformations(Transformations transformations) {
		this.transformations = transformations;
	}
}
