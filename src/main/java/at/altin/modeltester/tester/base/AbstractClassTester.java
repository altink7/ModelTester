package at.altin.modeltester.tester.base;

import at.altin.utils.exception.ModelTesterRuntimeException;

import java.lang.reflect.Method;

/**
 * Holds Basic Implementing for all Testers
 *
 * @author altin
 * @version 1.0
 * @since 12.08.2023
 */
public abstract class AbstractClassTester {

    /**
     * Creates an instance of the given class.
     *
     * @param clazz The class to create an instance of.
     * @return An instance of the class.
     */
    protected static Object createInstance(Class<?> clazz) {
        try {
            if (clazz.isEnum()) {
                return clazz.getEnumConstants()[0];
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (ReflectiveOperationException e) {
            throw new ModelTesterRuntimeException("Error creating instance of class " + clazz.getSimpleName(), e);
        }
    }

    /**
     * Retrieves the given method of the given class.
     *
     * @param clazz  The class to retrieve the toString() method from.
     * @param method method to be tested, for example: toString
     * @return The given method or null if not found.
     */
    protected static Method getMethod(Class<?> clazz, String method) {
        try {
            return clazz.getDeclaredMethod(method);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ModelTesterRuntimeException("Error creating instance of class " + className, e);
        }
    }

    /**
     * check if the given class implement the given interface
     * @param clazz class to be tested
     * @param interfaceName interface to be tested ( implemnted by class)
     */

    public static boolean isInstanceOfInterface(Class<?> clazz, Class<?> interfaceName){
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> interfaceClass : interfaces) {
            if (interfaceClass.equals(interfaceName)) {
                return true;
            }
        }
        return false;
    }
}
