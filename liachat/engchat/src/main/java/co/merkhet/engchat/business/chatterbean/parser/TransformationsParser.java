package co.merkhet.engchat.business.chatterbean.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import co.merkhet.engchat.business.chatterbean.config.ConfigException;
import co.merkhet.engchat.business.chatterbean.config.TokenizerConfig;
import co.merkhet.engchat.business.chatterbean.config.TokenizerConfigStream;
import co.merkhet.engchat.business.chatterbean.text.Tokenizer;
import co.merkhet.engchat.business.chatterbean.text.Transformations;

public class TransformationsParser {

	/**
	 * 
	 */
	private final SubstitutionBuilder substBuilder = new SubstitutionBuilder();

	/**
	 * 
	 */
	private final ReflectionHandler substHandler = new ReflectionHandler(substBuilder);

	/**
	 * 
	 */
	private final SplitterHandler splitHandler = new SplitterHandler();

	/**
	 * 
	 */
	private SAXParser parser;

	/**
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public TransformationsParser() throws ParserConfigurationException, SAXException {
		parser = SAXParserFactory.newInstance().newSAXParser();
	}

	/**
	 * 
	 * @param splitters
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	private List<String> parseSplitters(InputStream splitters) throws IOException, SAXException {
		splitHandler.clear();
		parser.parse(splitters, splitHandler);
		return splitHandler.parsed();
	}

	/**
	 * 
	 * @param substitutions
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	private Map<String, Map<String, String>> parseSubstitutions(InputStream substitutions)
			throws IOException, SAXException {
		substBuilder.clear();
		parser.parse(substitutions, substHandler);
		return substBuilder.parsed();
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	private byte[] toByteArray(InputStream input) throws IOException {
		List<Byte> list = new LinkedList<Byte>();
		for (int i = 0; (i = input.read()) > -1;)
			list.add((byte) i);

		int i = 0;
		byte[] bytes = new byte[list.size()];
		for (byte b : list)
			bytes[i++] = b;
		return bytes;
	}

	/**
	 * 
	 * @param splitters
	 * @param substitutions
	 * @return
	 * @throws ConfigException
	 * @throws IOException
	 * @throws SAXException
	 */
	public Transformations parse(InputStream splitters, InputStream substitutions)
			throws ConfigException, IOException, SAXException {
		byte[] bytes = toByteArray(splitters);

		TokenizerConfig config = new TokenizerConfigStream(new ByteArrayInputStream(bytes));
		Tokenizer tokenizer = new Tokenizer(config);

		List<String> splitChars = parseSplitters(new ByteArrayInputStream(bytes));

		Map<String, Map<String, String>> maps = parseSubstitutions(substitutions);
		return new Transformations(splitChars, maps, tokenizer);
	}
}