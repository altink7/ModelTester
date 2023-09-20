package at.altin.modeltester.utils;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Utility class to create sample values for testing.
 * This class is used by the ModelTester to create sample values for the properties of a class.
 * @author altin
 * @since 20.09.2023
 * @version 1.0.6
 */
public class ModelValueHelper {

    public static Object createSampleValue(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return 42;
        } else if (type == String.class) {
            return "sampleString";
        } else if (type == boolean.class || type == Boolean.class) {
            return true;
        } else if (type == long.class || type == Long.class) {
            return 123456789L;
        } else if (type == double.class || type == Double.class) {
            return 3.14;
        } else if (type == float.class || type == Float.class) {
            return 2.71f;
        } else if (type == char.class || type == Character.class) {
            return 'A';
        } else if (type == byte.class || type == Byte.class) {
            return (byte) 8;
        } else if (type == short.class || type == Short.class) {
            return (short) 16;
        } else if (type == byte[].class) {
            return new byte[]{1, 2, 3};
        } else if (type == int[].class) {
            return new int[]{4, 5, 6};
        } else if (type == String[].class) {
            return new String[]{"array", "of", "strings"};
        } else if (type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            if (enumValues.length > 0) {
                return enumValues[0];
            }
        } else if (Collection.class.isAssignableFrom(type)) {
            if (HashSet.class.isAssignableFrom(type)) {
                return new HashSet<>();
            } else if (ArrayList.class.isAssignableFrom(type)) {
                return new ArrayList<>();
            } else {
                return new ArrayList<>();
            }
        } else if (type == LocalDate.class) {
            return LocalDate.now();
        } else if (!type.isPrimitive()) {
            try {
                Constructor<?> noArgConstructor = type.getDeclaredConstructor();
                if (noArgConstructor.getParameterCount() == 0) {
                    return noArgConstructor.newInstance();
                } else {
                    throw new IllegalArgumentException("Type has no-arg constructor but cannot be instantiated: " + type.getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null; // Default for unknown types
    }
}
