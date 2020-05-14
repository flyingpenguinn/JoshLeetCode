import base.ArrayUtils;

import java.util.*;

/*
LC#493
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
 */
public class ReversePairs {
    // merge sort like reverse pair. use a linear scan for a[i]>a[j]*2
    public int reversePairs(int[] a) {
        return dor(a, 0, a.length - 1);
    }

    int dor(int[] a, int l, int u) {
        if (l >= u) {
            return 0;
        }
        int mid = l + (u - l) / 2;
        int lr = dor(a, l, mid);
        int rr = dor(a, mid + 1, u);
        int[] t = new int[u - l + 1];
        int ti = 0;
        int i = l;
        int j = mid + 1;
        int r = 0;
        while (i <= mid) { // invariant: a[i] > 2*a[j-1] if j-1 is valid
            while (j <= u && a[j] <= Integer.MAX_VALUE / 2 && (a[j] <= Integer.MIN_VALUE / 2 || a[i] > 2 * a[j])) {
                j++;
            }
            r += j - (mid + 1); // from mid+1 to j-1 all <a[i]
            i++;
        }
        i = l;
        j = mid + 1;
        while (i <= mid && j <= u) {
            if (a[i] <= a[j]) {
                t[ti++] = a[i++];
            } else {
                t[ti++] = a[j++];
            }
        }
        while (i <= mid) {
            t[ti++] = a[i++];
        }
        while (j <= u) {
            t[ti++] = a[j++];
        }
        for (int k = l; k <= u; k++) {
            a[k] = t[k - l];
        }
        return r + lr + rr;
    }

    public static void main(String[] args) {
        System.out.println(new ReversePairsBit().reversePairs(ArrayUtils.read1d("2,5,4,3,1")));
    }
}

class ReversePairsBit {
    // use treemap to look up nearest 2*a[i]
    public int reversePairs(int[] a) {
        TreeMap<Long, Integer> rank = new TreeMap<>();
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int rk = getrankmap(a, rank, n);
        int[] bit = new int[rk];
        int r = 0;
        for (int i = n - 1; i >= 0; i--) {
            long longai = (long) a[i];
            Long lowerkey = rank.lowerKey(longai);
            if (lowerkey != null) {
                int currank = rank.get(lowerkey);
                int cur = p(bit, currank);
                r += cur;
            }
            u(bit, rank.get(2 * longai));
        }
        return r;
    }

    private int getrankmap(int[] a, TreeMap<Long, Integer> rank, int n) {
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        int rk = 1;
        for (int i = 1; i <= n; i++) {
            if (i == n || ca[i] != ca[i - 1]) {
                rank.put(2 * (long) ca[i - 1], rk++);
            }
        }
        return rk;
    }

    int p(int[] b, int i) {
        int r = 0;
        while (i > 0) {
            r += b[i];
            i -= i & (-i);
        }
        return r;
    }

    void u(int[] b, int i) {
        while (i < b.length) {
            b[i]++;
            i += i & (-i);
        }
    }
}
