package com.merkhet.ui.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToDateConverter;

@SuppressWarnings("serial")
public class ShortStringToDateConverter extends StringToDateConverter {
  public static final String FORMAT = "dd/MM/yyyy";

  @Override
  protected DateFormat getFormat(Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT, locale);
    return sdf;
  }

}
