package co.merkhet.engchat.business.chat.rules.util;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class StringManipulator {

	public static final int wordTypeIndicator = 2;
	public static final int wordIndicator = 1;

	public static final String CLIENT_DATE_FORMAT = "yyyy/MM/dd";
	public static final String CURRENT_DATE_FORMAT = "yyyy-MM-dd";

	public static final String COUNT_WILDCARD = " TATAU";
	public static final String DAY_OF_WEEK = " TUHINGA";
	public static final String MONTH_WILDCARD = " MARAMA";
	public static final String KUPU_WILDCARD = "KUPU";
	public static final String WRONG_WORD_WILDCARD = "KUPUHE";
	public static final String OTHER_WORD_TO_TRANSLATE_WILDCARD = "TETAHIKUPUATU";
	public static final String NEXT_COUNT_WILDCARD = " TETATAU";
	public static final String COUNTING_CONGRATULATION = " TUHINGAXMIHI";

	private StringManipulator() {

	}

	public static String normaliceQuestion(String question) {
		question = Normalizer.normalize(question, Normalizer.Form.NFD);
		question = question.replaceAll("\\p{M}", "");
		question = question.replaceAll("[^\\p{Alnum}\\s]", "");
		question = question.replaceAll("[¿?]", "");

		return question;
	}

	public static String normalizeText(String text) {
		return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[\\\\W]|_", "").replaceAll("\\p{M}", "")
				.replaceAll("[^\\p{Alnum}\\s]", "");
	}

	public static String normaliceQuestionWithoutAlnum(String question) {
		question = Normalizer.normalize(question, Normalizer.Form.NFD);
		question = question.replaceAll("\\p{M}", "");
		question = question.replaceAll("[¿?]", "");

		return question;
	}

	public static String normaliceWord(String word) {
		word = word.trim();
		word = Normalizer.normalize(word, Normalizer.Form.NFD);
		word = word.replaceAll("\\p{M}", "");
		word = word.replaceAll("[^\\p{Alnum}\\s]", "_");
		word = word.replaceAll("\\s", "_");
		word = word.toLowerCase();

		return word;
	}

	public static String wordType(String word, int wordTypeIndicator) {
		Pattern p = Pattern.compile("(.+)(\\[.+\\])");
		Matcher m = p.matcher(word);
		if (m.find()) {
			String wordType = m.group(wordTypeIndicator).trim();

			if (StringUtils.isNotEmpty(wordType)) {
				return wordType;
			}
		}
		if (wordIndicator == wordTypeIndicator) {
			return word.trim();
		}

		return "";
	}

	public static String getCurrentPartOfDateAsString(String dateString, ChronoUnit chronoUnit, int addUnit)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(StringManipulator.CLIENT_DATE_FORMAT);
		Date date = sdf.parse(dateString);
		LocalDate dateToParse = LocalDate
				.parse(new SimpleDateFormat(StringManipulator.CURRENT_DATE_FORMAT).format(date));
		dateToParse = dateToParse.plus(addUnit, chronoUnit);

		switch (chronoUnit) {
		case DAYS:
			return dateToParse.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
		case MONTHS:
			return dateToParse.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		default:
			break;
		}
		return null;
	}

}
