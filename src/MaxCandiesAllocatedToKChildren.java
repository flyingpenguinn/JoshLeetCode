import base.ArrayUtils;

import java.util.Arrays;

public class MaxCandiesAllocatedToKChildren {
    // kids can't take more than 1 pile!
    public int maximumCandies(int[] a, long k) {
        int n = a.length;
        long l = 1;
        long u = 0;
        for (int ai : a) {
            u += ai;
        }
        while (l <= u) {
            long mid = l + (u - l) / 2;
            long remk = k;
            for (int i = 0; i < n && remk > 0; ++i) {
                remk -= a[i] / mid;
            }
            if (remk > 0) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) u;
    }


    public static void main(String[] args) {
        System.out.println(new MaxCandiesAllocatedToKChildren().maximumCandies(ArrayUtils.read1d("[224144,9905233,2054197,4459724,2371946,9346380,4297420,8450363,1725612,3588976,4879717,2790725,8385955,8867731,8935231,2862853,9887748,8349559,8843387]"), 102911033));
    }
}
