package co.merkhet.engchat.business.chatterbean.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import co.merkhet.engchat.business.chatterbean.AliceBot;
import co.merkhet.engchat.business.chatterbean.Context;
import co.merkhet.engchat.business.chatterbean.Graphmaster;
import co.merkhet.engchat.business.chatterbean.util.Searcher;

public class ChatterBeanParser {

	private AliceBotParser botParser;

	public ChatterBeanParser() throws AliceBotParserConfigurationException {
		try {
			botParser = new AliceBotParser();
		} catch (AliceBotParserConfigurationException e) {
			throw e;
		} catch (Exception e) {
			throw new AliceBotParserConfigurationException(e);
		}
	}

	private InputStream newResourceStream(String resource, String root, String path) throws Exception {
		if (root == null || path == null)
			throw new IllegalArgumentException(
					"Invalid path elements for retrieving " + resource + ": root(" + root + "), path(" + path + ")");

		path = root + path;

		try {
			return new FileInputStream(path);
		} catch (Exception e) {
			throw new Exception("Error while retrieving " + resource + ": " + path, e);
		}
	}

	public void parse(AliceBot aliceBot) throws AliceBotParserException {
		try {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("properties.xml");

			Properties properties = new Properties();
			properties.loadFromXML(input);

			String categories = properties.getProperty("categories");

			InputStream context = classLoader.getResourceAsStream(properties.getProperty("context"));
			InputStream splitters = classLoader.getResourceAsStream(properties.getProperty("splitters"));
			InputStream substitutions = classLoader.getResourceAsStream(properties.getProperty("substitutions"));

			Searcher searcher = new Searcher();
			botParser.parse(aliceBot, context, splitters, substitutions, searcher.search(categories, ".*\\.aiml"));
		} catch (AliceBotParserException e) {
			throw e;
		} catch (Exception e) {
			throw new AliceBotParserException(e);
		}
	}

	public <C extends Context> void contextClass(Class<C> contextClass) {
		botParser.contextClass(contextClass);
	}

	public <M extends Graphmaster> void graphmasterClass(Class<M> matcherClass) {
		botParser.graphmasterClass(matcherClass);
	}
}
