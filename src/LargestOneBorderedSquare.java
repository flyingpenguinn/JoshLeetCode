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
    public int largest1BorderedSquare(int[][] g) {
        int rows = g.length;
        int cols = g[0].length;
        int[][] left = new int[rows][cols];
        int[][] up = new int[rows][cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (g[i][j] == 0) {
                    continue;
                }
                // whether ij is the right corner
                left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                up[i][j] = (i == 0 ? 0 : up[i - 1][j]) + 1;
                int len = Math.min(left[i][j], up[i][j]);
                // need to try out every possible len
                for (int cl = len; cl >= 1; cl--) {
                    // "111"=> len==3 => i-2
                    if (up[i][j - cl + 1] >= cl && left[i - cl + 1][j] >= cl) {
                        max = Math.max(max, cl * cl);
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
