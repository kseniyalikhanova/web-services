package by.bsu.travelagency.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static final Logger LOG = LoggerFactory.getLogger("logger");

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
