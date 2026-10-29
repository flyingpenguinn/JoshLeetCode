public class MinNumberOfDaysToDisconnectIslands {
    public int minDays(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int pi = -1;
        int pj = -1;
        int count = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==1){                    
                    ++count;
                }
            }
        }
        if(count==0){
            return 0;
        }
        if(count==1){
            return 1;
        }
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==1){    
                    boolean[][] v = new boolean[m][n];
                    int nodes = dfs(a, i, j, v); 
                    if(nodes!= count){
                        return 0;
                    }else{
                        break;
                    }                    
                }
            }
        }
        
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==0){
                    continue;
                }
                a[i][j] = 0;
                boolean[][] v = new boolean[m][n];
                for(int[] d: dirs){
                    int ni = i+d[0];
                    int nj = j+d[1];
                    if(ni>=0 && ni<m && nj>=0 && nj<n && a[ni][nj] == 1){
                        int rest = dfs(a, ni, nj, v);
                        if(rest != count-1){
                            return 1;
                        }
                        else{
                            break;
                        }
                    }
                }
                a[i][j] = 1; 
            }
        }
        return 2;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int dfs(int[][] a, int i, int j, boolean[][] v){
        int m = a.length;
        int n = a[0].length;
        v[i][j] = true;
        int res = 1;
        for(int[] d: dirs){
            int ni = i+d[0];
            int nj = j+d[1];
            if(ni>=0  && ni<m && nj>=0 && nj<n && a[ni][nj] == 1 && !v[ni][nj]){
                res += dfs(a, ni, nj, v);
            }
        }
        return res;
    }
    
}
