import java.util.ArrayDeque;

public class MaxMovesToKillAllPawns {
    // min max + mask dp + bfs
    private static final int[][] knightMoves = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };

    public int maxMoves(int kx, int ky, int[][] positions) {
        int n = positions.length;
        int[][] dist = new int[n + 1][n];

        // Calculate knight moves from the start position to all pawns
        for (int i = 0; i < n; i++) {
            dist[n][i] = bfs(kx, ky, positions[i][0], positions[i][1]);
        }

        // Calculate knight moves between all pawns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = bfs(positions[i][0], positions[i][1], positions[j][0], positions[j][1]);
            }
        }

        // Memoization to store results for every mask, position, and turn
        Integer[][][] memo = new Integer[1 << n][n + 1][2];
        int result = dfs(0, n, memo, dist, n, 1);
        //System.out.println(Arrays.deepToString(memo));
        return result;
    }

    private int dfs(int mask, int pos, Integer[][][] memo, int[][] dist, int n, int turn) {
        // If all pawns have been captured, return the accumulated moves
        if (mask == (1 << n) - 1) {
            return 0;
        }

        // Check memoization for previously computed results
        if (memo[mask][pos][turn] != null) {
            return memo[mask][pos][turn];
        }

        if (turn == 1) {  // Alice's turn (Maximizing)
            int maxMoves = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    int cd = dist[pos][i];
                    int later = dfs(mask | (1 << i), i, memo, dist, n, 0);
                    int cur = cd + later;
                    maxMoves = Math.max(maxMoves, cur);
                }
            }
            memo[mask][pos][turn] = maxMoves;
        } else {  // Bob's turn (Minimizing)
            int minMoves = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    int cd = dist[pos][i];
                    int later = dfs(mask | (1 << i), i, memo, dist, n, 1);
                    int cur = cd + later;
                    minMoves = Math.min(minMoves, cur);
                }
            }
            memo[mask][pos][turn] = minMoves;
        }

        return memo[mask][pos][turn];
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

            for (int[] move : knightMoves) {
                int nx = x + move[0], ny = y + move[1];
                if (nx >= 0 && ny >= 0 && nx < 50 && ny < 50 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, moves + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
