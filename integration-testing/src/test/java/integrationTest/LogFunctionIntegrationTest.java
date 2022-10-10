package integrationTest;

import function.logarithm.Ln;
import function.logarithm.Log;
import function.logarithm.LogarithmicFunction;
import function.trigonometry.Sin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFunctionIntegrationTest {
    private final double ACCURACY = 0.0001;
    private final double DELTA = 0.01;
    private Log log2Spy;
    private Log log3Spy;
    private Log log5Spy;

    @BeforeEach
    void setUp() {
        Ln ln = new Ln(ACCURACY);
        log2Spy = Mockito.spy(new Log(ln, 2));
        log3Spy = Mockito.spy(new Log(ln, 3));
        log5Spy = Mockito.spy(new Log(ln, 5));
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
    void inputPositiveNumberTest(double y, double x) {
        LogarithmicFunction logarithmicFunction = new LogarithmicFunction(log2Spy, log3Spy, log5Spy);
        assertEquals(y, logarithmicFunction.apply(x), DELTA);

        Mockito.verify(log2Spy).apply(x);
        Mockito.verify(log3Spy, Mockito.times(2)).apply(x);
        Mockito.verify(log5Spy).apply(x);

    }

    @Test
    void inputPositiveInfinityTest() {
        LogarithmicFunction logarithmicFunction = new LogarithmicFunction(log2Spy, log3Spy, log5Spy);
        assertEquals(Double.POSITIVE_INFINITY, logarithmicFunction.apply(Double.POSITIVE_INFINITY));

        Mockito.verify(log2Spy, Mockito.never()).apply(Double.POSITIVE_INFINITY);
        Mockito.verify(log3Spy, Mockito.never()).apply(Double.POSITIVE_INFINITY);
        Mockito.verify(log5Spy, Mockito.never()).apply(Double.POSITIVE_INFINITY);

    }

    @Test
    void inputZeroTest() {
        LogarithmicFunction logarithmicFunction = new LogarithmicFunction(log2Spy, log3Spy, log5Spy);
        assertEquals(Double.NEGATIVE_INFINITY, logarithmicFunction.apply(0));

        Mockito.verify(log2Spy, Mockito.never()).apply(0);
        Mockito.verify(log3Spy, Mockito.never()).apply(0);
        Mockito.verify(log5Spy, Mockito.never()).apply(0);

    }
}
