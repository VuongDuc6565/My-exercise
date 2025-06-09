package hus.oop.rootsolver;

public class SecantSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public SecantSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    @Override
    public double solve(MyPolynomial poly, double x0, double x1) {
        double f0 = poly.evaluate(x0);
        double f1 = poly.evaluate(x1);

        for (int i = 0; i < maxIterations; i++) {
            if (Math.abs(f1 - f0) < 1e-15) break;

            double x2 = x1 - f1 * (x1 - x0) / (f1 - f0);
            if (Math.abs(x2 - x1) < tolerance) return x2;

            x0 = x1;
            f0 = f1;
            x1 = x2;
            f1 = poly.evaluate(x1);
        }

        return x1;
    }
}
