import base.ArrayUtils;

import java.util.Arrays;

public class StoneGameV {
    // brute force dp....passed with a squeaker
    public int stoneGameV(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j - 1; k++) {
                    // i..k is left
                    // k+1.. j is right
                    int left = seg(sum, i, k);
                    int right = seg(sum, k + 1, j);
                    if (left > right) {
                        dp[i][j] = Math.max(dp[i][j], right + dp[k + 1][j]);
                    } else if (left < right) {
                        dp[i][j] = Math.max(dp[i][j], left + dp[i][k]);
                    } else {
                        int maxlater = Math.max(dp[k + 1][j], dp[i][k]);
                        dp[i][j] = Math.max(dp[i][j], left + maxlater);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    private int seg(int[] sum, int i, int j) {
        return i == 0 ? sum[j] : sum[j] - sum[i - 1];
    }


    public static void main(String[] args) {
        System.out.println(new StoneGameV().stoneGameV(ArrayUtils.read1d("[30903,38111,41840,60598,84513,117898,127733,130461,149604,160886,169614,174907,248345,250181,288673,302598,325134,369558,389473,392977,397273,413269,420694,451901,459194,469824,496075,531944,550481,564955,590044,635671,637560,638431,658993,687473,709734,740089,744007,745858,753062,775115,780092,786877,793796,810733,823645,829132,841211,843017,881397,888325,911530,928316,932055,991263]")));
    }
}

class StoneGameTopDown {
    // top down dp will save a lot of unnecessary subproblems comparing to bottom up approach
    int[] sum;
    int[][] dp;

    public int stoneGameV(int[] a) {
        int n = a.length;
        sum = new int[n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        return domin(0, n - 1);
    }

    private int domin(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        for (int k = i; k <= j - 1; k++) {
            int left = seg(i, k);
            int right = seg(k + 1, j);
            if (left > right) {
                dp[i][j] = Math.max(dp[i][j], right + domin(k + 1, j));
            } else if (left < right) {
                dp[i][j] = Math.max(dp[i][j], left + domin(i, k));
            } else {
                int maxlater = Math.max(domin(k + 1, j), domin(i, k));
                dp[i][j] = Math.max(dp[i][j], left + maxlater);
            }
        }
        return dp[i][j];
    }


    private int seg(int i, int j) {
        return i == 0 ? sum[j] : sum[j] - sum[i - 1];
    }
}

class StoneGameVOn2 {
    // similar optimization as optimal bst problem...is this even correct?
    public int stoneGameV(int[] a) {
        int n = a.length;
        int[][] dp = new int[n + 1][n + 1];
        int[][] sel = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][i] = 0; // single stone scores are 0
            sel[i][i] = i;
        }
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int end = Math.min(sel[i + 1][j] + 1, j - 1);
                int start = Math.max(sel[i][j - 1] - 1, i);
                for (int k = start; k <= end; k++) {
                    // i..k is left
                    // k+1.. j is right
                    int left = seg(sum, i, k);
                    int right = seg(sum, k + 1, j);
                    if (left > right) {
                        if (right + dp[k + 1][j] > dp[i][j]) {
                            dp[i][j] = right + dp[k + 1][j];
                            sel[i][j] = k;
                        }
                    } else if (left < right) {
                        if (left + dp[i][k] > dp[i][j]) {
                            dp[i][j] = left + dp[i][k];
                            sel[i][j] = k;
                        }
                    } else {
                        int maxlater = Math.max(dp[k + 1][j], dp[i][k]);
                        if (left + maxlater > dp[i][j]) {
                            dp[i][j] = left + maxlater;
                            sel[i][j] = k;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    private int seg(int[] sum, int i, int j) {
        return i == 0 ? sum[j] : sum[j] - sum[i - 1];
    }
}