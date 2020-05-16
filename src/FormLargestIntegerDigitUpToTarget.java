import base.ArrayUtils;

import java.util.Arrays;

public class FormLargestIntegerDigitUpToTarget {
    // deal with impossibles!
    String[] dp;

    public String largestNumber(int[] a, int t) {
        dp = new String[t + 1];
        String r = dol(a, t);
        return r.equals("*") ? "0" : r;

    }

    private String dol(int[] a, int t) {
        if (t == 0) {
            return "";
        }
        if (dp[t] != null) {
            return dp[t];
        }
        String r = "*";
        for (int i = 9; i >= 1; i--) {
            if (t - a[i - 1] >= 0) {
                String lt = dol(a, t - a[i - 1]);
                if (lt.equals("*")) {
                    continue;
                }
                String cur = "" + i + lt;
                if (r.equals("*") || cur.length() > r.length()) {
                    r = cur;
                }
            }
        }
        dp[t] = r;
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new FormLargestIntegerStrictOtarget().largestNumber(ArrayUtils.read1d("[4,3,2,5,6,7,2,5,5]"), 9));
    }
}


class FormLargestIntegerStrictOtarget {

    int[] dp;

    public String largestNumber(int[] a, int t) {
        dp = new int[t + 1];
        Arrays.fill(dp, -2);
        int r = dol(a, t);
        return r == -1 ? "0" : solve(t, a);
    }

    private String solve(int t, int[] a) {
        if (t == 0) {
            return "";
        }
        for (int i = 9; i >= 1; i--) {
            if (t >= a[i - 1]) {
                if (dp[t] == dp[t - a[i - 1]] + 1) {
                    return i + solve(t - a[i - 1], a);
                }
            }
        }
        return "";
    }

    private int dol(int[] a, int t) {
        if (t == 0) {
            dp[t] = 0;
            return 0;
        }
        if (dp[t] != -2) {
            return dp[t];
        }
        int r = -1;
        for (int i = 9; i >= 1; i--) {
            if (t - a[i - 1] >= 0) {
                int lt = dol(a, t - a[i - 1]);
                if (lt == -1) {
                    // impossible
                    continue;
                }
                int cur = 1 + lt;
                r = Math.max(r, cur);
            }
        }
        dp[t] = r;
        return r;
    }
}