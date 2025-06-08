package hus.oop.integration;

import java.util.Random;

public class TestIntegrationCalculator {
    private MyPolynomial polynomial;          // dùng để lưu đa thức đang test
    private static final Random RAND = new Random();

    public TestIntegrationCalculator(MyPolynomial polynomial) {
        this.polynomial = polynomial;
    }

    public static void main(String[] args) {
        TestIntegrationCalculator tester = new TestIntegrationCalculator(null);
        tester.testArrayPolynomial();
        tester.testListPolynomial();
    }


    public void testArrayPolynomial() {
        System.out.println("TEST MyArrayPolynomial ");

        int size = RAND.nextInt(5) + 3;
        MyArrayPolynomial P = new MyArrayPolynomial();
        for (int i = 0; i < size; i++) {
            P.append(RAND.nextDouble() * 20 - 10);
        }
        this.polynomial = P;

        /* Sao lưu đa thức gốc để tính tích phân */
        MyArrayPolynomial P0 = new MyArrayPolynomial();
        for (double c : P.coefficients()) P0.append(c);

        System.out.println("P(x) ban đầu  = " + P0);

        /* Thêm hệ số mới */
        double extra = RAND.nextDouble() * 6 - 3;
        P.append(extra);
        System.out.println("Sau append(" + String.format("%.4f", extra) + ")  => " + P);

        /* Sửa hệ số bất kỳ */
        int idxSet = RAND.nextInt(P.degree() + 1);
        double newCoef = RAND.nextDouble() * 14 - 7;
        P.set(newCoef, idxSet);
        System.out.println("Sau set bậc " + idxSet + " = " + String.format("%.4f", newCoef) + " => " + P);

        /* “Xoá” hệ số (đặt 0) */
        int idxDel = RAND.nextInt(P.degree() + 1);
        P.set(0.0, idxDel);
        System.out.println("Sau xoá bậc " + idxDel + " (đặt 0)      => " + P);

        /* Tạo Q để test đại số */
        MyArrayPolynomial Q = new MyArrayPolynomial();
        for (int i = 0; i < size; i++) Q.append(RAND.nextDouble() * 10 - 5);
        System.out.println("Q(x)          = " + Q);
        System.out.println("P + Q         = " + P.plus(Q));
        System.out.println("P - Q         = " + P.minus(Q));
        System.out.println("P * Q         = " + P.multiply(Q));

        /* Giá trị tại x ngẫu nhiên */
        double xVal = RAND.nextDouble() * 10 - 5;
        System.out.printf("P(%.3f)       = %.6f%n", xVal, P.evaluate(xVal));

        /* Tích phân xác định đa thức gốc trên [1,5] */
        IntegrationCalculator calc = new IntegrationCalculator(new SimpsonRule(1e-9, 25), P0);
        double integral = calc.integrate(1.0, 5.0);
        System.out.printf("∫₁⁵ P(x) dx   = %.8f%n", integral);
    }

    public void testListPolynomial() {
        System.out.println("TEST MyListPolynomial ");

        int size = RAND.nextInt(5) + 3;
        MyListPolynomial P = new MyListPolynomial();
        for (int i = 0; i < size; i++) {
            P.append(RAND.nextDouble() * 20 - 10);
        }
        this.polynomial = P;

        MyListPolynomial P0 = new MyListPolynomial();
        for (double c : P.coefficients()) P0.append(c);

        System.out.println("P(x) ban đầu  = " + P0);

        double extra = RAND.nextDouble() * 6 - 3;
        P.append(extra);
        System.out.println("Sau append(" + String.format("%.4f", extra) + ")  => " + P);

        int idxSet = RAND.nextInt(P.degree() + 1);
        double newCoef = RAND.nextDouble() * 10 - 5;
        P.set(newCoef, idxSet);
        System.out.println("Sau set bậc " + idxSet + " = " + String.format("%.4f", newCoef) + " => " + P);

        int idxDel = RAND.nextInt(P.degree() + 1);
        P.set(0.0, idxDel);
        System.out.println("Sau xoá bậc " + idxDel + " (đặt 0)      => " + P);

        MyListPolynomial Q = new MyListPolynomial();
        for (int i = 0; i < size; i++) Q.append(RAND.nextDouble() * 8 - 4);
        System.out.println("Q(x)          = " + Q);
        System.out.println("P + Q         = " + P.plus(Q));
        System.out.println("P - Q         = " + P.minus(Q));
        System.out.println("P * Q         = " + P.multiply(Q));

        System.out.printf("P(3)          = %.6f%n", P.evaluate(3.0));

        IntegrationCalculator calc = new IntegrationCalculator(new TrapezoidRule(1e-7, 25), P0);
        double integral = calc.integrate(2.0, 6.0);
        System.out.printf("∫_2_6 P(x) dx   = %.8f%n", integral);
    }
}
