import base.ArrayUtils;

import java.util.Arrays;

public class SmallestSubarrayWithMaxBitwiseOr {
    // if certain digit is wanting, it is from the back
    public int[] smallestSubarrays(int[] a) {
        int n = a.length;
        int[] last = new int[32];
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int cmax = 1;
            for (int j = 0; j < 32; ++j) {
                if (((a[i] >> j) & 1) == 0) {
                    cmax = Math.max(cmax, last[j] - i + 1);
                } else {
                    last[j] = i;
                }
            }
            res[i] = cmax;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SmallestSubarrayWithMaxBitwiseOr().smallestSubarrays(ArrayUtils.read1d("1,0,2,1,3"))));
    }
}
