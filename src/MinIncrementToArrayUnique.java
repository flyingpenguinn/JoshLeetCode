import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeSet;

public class MinIncrementToArrayUnique {
    // when array problems dont need original position, sort first
    // sequenced not related so can sort
    public int minIncrementForUnique(int[] a) {
        Arrays.sort(a);
        int c = 0;
        for (int i = 1; i < a.length; i++) {
            int t = a[i - 1] + 1;
            if (a[i] <= t) {
                c += t - a[i];
                a[i] = t;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new MinIncrementToArrayUnique().minIncrementForUnique(ArrayUtils.read1d(
                "[3,2,1,2,1,7]")));
    }
}
