package com.warehouse.views;

import javax.swing.text.DefaultFormatter;
import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends DefaultFormatter {
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value instanceof Calendar) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return super.valueToString(value);
    }
}
