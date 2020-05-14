import java.util.Arrays;

public class MinSwapToMakeIncreasingArray {
    int Max = 10000;
    int[][] dp;

    public int minSwap(int[] a, int[] b) {
        dp = new int[a.length][2];
        for (int i = 0; i < a.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return domin(a, b, 0, 0);
    }

    // use swapped to help check value of i-1. it's part of the state!
    int domin(int[] a, int[] b, int i, int swapped) {
        if (i == a.length) {
            return 0;
        }
        if (dp[i][swapped] != -1) {
            return dp[i][swapped];
        }
        int pa = -1;
        int pb = -1;
        if (i > 0) {
            pa = swapped == 1 ? b[i - 1] : a[i - 1];
            pb = swapped == 1 ? a[i - 1] : b[i - 1];

        }
        int noswap = Max;
        if (a[i] > pa && b[i] > pb) {
            noswap = domin(a, b, i + 1, 0);
        }
        int swap = Max;
        if (b[i] > pa && a[i] > pb) {
            swap = 1 + domin(a, b, i + 1, 1);
        }
        //  System.out.println(i+" "+swapped+" "+noswap+" "+swap);
        int rt = Math.min(noswap, swap);
        dp[i][swapped] = rt;
        return rt;
    }
}
