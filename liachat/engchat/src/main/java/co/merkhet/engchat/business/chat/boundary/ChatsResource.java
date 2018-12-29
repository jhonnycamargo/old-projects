package co.merkhet.engchat.business.chat.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chat.rules.RuleEngineProducer;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;
import co.merkhet.engchat.business.chatterbean.boundary.cache.WordCache;
import co.merkhet.engchat.business.chatterbean.boundary.cache.WordLevelsCache;
import co.merkhet.engchat.business.logging.boundary.LogSink;
import opennlp.tools.sentdetect.SentenceDetectorME;

@Stateless
@Path("chats")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
public class ChatsResource {

	@Inject
	LogSink LOG;

	@Inject
	RulesEngine rulesEngine;

	@Inject
	Rules rules;

	@Inject
	AliceBotManager manager;

	@Inject
	@Named("sentenceDetectorME")
	SentenceDetectorME sentenceDetectorME;

	@Inject
	WordCache wordCache;

	@Inject
	WordLevelsCache wordLevelsCache;

	@GET
	@Path("/")
	public Response talk(@QueryParam("request") String request) {
		String response = "";
		if (request != null && !"".equals(request.trim()))
			try {
				response = manager.respond(request);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/conversation/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response talkId(@PathParam(value = "id") String request) {
		String response = "";
		Conversation conversation = null;
		if (request != null && !"".equals(request.trim()))
			try {
				response = manager.respond(request);
				conversation = new Conversation(response, "2017-01-01", true, true);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		return Response.status(200).entity(conversation).build();
	}

	@POST
	@Path("/conversation")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response talkConverstation(Conversation request) {
		LOG.log("Respondiendo: " + request.getMsg());
		Conversation conversation = null;

		// conversation = new Conversation("",
		// DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()), false,
		// false);
		conversation = new Conversation("", request.getDate(), false, false);

		Facts facts = new Facts();
		facts.put(RuleEngineProducer.CONVERSATION_REQUEST, request);
		facts.put(RuleEngineProducer.CONVERSATION_RESPONSE, conversation);
		facts.put(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT, manager);
		facts.put(RuleEngineProducer.SENTENCE_DETECTOR_AS_FACT, sentenceDetectorME);
		facts.put(RuleEngineProducer.WORD_LVLS_CACHE_AS_FACT, wordLevelsCache);
		facts.put(RuleEngineProducer.WORD_CACHE_AS_FACT, wordCache);

		rulesEngine.fire(rules, facts);

		return Response.status(200).entity(conversation).build();
	}
}
