package co.merkhet.engchat.business.chatterbean.boundary.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.google.common.cache.CacheLoader;

import co.merkhet.engchat.business.chat.entity.Word;
import co.merkhet.engchat.business.chat.entity.Word.WordType;
import co.merkhet.engchat.business.chat.rules.util.StringManipulator;
import co.merkhet.engchat.business.logging.boundary.LogSink;

@Stateless
public class WordCacheLoader extends CacheLoader<String, Word> {

	@Inject
	LogSink LOG;

	@Inject
	WordSerializer wordSerializer;

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
			fileName = getPrefix() + "Spanish.txt";
		} catch (IOException e) {
			LOG.log(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		Map<String, Word> englishWords = new HashMap<>();
		Map<String, Word> spanishWords = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("#")) {

					Word englisWord = lineToWord(line);
					addWordToMap(englishWords, englisWord);

					List<Word> spanishWordList = lineToTranslatedWords(line);
					spanishWordList.forEach(word -> addWordToMap(spanishWords, word));
				}
			}

			englishWords.forEach((key, word) -> wordSerializer.serialize(key, word));
			spanishWords.forEach((key, word) -> wordSerializer.serialize(key, word));

		} catch (IOException e) {
			LOG.log(e.getMessage());
		}
	}

	private void addWordToMap(Map<String, Word> words, Word word) {
		if (words.containsKey(word.getOriginal())) {
			words.get(word.getOriginal()).getTranslations().addAll(word.getTranslations());
		} else {
			words.put(word.getOriginal(), word);
		}
	}

	public Word load(String key) throws Exception {
		return wordSerializer.deserialize(key);
	}

	public Word lineToWord(String line) {
		String[] words = line.split("\\t");
		if (words.length <= 1) {
			line = line.replaceFirst("\\s+", "	");
		}
		words = StringManipulator.wordType(line, StringManipulator.wordIndicator).split("\\t");
		Word word = new Word();
		word.setOriginal(words[0].trim());
		String[] translations = words[1].split(",");
		Set<String> translationSet = new HashSet<>();
		Arrays.stream(translations)
				.forEach(t -> translationSet.add(StringManipulator.wordType(t, StringManipulator.wordIndicator)));
		word.setTranslations(translationSet);
		word.setType(WordType.fromString(StringManipulator.wordType(line, StringManipulator.wordTypeIndicator)));

		return word;
	}

	public List<Word> lineToTranslatedWords(String line) {
		String[] words = line.split("\\t");
		if (words.length <= 1) {
			line = line.replaceFirst("\\s+", "	");
		}
		words = StringManipulator.wordType(line, StringManipulator.wordIndicator).split("\\t");
		WordType wordType = WordType.fromString(StringManipulator.wordType(line, StringManipulator.wordTypeIndicator));
		String translation = words[0].trim();
		String[] originals = words[1].split(",");
		List<Word> wordList = new ArrayList<>();
		for (int i = 0; i < originals.length; i++) {
			String original = originals[i];
			Word addWord = new Word();
			String trimedOriginal = StringManipulator.wordType(original, StringManipulator.wordIndicator).trim();
			addWord.setOriginal(StringUtils.isNotEmpty(trimedOriginal) ? trimedOriginal : original.trim());
			Set<String> traslations = new HashSet<>();
			traslations.add(translation);
			addWord.setTranslations(traslations);
			addWord.setType(wordType);

			wordList.add(addWord);
		}

		return wordList;
	}
}
