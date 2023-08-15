package at.altin.modeltester.tester.standard;

import at.altin.modeltester.tester.base.*;

import java.lang.reflect.Method;

/**
 * Utility class to test the correctness of the hashCode() method of a class.
 * This class tests whether the hashCode() method of a given class produces consistent results,
 * and it handles both enums and regular classes.
 * If the hashCode() method is not defined in the class, it is not tested.
 * Note: Enums usually have predefined hashCode() implementations.
 * @see EqualsTester
 * @author altin
 * @since 12.08.2023
 * @version 1.0
 */
public class HashCodeTester extends AbstractClassTester implements Tester {

    @Override
    public void test(Object instance) {
        Class<?> clazz = instance.getClass();
        Method hashCodeMethod = getMethod(clazz, "hashCode");

        if (hashCodeMethod == null) {
            return; // No hashCode method, nothing to test
        }

        Object instance1 = createInstance(clazz);
        Object instance2 = createInstance(clazz);

        if (instance1.equals(instance2) && instance1.hashCode() != instance2.hashCode()) {
            throw new AssertionError("Consistency property of hashCode method failed for class " + clazz.getSimpleName());
        }
    }
}
