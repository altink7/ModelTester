package at.altin.modeltester.tester.standard;

import at.altin.modeltester.tester.base.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static at.altin.modeltester.utils.ModelValueHelper.createSampleValue;

/**
 * Utility class to test getters and setters of a class.
 * use instanceSupplier from ModelTester if you do not have default Constructor
 *
 * @author altin
 * @version 1.0
 * @since 12.08.2023
 */
public class GetterSetterTester extends AbstractClassTester implements Tester {

    @Override
    public void test(Object instance) {
        Class<?> clazz = instance.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                testGetter(method, clazz);
            } else if (isSetter(method)) {
                testSetter(method, clazz);
            }
        }
    }

    private static boolean isGetter(Method method) {
        return method.getName().startsWith("get") && method.getParameterCount() == 0 && !method.getReturnType().equals(void.class);
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set") && method.getParameterCount() == 1;
    }

    private static void testGetter(Method getter, Class<?> clazz) {
        String propertyName = getPropertyName(getter);
        try {
            Method setter = clazz.getDeclaredMethod("set" + propertyName, getter.getReturnType());
            Object sampleValue = createSampleValue(getter.getReturnType());
            Object instance = clazz.getDeclaredConstructor().newInstance();

            setter.invoke(instance, sampleValue);
            Object actualValue = getter.invoke(instance);

            assert sampleValue != null;
            if (!sampleValue.equals(actualValue)) {
                throw new AssertionError("Getter and Setter for property " + propertyName + " failed.");
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Error testing getter and setter for property " + propertyName, e);
        }
    }

    private static void testSetter(Method setter, Class<?> clazz) {
        String propertyName = getPropertyName(setter);
        try {
            //for boolean properties, the getter may be "isProperty" instead of "getProperty"
            Method getter = getMethod(setter, clazz, propertyName);
            Object sampleValue = createSampleValue(setter.getParameterTypes()[0]);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            setter.invoke(instance, sampleValue);
            Object actualValue = getter.invoke(instance);

            assert sampleValue != null;

            if (!sampleValue.equals(actualValue)) {
                throw new AssertionError("Setter and Getter for property " + propertyName + " failed.");
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Error testing setter and getter for property " + propertyName, e);
        }
    }

    private static Method getMethod(Method setter, Class<?> clazz, String propertyName) throws NoSuchMethodException {
        Method getter;
        if (setter.getParameterTypes()[0] == boolean.class || setter.getParameterTypes()[0] == Boolean.class) {
            try {
                getter = clazz.getDeclaredMethod("is" + propertyName);
            } catch (NoSuchMethodException e) {
                getter = clazz.getDeclaredMethod("get" + propertyName);
            }
        } else {
            getter = clazz.getDeclaredMethod("get" + propertyName);
        }
        return getter;
    }

    private static String getPropertyName(Method method) {
        String methodName = method.getName();
        if (methodName.startsWith("get") || methodName.startsWith("is")) {
            return methodName.substring(methodName.startsWith("get") ? 3 : 2); // Remove "get" or "is" prefix
        }
        if (methodName.startsWith("set")) {
            return methodName.substring(3); // Remove "set" prefix
        }
        throw new IllegalArgumentException("Method is not a valid getter: " + methodName);
    }

}