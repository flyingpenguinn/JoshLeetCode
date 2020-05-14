/*
LC#256
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
 */
public class PaintHouse {
    // from on dp to o1 space
    public int minCost(int[][] a) {
        int n = a.length;
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            int nc0 = Math.min(c1, c2) + a[i][0];
            int nc1 = Math.min(c0, c2) + a[i][1];
            int nc2 = Math.min(c0, c1) + a[i][2];
            c0 = nc0;
            c1 = nc1;
            c2 = nc2;
        }
        return Math.min(c0, Math.min(c1, c2));

    }
}
