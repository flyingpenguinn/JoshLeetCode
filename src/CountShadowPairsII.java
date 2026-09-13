import java.util.Arrays;
import java.util.TreeSet;

public class CountShadowPairsII {
    // TODO this is very tough...
    private int[] a;
    private int[] vals;
    private int[] upper;
    private int[] lower;
    private FenWick bit;

    public int shadowPairs(int[] nums) {
        a = nums;
        int n = a.length;

        int[] temp = a.clone();
        Arrays.sort(temp);

        int m = 0;
        for (int x : temp) {
            if (m == 0 || temp[m - 1] != x) {
                temp[m++] = x;
            }
        }
        vals = Arrays.copyOf(temp, m);

        upper = new int[n];
        lower = new int[n];
        bit = new FenWick(m + 1);

        return solve(0, n - 1);
    }

    private int solve(int l, int r) {
        if (l >= r) {
            return 0;
        }

        int mid = l + (r - l) / 2;

        int res = solve(l, mid) + solve(mid + 1, r);
        res += countCross(l, mid, r);

        return res;
    }

    private int countCross(int l, int mid, int r) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = mid; i >= l; --i) {
            Integer v = set.higher(a[i]);
            upper[i] = v == null ? Integer.MAX_VALUE : v;
            set.add(a[i]);
        }

        set.clear();

        for (int j = mid + 1; j <= r; ++j) {
            Integer v = set.lower(a[j]);
            lower[j] = v == null ? Integer.MIN_VALUE : v;
            set.add(a[j]);
        }

        Integer[] left = new Integer[mid - l + 1];
        for (int i = l; i <= mid; ++i) {
            left[i - l] = i;
        }
        Arrays.sort(left, (x, y) -> Integer.compare(upper[y], upper[x]));

        Integer[] right = new Integer[r - mid];
        for (int j = mid + 1; j <= r; ++j) {
            right[j - mid - 1] = j;
        }
        Arrays.sort(right, (x, y) -> Integer.compare(a[y], a[x]));

        int res = 0;
        int p = 0;

        for (int j : right) {
            while (p < left.length && upper[left[p]] >= a[j]) {
                bit.u(rank(a[left[p]]), 1);
                ++p;
            }

            int lessThanAj = bit.q(lowerBound(a[j]));
            int lessThanLj = bit.q(lowerBound(lower[j]));

            res += lessThanAj - lessThanLj;
        }

        for (int i = 0; i < p; ++i) {
            bit.u(rank(a[left[i]]), -1);
        }

        return res;
    }

    private int rank(int v) {
        return lowerBound(v) + 1;
    }

    private int lowerBound(long v) {
        int l = 0;
        int u = vals.length - 1;

        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (vals[mid] < v) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }

        return l;
    }

    static class FenWick {
        private int[] bit;

        public FenWick(int n) {
            this.bit = new int[n];
        }

        private int q(int i) {
            int res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & (-i);
            }
            return res;
        }

        private void u(int i, int d) {
            while (i < bit.length) {
                bit[i] += d;
                i += i & (-i);
            }
        }
    }
}
