import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
LC#1262
Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] becomes at grid[i][j + 1].
Element at grid[i][m - 1] becomes at grid[i + 1][0].
Element at grid[n - 1][m - 1] becomes at grid[0][0].
Return the 2D grid after applying shift operation k times.



Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]
Example 2:


Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]


Constraints:

1 <= grid.length <= 50
1 <= grid[i].length <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
 */
public class GreatestSumDivisibleByThree {

    // think from the point of minusing from sum, dont try to create from the lists!
    int Max = 10000000;

    public int maxSumDivThree(int[] a) {
        int sum = 0;
        int m11 = Max;
        int m12 = Max;
        int m21 = Max;
        int m22 = Max;
        for (int ai : a) {
            sum += ai;
            int mod = ai % 3;
            if (mod == 1) {
                if (ai < m11) {
                    m12 = m11;
                    m11 = ai;
                } else if (ai < m12) {
                    m12 = ai;
                }
            } else if (mod == 2) {
                if (ai < m21) {
                    m22 = m21;
                    m21 = ai;
                } else if (ai < m22) {
                    m22 = ai;
                }
            }
        }
        int mod = sum % 3;
        if (mod == 0) {
            return sum;
        } else if (mod == 1) {
            return Math.max(sum - m11, sum - m21 - m22);
        } else {
            return Math.max(sum - m21, sum - m11 - m12);
        }
    }
}
