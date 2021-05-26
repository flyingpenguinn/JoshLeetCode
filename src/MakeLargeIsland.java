import base.ArrayUtils;

import java.util.*;

/*
LC#827
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.
 */
public class MakeLargeIsland {
     // make sure we use set to dedupe, and handle only 1 case
    // especially handle only 1 without other merging case
    private int[][] dirs = {{-1, 0}, {0, -1},{1,0},  {0,1}};
    private int code(int x, int y, int n){
        return n*x+y;
    }

    private int find(int[] pa, int i){
        if(pa[i]==-1){
            return i;
        }
        int rt = find(pa, pa[i]);
        pa[i] = rt;
        return rt;
    }

    private int unions(int[] pa, int[] size, int i, int j){
        int ai = find(pa, i);
        int aj = find(pa, j);
        if(ai==aj){
            return size[ai];
        }
        int rt = 0;
        if(size[ai]< size[aj]){
            pa[ai] = aj;
            size[aj] += size[ai];
            rt = size[aj];
        }else{
            pa[aj] = ai;
            size[ai] += size[aj];
            rt = size[ai];
        }
        return rt;
    }

    public int largestIsland(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] pa = new int[m*n];
        Arrays.fill(pa, -1);
        int[] size = new int[m*n];
        Arrays.fill(size, 1);
        int res = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(a[i][j]==0){
                    continue;
                }
                res = Math.max(res, 1);
                // don't forget this to handle only 1
                int cij = code(i, j, n);
                for(int k = 0; k<2; k++){
                    // only need the first 2 for left and upper
                    int[] d = dirs[k];
                    int ni = i+d[0];
                    int nj = j+d[1];
                    if(ni>=0 && ni<m && nj>=0 && nj<n && a[ni][nj]==1){
                        int cnij = code(ni, nj, n);
                        int merged= unions(pa, size, cij, cnij);
                        res = Math.max(res, merged);
                    }
                }
            }
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(a[i][j]==1){
                    continue;
                }
                Set<Integer> ones = new HashSet<>();
                for(var d: dirs){
                    int ni = i+d[0];
                    int nj = j+d[1];
                    if(ni>=0 && ni<m && nj>=0 && nj<n && a[ni][nj]==1){
                        int cnij = code(ni, nj, n);
                        ones.add(find(pa, cnij ));
                    }
                }
                int cur = 1;
                for(int one: ones){
                    cur += size[one];
                }
                res = Math.max(res, cur);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MakeLargeIsland().largestIsland(ArrayUtils.read("[[1,0,1],[0,0,0],[0,1,1]]")));
    }
}