public class SumOfSortableIntegers {
    public int sortableIntegers(int[] a) {
        int n = a.length;
        int[] maxleft = new int[n];
        int[] minright = new int[n + 1];
        int[] inc = new int[n];
        maxleft[0] = a[0];
        int Max = (int) 1e9;
        minright[n] = Max;
        for (int i = 1; i < n; ++i) {
            maxleft[i] = Math.max(maxleft[i - 1], a[i]);
        }
        for (int i = n - 1; i >= 0; --i) {
            minright[i] = Math.min(minright[i + 1], a[i]);
        }
        inc[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (a[i] < a[i - 1]) {
                inc[i] = 1;
            } else {
                inc[i] = inc[i - 1] + 1;
            }
        }
        int res = 0;
        for (int k = 1; k * k <= n; ++k) {
            if (n % k != 0) {
                continue;
            }
            boolean bad = isBad(a, n, k, maxleft, minright, inc);
            if (!bad) {
                res += n / k;
            }
            int other = n / k;
            if (other != k) {
                bad = isBad(a, n, other, maxleft, minright, inc);
                if (!bad) {
                    res += n / other;
                }
            }
        }
        return res;
    }

    private static boolean isBad(int[] a, int n, int k, int[] maxleft, int[] minright, int[] inc) {
        int len = n / k;
        boolean bad = false;
        for (int i = 0; i < n; i += len) {
            int l = i;
            int u = i + len - 1;
            if (maxleft[u] > minright[u + 1]) {
                bad = true;
                break;
            }
            if (!sortable(a, i, i + len - 1, inc)) {
                bad = true;
                break;
            }
        }
        return bad;
    }

    private static boolean sortable(int[] a, int l, int u, int[] inc) {
        int left = u - inc[u] + 1;
        if (left <= l) {
            return true;
        }
        int rstart = u - inc[u] + 1;
        int lend = rstart - 1;
        int lendstart = lend - inc[lend] + 1;
        if (lendstart <= l && a[l] >= a[u]) {
            return true;
        }
        return false;
    }
}
