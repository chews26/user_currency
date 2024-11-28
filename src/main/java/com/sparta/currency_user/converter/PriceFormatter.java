package com.sparta.currency_user.converter;

import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PriceFormatter implements Formatter<Number> {

    @Override
    public Number parse(String text, Locale locale) throws ParseException {

        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }

    @Override
    public String print(Number object, Locale locale) {
        return NumberFormat.getInstance(locale).format(object);
    }
}