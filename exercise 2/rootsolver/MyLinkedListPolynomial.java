package hus.oop.rootsolver;

public class MyLinkedListPolynomial extends MyAbstractPolynomial {
    private MyLinkedList polynomial;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedListPolynomial() {
        this.polynomial = new MyLinkedList();
    }

    @Override
    public double coefficientAt(int index) {
        return polynomial.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[polynomial.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = polynomial.get(i);
        }
        return result;
    }

    @Override
    public void addAtStart(double coefficient) {
        polynomial.insert(coefficient, 0);
    }

    @Override
    public void addAtEnd(double coefficient) {
        polynomial.add(coefficient);
    }

    @Override
    public void addAtPosition(int index, double coefficient) {
        polynomial.insert(coefficient, index);
    }

    @Override
    public void set(int index, double coefficient) {
        polynomial.set(coefficient, index);
    }

    @Override
    public int degree() {
        return polynomial.size() - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < polynomial.size(); i++) {
            result += polynomial.get(i) * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public MyLinkedListPolynomial derivative() {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        for (int i = 1; i < polynomial.size(); i++) {
            result.addAtEnd(polynomial.get(i) * i);
        }
        return result;
    }

    @Override
    public MyLinkedListPolynomial plus(MyPolynomial another) {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        for (int i = 0; i <= maxDegree; i++) {
            double sum = (i <= this.degree() ? this.coefficientAt(i) : 0)
                    + (i <= another.degree() ? another.coefficientAt(i) : 0);
            result.addAtEnd(sum);
        }
        return result;
    }

    @Override
    public MyLinkedListPolynomial minus(MyPolynomial another) {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        int maxDegree = Math.max(this.degree(), another.degree());
        for (int i = 0; i <= maxDegree; i++) {
            double diff = (i <= this.degree() ? this.coefficientAt(i) : 0)
                    - (i <= another.degree() ? another.coefficientAt(i) : 0);
            result.addAtEnd(diff);
        }
        return result;
    }

    @Override
    public MyLinkedListPolynomial multiply(MyPolynomial another) {
        MyLinkedListPolynomial result = new MyLinkedListPolynomial();
        for (int i = 0; i <= this.degree() + another.degree(); i++) {
            result.addAtEnd(0);
        }
        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= another.degree(); j++) {
                double prod = this.coefficientAt(i) * another.coefficientAt(j);
                result.set(i + j, result.coefficientAt(i + j) + prod);
            }
        }
        return result;
    }
}
