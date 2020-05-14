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
    int[][] dirs = {{1, 3}, {-1, 1, 3}, {-1, 3}, {-3, 1}, {-3, -1, 1}, {-3, -1}};
    String done = "123450";
    Map<String, String> pre = new HashMap<>();

    public int slidingPuzzle(int[][] b) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(b[i][j]);
            }
        }
        String init = sb.toString();
        q.offer(init + "#0");
        while (!q.isEmpty()) {
            String[] sp = q.poll().split("#");
            String cur = sp[0];
            int step = Integer.valueOf(sp[1]);

            if (cur.equals(done)) {
                //   dfsprint(done,init);
                return step;
            }
            int zi = cur.indexOf('0');
            for (int d : dirs[zi]) {
                int j = zi + d;
                if (j >= 0 && j < 6) {
                    String next = swap(cur, zi, j);
                    if (!seen.contains(next)) {
                        seen.add(next);
                        //   pre.put(next,cur);
                        q.offer(next + "#" + (step + 1));
                    }
                }
            }
        }
        return -1;
    }

    String swap(String s, int i, int j) {
        char[] sc = s.toCharArray();
        char tmp = sc[i];
        sc[i] = sc[j];
        sc[j] = tmp;
        return new String(sc);
    }

    // if we need to print the solution
    void dfsprint(String end, String start) {
        System.out.println(end);
        if (end.equals(start)) {
            return;
        }

        end = pre.get(end);
        dfsprint(end, start);
    }
}
