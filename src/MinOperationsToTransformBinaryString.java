public class MinOperationsToTransformBinaryString {
    public int minOperations(String s1, String s2) {
        int n = s1.length();
        int inf = 1_000_000_000;

        int[] dp = new int[]{0, inf};

        for (int i = 0; i < n; ++i) {
            int[] next = new int[]{inf, inf};

            for (int left = 0; left <= 1; ++left) {
                if (dp[left] >= inf) {
                    continue;
                }

                int maxRight = i == n - 1 ? 0 : 1;

                for (int right = 0; right <= maxRight; ++right) {
                    int cost = getCost(s1, s2, i, left, right, inf);

                    if (cost >= inf) {
                        continue;
                    }

                    next[right] = Math.min(next[right], dp[left] + cost + right);
                }
            }

            dp = next;
        }

        return dp[0] >= inf ? -1 : dp[0];
    }

    private int getCost(String s1, String s2, int i, int left, int right, int inf) {
        int a = s1.charAt(i) - '0';
        int b = s2.charAt(i) - '0';
        int deg = left + right;

        if (deg == 0) {
            if (a == 1 && b == 0) {
                return inf;
            }

            if (a == 0 && b == 1) {
                return 1;
            }

            return 0;
        }

        return deg - a + b;
    }
}
