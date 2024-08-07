import base.ArrayUtils;

import java.util.Arrays;

/*
LC#1219
In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.


Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


Constraints:

1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
 */
public class PathWithMaxGold {


    // worst case 4^25 but in reality n^4*25: we visit 25 cells at most for each ij
    public int getMaximumGold(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]>0){
                    boolean[][] seen = new boolean[m][n];
                    int cur = dfs(a, i, j, seen, 0);
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private int dfs(int[][] a, int i, int j, boolean[][] seen, int csum){
        int m = a.length;
        int n = a[0].length;
        seen[i][j] = true;
        int res = a[i][j];
        int maxlater = 0;
        for(int[] d: dirs){
            int ni = i+d[0];
            int nj = j+d[1];
            if(ni>=0 && ni<m && nj>=0 && nj<n && a[ni][nj] >0 && !seen[ni][nj]){
                int later = dfs(a, ni, nj, seen, csum);
                maxlater = Math.max(maxlater, later);
            }
        }
        res += maxlater;
        seen[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PathWithMaxGold().getMaximumGold(ArrayUtils.read("[[0,6,0],[5,8,7],[0,9,0]]")));
        //   System.out.println(new PathWithMaxGold().getMaximumGold(ArrayUtils.read("[[1,0,7,0,0,0],[2,0,6,0,1,0],[3,5,6,7,4,2],[4,3,1,0,2,0],[3,0,5,0,20,0]]")));

    }
}
