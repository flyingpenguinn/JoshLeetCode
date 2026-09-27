import java.util.Arrays;

public class LongestSubarrayDivisibleByKWithOneNegationII {
    // TODO
    public int longestSubarray(int[] a, int k) {
        int n = a.length;
        int INF = 1 << 30;

        int[] first = new int[k];
        int[] best = new int[k];
        int[] processed = new int[k];
        Arrays.fill(first, INF);
        Arrays.fill(best, INF);

        int[] seen = new int[k];
        int sc = 0;

        first[0] = 0;
        seen[sc++] = 0;

        int pref = 0;
        int res = 0;

        for (int j = 0; j < n; ++j) {
            int d = mod(2L * a[j], k);

            for (int z = processed[d]; z < sc; ++z) {
                int q = seen[z];
                int x = (q + d) % k;
                best[x] = Math.min(best[x], first[q]);
            }
            processed[d] = sc;

            pref = mod((long) pref + a[j], k);

            int l = Math.min(first[pref], best[pref]);
            if (l < INF) {
                res = Math.max(res, j + 1 - l);
            }

            if (first[pref] == INF) {
                first[pref] = j + 1;
                seen[sc++] = pref;
            }
        }

        return res;
    }

    private int mod(long x, int k) {
        x %= k;
        if (x < 0) x += k;
        return (int) x;
    }
}
