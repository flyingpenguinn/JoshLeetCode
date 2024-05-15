public class NumberOfSpacesCleaningRobotCleaned {
    private int[][] dirs = {{1,0}, {0, -1}, {-1, 0}, {0, 1}};
    public int numberOfCleanRooms(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean[][][] seen = new boolean[m][n][4];
        dfs(a, 0, 0, 3, seen);
        int res = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                int visited = 0;
                for(int d=0; d<4; ++d){
                    if(seen[i][j][d]){
                        visited = 1;
                        break;
                    }
                }
                res += visited;
            }
        }
        return res;
    }

    private void dfs(int[][] a, int i, int j, int d, boolean[][][] seen){
        if(seen[i][j][d]){
            return;
        }
        int m = a.length;
        int n = a[0].length;
        seen[i][j][d] = true;
        int nd = d;
        while(true){
            int ni = i+dirs[nd][0];
            int nj = j+dirs[nd][1];
            if(ni>=0 && ni<m && nj>=0 && nj<n && a[ni][nj] == 0){
                break;
            }
            ++nd;
            nd %= 4;
            if(nd == d){
                return;
            }
        }
        i += dirs[nd][0];
        j += dirs[nd][1];
        dfs(a, i, j, nd, seen);
    }
}
