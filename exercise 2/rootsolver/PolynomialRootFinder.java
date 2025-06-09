package hus.oop.rootsolver;

public class PolynomialRootFinder {
    private MyPolynomial polynomial;
    private RootSolver rootSolver;

    /**
     * Khởi tạo mặc định
     */
    public PolynomialRootFinder() {
        this.polynomial = null;
        this.rootSolver = null;
    }

    /**
     * Khởi tạo đa thức.
     * @param polynomial
     */
    public PolynomialRootFinder(MyPolynomial polynomial) {
        this.polynomial = polynomial;
        this.rootSolver = null;
    }

    /**
     * Khởi tạo đa thức và phương pháp tìm nghiệm.
     * @param polynomial
     * @param rootSolver
     */
    public PolynomialRootFinder(MyPolynomial polynomial, RootSolver rootSolver) {
        this.polynomial = polynomial;
        this.rootSolver = rootSolver;
    }

    public void setPolynomial(MyPolynomial polynomial) {
        this.polynomial = polynomial;
    }

    public void setRootSolver(RootSolver rootSolver) {
        this.rootSolver = rootSolver;
    }

    /**
     * Tìm nghiệm của đa thức trong đoạn từ lower đến upper.
     * @param lower
     * @param upper
     * @return nghiệm tìm được
     */
    public double solve(double lower, double upper) {
        if (polynomial == null) {
            throw new IllegalStateException("Polynomial chưa được khởi tạo.");
        }
        if (rootSolver == null) {
            throw new IllegalStateException("RootSolver chưa được khởi tạo.");
        }
        // Giả sử rootSolver có phương thức solve(MyPolynomial, double, double)
        return rootSolver.solve(polynomial, lower, upper);
    }
}
