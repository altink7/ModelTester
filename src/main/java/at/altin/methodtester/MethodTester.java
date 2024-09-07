package at.altin.methodtester;

import at.altin.utils.exception.MethodTesterRuntimeException;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * Utility class for comprehensive testing of model classes.
 * This class provides a flexible way to test various aspects of a model class,
 * including getter and setter methods, toString(), equals(), and hashCode().
 * You can configure the tests to be performed, exclude certain testers, exclude specific methods,
 * and supply custom instance creation logic.
 * Usage example:
 * <pre>
 * {@code
 * ModelTester.forClass(YourClassToTest.class)
 *            .exclude(GetterSetterTester.class)
 *            .customTester(new CustomTester()) // Use the custom tester
 *            .instanceSupplier(() -> {
 *                // Custom instance creation logic
 *            })
 *            .test();
 * }****
 * </pre>
 *
 * @author altin
 * @version 1.0
 * @since 12.08.2023
 */
public class MethodTester<T> {
    private final T instance;
    private final Class<T> clazz;
    private final Supplier<T> instanceSupplier;

    private MethodTester(Class<T> clazz, Supplier<T> instanceSupplier) {
        this.clazz = clazz;
        this.instanceSupplier = instanceSupplier;
        this.instance = instanceSupplier.get();
    }

    public static <T> MethodTester<T> forClass(Class<T> clazz, Supplier<T> instanceSupplier) {
        return new MethodTester<>(clazz, instanceSupplier);
    }

    public void testMethod(String methodName, Object... args) {
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(instance, args);
        } catch (Exception e) {
            throw new MethodTesterRuntimeException("Error testing method " + methodName, e);
        }
    }
}