import java.util.List;

public class MaxValueOfFunctionBallPassingGame {
    // binary lifting
    public long getMaxFunctionValue(List<Integer> a, long k) {
        int m = 35; //  10 ^10 is 2^33.xx
        int n = a.size();
        int[][] pos = new int[m][n];  // pos[i][j]: the position if we start from j and walk 2^i steps
        long[][] profit = new long[m][n]; // the profit if we start from j and walk 2^i steps
        for (int j = 0; j < n; ++j) {
            // from j walk 1 step we get a.get(j) itself
            int v = a.get(j);
            pos[0][j] = v;
            profit[0][j] = v;
        }
        for (int i = 1; i < 35; ++i) {
            for (int j = 0; j < n; ++j) {
                // from j walk 2^i = from j walk 2^ i-1 then from that place walk 2^ i-1
                int mid = pos[i - 1][j];
                pos[i][j] = pos[i - 1][mid];
                long p1 = profit[i - 1][j];
                long p2 = profit[i - 1][mid];
                long cur = p1+p2;
                profit[i][j] = cur;
            }
        }
        long res = 0;
        for (int j = 0; j < n; ++j) {
            long cur = j;
            int cp = j;
            for (int i = 0; i < m; ++i) {
                // we split k into powers of two which is 1<<i
                if (((1L << i) & k) == 0) {
                    continue;
                }
                cur += profit[i][cp];
                cp = pos[i][cp];
            }
            res = Math.max(res, cur);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxValueOfFunctionBallPassingGame().getMaxFunctionValue(List.of(2, 0, 1), 4));
    }
}
