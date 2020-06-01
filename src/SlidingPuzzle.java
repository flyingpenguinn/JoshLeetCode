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
    class QItem {
        int[][] a;
        int step;

        public QItem(int[][] a, int step) {
            this.a = a;
            this.step = step;
        }
    }

    public int slidingPuzzle(int[][] a) {
        Deque<QItem> q = new ArrayDeque<>();
        q.offer(new QItem(a, 0));
        Set<Integer> seen = new HashSet<>();
        seen.add(code(a));
        while (!q.isEmpty()) {
            QItem top = q.poll();
            if (Arrays.deepEquals(top.a, end)) {
                return top.step;
            }
            List<int[][]> nexts = getnext(top.a);
            for (int[][] ne : nexts) {
                int code = code(ne);
                if (!seen.contains(code)) {
                    seen.add(code);
                    q.offer(new QItem(ne, top.step + 1));
                }
            }
        }
        return -1;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private List<int[][]> getnext(int[][] a) {
        int m = a.length;
        List<int[][]> r = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int n = a[0].length;
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    for (int[] d : dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                            int[][] copya = new int[m][n];
                            for (int k = 0; k < m; k++) {
                                copya[k] = Arrays.copyOf(a[k], a[k].length);
                            }
                            swap(copya, i, j, ni, nj);
                            r.add(copya);
                        }
                    }
                    break;
                }
            }
        }
        return r;
    }

    private void swap(int[][] a, int i, int j, int ni, int nj) {
        int tmp = a[i][j];
        a[i][j] = a[ni][nj];
        a[ni][nj] = tmp;
    }

    private int code(int[][] a) {
        // 123450=? 123450
        // 012345=> 12345
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                r = r * 10 + a[i][j];
            }
        }
        return r;
    }

    int[][] end = {{1, 2, 3}, {4, 5, 0}};
}
