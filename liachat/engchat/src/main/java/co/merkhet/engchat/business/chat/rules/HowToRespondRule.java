package co.merkhet.engchat.business.chat.rules;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chat.rules.util.StringManipulator;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;
import opennlp.tools.sentdetect.SentenceDetectorME;

@Rule(name = "How to respond rule", description = "Answer with rule engine or not.")
public class HowToRespondRule {

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse) {

		return !conversationResponse.getIsSuccess() && StringUtils.isNotEmpty(conversationRequest.getLastMsg())
				&& conversationRequest.getLastMsg().matches("\\¿.*\\?");
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.SENTENCE_DETECTOR_AS_FACT) SentenceDetectorME sentenceDetectorME,
			@Fact(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT) AliceBotManager aliceBotManager) throws Exception {

		String[] sentences = sentenceDetectorME.sentDetect(conversationRequest.getMsg());
		if (sentences != null && sentences.length > 0) {
			String asking = conversationRequest.getLastMsg().concat(" ").concat(sentences[0]);
			asking = StringManipulator.normaliceQuestion(asking);

			conversationResponse.setMsg(aliceBotManager.respond(asking));
			conversationResponse.setIsSuccess(true);
		}
	}

	@Priority
	public int getPriority() {
		return 2;
	}
}
