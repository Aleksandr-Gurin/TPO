package unitTest.function.system;

import function.logarithm.Ln;
import function.logarithm.Log;
import function.logarithm.LogarithmicFunction;
import function.system.SystemFunction;
import function.trigonometry.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyDouble;

public class SystemFunctionTest {
    private final double DELTA = 0.01;
    private static SystemFunction systemFunction;

    @BeforeAll
    static void init() {
        Sin sin = Mockito.mock(Sin.class);
        Mockito.when(sin.apply(anyDouble())).thenAnswer(i -> Math.sin((Double) i.getArguments()[0]));

        Log log2 = Mockito.mock(Log.class);
        Log log3 = Mockito.mock(Log.class);
        Log log5 = Mockito.mock(Log.class);
        Mockito.when(log2.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0])/Math.log(2));
        Mockito.when(log3.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0])/Math.log(3));
        Mockito.when(log5.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0])/Math.log(5));
        LogarithmicFunction logarithmicFunction = new LogarithmicFunction(log2,log3,log5);

        systemFunction = new SystemFunction(sin, logarithmicFunction);
    }

    static Stream<Arguments> sinValues() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(-0.5, -Math.PI/6),
                Arguments.of(-Math.sqrt(2)/2, -Math.PI/4),
                Arguments.of(-Math.sqrt(3)/2, -Math.PI/3),
                Arguments.of(-1, -Math.PI/2),
                Arguments.of(-Math.sqrt(3)/2, -2*Math.PI/3),
                Arguments.of(-Math.sqrt(2)/2, -3*Math.PI/4),
                Arguments.of(-0.5, -5*Math.PI/6),
                Arguments.of(0, -Math.PI)
        );
    }
    @ParameterizedTest
    @MethodSource("sinValues")
    void ifXIsLessThanOrEqualZeroThenSin(double y, double x) {
        assertEquals(y, systemFunction.apply(x), DELTA);
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
    void ifXIsMoreThanZeroThenLog(double y, double x) {
        assertEquals(y, systemFunction.apply(x), DELTA);
    }

    @Test
    void ifArgIsOneThenReturnZero() {
        assertEquals(0,  systemFunction.apply(1d));
    }

    @Test
    void ifArgIsPositiveInfinityThenReturnsPositiveInfinity() {
        assertEquals(Double.POSITIVE_INFINITY,  systemFunction.apply(Double.POSITIVE_INFINITY));
    }

    @Test
    void ifArgIsNanThenReturnNaN() {
        assertEquals(Double.NaN, systemFunction.apply(Double.NaN));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY})
    void ifArgIsNotFunctionScopeThenReturnNaN(double x) {
        assertEquals(Double.NaN, systemFunction.apply(x));
    }
}
