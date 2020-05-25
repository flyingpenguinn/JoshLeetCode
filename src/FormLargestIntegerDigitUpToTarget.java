import base.ArrayUtils;

import java.util.Arrays;

public class FormLargestIntegerDigitUpToTarget {
    // note how we excluded impossible ones
    public String largestNumber(int[] a, int t) {
        int[] dp = new int[t + 1];
        Arrays.fill(dp, -1000000); // all impossible by default
        dp[0] = 0;
        for (int i = 0; i <= t; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i - a[j - 1] >= 0) {
                    // if possible find the max len
                    dp[i] = Math.max(dp[i], dp[i - a[j - 1]] + 1);
                }
            }
        }
        return dp[t] <= 0 ? "0" : solve(dp, a, t);
    }

    private String solve(int[] dp, int[] a, int j) {
        if (j == 0) {
            return "";
        }
        for (int i = 9; i >= 1; i--) {
            if (j >= a[i - 1] && dp[j] == dp[j - a[i - 1]] + 1) {
                return "" + i + solve(dp, a, j - a[i - 1]);
            }
        }
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        System.out.println(new FormLargestIntegerQuicker().largestNumber(ArrayUtils.read1d("[4,3,2,5,6,7,2,5,5]"), 9));
    }
}


class FormLargestIntegerQuicker {
    // note how we excluded impossible ones
    public String largestNumber(int[] a, int t) {
        String[] dp = new String[t + 1];
        dp[0] = "";
        for (int i = 1; i <= t; i++) {
            // must start from 1 otherwise 0 is overridden with null
            String max = null;
            int maxj = -1;
            for (int j = 9; j >= 1; j--) {
                if (i - a[j - 1] >= 0) {
                    // if possible find the max len
                    String before = dp[i - a[j - 1]];
                    if (before == null) {
                        continue;
                    }
                    if (max == null || before.length() > max.length()) {
                        max = before;
                        maxj = j;
                    }
                }
            }
            dp[i] = max == null ? null : maxj + max;
        }
        return dp[t] == null ? "0" : dp[t];
    }
}