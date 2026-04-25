public class KthSmallestRemainingInteger {
    private int MAX = (int) (2e9);
    private int[] ce;

    public int[] kthRemainingInteger(int[] a, int[][] qs) {
        int n = a.length;
        ce = new int[n + 1];
        int evens = 0;
        for (int i = 0; i < n; ++i) {
            evens += a[i] % 2 == 0 ? 1 : 0;
            ce[i] = evens;
        }
        ce[n] = evens;
        int[] res = new int[qs.length];
        int ri = 0;
        for (int[] q : qs) {
            int start = q[0];
            int end = q[1];
            int k = q[2];
            int l = 1;
            int u = MAX;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                int included = range(a, start, end, 2 * mid);
                if (mid - included >= k) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            res[ri++] = 2 * l;
        }
        return res;
    }

    private int range(int[] a, int l, int u, int x) {
        int n = a.length;
        int v1 = find(a, u, x);
        int v2 = find(a, l - 1, x);
        return (v1 < 0 ? 0 : ce[v1]) - (v2 < 0 ? 0 : ce[v2]);
    }

    private int find(int[] a, int u, int x) {
        int l = 0;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= x) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
