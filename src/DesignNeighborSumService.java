class neighborSum {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] diags = {{-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    private int[][] a;
    public neighborSum(int[][] grid) {
        a = grid;
    }

    private int getsum(int v, int[][] dirs){
        int m = a.length;
        int n = a[0].length;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j] != v){
                    continue;
                }
                int sum = 0;
                for(int[] d: dirs){
                    int ni = i+d[0];
                    int nj = j+d[1];
                    if(inrange(ni, m) && inrange(nj, n)){
                        sum += a[ni][nj];
                    }
                }
                return sum;
            }
        }
        return 0;
    }

    private boolean inrange(int i, int n){
        return i>=0 && i<n;
    }

    public int adjacentSum(int value) {
        return getsum(value, dirs);
    }

    public int diagonalSum(int value) {
        return getsum(value, diags);
    }
}
