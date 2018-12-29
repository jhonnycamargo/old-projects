package co.merkhet.engchat.business.chat.rules;

import javax.enterprise.inject.Produces;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class RuleEngineProducer {

	public static final String CONVERSATION_REQUEST = "conversationRequest";
	public static final String CONVERSATION_RESPONSE = "conversationResponse";
	public static final String ALICEBOT_MANAGER_AS_FACT = "aliceBotManagerAsFact";
	public static final String SENTENCE_DETECTOR_AS_FACT = "sentenceDetectorMEAsFact";
	public static final String WORD_LVLS_CACHE_AS_FACT = "wordLevelsCacheAsFact";
	public static final String WORD_CACHE_AS_FACT = "wordCacheAsFact";

	@Produces
	public RulesEngine produceRulesEngine() {
		RulesEngine rulesEngine = new DefaultRulesEngine();

		return rulesEngine;
	}

	@Produces
	public Rules produceRules() {
		Rules rules = new Rules();
		rules.register(new TranslateBasicWordsRule());
		rules.register(new ExplainBasicWordRule());
		rules.register(new HowToRespondRule());

		rules.register(new BotAnswerRule());
		rules.register(new TranslateOtherBasicWordRule());
		rules.register(new DoNotTranslateOtherBasicWordRule());
		rules.register(new CorrectCountRule());
		rules.register(new IncorrectCountRule());
		rules.register(new CurrentDayOfWeek());
		rules.register(new CurrentMonthOfYear());

		rules.register(new KupuWildcardRule());

		return rules;
	}
}
