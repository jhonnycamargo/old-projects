package co.merkhet.engchat.business.chatterbean.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import co.merkhet.engchat.business.chatterbean.Context;

public class ContextParser {

	/**
	 * 
	 */
	private final ContextHandler handler = new ContextHandler();

	/**
	 * 
	 */
	private SAXParser parser;

	/**
	 * 
	 */
	private Context context;

	private class ContextHandler extends DefaultHandler {
		public void startElement(String namespace, String name, String qname, Attributes attributes)
				throws SAXException {
			if (qname.equals("set"))
				context.property("predicate." + attributes.getValue("name"), attributes.getValue("value"));
			else if (qname.equals("bot"))
				context.property("bot." + attributes.getValue("name"), attributes.getValue("value"));
		}
	}

	/**
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public ContextParser() throws ParserConfigurationException, SAXException {
		parser = SAXParserFactory.newInstance().newSAXParser();
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 */
	public Context parse(InputStream input) throws IOException, SAXException {
		parse(new Context(), input);
		return context;
	}

	/**
	 * 
	 * @param context
	 * @param input
	 * @throws IOException
	 * @throws SAXException
	 */
	public void parse(Context context, InputStream input) throws IOException, SAXException {
		this.context = context;
		parser.parse(input, handler);
	}
}
