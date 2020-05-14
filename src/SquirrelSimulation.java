/*
LC#573
There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
Example 1:

Input:
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:
​​​​​
Note:

All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.
 */

public class SquirrelSimulation {
    // sigma ti + min(sj-tj) we can split the min part out of sigma
    public int minDistance(int m, int n, int[] t, int[] s, int[][] nuts) {
        int r = 0;
        int mindiff = m * n;
        int sumdtree = 0;
        for (int[] nu : nuts) {
            int disttree = dist(t, nu);
            sumdtree += 2 * disttree;
            int dists = dist(s, nu);
            mindiff = Math.min(mindiff, dists - disttree);
        }
        return sumdtree + mindiff;
    }

    int dist(int[] a, int[] b) {
        int d1 = a[0] - b[0];
        int d2 = a[1] - b[1];
        return Math.abs(d1) + Math.abs(d2);
    }
}
