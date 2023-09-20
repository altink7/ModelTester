package at.altin.modeltester.model;

import at.altin.modeltester.ModelTester;
import at.altin.modeltester.tester.custom.ConstructorTester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String string;
    private int i;
    private double d;
    private boolean b;
    private char c;
    private float f;
    private long l;
    private byte by;
    private Test test;
}
