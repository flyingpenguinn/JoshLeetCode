import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestFibonacciSubsequence {
    Map<Integer, Integer> index = new HashMap<>();

    int[][] dp;

    public int lenLongestFibSubseq(int[] a) {
        int n = a.length;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            index.put(a[i], i);
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (index.containsKey(a[i] + a[j])) {
                    int len = 1 + dolen(a, i, j);
                    max = Math.max(max, len);
                }
            }
        }
        return max < 3 ? 0 : max;
    }

    private int dolen(int[] a, int pre, int i) {
        if (dp[i][pre] != -1) {
            return dp[i][pre];
        }
        int next = a[pre] + a[i];
        int max = 1; // at least len = 1
        Integer nextIndex = index.get(next);
        if (nextIndex != null) {
            int len = 1 + dolen(a, i, nextIndex);
            max = Math.max(max, len);
        }
        dp[i][pre] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestFibonacciSubsequence().lenLongestFibSubseq(ArrayUtils.read1d("1,2,3,4,5,6,7,8")));
    }

}