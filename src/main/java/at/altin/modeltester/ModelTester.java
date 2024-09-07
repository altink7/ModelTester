package at.altin.modeltester;

import at.altin.modeltester.tester.base.*;
import at.altin.modeltester.tester.standard.EqualsTester;
import at.altin.modeltester.tester.standard.GetterSetterTester;
import at.altin.modeltester.tester.standard.HashCodeTester;
import at.altin.modeltester.tester.standard.ToStringTester;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class ModelTester extends AbstractClassTester {
    /**
     * This constant contains the standard testers that are run by default.
     */
    public static final List<Tester> STANDARD_TESTER;

    static {
        STANDARD_TESTER = Arrays.asList(
                new EqualsTester(), new GetterSetterTester(), new HashCodeTester(), new ToStringTester());
    }

    private final Class<?> clazz;
    private final List<Class<? extends Tester>> excludedTesters = new ArrayList<>();
    private final List<Tester> testers = new ArrayList<>();
    private Supplier<Object> instanceSupplier;

    private ModelTester(Class<?> clazz) {
        this.clazz = clazz;
        this.instanceSupplier = this::createInstance;
        testers.addAll(STANDARD_TESTER);
    }

    /**
     * The class to be tested
     *
     * @param clazz class
     * @return the model tester
     */
    public static ModelTester forClass(Class<?> clazz) {
        return new ModelTester(clazz);
    }

    /**
     * exclude Standard Testers
     *
     * @param testerClass tester to be excluded
     * @return the model tester
     */
    public ModelTester exclude(Class<? extends Tester> testerClass) {
        excludedTesters.add(testerClass);
        return this;
    }

    /**
     * Add a custom tester, or implement directly a custom tester method
     *
     * @param tester tester to be added
     * @return the model tester
     */
    public ModelTester customTester(Tester tester) {
        testers.add(tester);
        return this;
    }

    /**
     * give a specific supplier instead of using the default one
     *
     * @param supplier class supplier
     * @return the model tester
     */
    public ModelTester instanceSupplier(Supplier<Object> supplier) {
        instanceSupplier = supplier;
        return this;
    }

    public ModelTester excludeStandardTesters() {
        testers.removeAll(STANDARD_TESTER);
        return this;
    }

    public void test() {
        Object instance = instanceSupplier.get();

        testers.forEach(tester -> tester.test(instance));
        runExcludedTesters(instance);
    }

    private void runExcludedTesters(Object instance) {
        excludedTesters.stream()
                .filter(testerClass -> !isTesterExcluded(testerClass))
                .forEach(testerClass -> {
                    try {
                        Tester testerInstance = testerClass.getDeclaredConstructor().newInstance();
                        testerInstance.test(instance);
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException("Error creating instance of tester class " + testerClass.getSimpleName(), e);
                    }
                });
    }

    private boolean isTesterExcluded(Class<? extends Tester> testerClass) {
        return excludedTesters.stream()
                .anyMatch(excludedTester -> excludedTester.equals(testerClass));
    }

    private Object createInstance() {
        try {
            if (clazz.isEnum()) {
                return clazz.getEnumConstants()[0];
            }
            return clazz.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Error creating instance of class " + clazz.getSimpleName(), e);
        }
    }
}
