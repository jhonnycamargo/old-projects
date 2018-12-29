package co.merkhet.engchat.business.chat.rules;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;

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

@Rule(name = "Current Day Of Week", description = "Lia teaches days of week.")
public class CurrentDayOfWeek {

	private static final String SABES_QUE_DIA_DE_LA_SEMANA_ES_HOY = "Sabes que dia de la semana es hoy";

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest) {
		String question = conversationRequest.getLastMsg();
		if (StringUtils.isEmpty(question)) {
			return false;
		}
		String userAnswer = conversationRequest.getMsg();

		question = StringManipulator.normaliceQuestion(question);

		if (question.contains(SABES_QUE_DIA_DE_LA_SEMANA_ES_HOY)) {
			try {
				String currentDay = StringManipulator.getCurrentPartOfDateAsString(conversationRequest.getDate(),
						ChronoUnit.DAYS, 0);

				return BasicTranslatedWordMaps.stringContainsItemFromList(currentDay,
						StringUtils.split(userAnswer.toLowerCase()));
			} catch (ParseException e) {
			}
		}

		return false;
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT) AliceBotManager aliceBotManager) throws Exception {

		conversationResponse.setMsg(aliceBotManager.respond(
				StringManipulator.getCurrentPartOfDateAsString(conversationRequest.getDate(), ChronoUnit.DAYS, 0)
						.concat(StringManipulator.DAY_OF_WEEK)));
		conversationResponse.setIsSuccess(true);
	}

	@Priority
	public int getPriority() {
		return 4;
	}
}
