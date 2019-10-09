package by.bsu.travelagency.parser;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(DataProviderRunner.class)
public class DecimalParserTest {
    @DataProvider
    public static Object[][] bigDecimals() {
        return new Object[][]{
                {"5", new BigDecimal(5)},
                {"5,3", new BigDecimal("5.3")},
                {"5.3", new BigDecimal("5.3")},
                {"5.000,3", new BigDecimal("5000.3")},
                {"5.000.000,3", new BigDecimal("5000000.3")},
                {"5.000.000", new BigDecimal("5000000")},
                {"5,000.3", new BigDecimal("5000.3")},
                {"5,000,000.3", new BigDecimal("5000000.3")},
                {"5,000,000", new BigDecimal("5000000")},
                {"5 000 000", new BigDecimal("5000000")},
                {"5    000    000", new BigDecimal("5000000")},
                {"5 000 000.3", new BigDecimal("5000000.3")},
                {"5 000 000,3", new BigDecimal("5000000.3")}
        };
    }

    @Test
    @UseDataProvider("bigDecimals")
    public void testParseToBigDecimal(String priceValue, BigDecimal price) {
        Assert.assertEquals(price, DecimalParser.parseToBigDecimal(priceValue));
    }
}
