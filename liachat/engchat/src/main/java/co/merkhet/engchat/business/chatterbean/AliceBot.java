package co.merkhet.engchat.business.chatterbean;

import co.merkhet.engchat.business.chatterbean.aiml.Category;
import co.merkhet.engchat.business.chatterbean.text.Request;
import co.merkhet.engchat.business.chatterbean.text.Response;
import co.merkhet.engchat.business.chatterbean.text.Sentence;
import co.merkhet.engchat.business.chatterbean.text.Transformations;

public class AliceBot {

	/**
	 * Context information for this bot current conversation.
	 */
	private Context context;

	/**
	 * The Graphmaster maps user requests to AIML categories.
	 */
	private Graphmaster graphmaster;

	/**
	 * Default constructor.
	 */
	public AliceBot() {
	}

	/**
	 * Creates a new AliceBot from a Graphmaster.
	 * 
	 * @param graphmaster
	 *            Graphmaster object.
	 */
	public AliceBot(Graphmaster graphmaster) {
		setContext(new Context());
		setGraphmaster(graphmaster);
	}

	/**
	 * Creates a new AliceBot from a Context and a Graphmaster.
	 * 
	 * @param context
	 *            A Context.
	 * @param graphmaster
	 *            A Graphmaster.
	 */
	public AliceBot(Context context, Graphmaster graphmaster) {
		setContext(context);
		setGraphmaster(graphmaster);
	}

	/**
	 * 
	 * @param sentence
	 * @param that
	 * @param topic
	 * @param response
	 */
	private void respond(Sentence sentence, Sentence that, Sentence topic, Response response) {
		if (sentence.length() > 0) {
			Match match = new Match(this, sentence, that, topic);
			Category category = graphmaster.match(match);
			response.append(category.process(match));
		}
	}

	/**
	 * Responds a request.
	 * 
	 * @param request
	 *            A Request.
	 * 
	 * @return A response to the request.
	 */
	public Response respond(Request request) {
		String original = request.getOriginal();
		if (original == null || "".equals(original.trim()))
			return new Response("");

		Sentence that = context.getThat();
		Sentence topic = context.getTopic();
		transformations().normalization(request);
		context.appendRequest(request);

		Response response = new Response();
		for (Sentence sentence : request.getSentences()) {
				respond(sentence, that, topic, response);
		}
			
		context.appendResponse(response);

		return response;
	}

	/**
	 * Responds a request.
	 * 
	 * @param A
	 *            request string.
	 * 
	 * @return A response to the request string.
	 */
	public String respond(String input) {
		Response response = respond(new Request(input));
		return response.trimOriginal();
	}

	/**
	 * 
	 * @return
	 */
	public Transformations transformations() {
		return context.getTransformations();
	}

	/**
	 * Returns this AliceBot's Context.
	 * 
	 * @return The Context associated to this AliceBot.
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * 
	 * @param context
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * 
	 * @return
	 */
	public Graphmaster getGraphmaster() {
		return graphmaster;
	}

	/**
	 * 
	 * @param graphmaster
	 */
	public void setGraphmaster(Graphmaster graphmaster) {
		this.graphmaster = graphmaster;
	}
}
