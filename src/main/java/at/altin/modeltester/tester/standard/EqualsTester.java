package at.altin.modeltester.tester.standard;

import at.altin.modeltester.tester.base.*;

import java.lang.reflect.Method;

/**
 * Utility class to test the correctness of the equals() method of a class.
 * This class tests whether the equals() method of a given class follows the contract rules
 * of reflexivity, symmetry, and consistency.
 * If the equals() method is not defined in the class, it is not tested.
 * Note: Enums usually have predefined equals() implementations.
 * @author altin
 * @since 12.08.2023
 * @version 1.0
 */
public class EqualsTester extends AbstractClassTester implements Tester {

    @Override
    public void test(Object instance) {
        Class<?> clazz = instance.getClass();
        Method equalsMethod = getMethod(clazz, "equals");
        if (equalsMethod == null) {
            return; // No 'equals' method, nothing to test
        }

        Object instance1 = createInstance(clazz);
        Object instance2 = createInstance(clazz);

        if (!instance1.equals(instance1)) {
            throw new AssertionError("Reflexivity property of equals method failed for class " + clazz.getSimpleName());
        }

        if (instance1.equals(instance2) != instance2.equals(instance1)) {
            throw new AssertionError("Symmetry property of equals method failed for class " + clazz.getSimpleName());
        }

        if (instance1.equals(instance2) && instance2.equals(instance1)) {
            if (instance1.hashCode() != instance2.hashCode()) {
                throw new AssertionError("Consistency property of equals and hashCode methods failed for class " + clazz.getSimpleName());
            }
        }
    }
}
