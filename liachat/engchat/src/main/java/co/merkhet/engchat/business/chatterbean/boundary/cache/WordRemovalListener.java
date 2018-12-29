package co.merkhet.engchat.business.chatterbean.boundary.cache;

import javax.ejb.Stateless;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import co.merkhet.engchat.business.chat.entity.Word;

@Stateless
public class WordRemovalListener implements RemovalListener<String, Word> {

	// @Inject
	// Logger logger;

	public void onRemoval(RemovalNotification<String, Word> notification) {
		// logger.info("Person associated with the key(" + notification.getKey() + ") is
		// removed.");
	}

}
