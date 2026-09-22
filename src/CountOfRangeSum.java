import java.util.Arrays;
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


    public int countRangeSum(int[] a, int lower, int upper) {
        int n = a.length;
        long[] psum = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            psum[i] = psum[i - 1] + a[i - 1];
        }
        TreeMap<Long, Integer> rank = new TreeMap<>();
        long[] psumcp = Arrays.copyOf(psum, n + 1);
        Arrays.sort(psumcp);
        int cr = 1;
        for (int i = 0; i <= n; ++i) {
            if (i == 0 || psumcp[i] != psumcp[i - 1]) {
                rank.put(psumcp[i], cr++);
            }
        }
        FenWick fenWick = new FenWick(cr);
        long res = 0;
        for (int i = 0; i <= n; ++i) {
            long v1 = psum[i] - lower;
            Long lookup1 = rank.floorKey(v1);
            int lookuprank1 = 0;
            if (lookup1 != null) {
                lookuprank1 = rank.get(lookup1);
            }
            long count1 = fenWick.q(lookuprank1);

            long v2 = psum[i] - upper - 1;
            Long lookup2 = rank.floorKey(v2);
            int lookuprank2 = 0;
            if (lookup2 != null) {
                lookuprank2 = rank.get(lookup2);
            }
            long count2 = fenWick.q(lookuprank2);
            res += count1 - count2;
            int crank = rank.get(psum[i]);
            fenWick.u(crank, 1);
        }

        return (int) res;
    }
}