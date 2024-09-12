package contest;

import base.ArrayUtils;

import java.util.*;

public class Solution2 {
    private static final int[][] KNIGHT_MOVES = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };
    private static final int BOARD_SIZE = 50;

    public int maxMoves(int kx, int ky, int[][] positions) {
        int n = positions.length;
        int[][] distances = new int[positions.length][positions.length];
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                distances[i][j] = bfs(positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
            }
        }

        int[] initialDist = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            initialDist[i] = bfs(kx, ky, positions[i][0], positions[i][1]);
        }
        dp = new Integer[(1 << n)][n + 1][2];

        int rt = dfs(initialDist, distances, 0, n, 1, n);
        System.out.println(Arrays.deepToString(dp));
        return rt;
    }

    private Integer[][][] dp;

    private int dfs(int[] initialDist, int[][] distances, int mask, int pos, int turn, int n) {
        if (mask + 1 == (1<<n)) return 0;

        if (dp[mask][pos][turn] != null) {
            return dp[mask][pos][turn];
        }

        int res = turn == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < initialDist.length; i++) {
            if ((mask & (1 << i)) == 0) {
                int newMask = mask ^ (1 << i);
                int dist = (pos == n) ? initialDist[i] : distances[pos][i];
                int subRes = dfs(initialDist, distances, newMask, i, (turn ^ 1), n);
                final int cur = subRes + dist;
                if (turn == 1) {
                    res = Math.max(res, cur);
                } else {
                    res = Math.min(res, cur);
                }
            }
        }

        dp[mask][pos][turn] = res;
        System.out.println(mask + " " + pos + " " + turn + " " + res);
        return res;
    }

    // BFS to calculate the minimum number of moves from (sx, sy) to (dx, dy)
    private int bfs(int sx, int sy, int dx, int dy) {
        boolean[][] visited = new boolean[50][50];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], moves = cur[2];

            if (x == dx && y == dy) {
                return moves;
            }

            for (int[] move : KNIGHT_MOVES) {
                int nx = x + move[0], ny = y + move[1];
                if (nx >= 0 && ny >= 0 && nx < 50 && ny < 50 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, moves + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        int result = sol.maxMoves(1, 0, ArrayUtils.read("[[3,7],[1,6],[9,0],[10,5]]"));
        System.out.println("Result: " + result);
    }
}
