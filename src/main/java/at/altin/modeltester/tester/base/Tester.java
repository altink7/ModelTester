package at.altin.modeltester.tester.base;

/**
 * This interface defines a tester for classes and methods functionality.
 * Test methods annotated with this should take an instance parameter for testing.
 *
 * @author altin
 * @version 1.0.0
 * @since 12.08.2023
 */
public interface Tester {
    /**
     * Test.
     *
     * @param instance the instance
     */
    void test(Object instance);
}
