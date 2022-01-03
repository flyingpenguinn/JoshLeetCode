public class MinOperationToRemoveAdjacentOnesInMatrix {
    // map is like a chessboard. divide nodes into a bipartie graph. for each connected component, count which part is samller and remove that part
    private int[][] dirs = {{-1, 0}, {1,0}, {0, -1}, {0,1}};
    private int[][] map;
    private int[] cur;
    boolean[][] v;
    public int minimumOperations(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        v = new boolean[m][n];
        map = new int[m][n];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(i==0 && j==0){
                    map[i][j] = 0;
                }else if(i==0){
                    map[i][j] = map[i][j-1] ^1;
                }else{
                    map[i][j] = map[i-1][j] ^1;
                }
            }
        }
        int res = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==1 && !v[i][j]){
                    cur = new int[2];
                    dfs(a, i, j);
                    res += Math.min(cur[0], cur[1]);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] a, int i, int j){
        int m = a.length;
        int n = a[0].length;
        int code = i*n + j;
        ++cur[map[i][j]];
        v[i][j] = true;
        for(int[] d: dirs){
            int ni = i+d[0];
            int nj = j+d[1];
            if(ni>=0 && ni<m && nj>=0 && nj<n && a[ni][nj] == 1 && !v[ni][nj]){
                dfs(a, ni, nj);
            }
        }
    }
}
