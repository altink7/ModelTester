package at.altin.modeltester.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestModel {
    private String string;
    private int i;
    private double d;
    private boolean b;
    private char c;
    private float f;
    private long l;
    private byte by;
    private TestModel testModel;
}
