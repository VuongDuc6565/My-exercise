package hus.oop.rootsolver;

public class NewtonRaphsonSolver implements RootSolver {
    private double tolerance;
    private int maxIterations;

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance
     * @param maxIterations
     */
    public NewtonRaphsonSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    @Override
    public double solve(MyPolynomial poly, double x0, double dummy) {
        for (int i = 0; i < maxIterations; i++) {
            double f = poly.evaluate(x0);
            double fPrime = poly.derivative().evaluate(x0);

            if (Math.abs(fPrime) < 1e-15) break;

            double x1 = x0 - f / fPrime;
            if (Math.abs(x1 - x0) < tolerance) return x1;

            x0 = x1;
        }

        return x0;
    }
}
