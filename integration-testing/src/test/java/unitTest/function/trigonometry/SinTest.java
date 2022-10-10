package unitTest.function.trigonometry;

import exception.FactorialException;
import function.trigonometry.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SinTest {
    private final double DELTA = 0.001;
    private static final double ACCURACY = 0.0001;
    private static Sin sin;

    @BeforeAll
    static void init(){
        sin = new Sin(ACCURACY);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void ifArgIsNotFunctionScopeThenReturnNaN(double x) {
        assertEquals(Double.NaN,sin.apply(x));
    }

    @Test
    void ifArgIsNaNThenReturnNaN() {
        assertEquals(Double.NaN,sin.apply(Double.NaN));
    }

    static Stream<Arguments> circleValues() {
        return Stream.of(
                Arguments.of(0.5, Math.PI/6),
                Arguments.of(Math.sqrt(2)/2, Math.PI/4),
                Arguments.of(Math.sqrt(3)/2, Math.PI/3),
                Arguments.of(Math.sqrt(3)/2, 2*Math.PI/3),
                Arguments.of(Math.sqrt(2)/2, 3*Math.PI/4),
                Arguments.of(0.5, 5*Math.PI/6),
                Arguments.of(-0.5, -Math.PI/6),
                Arguments.of(-Math.sqrt(2)/2, -Math.PI/4),
                Arguments.of(-Math.sqrt(3)/2, -Math.PI/3),
                Arguments.of(-Math.sqrt(3)/2, -2*Math.PI/3),
                Arguments.of(-Math.sqrt(2)/2, -3*Math.PI/4),
                Arguments.of(-0.5, -5*Math.PI/6)
        );
    }

    @ParameterizedTest
    @MethodSource("circleValues")
    void trigonometricCircleTest(double y, double x) {
        System.out.println(y + " " + x);
        assertEquals(y, sin.apply(x), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI, -Math.PI})
    void intersectionWithAxisXTest (double x) {
        assertEquals(0, sin.apply(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource({"1, 1.5708",
            "-1, -1.5708"})
    void extremumFunctionTest(double y, double x) {
        assertEquals(y, sin.apply(x), DELTA);
    }

    @Test
    void ifFactorialOutOfBoundsLongReturnException() {
        assertThrows(FactorialException.class,()-> sin.calculateFactorial(21));
    }

    @Test
    void ifNotCalculatedFactorialReturnNaN() {
        assertEquals(Double.NaN, sin.apply(7*Math.PI/3));
    }
}
