package com.epam.travelagency.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static final Logger LOG = LogManager.getLogger("logger");

    private DateParser() {
    }

    public static long parseStringDateToMilliseconds(final String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long milliseconds = 0;
        try {
            Date date = format.parse(stringDate);
            milliseconds = date.getTime();
        } catch (ParseException e) {
            LOG.error(e.getMessage());
        }
        return milliseconds;
    }
}
