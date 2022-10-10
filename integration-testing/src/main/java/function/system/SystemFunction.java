package function.system;

import function.Function;
import function.logarithm.LogarithmicFunction;
import function.trigonometry.Sin;

public class SystemFunction implements Function {
    private final Sin sin;
    private final LogarithmicFunction logarithmicFunction;

    public SystemFunction(Sin sin, LogarithmicFunction logarithmicFunction) {
        this.sin = sin;
        this.logarithmicFunction = logarithmicFunction;
    }

    @Override
    public double apply(double x) {
        if (x <= 0) {
            return sin.apply(x);
        } else {
            return logarithmicFunction.apply(x);
        }
    }
}
