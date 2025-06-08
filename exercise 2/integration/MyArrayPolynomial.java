package hus.oop.integration;
import java.util.Arrays;
public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficients;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        this.coefficients = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }
    private MyArrayPolynomial(double[] coeffs) {
        this.coefficients = Arrays.copyOf(coeffs, Math.max(DEFAULT_CAPACITY, coeffs.length));
        this.size = coeffs.length;
    }
    @Override
    public double coefficient(int index) {
        return (index >= size) ? 0 : coefficients[index];
    }

    @Override
    public double[] coefficients() {
        return Arrays.copyOf(coefficients, size);
    }

    @Override
    public MyArrayPolynomial append(double coefficient) {
        if (size == coefficients.length) allocateMore();
        coefficients[size++] = coefficient;
        return this;
    }

    @Override
    public MyArrayPolynomial add(double coefficient, int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        while (index >= coefficients.length) allocateMore();
        if (index >= size) size = index + 1;
        coefficients[index] += coefficient;
        return this;
    }

    @Override
    public MyArrayPolynomial set(double coefficient, int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        while (index >= coefficients.length) allocateMore();
        if (index >= size) size = index + 1;
        coefficients[index] = coefficient;
        return this;
    }

    @Override
    public int degree() {
        return Math.max(0, size - 1);
    }

    public double evaluate(double x) {
        // Thuật toán Horner
        double result = 0;
        for (int i = size - 1; i >= 0; i--) {
            result = result * x + coefficients[i];
        }
        return result;
    }


    @Override
    public MyArrayPolynomial derivative() {
        return new MyArrayPolynomial(differentiate());
    }

    @Override
    public MyArrayPolynomial plus(MyPolynomial right) {
        int maxDeg = Math.max(degree(), right.degree());
        MyArrayPolynomial res = new MyArrayPolynomial();
        for (int i = 0; i <= maxDeg; i++) {
            res.append(this.coefficient(i) + right.coefficient(i));
        }
        return res;
    }

    @Override
    public MyArrayPolynomial minus(MyPolynomial right) {
        int maxDeg = Math.max(degree(), right.degree());
        MyArrayPolynomial res = new MyArrayPolynomial();
        for (int i = 0; i <= maxDeg; i++) {
            res.append(this.coefficient(i) - right.coefficient(i));
        }
        return res;
    }

    @Override
    public MyArrayPolynomial multiply(MyPolynomial right) {
        int newDeg = degree() + right.degree();
        double[] prod = new double[newDeg + 1];
        for (int i = 0; i <= degree(); i++) {
            for (int j = 0; j <= right.degree(); j++) {
                prod[i + j] += this.coefficient(i) * right.coefficient(j);
            }
        }
        return new MyArrayPolynomial(prod);
    }

    /**
     * Tăng kích thước mảng lên gấp đôi để lưu đa thức khi cần thiết.
     */
    private void allocateMore() {
        coefficients = Arrays.copyOf(coefficients, coefficients.length * 2);
    }
}
