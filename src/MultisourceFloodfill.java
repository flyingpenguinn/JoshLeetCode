import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MultisourceFloodfill {
    private int[][] dirs = {{-1, 0}, {1,0}, {0, -1}, {0,1}};
    private int Max = (int)(1e9);
    public int[][] colorGrid(int n, int m, int[][] ss) {
        int[][] g = new int[n][m];
        int[][] dist = new int[n][m];
        for(int i=0; i<n; ++i){
            Arrays.fill(dist[i], Max);
        }
        Deque<int[]> q = new ArrayDeque<>();
        for(int[] s: ss){
            int sr = s[0];
            int sc = s[1];
            int cl = s[2];
            g[sr][sc] = cl;
            dist[sr][sc] = 0;
            q.offerLast(new int[]{sr, sc, 0});
        }
        while(!q.isEmpty()){
            int[] top = q.pollFirst();
            int tr = top[0];
            int tc = top[1];
            int tdist = top[2];
            int ndist = tdist+1;
            int tcc = g[tr][tc];
            for(int[] d: dirs){
                int nr = tr+d[0];
                int nc = tc+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m)
                    if(dist[nr][nc]>ndist){
                        g[nr][nc] = tcc;
                        dist[nr][nc] = ndist;
                        q.offerLast(new int[]{nr, nc, ndist});
                    } else if(dist[nr][nc] == ndist){
                        g[nr][nc] = Math.max(g[nr][nc], tcc);
                    }
            }
        }
        return g;
    }
}
