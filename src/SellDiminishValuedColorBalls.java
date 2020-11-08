import java.util.Arrays;

public class SellDiminishValuedColorBalls {
    // keep making a[i] the same as the previous value and expand the range of "same values". for each i we only need to visit once
    // 2,2,2,3,3,3,4,4-> 2,2,2,3,3,3,3,3-> 2,2,2,2,2,2,2.... etc
    private long mod = 1000000007;

    public int maxProfit(int[] a, int orders) {
        int n = a.length;
        Arrays.sort(a);
        int i = n - 1;
        long res = 0;
        int curcount = 0;
        while (i >= 0 && orders > 0) {
            int j = i;
            while (j >= 0 && a[j] == a[i]) {
                j--;
            }
            int pre = j == -1 ? 0 : a[j];
            int len = i - j + curcount;
            // i+1... j are all the same colors
            long curall = len * (a[i] - pre);
            if (orders >= curall) {
                // all balls from i+1...j
                orders -= curall;
                int start = pre + 1;
                long profit = (0L + start + a[i]) * curall / 2;
                profit %= mod;
                res += profit;
                res %= mod;
            } else {
                int rounds = orders / len;
                // rounds must be < a[i]-pre. we need to find out wheree it starts. for example, 2,4,4,4. between 2 and 4 there are 6 balls if orders = 4
                // we know we can get 3 balls to make it 2,3,3,3, then get the remaning 1 ball
                int start = a[i] - rounds + 1;
                long profit = (0L + start + a[i]) * len * rounds / 2;
                profit %= mod;
                res += profit;
                res %= mod;
                int remround = orders % len;
                long profit2 = 1L * remround * (start - 1);
                profit2 %= mod;
                res += profit2;
                res %= mod;
                break;
            }
            curcount += i - j;
            i = j;
        }
        return (int) res;
    }
}
