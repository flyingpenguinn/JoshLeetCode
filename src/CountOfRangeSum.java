import base.ArrayUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/*
LC#327
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 */
public class CountOfRangeSum {
    // typical rank based bit similar to count smaller after self
    public int countRangeSum(int[] a, int l, int u) {
        int n = a.length;
        long[] sum = new long[n + 1];
        sum[0] = 0L;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + a[i - 1];
        }
        Arrays.sort(sum);
        TreeMap<Long, Integer> m = new TreeMap<>();
        int rank = 1;
        m.put(sum[0], rank++);
        for (int i = 1; i <= n; i++) {
            if (sum[i] != sum[i - 1]) {
                m.put(sum[i], rank++);
            }
        }
        int[] b = new int[rank];
        u(b, m.get(0L));
        long csum = 0L;
        int r = 0;

        // reverse l,u here and note lower for u
        for (int i = 0; i < n; i++) {
            csum += a[i];
            Long ek = m.floorKey(csum - l);
            int pe = (ek == null) ? 0 : p(b, m.get(ek));
            Long sk = m.lowerKey(csum - u);
            int ps = (sk == null) ? 0 : p(b, m.get(sk));
            //   System.out.println(i+" start key="+sk+" ps= "+ps+" end key="+ek+" pe= "+pe);
            r += pe - ps;
            u(b, m.get(csum));
        }
        return r;
    }

    void u(int[] b, int i) {
        if (i == 0) {
            return;
        }
        while (i < b.length) {
            b[i]++;
            i += i & (-i);
        }
    }

    int p(int[] b, int i) {
        int r = 0;
        while (i > 0) {
            r += b[i];
            i -= i & (-i);
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new CountRangeSumDivideConquer().countRangeSum(ArrayUtils.read1d("[3,9,4,2,6,8]"), 1, 10));
    }
}

// whenever we feel we need a good submap.size to calc how many are bigger/smaller we should consider the merge sort way
class CountRangeSumDivideConquer {
    int r = 0;

    public int countRangeSum(int[] a, int nl, int nu) {
        int n = a.length;
        long[] sum = new long[n + 1];
        sum[0] = 0L;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + a[i - 1];
        }
        doc(sum, 0, n, nl, nu);
        return r;
    }

    private void doc(long[] a, int l, int u, int nl, int nu) {
        if (l >= u) {
            return;
        }
        long[] t = new long[u - l + 1];
        int mid = l + (u - l) / 2;
        doc(a, l, mid, nl, nu);
        doc(a, mid + 1, u, nl, nu);
        int j = mid + 1;
        int i = l;
        int k = l;
        // note this neat way to getting numbers between a[j]-u, a[j]-l for each j: effectively a sliding window!
        while (j <= u) {
            while (i <= mid && a[i] < a[j] - nu) {
                i++;
            }
            while (k <= mid && a[k] <= a[j] - nl) {
                k++;
            }
            // j...k-1
            r += k - i;
            j++;
        }
        j = mid + 1;
        i = l;
        int ti = 0;
        while (i <= mid && j <= u) {
            if (a[i] <= a[j]) {
                t[ti++] = a[i];
                i++;
            } else {
                t[ti++] = a[j];
                j++;
            }
        }
        while (i <= mid) {
            t[ti++] = a[i++];
        }
        while (j <= u) {
            t[ti++] = a[j++];
        }
        for (i = l; i <= u; i++) {
            a[i] = t[i - l];
        }
    }
}