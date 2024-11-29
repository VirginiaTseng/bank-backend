package com.virginiabank.bankdemo.tools;


import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for type conversions, particularly for safely converting objects
 * to BigDecimal, Integer, etc.
 * 
 * @author virginia.zane
 */
public class ConversionUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConversionUtils.class);

    /**
     * Converts an object to BigDecimal safely.
     * If the conversion fails, it returns BigDecimal.ZERO by default.
     *
     * @param value the object to be converted
     * @return the converted BigDecimal value, or BigDecimal.ZERO if conversion fails
     */
    public static BigDecimal toBigDecimal(Object value) {
        return toBigDecimal(value, BigDecimal.ZERO); // Default value is BigDecimal.ZERO
    }

    /**
     * Converts an object to BigDecimal safely, with a customizable default value.
     *
     * @param value        the object to be converted
     * @param defaultValue the default value to return if conversion fails
     * @return the converted BigDecimal value, or the default value if conversion fails
     */
    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        } else if (value instanceof String) {
            try {
                return new BigDecimal((String) value);
            } catch (NumberFormatException e) {
                logger.error("Invalid string for BigDecimal conversion: {}", value, e);
            }
        }
        logger.warn("Cannot convert value to BigDecimal: {}", value);
        return defaultValue;
    }

    /**
     * Converts an object to Integer safely, with a customizable default value.
     *
     * @param value        the object to be converted
     * @param defaultValue the default value to return if conversion fails
     * @return the converted Integer value, or the default value if conversion fails
     */
    public static Integer toInteger(Object value, Integer defaultValue) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                logger.error("Invalid string for Integer conversion: {}", value, e);
            }
        }
        logger.warn("Cannot convert value to Integer: {}", value);
        return defaultValue;
    }

    // Similar methods like toLong, toDouble, etc., can be added here for other conversions.
}
