# ModelTester Framework

The ModelTester framework provides a flexible and comprehensive way to test various aspects of model classes in Java.
It supports testing of getter and setter methods, `toString()`, `equals()`, and `hashCode()` methods.
You can also exclude specific testers, exclude certain methods, and supply custom instance creation logic.

## Getting Started

### Installation

Include the ModelTester framework in your project by adding it as a dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>at.altin</groupId>
    <artifactId>modeltester</artifactId>
    <version>1.0.0</version>
</dependency>
```

Usage

    Create a model class that you want to test. For example, let's consider a DummyClass:

java

public class DummyClass {
    private int id;
    private String name;

    // ... getters, setters, equals, hashCode, and toString methods ...
}

    Write unit tests for your model class using the ModelTester framework:

java

import at.altin.modeltester.ModelTester;
import at.altin.modeltester.tester.standard.EqualsTester;
import at.altin.modeltester.tester.standard.GetterSetterTester;
import at.altin.modeltester.tester.standard.HashCodeTester;
import at.altin.modeltester.tester.standard.ToStringTester;

public class DummyClassTest {

    @Test
    public void testModelClass() {
        ModelTester.forClass(DummyClass.class)
                .exclude(EqualsTester.class) // Exclude EqualsTester for this test
                .customTester(new CustomTester()) // Use a custom tester
                .test();
    }

    private static class CustomTester implements Tester {
        @Override
        public void test(Object instance) {
            // Implement custom testing logic
        }
    }
}

    Run your unit tests using your preferred testing framework (e.g., JUnit, TestNG).

Configuration

    Use the forClass(Class<?> clazz) method to specify the class you want to test.
    Use exclude(Class<? extends Tester> testerClass) to exclude specific testers.
    Use customTester(Tester tester) to add custom testers.
    Use instanceSupplier(Supplier<Object> supplier) to provide custom instance creation logic.
    Use excludeStandardTesters() to remove standard testers for a specific test run.

Contributors

    Altin Kelmendi

License

This project is licensed under the MIT License - see the LICENSE file for details.
