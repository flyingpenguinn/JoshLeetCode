public class NumberOfEnclaves {
    // walk from the edges
    int[][] dirs= {{-1,0},{1,0},{0,-1},{0,1}};

    public int numEnclaves(int[][] a) {
        int rows=a.length;
        int cols= a[0].length;
        for(int i=0;i<rows;i++){
            if(a[i][0]==1){
                dfs(a,i,0);
            }
            if(a[i][cols-1]==1){
                dfs(a,i,cols-1);
            }

        }

        for(int j=0;j<cols;j++){
            if(a[0][j]==1){
                dfs(a,0,j);
            }
            if(a[rows-1][j]==1){
                dfs(a,rows-1,j);
            }
        }
        int c=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(a[i][j]==1){
                    c++;
                }
            }
        }
        return c;
    }

    void dfs(int[][] a, int i, int j){
        int rows=a.length;
        int cols= a[0].length;

        a[i][j]=2;
        for(int[] d:dirs){
            int ni= i+d[0];
            int nj= j+d[1];
            if(ni>=0 && ni<rows && nj>=0 && nj<cols && a[ni][nj]==1){
                dfs(a,ni,nj);
            }
        }
    }
}
