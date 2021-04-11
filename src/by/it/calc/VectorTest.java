package by.it.calc;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class VectorTest {

    @Test
    public void testVectorToStringEnterString() {
        String vect = "{1.0, 2.0, 3.0}";
        Vector vector = new Vector(vect);
        String actual = vector.toString();
        String expected = "{1.0,2.0,3.0}";
        assertEquals("Error calc", expected, actual);
    }
    @Test
    public void testVectorToStringEnterVector() {
        double [] arr = {1.0, 2.0, 3.0};
        Vector vect= new Vector(arr);
        Vector vector = new Vector(vect);
        String actual = vector.toString();
        String expected = "{1.0,2.0,3.0}";
        assertEquals("Error calc", expected, actual);
    }
}