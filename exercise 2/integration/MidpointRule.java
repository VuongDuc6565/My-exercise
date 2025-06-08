package hus.oop.integration;

public class MidpointRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public MidpointRule(double precision, int maxIterations) {
        this.precision     = precision;
        this.maxIterations = maxIterations;
    }

    /**
     * Tính xấp xỉ giá trị tích phân. Giá trị xấp xỉ được chấp nhận nếu phép tính đạt độ chính xác đã cho,
     * hoặc có số vòng vượt quá ngưỡng quy định.
     * Độ chính xác được xác định như sau, chọn n0 tùy ý, sau đó tính I_n với n = n0, 2n0, 4n0, ...
     * Việc tính toán dừng lại khi |I_2n - In|/3 < eps (precision), hoặc số lần chia đôi vượt quá ngưỡng quy định (maxIterations).
     * @param polynomial
     * @param lower
     * @param upper
     * @return
     */
    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        if (lower == upper) return 0;
        boolean flipped = false;
        if (upper < lower) { double tmp = lower; lower = upper; upper = tmp; flipped = true; }

        int n = 4;
        double prev = integrate(polynomial, lower, upper, n);
        int iter = 0;
        while (iter < maxIterations) {
            n *= 2;
            double curr = integrate(polynomial, lower, upper, n);
            if (Math.abs(curr - prev) / 3.0 < precision) {
                return flipped ? -curr : curr;
            }
            prev = curr;
            iter++;
        }
        return flipped ? -prev : prev;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với numOfSubIntervals khoảng phân hoạch đều.
     * @param polynomial
     * @param lower
     * @param upper
     * @param numOfSubIntervals
     * @return giá trị xấp xỉ giá trị tích phân.
     */
    private double integrate(MyPolynomial poly, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double mid = a + (i + 0.5) * h;
            sum += poly.evaluate(mid);
        }
        return h * sum;
    }
}
