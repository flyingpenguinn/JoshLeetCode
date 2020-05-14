import base.ArrayUtils;

import java.util.Arrays;

/*
LC#1005
Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)

Return the largest possible sum of the array after modifying it in this way.



Example 1:

Input: A = [4,2,3], K = 1
Output: 5
Explanation: Choose indices (1,) and A becomes [4,-2,3].
Example 2:

Input: A = [3,-1,0,2], K = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
Example 3:

Input: A = [2,-3,-1,5,-4], K = 2
Output: 13
Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].


Note:

1 <= A.length <= 10000
1 <= K <= 10000
-100 <= A[i] <= 100
 */
public class MaxSumOfArrayAfterKNegotiations {
    public int largestSumAfterKNegations(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        int r = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] < 0 && k > 0) {
                a[i] = -a[i];
                k--;
            }
            r += a[i];
            min = Math.min(min, a[i]);
        }
        // revert 2*converted min. it could be last neg or first non neg
        if (k % 2 == 0) {
            return r;
        } else {
            r -= 2 * min;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSumOfArrayAfterKNegotiations().largestSumAfterKNegations(ArrayUtils.read1d("4,2,3"), 1));
        System.out.println(new MaxSumOfArrayAfterKNegotiations().largestSumAfterKNegations(ArrayUtils.read1d("3,-1,0,2"), 3));
        System.out.println(new MaxSumOfArrayAfterKNegotiations().largestSumAfterKNegations(ArrayUtils.read1d("2,-3,-1,5,-4"), 2));
        System.out.println(new MaxSumOfArrayAfterKNegotiations().largestSumAfterKNegations(ArrayUtils.read1d("2,-3,-1,5,-4"), 1));
        System.out.println(new MaxSumOfArrayAfterKNegotiations().largestSumAfterKNegations(ArrayUtils.read1d("-8,3,-5,-3,-5,-2"), 6));
    }
}
