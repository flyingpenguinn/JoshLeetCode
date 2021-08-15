import java.util.ArrayDeque;
import java.util.Deque;

public class LastDayWhereYouCanStillCross {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
    public int latestDayToCross(int row, int col, int[][] cells) {
        int cn = cells.length;
        int l = 0;
        int u = cn;
        while(l<=u){
            int mid = l+(u-l)/2;
            int[][] g = new int[row][col];
            for(int j=0; j<=mid-1; j++){
                g[cells[j][0]-1][cells[j][1]-1] = 1;

            }
            boolean rt = doable(g);
            if(rt){
                l = mid+1;
            }else{
                u = mid-1;;
            }
        }
        return u;
    }

    private boolean doable(int[][] g){
        Deque<int[]> q = new ArrayDeque<>();
        int m = g.length;
        int n = g[0].length;
        for(int j=0; j<n; j++){
            if(g[0][j]==0){
                g[0][j] = 2;
                q.offer(new int[]{0, j});
            }
        }
        while(!q.isEmpty()){
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            if(i==m-1){
                return true;
            }
            for(int[] d: dirs){
                int ni = i+d[0];
                int nj = j+d[1];
                if(ni>=0 && ni<m && nj>=0 && nj<n && g[ni][nj] == 0){
                    g[ni][nj] = 2;
                    q.offer(new int[]{ni, nj});
                }
            }
        }
        return false;
    }
}
