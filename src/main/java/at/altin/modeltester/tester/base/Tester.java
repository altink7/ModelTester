package at.altin.modeltester.tester.base;

/**
 * This interface defines a tester for classes and methods functionality.
 * Test methods annotated with this should take an instance parameter for testing.
 *
 * @author altin
 * @since 12.08.2023
 * @version 1.0.0
 */
public interface Tester {
    void test(Object instance);
}
