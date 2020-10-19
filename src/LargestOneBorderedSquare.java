import base.ArrayUtils;

/*
LC#1139
Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.



Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
Example 2:

Input: grid = [[1,1,0,0]]
Output: 1


Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1
 */
public class LargestOneBorderedSquare {

    // enumerate right end of the square. keep left and up counts and try to work out the best length of the edges
    public int largest1BorderedSquare(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] rows = new int[m][n];
        int[][] cols = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    continue;
                }
                rows[i][j] = i == 0 ? 1 : rows[i - 1][j] + 1;
                cols[i][j] = j == 0 ? 1 : cols[i][j - 1] + 1;
                for (int k = Math.min(rows[i][j], cols[i][j]); k >= 1; k--) {
                    int toprow = i - k + 1;
                    int leftcol = j - k + 1;
                    int ones1 = rows[i][j] - (toprow == 0 ? 0 : rows[toprow - 1][j]);
                    int ones2 = cols[i][j] - (leftcol == 0 ? 0 : cols[i][leftcol - 1]);
                    int ones3 = rows[i][leftcol] - (toprow == 0 ? 0 : rows[toprow - 1][leftcol]);
                    int ones4 = cols[toprow][j] - (leftcol == 0 ? 0 : cols[toprow][leftcol - 1]);

                    if (ones1 == k && ones2 == k && ones3 == k && ones4 == k) {
                        max = Math.max(max, k * k);
                        break;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LargestOneBorderedSquare().largest1BorderedSquare(ArrayUtils.read("[[1,1,1],[1,0,1],[1,1,1]]")));
    }
}
