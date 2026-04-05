import java.util.Arrays;

public class MinOperationsToAchieveAtlestKpeaks {
    // rolling dp
    class Solution {
        private int Max = (int) 1e9;
        private int Min = -Max;
        private long Mod = (long) (1e9 + 7);

        public int minOperations(int[] a, int k) {
            int n = a.length;
            if (k >= n / 2 + 10) {
                return -1;
            }
            int way1 = solve(a, k, n, n - 1, 1);
            int way2 = solve(a, k, n, n - 2, 0);
            int res = Math.min(way1, way2);
            return res >= Max ? -1 : res;
        }

        private int solve(int[] a, int k, int n, int end, int start) {
            int[] p2 = new int[k + 1];
            Arrays.fill(p2, Max);
            int[] p1 = new int[k + 1];
            Arrays.fill(p1, Max);
            p2[0] = 0;
            p1[0] = 0;

            for (int i = end; i >= start; --i) {
                int[] dpcur = new int[k + 1];
                for (int remk = 0; remk <= k; ++remk) {
                    int prev = i == 0 ? a[n - 1] : a[i - 1];
                    int next = i == n - 1 ? a[0] : a[i + 1];
                    int way1 = p1[remk];
                    int way2 = (int) Max;
                    if (remk > 0) {
                        int exp = Math.max(prev, next) + 1;
                        int diff = Math.max(0, exp - a[i]);
                        way2 = diff + p2[remk - 1];
                    }

                    int res = Math.min(way1, way2);
                    dpcur[remk] = res;
                }
                for (int j = 0; j <= k; ++j) {
                    p2[j] = p1[j];
                    p1[j] = dpcur[j];
                }
            }
            return p1[k];
        }
    }
}
