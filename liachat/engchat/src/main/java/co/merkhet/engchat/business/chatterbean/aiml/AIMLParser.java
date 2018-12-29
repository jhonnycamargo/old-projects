package co.merkhet.engchat.business.chatterbean.aiml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import co.merkhet.engchat.business.chatterbean.Graphmaster;

public class AIMLParser {

	/**
	 * 
	 */
	private final AIMLHandler handler = new AIMLHandler();

	/**
	 * 
	 */
	private SAXParser parser;

	/**
	 * 
	 * @throws AIMLParserConfigurationException
	 */
	public AIMLParser() throws AIMLParserConfigurationException {
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
		} catch (Exception e) {
			throw new AIMLParserConfigurationException(e);
		}
	}

	/**
	 * 
	 * @param graphmaster
	 * @param sources
	 * @throws AIMLParserException
	 */
	public void parse(Graphmaster graphmaster, InputStream... sources) throws AIMLParserException {
		try {
			for (InputStream aiml : sources)
				parser.parse(aiml, handler);

			graphmaster.append(handler.unload());
		} catch (Exception e) {
			throw new AIMLParserException(e);
		}
	}
}