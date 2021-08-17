import base.ArrayUtils;


import java.util.Arrays;
import java.util.TreeMap;

public class MakeArrayStrictlyIncrease {
    int[][][] dp;
    int Max = 1000000;

    public int makeArrayIncreasing(int[] a, int[] b) {
        Arrays.sort(b);
        int maxlen = Math.max(a.length, b.length);
        dp = new int[a.length][maxlen][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int rt1 = dom(1, 0, 0, a, b);
        // note we can change the first element too! so two dps
        int rt2 = 1 + dom(1, 0, 1, a, b);
        int rt = Math.min(rt1, rt2);
        return rt >= Max ? -1 : rt;
    }

    // pre and before all settled
    // now deal with a[i] and pre
    private int dom(int i, int j, int aorb, int[] a, int[] b) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j][aorb] != -1) {
            return dp[i][j][aorb];
        }
        // either keep a[i] if possible (since it's >pre)
        int pre = pre(aorb, j, a, b);
        int way1 = Max;
        if (a[i] > pre) {
            way1 = dom(i + 1, i, 0, a, b);
        }
        // or set it to the best value we can get based on pre and move forward
        int way2 = Max;
        int bsm = smallestbigger(b, pre);
        if (bsm >= 0 && bsm < b.length) {
            way2 = 1 + dom(i + 1, bsm, 1, a, b);
        }
        int rt = Math.min(way1, way2);
        dp[i][j][aorb] = rt;
        return rt;
    }

    private int smallestbigger(int[] b, int t) {
        int l = 0;
        int u = b.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (b[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }

    private int pre(int aorb, int j, int[] a, int[] b) {
        return aorb == 0 ? a[j] : b[j];
    }



}
