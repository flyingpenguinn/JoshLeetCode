/*
LC#152
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

import static java.lang.Math.*;

public class MaxProductSubarray {
    // max aend at ai is either max ending at ai-1 *ai,or min ending at ai-1*ai,or ai itself
    public int maxProduct(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int maxhere = a[0];
        int minhere = a[0];
        int max = maxhere;
        for (int i = 1; i < a.length; i++) {
            if (maxhere == 0) {
                maxhere = a[i];  // for later ones, we are better off starting here
                minhere = a[i];
            } else {
                int curmin = Math.min(maxhere * a[i], Math.min(minhere * a[i], a[i]));
                int curmax = Math.max(maxhere * a[i], Math.max(minhere * a[i], a[i]));
                maxhere = curmax;
                minhere = curmin;
            }
            max = Math.max(max, maxhere);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -8, -7, 2};
        System.out.println(new MaxProductSubarray().maxProduct(nums));
    }
}
