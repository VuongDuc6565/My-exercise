package hus.oop.rootsolver;

public interface MyPolynomial {
    /**
     * Lấy ra hệ số của đa thức tại phần tử index.
     * @return hệ số của đa thức tại phần tử index.
     */
    double coefficientAt(int index);

    /**
     * Lấy mảng các hệ số của đa thức.
     * @return mảng các hệ số của đa thức.
     */
    double[] coefficients();

    /**
     * Thêm phần tử vào đầu đa thức.
     * @param coefficient
     */
    void addAtStart(double coefficient);

    /**
     * Thêm phần tử vào cuối đa thức.
     * @param coefficient
     */
    void addAtEnd(double coefficient);

    /**
     * Thêm phần tử vào vị trí index.
     * @param coefficient
     */
    void addAtPosition(int index, double coefficient);

    /**
     * Thay đổi hệ số của đa thức ở vị trí index thành coefficient
     * @param index
     * @param coefficient
     */
    void set(int index, double coefficient);

    /**
     * Lấy bậc của đa thức.
     * @return bậc của đa thức.
     */
    int degree();

    /**
     * Tính giá trị của đa thức khi biết giá trị của x.
     * @return giá trị của đa thức.
     */
    double evaluate(double x);

    /**
     * Lấy đạo hàm của đa thức.
     * @return Đa thức là đa thức đạo hàm của đa thức ban đầu.
     */
    MyPolynomial derivative();

    /**
     * Cộng một đa thức khác vào đa thức hiện tại.
     * @param another
     * @return đa thức mới là tổng của 2 đa thức
     */
    MyPolynomial plus(MyPolynomial another);

    /**
     * Trừ đa thức hiện tại với đa thức khác.
     * @param another
     * @return đa thức mới là hiệu của 2 đa thức
     */
    MyPolynomial minus(MyPolynomial another);

    /**
     * Nhân đa thức hiện tại với đa thức khác.
     * @param another
     * @return đa thức mới là tích của 2 đa thức
     */
    MyPolynomial multiply(MyPolynomial another);
}
