public class MinStabilityFactorOfArray {
    // TODO
    int n, maxC;
    int[] a;
    int[][] st;
    int[] log2;

    public int minStable(int[] nums, int maxC) {
        this.n = nums.length;
        this.maxC = maxC;
        this.a = nums;

        // Quick check for S=0: if you can modify every ≥2 element, stability→0
        int cnt2 = 0;
        for (int v : a) if (v >= 2) cnt2++;
        if (cnt2 <= maxC) return 0;

        // build logs and gcd-sparse‐table
        buildSparseTable();

        // binary‐search S in [1..n]
        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (canBreakAll(mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private void buildSparseTable() {
        // precompute floor(log2)
        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++)
            log2[i] = log2[i >>> 1] + 1;

        int K = log2[n] + 1;
        st = new int[K][n];
        // level 0 = the array itself
        for (int i = 0; i < n; i++)
            st[0][i] = a[i];
        // build up
        for (int p = 1; p < K; p++) {
            int len = 1 << p;
            for (int i = 0; i + len <= n; i++) {
                st[p][i] = gcd(st[p - 1][i], st[p - 1][i + (len >>> 1)]);
            }
        }
    }

    // query gcd on [L..R]
    private int rangeGcd(int L, int R) {
        int p = log2[R - L + 1];
        return gcd(st[p][L], st[p][R - (1 << p) + 1]);
    }

    // can we ensure no stable subarray > S after ≤ maxC changes?
    private boolean canBreakAll(int S) {
        int used = 0;
        int window = S + 1;
        int i = 0;
        while (i + window - 1 < n) {
            if (rangeGcd(i, i + window - 1) >= 2) {
                // must break this window: place break at right end
                used++;
                if (used > maxC) return false;
                // skip all windows that include that break
                i = i + window;
            } else {
                i++;
            }
        }
        return true;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}


