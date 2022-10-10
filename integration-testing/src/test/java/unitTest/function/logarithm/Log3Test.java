package unitTest.function.logarithm;

import function.logarithm.Ln;
import function.logarithm.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyDouble;

public class Log3Test {
    private final double DELTA = 0.01;
    private static final double ACCURACY = 0.0001;
    private static Log log3;

    @BeforeAll
    static void init(){
        Ln ln = Mockito.mock(Ln.class);
        Mockito.when(ln.apply(anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]));
        log3 = new Log(new Ln(ACCURACY), 3);
    }

    @ParameterizedTest
    @CsvSource({"-0.63, 0.5",
            "-1, 0.3333",
            "-1.26, 0.25",
            "-1.46, 0.2",
            "-5.53, 0.0023"})
    void ifTheArgIsLessThanOneThenFuncIsNegative(double y, double x) {
        assertEquals(y,log3.apply(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource({"1, 3",
            "0.63, 2",
            "2, 9",
            "1.70, 6.5",
            "2.10, 10",
            "4.19, 100"})
    void ifTheArgIsGreaterThanOneThenFuncIsPositive(double y, double x) {
        assertEquals(y,log3.apply(x), DELTA);
    }
}
