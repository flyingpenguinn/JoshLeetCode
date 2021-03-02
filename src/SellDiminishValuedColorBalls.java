import base.ArrayUtils;

import java.util.Arrays;

public class SellDiminishValuedColorBalls {
    // keep making a[i] the same as the previous value and expand the range of "same values". for each i we only need to visit once
    // 2,2,2,3,3,3,4,4-> 2,2,2,3,3,3,3,3-> 2,2,2,2,2,2,2.... etc
    private int mod = 1000000007;

    public int maxProfit(int[] a, int orders) {
        int n = a.length;
        Arrays.sort(a);
        long res = 0;
        int i = n - 1;
        while (i >= 0) {
            int j = i;
            while (j >= 0 && a[j] == a[i]) {
                j--;
            }
            // balls from j+1 to n-1
            long batch = n - j - 1;
            long diff = a[i] - (j == -1 ? 0 : a[j]);
            // how many "levels" do we take the batch number of balls
            long taken = Math.min(orders / batch, diff);
            res += batch * (a[i] + a[i] - taken + 1) * taken / 2;
            res %= mod;
            // a[i], a[i]-1...a[i]-taken+1, there are "batch" balls like this
            orders -= taken * batch;
            if (taken < diff) {
                // we now take a[i]-taken level and take "order" number of them
                res += (a[i] - taken) * orders;
                res %= mod;
                break;
            }
            i = j;
        }
        return (int) res;
    }


    public static void main(String[] args) {
        System.out.println(new SellDiminishValuedColorBalls().maxProfit(ArrayUtils.read1d("497978859,167261111,483575207,591815159"), 836556809));
    }
}
