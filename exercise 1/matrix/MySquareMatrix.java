package hus.oop.matrix;
import java.util.*;
import java.util.stream.Collectors;
public class MySquareMatrix {
    private int[][] data;

    /**
     * Hàm dựng, khởi tạo một ma trận có các phần tử được sinh ngẫu nhiên trong khoảng [1, 100]
     * @param size
     */
    public MySquareMatrix(int size) {
        initRandom(size);
    }

    private void initRandom(int size) {
        data = new int[size][size];
        Random rnd = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = rnd.nextInt(81) + 10;    // [10,90]
            }
        }
    }

    /**
     * Lấy ra các phần tử trên đường chéo chính của ma trận.
     * @return đường chéo chính của ma trận.
     */
    public int[] principalDiagonal() {
        int n = size();
        int[] diag = new int[n];
        for (int i = 0; i < n; i++) diag[i] = data[i][i];
        return diag;
    }

    public int[] secondaryDiagonal() {
        int n = size();
        int[] diag = new int[n];
        for (int i = 0; i < n; i++) diag[i] = data[i][n - 1 - i];
        return diag;
    }

    /**
     * Phương thức lấy ra các số là số nguyên tố trong ma trận.
     * @return các số nguyên tố trong ma trận.
     */
    public int[] primes() {
        List<Integer> list = new ArrayList<>();
        for (int[] row : data) {
            for (int v : row) {
                if (isPrime(v)) list.add(v);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        if (x % 2 == 0) return x == 2;
        for (int d = 3; d * d <= x; d += 2)
            if (x % d == 0) return false;
        return true;
    }
    public MySquareMatrix getSortedMatrix() {
        int n = size();
        int total = n * n;
        int[] flat = new int[total];
        int idx = 0;
        for (int[] row : data)
            for (int v : row) flat[idx++] = v;

        Arrays.sort(flat);                     // tăng dần
        // đảo chiều để được giảm dần
        for (int i = 0; i < total / 2; i++) {
            int tmp = flat[i];
            flat[i] = flat[total - 1 - i];
            flat[total - 1 - i] = tmp;
        }

        MySquareMatrix res = new MySquareMatrix(n);
        idx = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res.data[i][j] = flat[idx++];
        return res;
    }

    /**
     * Lấy giá trị phần tử ở vị trí (row, col).
     * @param row
     * @param col
     * @return
     */
    public int get(int row, int col) {
        return data[row][col];
    }

    public void set(int row, int col, int value) {
        data[row][col] = value;
    }

    public int size() {
        return data.length;
    }
    public MySquareMatrix add(MySquareMatrix that) {
        int n = size();
        MySquareMatrix res = new MySquareMatrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res.data[i][j] = this.data[i][j] + that.data[i][j];
        return res;
    }

    public MySquareMatrix minus(MySquareMatrix that) {
        int n = size();
        MySquareMatrix res = new MySquareMatrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res.data[i][j] = this.data[i][j] - that.data[i][j];
        return res;
    }

    public MySquareMatrix multiply(MySquareMatrix that) {
        int n = size();
        MySquareMatrix res = new MySquareMatrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) sum += this.data[i][k] * that.data[k][j];
                res.data[i][j] = sum;
            }
        return res;
    }

    public MySquareMatrix scaled(int value) {
        int n = size();
        MySquareMatrix res = new MySquareMatrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res.data[i][j] = this.data[i][j] * value;
        return res;
    }

    public MySquareMatrix transpose() {
        int n = size();
        MySquareMatrix res = new MySquareMatrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res.data[i][j] = this.data[j][i];
        return res;
    }

    /**
     * Mô tả ma trận theo định dạng biểu diễn ma trận, ví dụ
     *   1 2 3
     *   4 5 6
     *   7 8 9
     * @return một chuỗi mô tả ma trận.
     */
    @Override
    public String toString() {
        return Arrays.stream(data)
                .map(row -> Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining("\n"));
    }
}
