package co.merkhet.engchat.business.chat.rules;

import java.text.Normalizer;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chat.rules.util.BasicTranslatedWordMaps;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;

@Rule(name = "Do not translate Other Basic Word rule", description = "No Translate other world from Spanish to English when user wants it.")
public class DoNotTranslateOtherBasicWordRule {

	private static final String QUIERES_OTRA_PALABRA = "Quieres otra palabra";

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest) {
		String question = conversationRequest.getLastMsg();
		if (StringUtils.isEmpty(question)) {
			return false;
		}
		String userAnswer = conversationRequest.getMsg();

		question = Normalizer.normalize(question, Normalizer.Form.NFD);

		if (question.contains(QUIERES_OTRA_PALABRA)) {
			return BasicTranslatedWordMaps.stringContainsItemFromList(userAnswer,
					BasicTranslatedWordMaps.negativeAnswers());
		}

		return false;
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT) AliceBotManager aliceBotManager) throws Exception {

		String asking = conversationRequest.getLastMsg().replaceAll("[¿?¡!]", "").concat(" ").concat("no");
		conversationResponse.setMsg(aliceBotManager.respond(asking));
		conversationResponse.setIsSuccess(true);
	}

	@Priority
	public int getPriority() {
		return 4;
	}
}
