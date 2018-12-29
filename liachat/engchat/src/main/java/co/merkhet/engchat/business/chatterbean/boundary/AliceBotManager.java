package co.merkhet.engchat.business.chatterbean.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import co.merkhet.engchat.business.chatterbean.AliceBot;

@Stateless
public class AliceBotManager {

	@Inject
	@Named("aliceBot")
	AliceBot aliceBot;

	public String respond(String request) {
		return aliceBot.respond(request);
	}
}
