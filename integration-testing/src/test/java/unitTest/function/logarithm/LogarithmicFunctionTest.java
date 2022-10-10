package unitTest.function.logarithm;

import function.logarithm.Ln;
import function.logarithm.Log;
import function.logarithm.LogarithmicFunction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyDouble;

public class LogarithmicFunctionTest {
    private final double DELTA = 0.01;
    private static LogarithmicFunction logarithmicFunction;

    @BeforeAll
    static void init() {
        Log log2 = Mockito.mock(Log.class);
        Log log3 = Mockito.mock(Log.class);
        Log log5 = Mockito.mock(Log.class);

        Mockito.when(log2.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0])/Math.log(2));
        Mockito.when(log3.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0])/Math.log(3));
        Mockito.when(log5.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0])/Math.log(5));
        logarithmicFunction = new LogarithmicFunction(log2, log3, log5);
    }


    @ParameterizedTest
    @CsvSource({"-0.05, 0.005",
            "-0.04, 0.006",
            "-0.03, 0.007",
            "-0.02, 0.009",
            "-0.01, 0.014",
            "-0.009, 0.016",
            "-0.008, 0.017",
            "-0.006, 0.02",
            "-0.004, 0.024",
            "-0.003, 0.03",
            "-0.002, 0.035",
            "-0.001, 0.049"})
    void equalityPointsTest(double y, double x) {
        assertEquals(y, logarithmicFunction.apply(x), DELTA);
    }

    @Test
    void intersectsXAxisWhenXisOne() {
        assertEquals(0, logarithmicFunction.apply(1), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -3, Double.NEGATIVE_INFINITY})
    void ifArgIsLessThanZeroThenReturnNaN(double x) {
        assertEquals(Double.NaN, logarithmicFunction.apply(x));
    }

    @Test
    void ifArgIsPositiveInfinityThenReturnsPositiveInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, logarithmicFunction.apply(Double.POSITIVE_INFINITY));
    }

    @Test
    void ifArgIsZeroThenReturnsNegativeInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, logarithmicFunction.apply(0));
    }

    @Test
    void ifArgIsNanThenReturnNaN() {
        assertEquals(Double.NaN, logarithmicFunction.apply(Double.NaN));
    }
}
