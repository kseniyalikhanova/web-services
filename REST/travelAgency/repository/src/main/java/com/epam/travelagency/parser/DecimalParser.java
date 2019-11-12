package com.epam.travelagency.parser;

import java.math.BigDecimal;

public class DecimalParser {
    private static final String POINT = ".";
    private static final String COMMA = ",";
    private static final String EMPTY = "";

    private DecimalParser() {
    }

    /**
     * Parses a String to a BigDecimal.
     * If there is more than 1 '.',
     *      the points are interpreted as thousand-separator
     *      and will be removed for conversion
     * If there is more than 1 ',',
     *      the commas are interpreted as thousand-separator
     *      and will be removed for conversion
     * the last '.' or ',' will be interpreted
     *          as the separator for the decimal places
     *
     * @param value the price in string format
     * @return The price in BigDecimal format of the given string
     */
    public static BigDecimal parseToBigDecimal(String value) {
        BigDecimal result;
        value = value.replaceAll("\\s+", "");
        int lastPointPosition = value.lastIndexOf(POINT);
        int lastCommaPosition = value.lastIndexOf(COMMA);
        boolean pointIsPresent = lastPointPosition > -1;
        boolean commaIsPresent = lastCommaPosition > -1;
        if (!pointIsPresent && !commaIsPresent) {
            result = new BigDecimal(value);
        } else if (pointIsPresent && !commaIsPresent) {
            int firstPointPosition = value.indexOf(POINT);
            if (firstPointPosition != lastPointPosition) {
                result = new BigDecimal(value.replace(POINT, EMPTY));
            } else {
                result = new BigDecimal(value);
            }
        } else if (!pointIsPresent && commaIsPresent) {
            int firstCommaPosition = value.indexOf(COMMA);
            if (firstCommaPosition != lastCommaPosition) {
                result = new BigDecimal(value.replace(COMMA, EMPTY));
            } else {
                result = new BigDecimal(value.replace(COMMA, POINT));
            }
        } else if (lastPointPosition > lastCommaPosition) {
            result = new BigDecimal(value.replace(COMMA, EMPTY));
        } else if (lastPointPosition < lastCommaPosition) {
            result = new BigDecimal(value.replace(POINT, EMPTY)
                    .replace(COMMA, POINT));
        } else {
            throw new NumberFormatException(
                    "Unexpected number format. Cannot convert '"
                            + value + "' to BigDecimal.");
        }
        return result;
    }
}
