package co.merkhet.engchat.business.chat.rules;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

import co.merkhet.engchat.business.chat.entity.Conversation;
import co.merkhet.engchat.business.chat.entity.Word;
import co.merkhet.engchat.business.chat.rules.util.BasicTranslatedWordMaps;
import co.merkhet.engchat.business.chat.rules.util.StringManipulator;
import co.merkhet.engchat.business.chatterbean.boundary.AliceBotManager;
import co.merkhet.engchat.business.chatterbean.boundary.cache.WordCache;

@Rule(name = "Translate Basic Words rule", description = "Translate Basic world from Spanish to English.")
public class TranslateBasicWordsRule {

	private static final String COMO_SE_ESCRIBE = "como se escribe";

	@Condition
	public boolean when(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.WORD_CACHE_AS_FACT) WordCache wordCache) {
		String question = conversationRequest.getLastMsg();

		if (StringUtils.isEmpty(question)) {
			return false;
		}

		String userAnswer = conversationRequest.getMsg();

		question = StringManipulator.normaliceQuestionWithoutAlnum(question);

		if (question.contains(COMO_SE_ESCRIBE)) {
			Pattern p = Pattern.compile("\"([^\"]*)\"");
			Matcher m = p.matcher(question);
			if (m.find()) {
				String wordAsked = m.group(1);
				try {
					Word word = wordCache.get(wordAsked.toLowerCase());
					for (String translation : word.getTranslations()) {
						if (BasicTranslatedWordMaps.findWordInText(translation, userAnswer)) {
							return true;
						}
					}
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	@Action
	public void then(@Fact(RuleEngineProducer.CONVERSATION_REQUEST) Conversation conversationRequest,
			@Fact(RuleEngineProducer.CONVERSATION_RESPONSE) Conversation conversationResponse,
			@Fact(RuleEngineProducer.ALICEBOT_MANAGER_AS_FACT) AliceBotManager aliceBotManager,
			@Fact(RuleEngineProducer.WORD_CACHE_AS_FACT) WordCache wordCache) throws Exception {

		String question = conversationRequest.getLastMsg();
		String userAnswer = conversationRequest.getMsg();

		question = StringManipulator.normaliceQuestionWithoutAlnum(question);

		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(question);
		if (m.find()) {
			String wordAsked = m.group(1);
			try {
				Word word = wordCache.get(wordAsked.toLowerCase());
				String userTranslation = "";
				for (String translation : word.getTranslations()) {
					if (BasicTranslatedWordMaps.findWordInText(translation, userAnswer)) {
						userTranslation = translation;
						break;
					}
				}

				String asking = String.join(" ", userTranslation, StringManipulator.OTHER_WORD_TO_TRANSLATE_WILDCARD);
				conversationResponse.setMsg(aliceBotManager.respond(asking));
				conversationResponse.setIsSuccess(true);
			} catch (ExecutionException e) {
				e.printStackTrace();
				conversationResponse.setMsg("!Correcto! ¿Quieres otra palabra?");
				conversationResponse.setIsSuccess(true);
			}
		}
	}

	@Priority
	public int getPriority() {
		return 1;
	}
}
