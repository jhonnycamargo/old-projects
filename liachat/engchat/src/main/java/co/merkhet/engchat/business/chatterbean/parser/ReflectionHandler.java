package co.merkhet.engchat.business.chatterbean.parser;

import java.lang.reflect.Method;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ReflectionHandler extends DefaultHandler {

	/**
	 * 
	 */
	private ReflectionBuilder builder = null;

	/**
	 * 
	 */
	public ReflectionHandler() {
	}

	/**
	 * 
	 * @param builder
	 */
	public ReflectionHandler(ReflectionBuilder builder) {
		this.builder = builder;
	}

	/**
	 * 
	 */
	public void characters(char[] chars, int start, int length) {
		builder.characters(chars, start, length);
	}

	/**
	 * 
	 */
	public void endElement(String namespace, String name, String qname) {
		try {
			String methodName = "end" + qname.substring(0, 1).toUpperCase() + qname.substring(1);
			Method event = builder.getClass().getMethod(methodName);
			event.invoke(builder);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void startElement(String namespace, String name, String qname, Attributes attributes) {
		try {
			String methodName = "start" + qname.substring(0, 1).toUpperCase() + qname.substring(1);
			Method event = builder.getClass().getMethod(methodName, Attributes.class);
			event.invoke(builder, attributes);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public ReflectionBuilder getReflectionBuilder() {
		return builder;
	}

	/**
	 * 
	 * @param builder
	 */
	public void setReflectionBuilder(ReflectionBuilder builder) {
		this.builder = builder;
	}
}
