package co.merkhet.engchat.business.chatterbean.boundary.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import co.merkhet.engchat.business.logging.boundary.LogSink;

@Singleton
@Startup
public class WordLevelsCache {

	@Inject
	LogSink LOG;

	private Random rand = new Random();

	private List<String> basicNativeWords;

	public String getPrefix() throws InvalidPropertiesFormatException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("properties.xml");

		Properties properties = new Properties();
		properties.loadFromXML(input);

		String categories = properties.getProperty("categories");

		return categories;
	}

	@PostConstruct
	public void init() {
		String fileName;
		try {
			fileName = getPrefix() + "BasicSpanishWords.txt";
		} catch (IOException e) {
			LOG.log(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			basicNativeWords = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				basicNativeWords.add(StringUtils.capitalize(line.trim()));
			}
		} catch (IOException e) {
			LOG.log(e.getMessage());
		}
	}

	public String getRandomBasicNativeWord() {
		return basicNativeWords.get(rand.nextInt(basicNativeWords.size()));
	}
}
