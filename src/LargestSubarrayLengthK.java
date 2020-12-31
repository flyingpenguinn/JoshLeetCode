import base.ArrayUtils;

import java.util.Arrays;

public class LargestSubarrayLengthK {

    // similar to #1163
    public int[] largestSubarray(int[] a, int k) {
        int n = a.length;
        int l = 0;
        int r = 1;
        int m = 0;
        while (r + k - 1 < n && r + m < n) {
            if (a[l + m] == a[r + m]) {
                m++;
            } else if (a[l + m] > a[r + m]) {
                r = r + m + 1;
                m = 0;
            } else {
                l = Math.max(l + m + 1, r);
                r = r + 1;
                m = 0;
            }
        }
        int[] res = new int[k];
        for (int i = l; i < l + k; i++) {
            res[i - l] = a[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LargestSubarrayLengthK().largestSubarray(ArrayUtils.read1d("3,1,3,1,3,2"), 2)));
    }
}
