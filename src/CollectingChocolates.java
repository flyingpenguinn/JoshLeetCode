import base.ArrayUtils;

import java.util.Arrays;

public class CollectingChocolates {
    // iterate through the max possible moves. Note each item is independent in cost, but share the overall transform cost
    public long minCost(int[] a, int x) {
        int n = a.length;
        long Max = (long) 1e16;
        long res = Max;

        long[] cmin = new long[n];

        Arrays.fill(cmin, Max);
        for (long moves = 0; moves < n; ++moves) {
            long mc = moves * x;
            for (int i = 0; i < n; ++i) {
                int npos = (int) ((i + moves) % n);
                long cc = a[npos];
                cmin[i] = Math.min(cmin[i], cc);
            }
            long csum = mc;
            for (int i = 0; i < n; ++i) {
                csum += cmin[i];
            }
            res = Math.min(res, csum);
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
