package com.epam.travelagency.parser;

import com.epam.travelagency.entity.enumeration.Feature;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HotelFeaturesParser {

    private static final String UNDERSCORE = "_";
    private static final String SPACE = " ";

    private HotelFeaturesParser() {
    }

    public static List<Feature> parseSqlArray(final Array featuresString)
                                                        throws SQLException {
        Object[] features = (Object[]) featuresString.getArray();
        return Arrays.stream(features)
                     .map(Object::toString)
                     .map(str -> str.replaceAll(SPACE, UNDERSCORE))
                     .map(Feature::valueOf)
                     .collect(Collectors.toList());
    }

    public static Array parseFeatureList(final PreparedStatement statement,
                                         final List<Feature> features)
                                                           throws SQLException {
        return statement.getConnection().createArrayOf(
                "travel_agency.feature",
                          features.stream()
                                  .map(Enum::name)
                                  .map(str -> str.replaceAll(UNDERSCORE, SPACE))
                                  .toArray());
    }
}
