import base.ArrayUtils;

import java.util.Arrays;

public class CollectingChocolates {
    public long minCost(int[] a, long x) {
        int n = a.length;
        long[] na = new long[n];
        for (int i = 0; i < n; ++i) {
            na[i] = a[i];
        }
        long res = (long) 2e18;
        for (int i = 0; i < n; ++i) {
            long cur = i * x;
            for (int j = 0; j < n; ++j) {
                na[j] = Math.min(na[j], a[(i + j) % n]);
            }
            for (int j = 0; j < n; ++j) {
                cur += na[j];
            }
            res = Math.min(res, cur);
        }
        return res;
    }

    /*

    [15,150,56,69,214,203]
    42
     */
    public static void main(String[] args) {
        System.out.println(new CollectingChocolates().minCost(ArrayUtils.read1d("[15,150, 56,214]"), 200));
        //System.out.println(new CollectingChocolates().minCost(ArrayUtils.read1d("[20,1,15]"), 5));
    }
}
