package co.merkhet.engchat.business.chatterbean.script;

import co.merkhet.engchat.business.chatterbean.script.Interpreter;
import co.merkhet.engchat.business.chatterbean.script.InterpretingException;

/**
 * Interpreter for Beanshell scripts.
 */
public class BeanshellInterpreter implements Interpreter {

	/**
	 * Beanshell interpreter.
	 */
	private final bsh.Interpreter interpreter = new bsh.Interpreter();

	/**
	 * 
	 */
	public Object evaluate(String script) throws InterpretingException {
		try {
			return interpreter.eval(script);
		} catch (Exception e) {
			throw new InterpretingException(e);
		}
	}

	/**
	 * 
	 */
	public Object variable(String name) throws InterpretingException {
		try {
			return interpreter.get(name);
		} catch (Exception e) {
			throw new InterpretingException(e);
		}
	}

	/**
	 * 
	 */
	public void variable(String name, Object value) throws InterpretingException {
		try {
			interpreter.set(name, value);
		} catch (Exception e) {
			throw new InterpretingException(e);
		}
	}
}
