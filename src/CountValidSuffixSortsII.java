
import java.util.*;

public class CountValidSuffixSortsII {
    // TODO 
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE = 1007;

    private static final int V = 100000;

    private int[] size;
    private long[] hash1, hash2;
    private long[] pow1, pow2;
    private long[] sum1, sum2;

    public int[] countSuffix(int[] a, int[] t) {
        int n = a.length;
        int m = t.length;

        pow1 = new long[n + 1];
        pow2 = new long[n + 1];
        sum1 = new long[n + 1];
        sum2 = new long[n + 1];

        pow1[0] = pow2[0] = 1;

        for (int i = 1; i <= n; ++i) {
            pow1[i] = pow1[i - 1] * BASE % MOD1;
            pow2[i] = pow2[i - 1] * BASE % MOD2;

            sum1[i] = (sum1[i - 1] * BASE + 1) % MOD1;
            sum2[i] = (sum2[i - 1] * BASE + 1) % MOD2;
        }

        // Hash of unchanged prefixes of a
        long[] pre1 = new long[n + 1];
        long[] pre2 = new long[n + 1];

        for (int i = 0; i < n; ++i) {
            pre1[i + 1] =
                (pre1[i] * BASE + a[i]) % MOD1;
            pre2[i + 1] =
                (pre2[i] * BASE + a[i]) % MOD2;
        }

        size = new int[4 * (V + 1)];
        hash1 = new long[4 * (V + 1)];
        hash2 = new long[4 * (V + 1)];

        Map<Long, Integer> cnt = new HashMap<>();

        // Generate hashes for all n possible suffix sorts.
        for (int i = n - 1; i >= 0; --i) {
            add(1, 1, V, a[i]);

            int len = n - i;

            long h1 =
                (pre1[i] * pow1[len] + hash1[1]) % MOD1;
            long h2 =
                (pre2[i] * pow2[len] + hash2[1]) % MOD2;

            long key = (h1 << 32) | h2;
            cnt.merge(key, 1, Integer::sum);
        }

        // Rolling hash over target.
        long[] target1 = new long[m + 1];
        long[] target2 = new long[m + 1];

        for (int i = 0; i < m; ++i) {
            target1[i + 1] =
                (target1[i] * BASE + t[i]) % MOD1;
            target2[i + 1] =
                (target2[i] * BASE + t[i]) % MOD2;
        }

        int[] res = new int[m - n + 1];

        for (int i = 0; i + n <= m; ++i) {
            long h1 =
                (target1[i + n] - target1[i] * pow1[n] % MOD1 + MOD1) % MOD1;
            long h2 =
                (target2[i + n] - target2[i] * pow2[n] % MOD2 + MOD2) % MOD2;

            long key = (h1 << 32) | h2;
            res[i] = cnt.getOrDefault(key, 0);
        }

        return res;
    }

    // Insert one value into the sorted suffix multiset.
    private void add(int node, int l, int r, int v) {
        if (l == r) {
            ++size[node];
            int count = size[node];

            hash1[node] = v * sum1[count] % MOD1;
            hash2[node] = v * sum2[count] % MOD2;
            return;
        }

        int mid = l + (r - l) / 2;

        if (v <= mid) {
            add(node * 2, l, mid, v);
        } else {
            add(node * 2 + 1, mid + 1, r, v);
        }

        int left = node * 2;
        int right = node * 2 + 1;

        size[node] = size[left] + size[right];

        // Descending: larger values (right) come first.
        hash1[node] =
            (hash1[right] * pow1[size[left]]
                + hash1[left]) % MOD1;

        hash2[node] =
            (hash2[right] * pow2[size[left]]
                + hash2[left]) % MOD2;
    }
}

