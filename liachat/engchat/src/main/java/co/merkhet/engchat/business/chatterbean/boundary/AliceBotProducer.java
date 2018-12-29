package co.merkhet.engchat.business.chatterbean.boundary;

import javax.enterprise.inject.Produces;
import co.merkhet.engchat.business.chatterbean.AliceBot;
import co.merkhet.engchat.business.chatterbean.ChatterBeanException;
import co.merkhet.engchat.business.chatterbean.parser.AliceBotParserConfigurationException;
import co.merkhet.engchat.business.chatterbean.parser.AliceBotParserException;
import co.merkhet.engchat.business.chatterbean.parser.ChatterBeanParser;
import javax.inject.Named;

public class AliceBotProducer {

	@Produces
	@Named("aliceBot")
	public AliceBot produce() {
		AliceBot aliceBot = new AliceBot();
		ChatterBeanParser parser;
		try {
			parser = new ChatterBeanParser();
			parser.parse(aliceBot);

			return aliceBot;
		} catch (AliceBotParserConfigurationException | AliceBotParserException e) {
			throw new ChatterBeanException(e);
		}
	}
}
