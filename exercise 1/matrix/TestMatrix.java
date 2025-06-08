package hus.oop.matrix;

import java.util.Arrays;
import java.util.Random;

public class TestMatrix {
    public static void main(String[] args) {
        Random rnd = new Random();
        int size = rnd.nextInt(6) + 5;      // [5..10]

        MySquareMatrix A = new MySquareMatrix(size);
        MySquareMatrix B = new MySquareMatrix(size);

        System.out.println("Ma trận A:");
        System.out.println(A + "\n");

        System.out.println("Ma trận B:");
        System.out.println(B + "\n");

        System.out.println("A^T (chuyển vị):");
        System.out.println(A.transpose() + "\n");

        System.out.println("B^T (chuyển vị):");
        System.out.println(B.transpose() + "\n");

        System.out.println("Đường chéo chính của A: " + Arrays.toString(A.principalDiagonal()));
        System.out.println("Đường chéo phụ   của A: " + Arrays.toString(A.secondaryDiagonal()) + "\n");

        System.out.println("Đường chéo chính của B: " + Arrays.toString(B.principalDiagonal()));
        System.out.println("Đường chéo phụ   của B: " + Arrays.toString(B.secondaryDiagonal()) + "\n");

        System.out.println("A + B:");
        System.out.println(A.add(B) + "\n");

        System.out.println("A - B:");
        System.out.println(A.minus(B) + "\n");

        System.out.println("A × B:");
        System.out.println(A.multiply(B) + "\n");

        System.out.println("Các số nguyên tố trong A: " + Arrays.toString(A.primes()));
        System.out.println("Các số nguyên tố trong B: " + Arrays.toString(B.primes()) + "\n");

        System.out.println("Ma trận A sắp xếp giảm dần:");
        System.out.println(A.getSortedMatrix());
    }
}
