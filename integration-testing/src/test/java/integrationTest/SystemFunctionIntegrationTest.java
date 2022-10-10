package integrationTest;

import function.logarithm.Ln;
import function.logarithm.Log;
import function.logarithm.LogarithmicFunction;
import function.system.SystemFunction;
import function.trigonometry.Sin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemFunctionIntegrationTest {
    private final double ACCURACY = 0.0001;
    private final double DELTA = 0.01;
    private Sin sinSpy;
    private LogarithmicFunction logFuncSpy;


    @BeforeEach
    void setUp() {
        sinSpy = Mockito.spy(new Sin(ACCURACY));
        Ln ln = new Ln(ACCURACY);
        Log log2 = new Log(ln, 2);
        Log log3 = new Log(ln, 3);
        Log log5 = new Log(ln, 5);
        logFuncSpy = Mockito.spy(new LogarithmicFunction(log2, log3, log5));
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
    void systemUseSin(double y, double x) {
        SystemFunction systemFunction = new SystemFunction(sinSpy, logFuncSpy);
        assertEquals(y, systemFunction.apply(x), DELTA);

        Mockito.verify(sinSpy).apply(x);
        Mockito.verify(logFuncSpy, Mockito.never()).apply(x);
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
    void systemUseLogFunc(double y, double x) {
        SystemFunction systemFunction = new SystemFunction(sinSpy, logFuncSpy);
        assertEquals(y, systemFunction.apply(x), DELTA);

        Mockito.verify(logFuncSpy).apply(x);
        Mockito.verify(sinSpy, Mockito.never()).apply(x);
    }
}
