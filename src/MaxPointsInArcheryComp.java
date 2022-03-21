import base.ArrayUtils;

import java.util.Arrays;

public class MaxPointsInArcheryComp {
    // dp then backout the solution
    private Integer[][] dp;
    private Integer[][] choice;

    public int[] maximumBobPoints(int numArrows, int[] a) {
        int n = a.length;
        dp = new Integer[n][numArrows + 1];
        choice = new Integer[n][numArrows + 1];
        solve(a, 0, numArrows);

        int[] bb = new int[n];
        populate(bb, 0, numArrows, a);
        return bb;
    }

    private void populate(int[] bb, int i, int j, int[] a) {
        int n = bb.length;
        if (i == n) {
            return;
        }
        if (choice[i][j] == 1) {
            bb[i] = a[i] + 1;
            populate(bb, i + 1, j - a[i] - 1, a);
        } else if (choice[i][j] == 2) {
            populate(bb, i + 1, j, a);
        }
    }

    // ith pos, j rem arrows
    private int solve(int[] a, int i, int j) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int way1 = 0;
        if (j >= a[i] + 1) {
            way1 = i + solve(a, i + 1, j - a[i] - 1);
        }
        int way2 = solve(a, i + 1, j);
        int rt = Math.max(way1, way2);
        if (way1 >= way2 && way1 != 0) {
            choice[i][j] = 1;
        } else {
            choice[i][j] = 2;
        }
        dp[i][j] = rt;
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxPointsInArcheryComp().maximumBobPoints(3, ArrayUtils.read1d("[0,0,1,0,0,0,0,0,0,0,0,2]"))));
    }
}


class MaxPointsInArcheryCompetitionSol2 {
    public int[] maximumBobPoints(int numArrows, int[] a) {
        int n = a.length;
        int maxPoints = 0;
        int[] maxb = new int[n];
        for (int st = 0; st < (1 << n); ++st) {
            int cur = 0;
            int[] cb = new int[n];
            int arrows = 0;
            for (int i = 0; i < n; ++i) {
                if (((st >> i) & 1) == 1) {
                    cur += i;
                    arrows += a[i] + 1;
                    cb[i] = a[i] + 1;
                }
            }
            if (arrows <= numArrows) {
                cb[0] += numArrows - arrows; // trap. give all the remaining ones to the first pos
                if (cur > maxPoints) {
                    maxPoints = cur;
                    maxb = cb;
                }
            }
        }
        return maxb;
    }
}