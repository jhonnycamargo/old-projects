package co.merkhet.engchat.business.chat.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chat.rules.util.StringManipulator;
import co.merkhet.engchat.business.chatterbean.boundary.cache.WordLevelsCache;

@Rule(name = "Kupu wildcard Words rule", description = "Replace kupu wildcard with some native word.")
public class KupuWildcardRule {

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse) {
		if (conversationResponse.getMsg().contains(StringManipulator.KUPU_WILDCARD)) {
			return true;
		}
		return false;
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.WORD_LVLS_CACHE_AS_FACT) WordLevelsCache wordLevelsCache) throws Exception {
		String randomNativeWord = wordLevelsCache.getRandomBasicNativeWord();

		conversationResponse
				.setMsg(conversationResponse.getMsg().replaceAll(StringManipulator.KUPU_WILDCARD, randomNativeWord));
	}

	@Priority
	public int getPriority() {
		return 100;
	}
}
