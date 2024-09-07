package at.altin.modeltester.model;

import at.altin.modeltester.ModelTester;
import at.altin.modeltester.tester.custom.ConstructorTester;
import at.altin.modeltester.tester.custom.CopyTester;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * This class tests all model classes.
 * <p>
 *     It uses the {@link ModelTester} to test all model classes.
 *     The {@link ModelTester} uses the {@link ConstructorTester} and the {@link CopyTester} to test the model classes.
 *     The {@link ConstructorTester} tests the constructors of the model classes.
 *     The {@link CopyTester} tests the copy constructors of the model classes.
 * </p>
 *
 */
class ModelUnitTestModel {

    @ParameterizedTest
    @MethodSource("classProvider")
    void testModelClasses(Class<?> clazz) {
        ModelTester.forClass(clazz)
                .customTester(new CopyTester())
                .test();
    }

    static Stream<Class<?>> classProvider() {
        return Stream.of(
                Address.class,
                Customer.class,
                OrderHistory.class,
                OrderItem.class,
                OrderType.class,
                Payment.class,
                Product.class,
                Promotion.class,
                PurchaseOrder.class,
                Review.class,
                WishList.class,
                TestModel.class
        );
    }
}