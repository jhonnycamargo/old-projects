package co.merkhet.engchat.business.chat.rules;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chat.rules.util.BasicTranslatedWordMaps;
import co.merkhet.engchat.business.chat.rules.util.StringManipulator;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;

@Rule(name = "Continue counting with Lia", description = "Student must continue with the next number in the count.")
public class CorrectCountRule {

	private static final String CUAL_ES_EL_SIGUIENTE_NUMERO = "Cual es el siguiente numero";

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest) {
		String question = conversationRequest.getLastMsg();
		if (StringUtils.isEmpty(question)) {
			return false;
		}
		String userAnswer = conversationRequest.getMsg();

		question = StringManipulator.normaliceQuestion(question);

		if (question.contains(CUAL_ES_EL_SIGUIENTE_NUMERO)) {
			String currentNumber = BasicTranslatedWordMaps
					.lastWord(question.substring(0, question.indexOf(CUAL_ES_EL_SIGUIENTE_NUMERO)));
			Integer number = (Integer) BasicTranslatedWordMaps.englishNumbers.get(currentNumber);
			String correctAnswer = (String) BasicTranslatedWordMaps.englishNumbers.getKey(number + 1);

			return BasicTranslatedWordMaps.stringContainsItemFromList(correctAnswer,
					StringUtils.split(userAnswer.toLowerCase()));
		}

		return false;
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT) AliceBotManager aliceBotManager) throws Exception {

		String number = BasicTranslatedWordMaps.lastNumberWord(conversationRequest.getMsg());
		if (StringUtils.equalsIgnoreCase(number, BasicTranslatedWordMaps.lastCountingNumber())) {
			conversationResponse
					.setMsg(aliceBotManager.respond(number.concat(StringManipulator.COUNTING_CONGRATULATION)));
			conversationResponse.setIsSuccess(true);
		} else {
			conversationResponse.setMsg(aliceBotManager.respond(number.concat(StringManipulator.COUNT_WILDCARD)));
			conversationResponse.setIsSuccess(true);
		}
	}

	@Priority
	public int getPriority() {
		return 4;
	}
}
