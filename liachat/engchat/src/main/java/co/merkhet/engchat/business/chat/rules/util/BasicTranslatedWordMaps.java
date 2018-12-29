package co.merkhet.engchat.business.chat.rules.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.lang3.StringUtils;

import co.merkhet.engchat.business.chat.entity.Word;

public class BasicTranslatedWordMaps {

	public static final Map<String, String> englishWordsToSpanish = new HashMap<>();
	public static final Map<String, String> spanishWordsToEnglish = new HashMap<>();
	public static final BidiMap englishNumbers = new DualHashBidiMap();
	public static final String[] affirmativeAnswers = { "yes", "si", "yeah", "yep" };
	public static final String[] negativeAnswers = { "no", "not" };

	static {
		englishWordsToSpanish.put("father", "padre");
		englishWordsToSpanish.put("car", "carro");
		englishWordsToSpanish.put("hard", "duro");
		englishWordsToSpanish.put("park", "parque");
		englishWordsToSpanish.put("ask", "preguntar");
		englishWordsToSpanish.put("hat", "sombrero");
		englishWordsToSpanish.put("that", "que");
		englishWordsToSpanish.put("hand", "mano");
		englishWordsToSpanish.put("late", "tarde");
		englishWordsToSpanish.put("cake", "torta");
		englishWordsToSpanish.put("same", "mismo");
		englishWordsToSpanish.put("tape", "cinta");
		englishWordsToSpanish.put("any", "cualquiera");
		englishWordsToSpanish.put("many", "muchos");
		englishWordsToSpanish.put("all", "todo");
		englishWordsToSpanish.put("quart", "cuarto");
		englishWordsToSpanish.put("call", "llamar");
		englishWordsToSpanish.put("draw", "dibujar");
		englishWordsToSpanish.put("she", "ella");
		englishWordsToSpanish.put("he", "él");
		englishWordsToSpanish.put("begin", "comenzar");
		englishWordsToSpanish.put("repair", "arreglar");
		englishWordsToSpanish.put("get", "obtener");
		englishWordsToSpanish.put("lesson", "lección");
		englishWordsToSpanish.put("let", "permitir");
		englishWordsToSpanish.put("metal", "metal");
		englishWordsToSpanish.put("her", "ella");
		englishWordsToSpanish.put("dinner", "cena");
		englishWordsToSpanish.put("large", "grande");
		englishWordsToSpanish.put("milk", "leche");
		englishWordsToSpanish.put("silver", "plata");
		englishWordsToSpanish.put("bridge", "puente");
		englishWordsToSpanish.put("pin", "alfiler");
		englishWordsToSpanish.put("machine", "máquina");
		englishWordsToSpanish.put("police", "policía");
		englishWordsToSpanish.put("high", "alto");
		englishWordsToSpanish.put("like", "gustar");
		englishWordsToSpanish.put("kind", "bondadoso");
		englishWordsToSpanish.put("price", "precio");
		englishWordsToSpanish.put("bird", "pájaro");
		englishWordsToSpanish.put("flirt", "coquetear");
		englishWordsToSpanish.put("go", "ir");
		englishWordsToSpanish.put("home", "casa");
		englishWordsToSpanish.put("nose", "nariz");
		englishWordsToSpanish.put("no", "no");
		englishWordsToSpanish.put("to", "a");
		englishWordsToSpanish.put("who", "quién");
		englishWordsToSpanish.put("do", "hacer");
		englishWordsToSpanish.put("move", "mover");
		englishWordsToSpanish.put("not", "no");
		englishWordsToSpanish.put("hot", "caliente");
		englishWordsToSpanish.put("shop", "tienda");
		englishWordsToSpanish.put("more", "más");
		englishWordsToSpanish.put("before", "antes");
		englishWordsToSpanish.put("corn", "maíz");
		englishWordsToSpanish.put("cute", "linda");
		englishWordsToSpanish.put("pupil", "alumno");
		englishWordsToSpanish.put("confuse", "confuso");
		englishWordsToSpanish.put("pure", "puro");
		englishWordsToSpanish.put("pull", "halar");
		englishWordsToSpanish.put("put", "poner");
		englishWordsToSpanish.put("push", "empujar");
		englishWordsToSpanish.put("full", "lleno");
		englishWordsToSpanish.put("tub", "tina");
		englishWordsToSpanish.put("fun", "diversión");
		englishWordsToSpanish.put("but", "pero");
		englishWordsToSpanish.put("cut", "cortar");
		englishWordsToSpanish.put("burn", "quemar");
		englishWordsToSpanish.put("fur", "piel");
		englishWordsToSpanish.put("occur", "ocurrir");
		englishWordsToSpanish.put("lonely", "solo");
		englishWordsToSpanish.put("many", "muchos");
		englishWordsToSpanish.put("by", "por");
		englishWordsToSpanish.put("eye", "ojo");
		englishWordsToSpanish.put("action", "acción");
		englishWordsToSpanish.put("agent", "agente");
		englishWordsToSpanish.put("attention", "atención");
		englishWordsToSpanish.put("case", "caso");
		englishWordsToSpanish.put("check", "cheque");
		englishWordsToSpanish.put("certain", "cierto");
		englishWordsToSpanish.put("different", "diferente");
		englishWordsToSpanish.put("example", "ejemplo");
		englishWordsToSpanish.put("guitar", "guitarra");
		englishWordsToSpanish.put("important", "importante");
		englishWordsToSpanish.put("interesting", "interesante");
		englishWordsToSpanish.put("necessary", "necesario");
		englishWordsToSpanish.put("radio", "radio");
		englishWordsToSpanish.put("tea", "té");
		englishWordsToSpanish.put("telephone", "teléfono");
		englishWordsToSpanish.put("theater", "teatro");
		englishWordsToSpanish.put("train", "tren");
		englishWordsToSpanish.put("visit", "visita");

		spanishWordsToEnglish.put("padre", "father");
		spanishWordsToEnglish.put("carro", "car");
		spanishWordsToEnglish.put("duro", "hard");
		spanishWordsToEnglish.put("parque", "park");
		spanishWordsToEnglish.put("preguntar", "ask");
		spanishWordsToEnglish.put("sombrero", "hat");
		spanishWordsToEnglish.put("que", "that");
		spanishWordsToEnglish.put("mano", "hand");
		spanishWordsToEnglish.put("tarde", "late");
		spanishWordsToEnglish.put("torta", "cake");
		spanishWordsToEnglish.put("mismo", "same");
		spanishWordsToEnglish.put("cinta", "tape");
		spanishWordsToEnglish.put("cualquiera", "any");
		spanishWordsToEnglish.put("muchos", "many");
		spanishWordsToEnglish.put("todo", "all");
		spanishWordsToEnglish.put("cuarto", "quart");
		spanishWordsToEnglish.put("llamar", "call");
		spanishWordsToEnglish.put("dibujar", "draw");
		spanishWordsToEnglish.put("ella", "she");
		spanishWordsToEnglish.put("el", "he");
		spanishWordsToEnglish.put("comenzar", "begin");
		spanishWordsToEnglish.put("arreglar", "repair");
		spanishWordsToEnglish.put("obtener", "get");
		spanishWordsToEnglish.put("leccion", "lesson");
		spanishWordsToEnglish.put("permitir", "let");
		spanishWordsToEnglish.put("metal", "metal");
		spanishWordsToEnglish.put("cena", "dinner");
		spanishWordsToEnglish.put("grande", "large");
		spanishWordsToEnglish.put("leche", "milk");
		spanishWordsToEnglish.put("plata", "silver");
		spanishWordsToEnglish.put("puente", "bridge");
		spanishWordsToEnglish.put("alfiler", "pin");
		spanishWordsToEnglish.put("maquina", "machine");
		spanishWordsToEnglish.put("policia", "police");
		spanishWordsToEnglish.put("alto", "high");
		spanishWordsToEnglish.put("gustar", "like");
		spanishWordsToEnglish.put("bondadoso", "kind");
		spanishWordsToEnglish.put("precio", "price");
		spanishWordsToEnglish.put("pajaro", "bird");
		spanishWordsToEnglish.put("coquetear", "flirt");
		spanishWordsToEnglish.put("ir", "go");
		spanishWordsToEnglish.put("casa", "home");
		spanishWordsToEnglish.put("nariz", "nose");
		spanishWordsToEnglish.put("no", "no");
		spanishWordsToEnglish.put("a", "to");
		spanishWordsToEnglish.put("quien", "who");
		spanishWordsToEnglish.put("hacer", "do");
		spanishWordsToEnglish.put("mover", "move");
		spanishWordsToEnglish.put("caliente", "hot");
		spanishWordsToEnglish.put("tienda", "shop");
		spanishWordsToEnglish.put("mas", "more");
		spanishWordsToEnglish.put("antes", "before");
		spanishWordsToEnglish.put("maiz", "corn");
		spanishWordsToEnglish.put("linda", "cute");
		spanishWordsToEnglish.put("alumno", "pupil");
		spanishWordsToEnglish.put("confuso", "confuse");
		spanishWordsToEnglish.put("puro", "pure");
		spanishWordsToEnglish.put("halar", "pull");
		spanishWordsToEnglish.put("poner", "put");
		spanishWordsToEnglish.put("empujar", "push");
		spanishWordsToEnglish.put("lleno", "full");
		spanishWordsToEnglish.put("tina", "tub");
		spanishWordsToEnglish.put("diversion", "fun");
		spanishWordsToEnglish.put("pero", "but");
		spanishWordsToEnglish.put("cortar", "cut");
		spanishWordsToEnglish.put("quemar", "burn");
		spanishWordsToEnglish.put("piel", "fur");
		spanishWordsToEnglish.put("ocurrir", "occur");
		spanishWordsToEnglish.put("solo", "lonely");
		spanishWordsToEnglish.put("muchos", "many");
		spanishWordsToEnglish.put("por", "by");
		spanishWordsToEnglish.put("ojo", "eye");
		spanishWordsToEnglish.put("accion", "action");
		spanishWordsToEnglish.put("agente", "agent");
		spanishWordsToEnglish.put("atencion", "attention");
		spanishWordsToEnglish.put("caso", "case");
		spanishWordsToEnglish.put("cheque", "check");
		spanishWordsToEnglish.put("cierto", "certain");
		spanishWordsToEnglish.put("diferente", "different");
		spanishWordsToEnglish.put("ejemplo", "example");
		spanishWordsToEnglish.put("guitarra", "guitar");
		spanishWordsToEnglish.put("importante", "important");
		spanishWordsToEnglish.put("interesante", "interesting");
		spanishWordsToEnglish.put("necesario", "necessary");
		spanishWordsToEnglish.put("radio", "radio");
		spanishWordsToEnglish.put("te", "tea");
		spanishWordsToEnglish.put("telefono", "telephone");
		spanishWordsToEnglish.put("teatro", "theater");
		spanishWordsToEnglish.put("tren", "train");
		spanishWordsToEnglish.put("visita", "visit");

		englishNumbers.put("one", 1);
		englishNumbers.put("two", 2);
		englishNumbers.put("three", 3);
		englishNumbers.put("four", 4);
		englishNumbers.put("five", 5);
		englishNumbers.put("six", 6);
		englishNumbers.put("seven", 7);
		englishNumbers.put("eight", 8);
		englishNumbers.put("nine", 9);
		englishNumbers.put("ten", 10);
	}

	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
		if (StringUtils.isNotBlank(inputStr)) {
			return StringUtils.indexOfAny(inputStr.toLowerCase(), items) >= 0;
		}
		return false;
	}

	public static String lastCountingNumber() {
		return (String) englishNumbers.getKey(englishNumbers.size());
	}

	public static String[] affirmativeAnswers() {
		return affirmativeAnswers;
	}

	public static String[] negativeAnswers() {
		return negativeAnswers;
	}

	public static String lastWord(String sentence) {
		String[] parts = sentence.split(" ");
		return parts[parts.length - 1];
	}

	public static String lastNumberWord(String sentence) {
		String[] parts = sentence.split(" ");
		for (String part : parts) {
			if (englishNumbers.get(part) != null) {
				return part;
			}
		}
		return null;
	}

	public static boolean findWordInText(String word, String text) {
		String dirtyText = StringManipulator.normalizeText(text).toLowerCase().trim();
		String toFind = word.toLowerCase().trim();
		if (dirtyText.contains(toFind)) {
			String[] words = dirtyText.split(" ");
			for (String w : words) {
				if (w.equals(toFind)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isWordInText(String text, Word word) {
		for (String translation : word.getTranslations()) {
			if (BasicTranslatedWordMaps.findWordInText(translation, text)) {
				return true;
			}
		}
		return false;
	}

	public static String getRandomWordFromList(List<String> words) {
		Random rand = new Random();
		return words.get(rand.nextInt(words.size()));
	}
}
