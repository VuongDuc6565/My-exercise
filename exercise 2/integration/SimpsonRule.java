package hus.oop.integration;

public class SimpsonRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public SimpsonRule(double precision, int maxIterations) {
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

        int n = 4;                 // n0 chẵn
        if (n % 2 == 1) n++;       // đảm bảo chẵn
        double prev = integrate(polynomial, lower, upper, n);
        int iter = 0;
        while (iter < maxIterations) {
            n *= 2;
            double curr = integrate(polynomial, lower, upper, n);
            if (Math.abs(curr - prev) / 15.0 < precision) {   // Simpson sai số ~|I2n-In|/15
                return flipped ? -curr : curr;
            }
            prev = curr;
            iter++;
        }
        return flipped ? -prev : prev;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với numOfSubIntervals (số chẵn) khoảng phân hoạch đều.
     * @param polynomial
     * @param lower
     * @param upper
     * @param numOfSubIntervals
     * @return giá trị xấp xỉ giá trị tích phân.
     */
    private double integrate(MyPolynomial poly, double a, double b, int n) {
        if (n % 2 == 1) n++;                          // Simpson yêu cầu chẵn
        double h = (b - a) / n;
        double sum1 = 0.0, sum2 = 0.0;                // lẻ và chẵn
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 1) sum1 += poly.evaluate(x); // hệ số 4
            else            sum2 += poly.evaluate(x); // hệ số 2
        }
        return (h / 3.0) * (poly.evaluate(a) + poly.evaluate(b) + 4 * sum1 + 2 * sum2);
    }
}
