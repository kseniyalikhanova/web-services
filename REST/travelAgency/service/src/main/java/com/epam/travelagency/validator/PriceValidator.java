package com.epam.travelagency.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceValidator {
    private static final String PRICE_PATTERN =
            "^[1-9]\\d*((,\\d+)*(\\.\\d+)?)|((\\.\\d+)*(,\\d+)?)$";

    private PriceValidator() { }

    public static boolean isValidPrice(final String price) {
        Pattern pricePattern = Pattern.compile(PRICE_PATTERN);
        Matcher priceMatcher = pricePattern.matcher(price);
        return priceMatcher.matches();
    }
}
