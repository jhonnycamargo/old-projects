package co.merkhet.trex.constant;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.format.DateTimeFormatter;

public class UtilityConstant {

	public static final String DATE_PATTERN = "YYYY-MM-dd HH:mm";

	public static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");

	private UtilityConstant() {
		super();
	}
}
