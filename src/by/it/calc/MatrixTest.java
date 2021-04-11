package by.it.calc;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MatrixTest {

    @Test
    public void testMatrixToStringDouble() {
        double [][] mart = {{1.0, 2.0, 3.0},{1.0,2.0,3.4}};
        Matrix matrix = new Matrix(mart);
        String actual = matrix.toString();
        String expected = "{{1.0,2.0,3.0},{1.0,2.0,3.4}}";
        assertEquals("Error calc", expected, actual);
    }

    @Test
    public void testMatrixToStringEnterVector() {
        double [][] mart = {{1.0, 2.0, 3.0},{1.0,2.0,3.4}};
        Matrix matrix = new Matrix(mart);
        Matrix matrixA = new Matrix(matrix);
        String actual = matrixA.toString();
        String expected = "{{1.0,2.0,3.0},{1.0,2.0,3.4}}";
        assertEquals("Error calc", expected, actual);
    }
}