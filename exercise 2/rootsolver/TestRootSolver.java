package hus.oop.rootsolver;

public class TestRootSolver {
    private PolynomialRootFinder rootFinder;

    public TestRootSolver(PolynomialRootFinder rootFinder) {
        this.rootFinder = rootFinder;
    }

    public static void main(String[] args) {
        // Tạo một đối tượng RootSolver (ví dụ BisectionSolver)
        RootSolver bisectionSolver = new BisectionSolver(1e-6, 100);
        PolynomialRootFinder finder = new PolynomialRootFinder(bisectionSolver);

        // Truyền finder vào lớp TestRootSolver
        TestRootSolver test = new TestRootSolver(finder);

        // Gọi các hàm test
        test.testMyArrayPolynomial();
        System.out.println("-----------------------------------");
        test.testMyListPolynomial();
        System.out.println("-----------------------------------");
        test.testMyLinkedListPolynomial();
    }

    public void testMyArrayPolynomial() {
        int size = (int)(Math.random() * 5 + 3);
        double[] coeffs = new double[size];
        for (int i = 0; i < size; i++) {
            coeffs[i] = Math.random() * 20 - 10;
        }

        MyPolynomial poly = new MyArrayPolynomial();
        for (int i = 0; i < size; i++) {
            poly.addAtEnd(coeffs[i]);
        }

        System.out.println("Original MyArrayPolynomial:");
        System.out.println(poly);

        poly.set(1, poly.coefficientAt(1) - 1); // chỉnh sửa hệ số bậc 1
        poly.addAtStart(5.5);                   // thêm hệ số mới ở đầu
        System.out.println("After modification:");
        System.out.println(poly);

        MyPolynomial another = new MyArrayPolynomial();
        another.addAtEnd(0);
        another.addAtEnd(-2);
        another.addAtEnd(1); // x^2 - 2x

        MyPolynomial sum = poly.plus(another);
        MyPolynomial diff = poly.minus(another);
        MyPolynomial prod = poly.multiply(another);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + prod);

        double x = 2.0;
        System.out.println("P(" + x + ") = " + poly.evaluate(x));

        double a = -10, b = 10;
        while (poly.evaluate(a) * poly.evaluate(b) > 0) {
            a = Math.random() * 20 - 10;
            b = Math.random() * 20 - 10;
        }

        System.out.printf("Interval found: [%.2f, %.2f]\n", a, b);

        RootSolver secant = new SecantSolver(1e-6, 100);
        RootSolver bisection = new BisectionSolver(1e-6, 100);
        RootSolver newton = new NewtonRaphsonSolver(1e-6, 100);

        PolynomialRootFinder finder = new PolynomialRootFinder(secant);
        System.out.println("Secant root: " + finder.solve(poly, a, b));

        finder = new PolynomialRootFinder(bisection);
        System.out.println("Bisection root: " + finder.solve(poly, a, b));

        finder = new PolynomialRootFinder(newton);
        System.out.println("Newton-Raphson root: " + finder.solve(poly, a, b));
    }

    public void testMyListPolynomial() {
        int size = (int)(Math.random() * 5 + 3);
        double[] coeffs = new double[size];
        for (int i = 0; i < size; i++) {
            coeffs[i] = Math.random() * 20 - 10;
        }

        MyPolynomial poly = new MyListPolynomial();
        for (int i = 0; i < size; i++) {
            poly.addAtEnd(coeffs[i]);
        }

        System.out.println("Original MyListPolynomial: ");
        System.out.println(poly);

        poly.set(0, poly.coefficientAt(0) + 1); // sửa hệ số bậc 0
        poly.addAtEnd(3.3);                     // thêm hệ số mới
        System.out.println("After modification: ");
        System.out.println(poly);

        MyPolynomial another = new MyListPolynomial();
        another.addAtEnd(2);
        another.addAtEnd(0);
        another.addAtEnd(-2); // 2 - 2x^2

        MyPolynomial sum = poly.plus(another);
        MyPolynomial diff = poly.minus(another);
        MyPolynomial prod = poly.multiply(another);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + prod);

        double x = 1.5;
        System.out.println("P(" + x + ") = " + poly.evaluate(x));

        double a = -10, b = 10;
        while (poly.evaluate(a) * poly.evaluate(b) > 0) {
            a = Math.random() * 20 - 10;
            b = Math.random() * 20 - 10;
        }

        System.out.printf("Interval found: [%.2f, %.2f]\n", a, b);

        RootSolver secant = new SecantSolver(1e-6, 100);
        RootSolver bisection = new BisectionSolver(1e-6, 100);
        RootSolver newton = new NewtonRaphsonSolver(1e-6, 100);

        PolynomialRootFinder finder = new PolynomialRootFinder(secant);
        System.out.println("Secant root: " + finder.solve(poly, a, b));

        finder = new PolynomialRootFinder(bisection);
        System.out.println("Bisection root: " + finder.solve(poly, a, b));

        finder = new PolynomialRootFinder(newton);
        System.out.println("Newton-Raphson root: " + finder.solve(poly, a, b));
    }


    public void testMyLinkedListPolynomial() {
        int size = (int)(Math.random() * 5 + 3);
        double[] coeffs = new double[size];
        for (int i = 0; i < size; i++) {
            coeffs[i] = Math.random() * 20 - 10;
        }

        MyPolynomial poly = new MyLinkedListPolynomial();
        for (int i = 0; i < size; i++) {
            poly.addAtEnd(coeffs[i]);
        }

        System.out.println("Original MyLinkedListPolynomial: ");
        System.out.println(poly);

        poly.set(0, poly.coefficientAt(0) + 2); // sửa hệ số bậc 0
        poly.addAtEnd(-4.4);                   // thêm hệ số mới
        System.out.println("After modification: ");
        System.out.println(poly);

        MyPolynomial another = new MyLinkedListPolynomial();
        another.addAtEnd(1);
        another.addAtEnd(1);
        another.addAtEnd(-1); // x^2 + x - 1

        MyPolynomial sum = poly.plus(another);
        MyPolynomial diff = poly.minus(another);
        MyPolynomial prod = poly.multiply(another);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + prod);

        double x = -1.0;
        System.out.println("P(" + x + ") = " + poly.evaluate(x));

        double a = -10, b = 10;
        while (poly.evaluate(a) * poly.evaluate(b) > 0) {
            a = Math.random() * 20 - 10;
            b = Math.random() * 20 - 10;
        }

        System.out.printf("Interval found: [%.2f, %.2f]\n", a, b);

        RootSolver secant = new SecantSolver(1e-6, 100);
        RootSolver bisection = new BisectionSolver(1e-6, 100);
        RootSolver newton = new NewtonRaphsonSolver(1e-6, 100);

        PolynomialRootFinder finder = new PolynomialRootFinder(secant);
        System.out.println("Secant root: " + finder.solve(poly, a, b));

        finder = new PolynomialRootFinder(bisection);
        System.out.println("Bisection root: " + finder.solve(poly, a, b));

        finder = new PolynomialRootFinder(newton);
        System.out.println("Newton-Raphson root: " + finder.solve(poly, a, b));
    }


}
