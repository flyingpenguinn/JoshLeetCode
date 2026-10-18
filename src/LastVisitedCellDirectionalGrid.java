public class LastVisitedCellDirectionalGrid {
    public int[] lastVisitedCell(char[][] g) {
        int m = g.length;
        int n = g[0].length;
        int i = 0;
        int j = 0;
        int[] last = new int[]{0,0};
        boolean[][] v = new boolean[m][n];        
        while(i<m && j<n && i>=0 && j>=0){
            if(v[i][j]){
                break;
            }
            last = new int[]{i, j};
            v[i][j] = true;
            char c = g[i][j];
            if(c=='R'){
                ++j;
            }
            else if(c=='D'){
                ++i;
            }else if(c=='L'){
                --j;
            }else{
                --i;
            }
            
        }
        return last;
    }
}
