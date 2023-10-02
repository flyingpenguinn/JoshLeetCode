import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class MinSizeSubarraySumInfinite {
    private int MAX = (int) 1e9;

    public int minSizeSubarray(int[] a, int target) {
        long sum = 0;
        int n = a.length;
        for (int ai : a) {
            sum += ai;
        }

        long times = target / sum;
        long rem = target % sum;
        if (rem == 0) {
            return (int) (times * n);
        }
        int res = minlen1(a, n + n, rem);
        return res >= MAX ? -1 : (int) (res + times * n);
    }

    protected int minlen1(int[] a, int n, long target) {

        Map<Long, Integer> m = new HashMap<>();
        m.put(0L, -1);
        int res = MAX;
        long accu = 0;
        for (int i = 0; i < n; ++i) {
            accu += a[i % a.length];
            long look = accu - target;
            if (m.containsKey(look)) {
                int pre = m.get(look);
                int diff = i - pre;
                res = Math.min(res, diff);
            }
            m.put(accu, i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinSizeSubarraySumInfinite().minSizeSubarray(ArrayUtils.read1d("1,6,5,5,1,1,2,5,3,1,5,3,2,4,6,6"), 56));
    }
}
