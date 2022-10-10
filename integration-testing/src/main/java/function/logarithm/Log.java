package function.logarithm;

import function.Function;
import function.logarithm.Ln;

public class Log implements Function {
    private final Ln ln;
    private final double base;

    public Log(Ln ln, double base) {
        this.ln = ln;
        this.base = base;
    }

    @Override
    public double apply(double x) {
        return ln.apply(x)/ln.apply(this.base);
    }
}
