package co.merkhet.engchat.business.chatterbean.script;

import co.merkhet.engchat.business.chatterbean.script.InterpretingException;

public interface Interpreter {
	/**
	 * 
	 * @param script
	 * @return
	 * @throws InterpretingException
	 */
	public Object evaluate(String script) throws InterpretingException;

	/**
	 * 
	 * @param name
	 * @return
	 * @throws InterpretingException
	 */
	public Object variable(String name) throws InterpretingException;

	/**
	 * 
	 * @param name
	 * @param value
	 * @throws InterpretingException
	 */
	public void variable(String name, Object value) throws InterpretingException;
}
