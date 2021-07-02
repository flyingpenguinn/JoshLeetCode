import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinTimeKVirusSpread {
// can't submit yet...


    private int Max = 1000000010;


    public int minDayskVariants(int[][] a, int k) {
        int n = a.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Max);
        int res = Max;
        for (int st = 1; st < (1 << n); st++) {
            int covered = Integer.bitCount(st);
            if (covered == 1) {
                dp[st] = 0;
                continue;
            }
            // from k-1 1s to k ones. flip one of the 1 bits, see how we go from pst to st
            for (int i = 0; i < n; i++) {
                if (((st >> i) & 1) != 1) {
                    continue;
                }
                int pst = st ^ (1 << i);
                int minDays = Max;
                // i is flipped. j is in pst. in order to get k points covered, we need to allow some point between i and j to be covered.
                for (int j = 0; j < n; j++) {
                    if (((pst >> j) & 1) != 1) {
                        continue;
                    }
                    int days = getdays(a, i, j);
                    minDays = Math.min(minDays, days);
                }
                dp[st] = Math.min(dp[st], Math.max(dp[pst], minDays));
            }
            if (covered >= k) {
                res = Math.min(res, dp[st]);
            }
        }
        return res;
    }

    private int getdays(int[][] a, int i, int j) {
        int dx = Math.abs(a[i][0] - a[j][0]);
        int dy = Math.abs(a[i][1] - a[j][1]);
        return (dx + dy + 1) / 2;
    }

    public static void main(String[] args) {
        System.out.println(new MinTimeKVirusSpread().minDayskVariants(ArrayUtils.read("[[0,0],[2,0],[0,2]]"), 3));
        System.out.println(new MinTimeKVirusSpread().minDayskVariants(ArrayUtils.read("[[45,78],[34,6],[94,59]]"), 2));
    }
}
