import base.ArrayUtils;

import java.util.*;


public class CatAndMouse {

    // the bfs approach towards min max. go from known status. color the immediate next moves when the next move's mover is guranteed to win: because mouse just needs to move to mouse win position. if it's cat moving then we need to make sure this position can only lose (Degree==0) then mark it as a lose
    public int catMouseGame(int[][] g) {
        int n = g.length;
        int[][][] c = new int[n][n][3];
        // color
        // == 0: draw
        // == 1: mouse win
        // == 2: cat win
        // mouse pos
        // cat pos
        // 1: mouse move 2: cat move
        Deque<int[]> q = new ArrayDeque<>();
        // in queue: mouse pos, cat pos, mouse or cat moves, and known color
        int[][][] d = new int[n][n][3];
        for(int i=0; i<n; i++){
            c[i][i][1] = 2;
            c[i][i][2] = 2;
            q.offer(new int[]{i,i,1,2});
            q.offer(new int[]{i,i,2,2});
            // cat caught mouse is a cat win
        }
        for(int i=0; i<n; i++){
            c[0][i][1] = 1;
            c[0][i][2] = 1;
            q.offer(new int[]{0, i,1,1});
            q.offer(new int[]{0, i,2,1});
            // mouse at 0 is a mouse win
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                d[i][j][1] = g[i].length;
                // mouse at i, mouse go, can go to g[i] places
                d[i][j][2] = g[j].length;
                // cat at j, cat go,  can go to g[j] places
                for(int k: g[j]){
                    if(k==0){
                        d[i][j][2]--;
                        break;
                    }
                }
                // cat can't go to 0, so this degree needs to  be deducted. note there is no 0-> j to reduce the degree at j here so we must deduct upfront because there is no such edge
            }
        }
        while(!q.isEmpty()){
            int[] top = q.poll();
            int mp = top[0];
            int cp = top[1];
            int move = top[2];
            int color = top[3];
            //   System.out.println("top = "+Arrays.toString(top));
            for(int[] pa: parents(g, mp, cp, move)){
                int pmp = pa[0];
                int pcp = pa[1];
                int pmove = pa[2];
                if(c[pmp][pcp][pmove] != 0){
                    continue;
                    // already decided
                }
                if(pmove == color){
                    //   System.out.println("direct impacting "+ Arrays.toString(pa)+" coloring it as "+color);
                    c[pmp][pcp][pmove] = color;
                    q.offer(new int[]{pmp, pcp, pmove, color});
                    // if pa is mouse move, and current position can win, mouse should just move this way
                }else{
                    // otherwise if it's a cat move then we need to make sure there is no other way for cat to win. each time cat loses, we --. same logic goes if pa is a mouse move
                    d[pmp][pcp][pmove]--;
                    if(d[pmp][pcp][pmove]==0){

                        //   System.out.println("another impacting "+ Arrays.toString(pa)+" coloring it as "+color);
                        c[pmp][pcp][pmove] = color;
                        q.offer(new int[]{pmp, pcp, pmove, color});
                    }
                }
            }
        }
        return c[1][2][1]; // initially mouse at 1, cat at 2, and mouse goes first
    }

    private List<int[]> parents(int[][] g, int m, int c, int move){
        List<int[]> res = new ArrayList<>();
        if(move==1){
            // mouse move, check cat vertex's nexts
            for(int k: g[c]){
                if(k!=0){
                    // mouse at m, cat at c, mouse move must be from some point k that is connected to c, cat moved
                    // note we need to avoid k==0 for cat moves
                    res.add(new int[]{m, k, 2 });
                }
            }
        }else{
            // move == 2
            for(int k: g[m]){
                res.add(new int[]{k, c, 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] graph = ArrayUtils.readUnEven("[[6],[4,11],[9,12],[5],[1,5,11],[3,4,6],[0,5,10],[8,9,10],[7],[2,7,12],[6,7],[1,4],[2,9]]");
        //    int[][] graph = ArrayUtils.readUnEven("[[1,3],[0],[3],[0,2]]");
        System.out.println(new CatAndMouse().catMouseGame(graph));
    }
}
