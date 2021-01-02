import base.ArrayUtils;

import java.util.Arrays;

public class SellDiminishValuedColorBalls {
    // keep making a[i] the same as the previous value and expand the range of "same values". for each i we only need to visit once
    // 2,2,2,3,3,3,4,4-> 2,2,2,3,3,3,3,3-> 2,2,2,2,2,2,2.... etc

    private int Mod = 1000000007;

    public int maxProfit(int[] a, int orders) {
        int n = a.length;
        Arrays.sort(a);
        int batch = 0;
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            // we now have batch *a[i] waiting to be picked up
            long cur = a[i];
            long prev = i == 0 ? 0 : a[i - 1];
            long diff = cur - prev;
            batch++;
            // key: in one round we take 1 off each one in batch
            long round = Math.min(orders / batch, diff);
            // there could be <round of balls in the diff. we then have to take them all
            orders -= round * batch;
            // in the same batch we take rounds so for each one, price is from cur ...to cur-round+1. we sum up from cur-round+1.. cur
            res += (cur - round + 1 + cur) * round * batch / 2;
            res %= Mod;
            //     System.out.println(i+" "+round+" "+orders+" "+res);
            if (diff > round) {
                // with cur-round still > prev and we can't finishn a full round we know we will end up taking as much as we can in cur-round
                res += orders * (cur - round);
                res %= Mod;
                // can definitely break out because we know we will use up all orders
                break;
            }
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(new SellDiminishValuedColorBalls().maxProfit(ArrayUtils.read1d("497978859,167261111,483575207,591815159"), 836556809));
    }
}
