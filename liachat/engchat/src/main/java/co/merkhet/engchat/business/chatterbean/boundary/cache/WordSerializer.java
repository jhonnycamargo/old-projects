package co.merkhet.engchat.business.chatterbean.boundary.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import co.merkhet.engchat.business.chat.entity.Word;
import co.merkhet.engchat.business.chat.rules.util.StringManipulator;
import co.merkhet.engchat.business.logging.boundary.LogSink;

@Stateless
public class WordSerializer {

	private static final String WORDS_DIR = "Words/";
	@Inject
	LogSink LOG;

	public String getPrefix() throws InvalidPropertiesFormatException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("properties.xml");

		Properties properties = new Properties();
		properties.loadFromXML(input);

		String categories = properties.getProperty("categories");

		return categories;
	}

	public void serialize(String key, Word word) {
		key = StringManipulator.normaliceWord(key);
		if (StringUtils.isNotEmpty(key)) {
			try {
				String prefix = getPrefix();
				File file = new File(prefix + WORDS_DIR);
				if (!file.exists()) {
					file.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(getPrefix() + WORDS_DIR + key);
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(word);
				out.close();
			} catch (IOException ex) {
				LOG.log(ex.getMessage());
			}
		}
	}

	public Word deserialize(String key) {
		key = StringManipulator.normaliceWord(key);
		if (StringUtils.isNotEmpty(key)) {
			Word word = null;
			try {
				FileInputStream fis = new FileInputStream(getPrefix() + WORDS_DIR + key);
				ObjectInputStream in = new ObjectInputStream(fis);
				word = (Word) in.readObject();
				in.close();
			} catch (IOException ex) {
				LOG.log(ex.getMessage());
			} catch (ClassNotFoundException ex) {
				LOG.log(ex.getMessage());
			}
			return word;
		}
		return null;
	}
}
