package hus.oop.integration;
import java.util.ArrayList;
import java.util.List;

public class MyListPolynomial extends MyAbstractPolynomial {
    private List<Double> coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyListPolynomial() {
        this.coefficients = new ArrayList<>();
    }
    private MyListPolynomial(List<Double> coeffCopy) {
        this.coefficients = coeffCopy;
    }
    @Override
    public double coefficient(int index) {
        return (index >= coefficients.size()) ? 0 : coefficients.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] arr = new double[coefficients.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = coefficients.get(i);
        return arr;
    }

    @Override
    public MyListPolynomial append(double coefficient) {
        coefficients.add(coefficient);
        return this;
    }

    @Override
    public MyListPolynomial add(double coefficient, int index) {
        ensureSize(index + 1);
        coefficients.set(index, coefficients.get(index) + coefficient);
        return this;
    }

    @Override
    public MyListPolynomial set(double coefficient, int index) {
        ensureSize(index + 1);
        coefficients.set(index, coefficient);
        return this;
    }

    @Override
    public int degree() {
        return Math.max(0, coefficients.size() - 1);
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = degree(); i >= 0; i--) {
            result = result * x + coefficient(i);
        }
        return result;
    }

    @Override
    public MyListPolynomial derivative() {
        List<Double> diff = new ArrayList<>();
        for (int i = 1; i < coefficients.size(); i++) {
            diff.add(i * coefficients.get(i));
        }
        return new MyListPolynomial(diff);
    }

    @Override
    public MyListPolynomial plus(MyPolynomial right) {
        int maxDeg = Math.max(degree(), right.degree());
        MyListPolynomial res = new MyListPolynomial();
        for (int i = 0; i <= maxDeg; i++) {
            res.append(this.coefficient(i) + right.coefficient(i));
        }
        return res;
    }

    @Override
    public MyListPolynomial minus(MyPolynomial right) {
        int maxDeg = Math.max(degree(), right.degree());
        MyListPolynomial res = new MyListPolynomial();
        for (int i = 0; i <= maxDeg; i++) {
            res.append(this.coefficient(i) - right.coefficient(i));
        }
        return res;
    }

    @Override
    public MyListPolynomial multiply(MyPolynomial right) {
        int newDeg = degree() + right.degree();
        List<Double> prod = new ArrayList<>();
        for (int i = 0; i <= newDeg; i++) prod.add(0.0);

        for (int i = 0; i <= degree(); i++) {
            for (int j = 0; j <= right.degree(); j++) {
                prod.set(i + j, prod.get(i + j) + this.coefficient(i) * right.coefficient(j));
            }
        }
        return new MyListPolynomial(prod);
    }
    private void ensureSize(int n) {
        while (coefficients.size() < n) coefficients.add(0.0);
    }
}
