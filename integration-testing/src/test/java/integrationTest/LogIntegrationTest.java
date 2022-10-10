package integrationTest;

import function.logarithm.Ln;
import function.logarithm.Log;
import function.trigonometry.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogIntegrationTest {
    private final double ACCURACY = 0.0001;
    private final double DELTA = 0.01;
    private Ln spyLn;

    @BeforeEach
    void setUp() {
        spyLn = Mockito.spy(new Ln(ACCURACY));
    }

    static Stream<Arguments> logTestData() {
        return Stream.of(
                Arguments.of(2, -1, Double.NaN),
                Arguments.of(3, -1, Double.NaN),
                Arguments.of(5, -1, Double.NaN),
                Arguments.of(2, 0, Double.NEGATIVE_INFINITY),
                Arguments.of(3, 0, Double.NEGATIVE_INFINITY),
                Arguments.of(5, 0, Double.NEGATIVE_INFINITY),
                Arguments.of(2, Double.NaN, Double.NaN),
                Arguments.of(3, Double.NaN, Double.NaN),
                Arguments.of(5, Double.NaN, Double.NaN),
                Arguments.of(2, Double.NEGATIVE_INFINITY, Double.NaN),
                Arguments.of(3, Double.NEGATIVE_INFINITY, Double.NaN),
                Arguments.of(5, Double.NEGATIVE_INFINITY, Double.NaN),
                Arguments.of(2, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
                Arguments.of(3, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
                Arguments.of(5, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
                Arguments.of(2, 1, 0),
                Arguments.of(3, 1, 0),
                Arguments.of(5, 1, 0),
                Arguments.of(2, 2, 1),
                Arguments.of(3, 3, 1),
                Arguments.of(5, 5, 1),
                Arguments.of(2, 10, 3.322),
                Arguments.of(3, 10, 2.096),
                Arguments.of(5, 10, 1.431),
                Arguments.of(2, 100, 6.644),
                Arguments.of(3, 100, 4.192),
                Arguments.of(5, 100, 2.861),
                Arguments.of(2, 0.5, -1),
                Arguments.of(3, 0.5, -0.631),
                Arguments.of(5, 0.5, -0.431)
        );
    }

    @ParameterizedTest
    @MethodSource("logTestData")
    void logUseLn(double base, double x, double y) {
        Log log = new Log(spyLn, base);
        assertEquals(y, log.apply(x), DELTA);

        if (x == base) {
            Mockito.verify(spyLn, Mockito.times(2)).apply(x);
        } else {
            Mockito.verify(spyLn).apply(x);
            Mockito.verify(spyLn).apply(base);
        }
    }
}
