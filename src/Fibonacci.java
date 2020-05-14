public class Fibonacci {

    int[][] matrix = {{1, 1}, {1, 0}};

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[][] m = pow(matrix, n - 1);
        return m[0][0];
    }

    private int[][] pow(int[][] matrix, int n) {
        if (n == 1) {
            return matrix;
        }
        if (n % 2 == 0) {
            int[][] p = pow(matrix, n / 2);
            return multi(p, p);
        } else {
            int[][] p = pow(matrix, (n - 1) / 2);
            return multi(matrix, multi(p, p));
        }
    }

    // m*n
    private int[][] multi(int[][] p1, int[][] p2) {
        if (p1[0].length != p2.length) {
            throw new IllegalArgumentException();
        }
        int[][] r = new int[p1.length][p2[0].length];
        for (int i = 0; i < p1.length; i++) {
            for (int j = 0; j < p2[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < p1[0].length; k++) {
                    /// this dimension is shared by the two. 2nd matrix's row number is first matrix's column number
                    sum += p1[i][k] * p2[k][j];
                }
                r[i][j] = sum;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        //    System.out.println(fib.fib(2));
        System.out.println(fib.fib(30));
    }
}

class FiboIterative {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int a1 = 0;
        int a2 = 1;
        while (n > 1) {
            int a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
            n--;
        }
        return a2;
    }
}
