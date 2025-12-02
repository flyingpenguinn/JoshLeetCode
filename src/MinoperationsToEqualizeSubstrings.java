import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinoperationsToEqualizeSubstrings {
    // TODO
    int[] lg;
    int[][] stMin;
    int[][] stMax;

    int n;
    int[][] tv;
    long[][] tp;
    int[] vals;
    long[] ps;

    public long[] minOperations(int[] nums, int k, int[][] queries) {
        n = nums.length;
        int qn = queries.length;

        int[] rem = new int[n];
        for (int i = 0; i < n; ++i) {
            rem[i] = (int) (nums[i] % k);
        }
        buildST(rem);

        tv = new int[4 * n + 4][];
        tp = new long[4 * n + 4][];
        buildMST(1, 0, n - 1, nums);

        int[] tmp = nums.clone();
        Arrays.sort(tmp);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || tmp[i] != tmp[i - 1]) {
                tmp[m++] = tmp[i];
            }
        }
        vals = Arrays.copyOf(tmp, m);

        ps = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            ps[i + 1] = ps[i] + nums[i];
        }

        long[] res = new long[qn];
        for (int qi = 0; qi < qn; ++qi) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            if (!sameRem(l, r)) {
                res[qi] = -1;
                continue;
            }
            int len = r - l + 1;
            int need = (len + 1) / 2;

            int lo = 0;
            int hi = vals.length - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int v = vals[mid];
                long cnt = queryCntSum(1, 0, n - 1, l, r, v)[0];
                if (cnt >= need) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            int med = vals[lo];

            long[] cs = queryCntSum(1, 0, n - 1, l, r, med);
            long cntL = cs[0];
            long sumL = cs[1];
            long sumAll = ps[r + 1] - ps[l];
            long cntR = len - cntL;
            long sumR = sumAll - sumL;
            long sumAbs = med * cntL - sumL + sumR - med * cntR;
            res[qi] = sumAbs / k;
        }

        return res;
    }

    void buildST(int[] a) {
        int len = a.length;
        lg = new int[len + 1];
        for (int i = 2; i <= len; ++i) {
            lg[i] = lg[i / 2] + 1;
        }
        int K = lg[len] + 1;
        stMin = new int[K][len];
        stMax = new int[K][len];
        for (int i = 0; i < len; ++i) {
            stMin[0][i] = a[i];
            stMax[0][i] = a[i];
        }
        for (int k = 1; k < K; ++k) {
            int step = 1 << k;
            int half = step >> 1;
            for (int i = 0; i + step <= len; ++i) {
                int v1 = stMin[k - 1][i];
                int v2 = stMin[k - 1][i + half];
                stMin[k][i] = v1 < v2 ? v1 : v2;
                int u1 = stMax[k - 1][i];
                int u2 = stMax[k - 1][i + half];
                stMax[k][i] = u1 > u2 ? u1 : u2;
            }
        }
    }

    boolean sameRem(int l, int r) {
        int len = r - l + 1;
        int k = lg[len];
        int off = r - (1 << k) + 1;
        int mn1 = stMin[k][l];
        int mn2 = stMin[k][off];
        int mn = mn1 < mn2 ? mn1 : mn2;
        int mx1 = stMax[k][l];
        int mx2 = stMax[k][off];
        int mx = mx1 > mx2 ? mx1 : mx2;
        return mn == mx;
    }

    void buildMST(int idx, int l, int r, int[] a) {
        if (l == r) {
            int[] arr = new int[1];
            arr[0] = a[l];
            tv[idx] = arr;
            long[] pf = new long[1];
            pf[0] = a[l];
            tp[idx] = pf;
            return;
        }
        int mid = l + (r - l) / 2;
        int left = idx * 2;
        int right = idx * 2 + 1;
        buildMST(left, l, mid, a);
        buildMST(right, mid + 1, r, a);
        int[] av = tv[left];
        int[] bv = tv[right];
        int na = av.length;
        int nb = bv.length;
        int[] cv = new int[na + nb];
        long[] cp = new long[na + nb];
        int i = 0;
        int j = 0;
        int p = 0;
        long s = 0;
        while (i < na || j < nb) {
            if (j == nb || (i < na && av[i] <= bv[j])) {
                cv[p] = av[i];
                s += av[i];
                cp[p] = s;
                i++;
                p++;
            } else {
                cv[p] = bv[j];
                s += bv[j];
                cp[p] = s;
                j++;
                p++;
            }
        }
        tv[idx] = cv;
        tp[idx] = cp;
    }

    long[] queryCntSum(int idx, int l, int r, int ql, int qr, int x) {
        if (qr < l || r < ql) {
            return new long[]{0L, 0L};
        }
        if (ql <= l && r <= qr) {
            int[] arr = tv[idx];
            long[] pf = tp[idx];
            int pos = upperBound(arr, x);
            if (pos <= 0) {
                return new long[]{0L, 0L};
            }
            long cnt = pos;
            long sum = pf[pos - 1];
            return new long[]{cnt, sum};
        }
        int mid = l + (r - l) / 2;
        int left = idx * 2;
        int right = idx * 2 + 1;
        long[] a = queryCntSum(left, l, mid, ql, qr, x);
        long[] b = queryCntSum(right, mid + 1, r, ql, qr, x);
        return new long[]{a[0] + b[0], a[1] + b[1]};
    }

    int upperBound(int[] a, int x) {
        int l = 0;
        int r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] <= x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}

