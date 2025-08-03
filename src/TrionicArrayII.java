import base.ArrayUtils;

public class TrionicArrayII {

    public long maxSumTrionic(int[] a) {
        int n = a.length;
        long[] P = new long[n];
        P[0] = a[0];
        for (int i = 1; i < n; ++i) {
            P[i] = P[i-1] + a[i];
        }

        long[] incsumleft = new long[n];
        int[] stinc = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i > 0 && a[i-1] < a[i]) {
                long ext = incsumleft[i-1] + a[i];
                long two = (long)a[i-1] + a[i];
                if (ext > two) {
                    incsumleft[i] = ext;
                    stinc[i] = stinc[i-1];
                } else {
                    incsumleft[i] = two;
                    stinc[i] = i-1;
                }
            } else {
                incsumleft[i] = a[i];
                stinc[i] = i;
            }
        }

        long[] inc2 = new long[n];
        int[] incend = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            if (i + 1 < n && a[i] < a[i+1]) {
                long ext = inc2[i+1] + a[i];
                long two = (long)a[i] + a[i+1];
                if (ext > two) {
                    inc2[i] = ext;
                    incend[i] = incend[i+1];
                } else {
                    inc2[i] = two;
                    incend[i] = i+1;
                }
            } else {
                inc2[i] = a[i];
                incend[i] = i;
            }
        }

        long res = Long.MIN_VALUE;
        for (int u = 0; u < n-1; ++u) {
            if (a[u] > a[u+1]) {
                int v = u;
                while (v+1 < n && a[v] > a[v+1]) {
                    v++;
                }
                long bestA = Long.MIN_VALUE;
                for (int q = u+1; q <= v; ++q) {
                    int p = q-1;
                    if (stinc[p] < p) {
                        long A = incsumleft[p] - a[p] - (p>0 ? P[p-1] : 0);
                        if (A > bestA) bestA = A;
                    }
                    int r = incend[q];
                    if (bestA != Long.MIN_VALUE && r > q) {
                        long total = bestA + P[q] + inc2[q] - a[q];
                        if (total > res) res = total;
                    }
                }
                u = v;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new TrionicArrayII().maxSumTrionic(ArrayUtils.read1d("[2,993,-791,-635,-569]")));
    }

}
