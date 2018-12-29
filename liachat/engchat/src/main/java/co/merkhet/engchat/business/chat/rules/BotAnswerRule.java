package co.merkhet.engchat.business.chat.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;

@Rule(name = "Bot answer rule", description = "Do the bot answer.")
public class BotAnswerRule {

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse) {
		return !conversationResponse.getIsSuccess();
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT) AliceBotManager aliceBotManager) throws Exception {

		conversationResponse.setMsg(aliceBotManager.respond(conversationRequest.getMsg()));
		conversationResponse.setIsSuccess(true);
	}

	@Priority
	public int getPriority() {
		return 3;
	}
}
