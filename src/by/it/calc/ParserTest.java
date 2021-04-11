package by.it.calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    Parser parser;
    @Before
    public void setUp() throws Exception {
        parser = new Parser();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calc() throws CalcException {
        Var var = parser.calc("A=2+5.3");
        double actual = Double.parseDouble(var.toString());
        double expected = 7.3;
        assertEquals("Error calc", expected, actual, 1e-5);

    }
    @Test
    public void calcA2() throws  CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        Var var = parser.calc("B=A*3.5");
        double actual = Double.parseDouble(var.toString());
        double expected = 25.55;
        assertEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void calcA3() throws  CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        parser.calc("B=A*3.5");
        Var var = parser.calc("B1=B+0.11*-5");
        double actual = Double.parseDouble(var.toString());
        double expected = 25;
        assertEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void calcA4() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        parser.calc("B=A*3.5");
        parser.calc("B1=B+0.11*-5");
        Var var = parser.calc("B2=A/2-1");
        double actual = Double.parseDouble(var.toString());
        double expected = 2.65;
        assertEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void calcB1() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        parser.calc("B=A*3.5");
        parser.calc("B1=B+0.11*-5");
        parser.calc("B2=A/2-1");
        Var var = parser.calc("C=B+(A*2)");
        double actual = Double.parseDouble(var.toString());
        double expected = 40.15;
        assertEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void calcB2() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        parser.calc("B=A*3.5");
        parser.calc("C=B+(A*2)");
        Var var = parser.calc("D=((C-0.15)-20)/(7-5)");
        double actual = Double.parseDouble(var.toString());
        double expected = 10;
        assertEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void calcB3() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        parser.calc("B=A*3.5");
        parser.calc("C=B+(A*2)");
        parser.calc("D=((C-0.15)-20)/(7-5)");
        Var var = parser.calc("E={2,3}*(D/2)");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {10, 15};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void vectorAddScalar() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("E={2,3,4}+2");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {4, 5, 6};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void vectorAddVector() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("E={2,3,4}+{5,6,7}");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {7, 9, 11};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void vectorSubScalar() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("E={2,3,4}-5");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {-3, -2, -1};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void vectorSubVector() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("{2,3,4}-{5,6,7}");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {-3, -3, -3};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void vectorMulVector() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("{2,3,4}*{5,6,7}");
        double actual = Double.parseDouble(var.toString());
        double expected = 56;
        assertEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void vectorDivScalar() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("{2,3,4}/3");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {0.6666666666666666, 1.0, 1.3333333333333333};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void matrixMulVector() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("{{1,2},{8,3}}*{1,2}");
        double[] actual = ((Vector) var).getValue();
        double[] expected = {5.0, 14.0};
        assertArrayEquals("Error calc", expected, actual, 1e-5);
    }

    @Test
    public void matrixAddMatrix() throws CalcException {
        Parser parser = new Parser();
        Var var = parser.calc("{{1,2},{8,3}}+{{1,2},{8,3}}");
        double[][] actual = ((Matrix) var).getValue();
        double[][] expected = {{2.0, 4.0}, {16.0, 6.0}};
        for (int i = 0; i < 2; i++) {
            assertArrayEquals("Error calc", expected[i], actual[i], 1e-5);
        }
    }

}