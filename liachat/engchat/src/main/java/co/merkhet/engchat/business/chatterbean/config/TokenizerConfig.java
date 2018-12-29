package co.merkhet.engchat.business.chatterbean.config;

import co.merkhet.engchat.business.chatterbean.text.Tokenizer;

public interface TokenizerConfig {

	/**
	 * 
	 * @return
	 */
	public Tokenizer newInstance();

	/**
	 * 
	 * @return
	 */
	public String[] splitters();
}
