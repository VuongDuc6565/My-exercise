package hus.oop.integration;
import java.util.StringJoiner;
public abstract class MyAbstractPolynomial implements MyPolynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" + ", "[", "]");
        double[] c = coefficients();
        for (int i = 0; i < c.length; i++) {
            double coef = c[i];
            if (Math.abs(coef) < 1e-12) continue;          // Bỏ qua hệ số 0
            String term = (i == 0) ? String.format("%.4f", coef)
                    : (i == 1) ? String.format("%.4fx", coef)
                    : String.format("%.4fx^%d", coef, i);
            sj.add(term.replaceAll("\\.?0+$", ""));        // Bỏ đuôi .0
        }
        return sj.length() == 0 ? "[0]" : sj.toString();
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
        int deg = degree();
        if (deg == 0) return new double[]{0};
        double[] diff = new double[deg];
        for (int i = 0; i < deg; i++) {
            diff[i] = (i + 1) * coefficient(i + 1);
        }
        return diff;
    }
}
