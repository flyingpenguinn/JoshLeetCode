public class DisconnectMatrixByAtmostOneFlip {
    public boolean isPossibleToCutPath(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean found = dfs(a, 0, 0);
        if(!found){
            return true;
        }
        return !dfs(a, 0, 0);
    }

    private boolean dfs(int[][] g, int i, int j){
        int m = g.length;
        int n = g[0].length;
        if(i==m-1 && j==n-1){
            return true;
        }
        if(i!=0 || j!=0){
            g[i][j] = 0;
        }
        if(i+1<m && g[i+1][j]==1){
            boolean later = dfs(g, i+1, j);
            if(later){
                return true;
            }
        }
        if(j+1<n && g[i][j+1]==1){
            boolean later = dfs(g, i, j+1);
            if(later){
                return true;
            }
        }
        return false;
    }
}
