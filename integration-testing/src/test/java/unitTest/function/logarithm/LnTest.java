package unitTest.function.logarithm;

import function.logarithm.Ln;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {
    private final double DELTA = 0.01;
    private static final double ACCURACY = 0.0001;
    private static Ln ln;

    @BeforeAll
    static void init(){
        ln = new Ln(ACCURACY);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -3, Double.NEGATIVE_INFINITY})
    void ifArgIsNotFunctionScopeThenReturnNaN(double x) {
        assertEquals(Double.NaN, ln.apply(x));
    }

    @Test
    void intersectsXAxisWhenXisOne() {
        assertEquals(0, ln.apply(1), DELTA);
    }

    @ParameterizedTest
    @CsvSource({"-0.6931, 0.5",
            "-1.0986, 0.3333",
            "-1.3863, 0.25",
            "-1.6094, 0.2",
            "-6.0748, 0.0023"})
    void ifTheArgIsLessThanOneThenFuncIsNegative(double y, double x) {
        assertEquals(y, ln.apply(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource({"1, 2.7183",
            "0.6931, 2",
            "1.0986, 3",
            "1.5041, 4.5",
            "2.3026, 10",
            "4.6052, 100"})
    void ifTheArgIsGreaterThanOneThenFuncIsPositive(double y, double x) {
        assertEquals(y, ln.apply(x), DELTA);
    }

    @Test
    void ifArgIsPositiveInfinityThenReturnsPositiveInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, ln.apply(Double.POSITIVE_INFINITY));
    }

    @Test
    void ifArgIsZeroThenReturnsNegativeInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, ln.apply(0));
    }

    @Test
    void ifArgIsNanThenReturnNaN() {
        assertEquals(Double.NaN, ln.apply(Double.NaN));
    }
}
