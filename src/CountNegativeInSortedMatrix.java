/*
LC#1351
Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.

Return the number of negative numbers in grid.



Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0
Example 3:

Input: grid = [[1,-1],[-1,-1]]
Output: 3
Example 4:

Input: grid = [[-1]]
Output: 1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100
 */
public class CountNegativeInSortedMatrix {
    // traverse almost along the diagonal along the fault line of pos/neg
    public int countNegatives(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int i = 0;
        int j = n - 1;
        int r = 0;
        while (i < m && j >= 0) {
            while (i < m && g[i][j] >= 0) {
                i++;
            }
            if (i == m) {
                break;
            }
            r += m - i;
            j--;
        }
        return r;
    }
}
