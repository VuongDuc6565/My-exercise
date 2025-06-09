package hus.oop.rootsolver;

import java.util.ArrayList;
import java.util.List;

public class MyListPolynomial extends MyAbstractPolynomial {
    private List<Double> coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyListPolynomial() {
        coefficients = new ArrayList<>();
    }

    @Override
    public double coefficientAt(int index) {
        return coefficients.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] array = new double[coefficients.size()];
        for (int i = 0; i < coefficients.size(); i++) {
            array[i] = coefficients.get(i);
        }
        return array;
    }

    @Override
    public void addAtStart(double coefficient) {
        coefficients.add(0, coefficient);
    }

    @Override
    public void addAtEnd(double coefficient) {
        coefficients.add(coefficient);
    }

    @Override
    public void addAtPosition(int index, double coefficient) {
        coefficients.add(index, coefficient);
    }

    @Override
    public void set(int index, double coefficient) {
        coefficients.set(index, coefficient);
    }

    @Override
    public int degree() {
        return coefficients.size() - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyListPolynomial derivative() {
        MyListPolynomial derivative = new MyListPolynomial();
        for (int i = 1; i < coefficients.size(); i++) {
            derivative.addAtEnd(i * coefficients.get(i));
        }
        return derivative;
    }

    @Override
    public MyListPolynomial plus(MyPolynomial another) {
        MyListPolynomial result = new MyListPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        for (int i = 0; i <= maxDegree; i++) {
            double coef1 = (i <= this.degree()) ? this.coefficientAt(i) : 0;
            double coef2 = (i <= another.degree()) ? another.coefficientAt(i) : 0;
            result.addAtEnd(coef1 + coef2);
        }
        return result;
    }

    @Override
    public MyListPolynomial minus(MyPolynomial another) {
        MyListPolynomial result = new MyListPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        for (int i = 0; i <= maxDegree; i++) {
            double coef1 = (i <= this.degree()) ? this.coefficientAt(i) : 0;
            double coef2 = (i <= another.degree()) ? another.coefficientAt(i) : 0;
            result.addAtEnd(coef1 - coef2);
        }
        return result;
    }

    @Override
    public MyListPolynomial multiply(MyPolynomial another) {
        int newDegree = this.degree() + another.degree();
        double[] resultCoefficients = new double[newDegree + 1];
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= another.degree(); j++) {
                resultCoefficients[i + j] += this.coefficientAt(i) * another.coefficientAt(j);
            }
        }
        MyListPolynomial result = new MyListPolynomial();
        for (double coef : resultCoefficients) {
            result.addAtEnd(coef);
        }
        return result;
    }
}
