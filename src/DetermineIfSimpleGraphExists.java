public class DetermineIfSimpleGraphExists {
    // Erdős–Gallai decision: does a simple (loopless, no multi-edges) graph exist with degree sequence a?
    // Returns true iff the sequence is graphical.
    public boolean simpleGraphExists(int[] a) {
        int n = a.length;                                   // number of vertices
        int[] c = new int[n];                               // c[d] = count of vertices with degree d
        long s = 0L;                                        // sum of degrees (must be even)
        for (int x : a) {
            if (x < 0 || x >= n) {
                return false;              // degree must be in [0, n-1]
            }
            c[x] += 1;                                      // count this degree
            s += x;                                         // accumulate sum for parity check
        }
        if ((s & 1L) == 1L) {
            return false;                   // odd sum cannot be graphical
        }

        int[] b = new int[n];                               // degrees sorted nonincreasingly
        int t = 0;                                          // write index for b
        for (int d = n - 1; d >= 0; --d) {
            int u = c[d];                                   // how many occurrences of degree d
            while (u > 0) {
                b[t] = d;                                   // place d into sorted array
                t += 1;                                     // advance write index
                u -= 1;                                     // consume one
            }
        }

        long[] p = new long[n + 1];                         // prefix sums: p[k] = b[0]+...+b[k-1]
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] + b[i];                         // build prefix sums
        }

        int j = n;                                          // j = #{i : b[i] >= k}, maintained decreasing with k
        for (int k = 1; k <= n; ++k) {
            while (j > 0 && b[j - 1] < k)                   // move j left so that indices [0..j-1] have b[i] >= k
            {
                j -= 1;
            }
            long inside = 1L * k * (k - 1);                 // max stubs used by edges inside top-k: 2*C(k,2) = k(k-1)
            long tailHeavy = 1L * k * Math.max(0, j - k);   // among indices > k-1, those with b[i] >= k contribute k each
            long tailLight = p[n] - p[Math.max(j, k)];      // remaining tail with b[i] < k contribute b[i] each
            long rhs = inside + tailHeavy + tailLight;      // total available stubs to satisfy top-k demand
            if (p[k] > rhs) return false;                   // Erdős–Gallai inequality fails
        }

        return true;                                        // all inequalities passed: sequence is graphical
    }
}
