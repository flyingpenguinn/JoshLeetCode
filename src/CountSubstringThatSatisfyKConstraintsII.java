import base.ArrayUtils;

import java.util.Arrays;

public class CountSubstringThatSatisfyKConstraintsII {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        int[] lefts = new int[n];
        int j = 0;
        int c0 = 0;
        int c1 = 0;
        long[] accuLeft = new long[n];
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int cind = c - '0';
            if (cind == 0) {
                ++c0;
            } else {
                ++c1;
            }
            while (c0 > k && c1 > k) {
                if (s.charAt(j) == '0') {
                    --c0;
                } else {
                    --c1;
                }
                ++j;
            }
            lefts[i] = j;
            long count = i - j + 1;
            accuLeft[i] = (i == 0 ? 0 : accuLeft[i - 1]) + count;
        }
        j = n - 1;
        long[] accuRight = new long[n];
        c0 = 0;
        c1 = 0;
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            int cind = c - '0';
            if (cind == 0) {
                ++c0;
            } else {
                ++c1;
            }
            while (c0 > k && c1 > k) {
                if (s.charAt(j) == '0') {
                    --c0;
                } else {
                    --c1;
                }
                --j;
            }
            long count = j - i + 1;
            accuRight[i] = (i == n - 1 ? 0 : accuRight[i + 1]) + count;
        }

        long[] qr = new long[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int[] q = queries[i];
            int start = q[0];
            int end = q[1];
            int mid = Math.max(start, lefts[end]);
            long len = end - mid + 1;
            long cur = len * (len + 1) / 2;
            long add = accuRight[start] - accuRight[mid];
            // here we have to go from the other direction because otherwise there could be strings span from < mid to >=mid
            cur += add;

            qr[i] = cur;
        }
        return qr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountSubstringThatSatisfyKConstraintsII().countKConstraintSubstrings("010101", 1, ArrayUtils.read("[[0,5],[1,4],[2,3]]"))));
    }
}
