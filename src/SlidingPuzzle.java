import java.util.*;

/*
LC#773
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 */
public class SlidingPuzzle {
    // bfs on states

    private int solved = 123450;
    private int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public int slidingPuzzle(int[][] b) {
        int m = b.length;
        int n = b[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        int start = number(b);
        q.offer(new int[]{start,0});
        Set<Integer> seen = new HashSet<>();
        seen.add(start);
        while(!q.isEmpty()){
            int[] top = q.poll();
            //System.out.println(top[0]);
            if(top[0]==solved){
                return top[1];
            }
            int[][] cb = board(top[0], m,n);
            for(int i=0; i<cb.length; i++){
                for(int j=0; j<cb[i].length;j++){
                    if(cb[i][j]==0){
                        for(int[] d: dirs){
                            int ni = i+d[0];
                            int nj = j+d[1];
                            if(ni>=0 && ni<m && nj>=0 && nj<n){
                                int v = cb[ni][nj];
                                cb[i][j] = v;
                                cb[ni][nj] = 0;
                                int ncode = number(cb);
                                if(!seen.contains(ncode)){
                                    seen.add(ncode);
                                    q.offer(new int[]{ncode,top[1]+1});
                                }
                                cb[i][j] = 0;
                                cb[ni][nj] = v;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return -1;
    }

    private int number(int[][] b){
        int res = 0;
        for(int i=0; i<b.length;i++){
            for(int j=0; j<b[0].length;j++){
                res = res*10+ b[i][j];
            }
        }
        return res;
    }

    private int[][] board(int num, int m, int n){
        int[][] res = new int[m][n];
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                res[i][j] = num%10;
                num /= 10;
            }
        }
        return res;
    }
}
