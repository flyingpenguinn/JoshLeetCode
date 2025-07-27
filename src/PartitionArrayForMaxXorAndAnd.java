import java.util.Arrays;

public class PartitionArrayForMaxXorAndAnd {
    // TODO
    public long maximizeXorAndXor(int[] a) {
        int n = a.length, N = 1 << n, full = N - 1;
        int[] xor = new int[N], andv = new int[N];
        Arrays.fill(andv, -1);

        // precompute XOR and AND for each subset
        for (int m = 1; m < N; ++m) {
            int lb = m & -m, i = Integer.numberOfTrailingZeros(lb), p = m ^ lb;
            xor[m] = xor[p] ^ a[i];
            andv[m] = p == 0 ? a[i] : (andv[p] & a[i]);
        }

        // bestX[m] = max_{s ⊆ m} (xor[s] + xor[m ^ s]),
        // but only iterate s > (m ^ s) to cut work in half
        long[] bestX = new long[N];
        for (int m = 1; m < N; ++m) {
            long bx = xor[m];
            for (int s = (m - 1) & m; s > (m ^ s); s = (s - 1) & m) {
                long v = (long) xor[s] + xor[m ^ s];
                if (v > bx) bx = v;
            }
            bestX[m] = bx;
        }

        // combine with every choice of B
        long ans = 0;
        for (int b = 0; b < N; ++b) {
            long av = andv[b] < 0 ? 0L : andv[b];
            long tot = bestX[full ^ b] + av;
            if (tot > ans) ans = tot;
        }
        return ans;
    }
}

