/*
LC#695
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
 */
public class MaxAreaOfIsland {
    // biggest connected component in undirected graph
    public int maxAreaOfIsland(int[][] g) {
        // validate input
        if(g==null || g.length==0){
            return 0;
        }
        int m = g.length;
        int n = g[0].length;
        int max = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(g[i][j]==1){
                    int cur = dfs(g, i, j);
                    max = Math.max(max, cur);
                }
            }
        }
        return max;
    }

    private int[][] dirs = {{-1,0}, {1,0}, {0, -1}, {0,1}};

    private int dfs(int[][] g, int i, int j){
        int m = g.length;
        int n = g[0].length;
        int r = 1;
        g[i][j]=2;
        for(int[] d: dirs){
            int ni = i+d[0];
            int nj = j+d[1];
            if(ni>=0 && ni<m && nj>=0 && nj<n && g[ni][nj] ==1){
                r += dfs(g, ni, nj);
            }
        }
        return r;
    }
}
