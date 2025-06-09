package hus.oop.rootsolver;

public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficients;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        coefficients = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public double coefficientAt(int index) {
        return coefficients[index];
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[size];
        System.arraycopy(coefficients, 0, result, 0, size);
        return result;
    }

    @Override
    public void addAtStart(double coefficient) {
        addAtPosition(0, coefficient);
    }

    @Override
    public void addAtEnd(double coefficient) {
        if (size == coefficients.length) allocateMore();
        coefficients[size++] = coefficient;
    }

    @Override
    public void addAtPosition(int index, double coefficient) {
        if (size == coefficients.length) allocateMore();
        System.arraycopy(coefficients, index, coefficients, index + 1, size - index);
        coefficients[index] = coefficient;
        size++;
    }

    @Override
    public void set(int index, double coefficient) {
        coefficients[index] = coefficient;
    }

    @Override
    public int degree() {
        return size - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial derivative() {
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 1; i < size; i++) {
            result.addAtEnd(coefficients[i] * i);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial plus(MyPolynomial another) {
        int maxDeg = Math.max(this.degree(), another.degree());
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 0; i <= maxDeg; i++) {
            double a = i <= this.degree() ? coefficientAt(i) : 0;
            double b = i <= another.degree() ? another.coefficientAt(i) : 0;
            result.addAtEnd(a + b);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial minus(MyPolynomial another) {
        int maxDeg = Math.max(this.degree(), another.degree());
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 0; i <= maxDeg; i++) {
            double a = i <= this.degree() ? coefficientAt(i) : 0;
            double b = i <= another.degree() ? another.coefficientAt(i) : 0;
            result.addAtEnd(a - b);
        }
        return result;
    }

    @Override
    public MyArrayPolynomial multiply(MyPolynomial another) {
        int newDeg = this.degree() + another.degree();
        double[] product = new double[newDeg + 1];
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= another.degree(); j++) {
                product[i + j] += coefficientAt(i) * another.coefficientAt(j);
            }
        }
        MyArrayPolynomial result = new MyArrayPolynomial();
        for (double v : product) result.addAtEnd(v);
        return result;
    }

    private void allocateMore() {
        double[] newCoeffs = new double[coefficients.length * 2];
        System.arraycopy(coefficients, 0, newCoeffs, 0, coefficients.length);
        coefficients = newCoeffs;
    }
}
