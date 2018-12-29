package co.merkhet.engchat.business.chatterbean.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SplitterHandler extends DefaultHandler {

	/**
	 * 
	 */
	private List<String> splitters;

	/**
	 * 
	 */
	public SplitterHandler() {
		splitters = new ArrayList<String>(4);
	}

	/**
	 * 
	 * @param splitters
	 */
	public SplitterHandler(List<String> splitters) {
		this.splitters = splitters;
	}

	/**
	 * 
	 */
	public void startElement(String namespace, String name, String qname, Attributes attributes) throws SAXException {
		if (qname.equals("splitter") && !"word".equals(attributes.getValue("type")))
			splitters.add(attributes.getValue(0));
	}

	/**
	 * 
	 */
	public void clear() {
		splitters.clear();
	}

	/**
	 * 
	 * @return
	 */
	public List<String> parsed() {
		return new ArrayList<String>(splitters);
	}
}