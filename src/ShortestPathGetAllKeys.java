import java.util.*;

/*
LC#864
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.



Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6


Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
 */
public class ShortestPathGetAllKeys {
    // note this is different from lcp/treasure hunt. in treasure hunt there is no "state" that can block us from going to some node. hence there we can
    // cache the dist and run tsp on treasures
    // same trick can't be applied here because if we dont have the key we can't pass. so this is state on top of bfs problem. no need to enumerate permutation
    // similar to #1293 when it wants shortest path with some optional obstacle we can build the state into bfs states
    private int[][] dirs = {{-1,0}, {1,0}, {0, -1}, {0,1}};
    public int shortestPathAllKeys(String[] a) {
        Deque<int[]> q = new ArrayDeque<>();
        // row, col, key obtain status, dist
        int m = a.length;
        int n = a[0].length();
        int keys = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char c = a[i].charAt(j);
                if(c=='@'){
                    q.offer(new int[]{i, j, 0, 0 });
                }else if(Character.isLowerCase(c)){
                    keys++;
                }
            }
        }
        boolean[][][] v = new boolean[m][n][(1<<keys)];
        v[0][0][0] = true;
        while(!q.isEmpty()){
            // just into i, j, if there's key on ij it's not set yet
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];

            int ck = top[2];
            int dist = top[3];
            if( (ck+1) ==(1<<keys)){
                return dist;
            }
            for(int[] d: dirs){
                int ni = i+d[0];
                int nj = j+d[1];
                if(ni>=0 && ni<m && nj>=0 && nj<n ){
                    char nc = a[ni].charAt(nj);
                    int nck = ck;
                    if(Character.isLowerCase(nc)){
                        nck |= (1<<(nc-'a'));
                    }
                    if(canstep(a[ni].charAt(nj), ck) && !v[ni][nj][nck] ){
                        v[ni][nj][nck] = true;
                        q.offer(new int[]{ni, nj, nck, dist+1});
                    }
                }
            }
        }
        return -1;
    }

    private boolean canstep(char c, int ck){
        if(Character.isUpperCase(c)){
            int lock= c-'A';
            return ((ck>>lock) & 1) ==1;
        }else if(c=='#'){
            return false;
        }else{
            return true;
        }
    }

    public static void main(String[] args) {
        String[] grid = {"@abcdeABCDEFf"};
        System.out.println(new ShortestPathGetAllKeys().shortestPathAllKeys(grid));
    }
}
