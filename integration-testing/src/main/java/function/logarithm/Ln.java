package function.logarithm;

import function.Function;

public class Ln implements Function {
    private final double ACCURACY;

    public Ln(double ACCURACY) {
        this.ACCURACY = ACCURACY;
    }

    @Override
    public double apply(double x) {
        if (x < 0 || Double.isNaN(x))
            return Double.NaN;
        if (x == 0)
            return Double.NEGATIVE_INFINITY;
        if (x == Double.POSITIVE_INFINITY)
            return Double.POSITIVE_INFINITY;
        int n = 0;
        double cur = 0;
        double prev = -1;
        while (Math.abs(cur - prev) >= ACCURACY) {
            prev = cur;
            cur += -2 * 1d/(2*n+1) * Math.pow((1-x)/(1+x),2*n+1);
            ++n;
        }
        return cur;
    }
}
