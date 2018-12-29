package co.merkhet.engchat.business.chat.rules;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;

@Rule(name = "Introduction chat rule.", description = "Student first chat.")
public class IntroductionChatRule {

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest) {
		String question = conversationRequest.getLastMsg();
		if (StringUtils.isEmpty(question)) {
			return true;
		}

		return false;
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
		return 4;
	}
}
