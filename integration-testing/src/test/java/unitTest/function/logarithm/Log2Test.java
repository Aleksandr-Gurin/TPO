package unitTest.function.logarithm;

import function.logarithm.Ln;
import function.logarithm.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyDouble;

public class Log2Test {
    private final double DELTA = 0.01;
    private static final double ACCURACY = 0.0001;
    private static Log log2;

    @BeforeAll
    static void init(){
        Ln ln = Mockito.mock(Ln.class);
        Mockito.when(ln.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]));
        log2 = new Log(new Ln(ACCURACY), 2);
    }

    @ParameterizedTest
    @CsvSource({"-1, 0.5",
            "-1.59, 0.3333",
            "-2, 0.25",
            "-2.32, 0.2",
            "-8.76, 0.0023"})
    void ifTheArgIsLessThanOneThenFuncIsNegative(double y, double x) {
        assertEquals(y,log2.apply(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource({"1, 2",
            "1.5850, 3",
            "2, 4",
            "2.70, 6.5",
            "3.32, 10",
            "6.64, 100"})
    void ifTheArgIsGreaterThanOneThenFuncIsPositive(double y, double x) {
        assertEquals(y,log2.apply(x), DELTA);
    }
}
