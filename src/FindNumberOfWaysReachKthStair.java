import java.util.HashMap;
import java.util.Map;

public class FindNumberOfWaysReachKthStair {
    // we can have to up plus + 1 minus. slot it in between plus, how many ways
    private int[][][] dp;

    public int waysToReachStair(int k) {
        // Handle base cases
        if (k == 0) {
            return 2;
        }
        if (k == 1) {
            return 4;
        }

        k--; // Decrement k
        int base = 0;
        int times = 0;
        int res = 0;

        while (true) {
            if (base < k) {
                base += (1 << times);
                times++;
                continue;
            }

            int diff = base - k;
            if (diff > times + 1) {
                break;
            }

            int all = diff + times;
            // System.out.println("base=" + base + " diff=" + diff + " all=" + all);

            // Initialize dp array for each new (all, diff) combination
            dp = new int[all][2][diff + 1];
            for (int a = 0; a < all; a++) {
                for (int b = 0; b < 2; b++) {
                    for (int c = 0; c < diff + 1; c++) {
                        dp[a][b][c] = -1;
                    }
                }
            }

            int ways = count(0, 0, diff, all);
            // System.out.println("found ways " + ways);

            res += ways;
            base += (1 << times);
            times++;
        }

        return res;
    }

    private int count(int i, int isMinus, int minus, int n) {
        int plus = n - minus - i;

        // Out-of-bounds or invalid partition
        if (minus < 0 || plus < 0) {
            return 0;
        }

        // If we've placed all n items
        if (i == n) {
            return 1;
        }

        // System.out.println("i=" + i + " isMinus=" + isMinus + " minus=" + minus);

        // Memoized result
        if (dp[i][isMinus][minus] != -1) {
            return dp[i][isMinus][minus];
        }

        // Option 1: put this step in the plus partition
        int way1 = count(i + 1, 0, minus, n);

        // Option 2: if possible, put this step in the minus partition
        int way2 = 0;
        if (isMinus == 0) {
            way2 = count(i + 1, 1, minus - 1, n);
        }

        int result = way1 + way2;
        dp[i][isMinus][minus] = result;
        return result;
    }

}
