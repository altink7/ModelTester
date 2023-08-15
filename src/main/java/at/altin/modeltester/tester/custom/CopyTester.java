package at.altin.modeltester.tester.custom;

import at.altin.modeltester.tester.base.*;

import java.lang.reflect.Method;

/**
 * Utility class to test the correctness of the copy() method of a class.
 * This class tests whether the copy() method of a given class creates a deep copy
 * that follows the contract rules of being a separate instance with the same properties.
 * If the copy() method is not defined in the class, it is not tested.
 * @author altin
 * @since 12.08.2023
 * @version 1.0
 */
public class CopyTester extends AbstractClassTester implements Tester {

    @Override
    public void test(Object instance) {
        Class<?> clazz = instance.getClass();

        Method copyMethod = getMethod(clazz, "copy");
        if (copyMethod == null) {
            return; // No 'copy' method, nothing to test
        }

        try {
            Object copy = copyMethod.invoke(instance);

            if (copy == instance) {
                throw new AssertionError("Copy method did not create a new instance for class " + clazz.getSimpleName());
            }

            if (!instance.equals(copy)) {
                throw new AssertionError("Copy method did not create an equivalent copy for class " + clazz.getSimpleName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error testing copy method for class " + clazz.getSimpleName(), e);
        }
    }

}
