public class MinFlipsBinaryGridPalindromicII {
    // trick is in the middle row/col
    // because the 4 corners will either have all 1 or all 0 which is divisible by 4
    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int totalFlips = 0;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int[][] positions = {
                        {i, j}, {i, n - 1 - j}, {m - 1 - i, j}, {m - 1 - i, n - 1 - j}
                };
                int[] count = new int[2]; // Binary values 0 and 1

                for (int[] pos : positions) {
                    count[mat[pos[0]][pos[1]]]++;
                }

                int flip0s = 4 - count[0];
                int flip1s = 4 - count[1];
                totalFlips += Math.min(flip0s, flip1s);

            }
        }

        // Handle the middle row if the number of rows is odd
        int singles = 0;
        int doubles = 0;
        if (m % 2 == 1) {
            for (int j = 0; j < n / 2; j++) {

                int ones = mat[m / 2][j] + mat[m / 2][n - 1 - j];
                if (ones == 1) {
                    ++singles;  // 1, 0
                } else if (ones == 2) {
                    ++doubles; // 1,1
                }

            }
        }

        // Handle the middle column if the number of columns is odd
        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; i++) {
                int ones = mat[i][n / 2] + mat[m - 1 - i][n / 2];
                if (ones == 1) {
                    ++singles;  // 1, 0
                } else if (ones == 2) {
                    ++doubles; // 1,1
                }
            }
            if (m % 2 == 1) {
                totalFlips += mat[n / 2][m / 2];
            }
        }

        if (doubles % 2 == 0) {
            return totalFlips + singles; // even number of 1,1 means it's divisible by 4. change any flip to 0,0
        } else {
            if (singles > 0) {
                return totalFlips + singles; // one of the 1,0 goes to 1,1, to make the 1,1 pair even
            } else {
                // we now have odd number of 1,1 but we need it even, and there is no singles to spare...
                return totalFlips + 2;
            }
        }
    }

    public static void main(String[] args) {
        MinFlipsBinaryGridPalindromicII sol = new MinFlipsBinaryGridPalindromicII();
        int[][] grid1 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(sol.minFlips(grid1)); // Expected output: 3

        int[][] grid2 = {{0, 1}, {0, 1}, {0, 0}};
        System.out.println(sol.minFlips(grid2)); // Expected output: 2

        int[][] grid3 = {{1}, {1}};
        System.out.println(sol.minFlips(grid3)); // Expected output: 2
    }
}
