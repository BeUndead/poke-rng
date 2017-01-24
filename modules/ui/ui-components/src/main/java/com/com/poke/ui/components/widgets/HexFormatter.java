package com.com.poke.ui.components.widgets;

import javax.swing.text.DefaultFormatter;
import java.text.ParseException;
import java.util.Objects;

public class HexFormatter extends DefaultFormatter {

    @Override
    public Object stringToValue(final String text) throws ParseException {

        try {
            return Long.valueOf(text, 16);
        } catch (final NumberFormatException nfe) {
            throw new ParseException(text, 0);
        }
    }

    @Override
    public String valueToString(final Object value) throws ParseException {

        try {
            final Long valueAsLong = (Long) value;
            return Long.toHexString(valueAsLong).toUpperCase();
        } catch (final ClassCastException ccEx) {
            throw new ParseException(Objects.toString(value), 0);
        }
    }
}
