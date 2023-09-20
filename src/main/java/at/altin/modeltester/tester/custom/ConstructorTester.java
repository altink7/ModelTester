package at.altin.modeltester.tester.custom;


import at.altin.modeltester.tester.base.Tester;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import static at.altin.modeltester.utils.ModelValueHelper.createSampleValue;

/**
 * Utility class to test all the constructors of a class.
 * @author altin
 * @since 20.09.2023
 * @version 1.0.6
 */
public class ConstructorTester implements Tester {
    @Override
    public void test(Object instance) {
        Class<?> clazz = instance.getClass();

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();


        Arrays.stream(constructors).forEachOrdered(constructor -> {
            try {
                constructor.setAccessible(true);
                constructor.newInstance();

                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = createSampleValue(parameterTypes[i]);
                }
                constructor.newInstance(parameters);
            } catch (Exception e) {
                throw new RuntimeException("Error testing constructor for class " + clazz.getSimpleName(), e);
            }
        });
    }
}
