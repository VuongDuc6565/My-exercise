package hus.oop.rootsolver;

public class BisectionSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public BisectionSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }
    @Override
    public double solve(MyPolynomial poly, double lower, double upper) {
        double a = lower;
        double b = upper;
        double c = a;

        if (poly.evaluate(a) * poly.evaluate(b) >= 0) {
            throw new IllegalArgumentException("Interval không hợp lệ.");
        }

        for (int i = 0; i < maxIterations; i++) {
            c = (a + b) / 2;
            double fc = poly.evaluate(c);

            if (Math.abs(fc) < tolerance || (b - a) / 2 < tolerance)
                return c;

            if (fc * poly.evaluate(a) < 0)
                b = c;
            else
                a = c;
        }

        return c;
    }
}
