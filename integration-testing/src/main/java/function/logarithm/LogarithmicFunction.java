package function.logarithm;

import function.Function;
import function.logarithm.Log;

public class LogarithmicFunction implements Function {
    private final Log log2;
    private final Log log3;
    private final Log log5;

    public LogarithmicFunction(Log log2, Log log3, Log log5) {
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
    }

    @Override
    public double apply(double x) {
        if (x == Double.POSITIVE_INFINITY)
           return Double.POSITIVE_INFINITY;
        if (x == 0)
            return Double.NEGATIVE_INFINITY;
        return Math.pow(Math.pow(log5.apply(x) - log2.apply(x) + log3.apply(x), 2), 3) * log3.apply(x);
    }
}
