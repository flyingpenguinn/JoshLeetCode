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
        // check null etc
        int n = a.length;
        int maxe = a[0];
        int mine = a[0];
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i - 1] == 0) {
                maxe = a[i];
                mine = a[i];
            } else {
                int n1 = maxe * a[i];
                int n2 = mine * a[i];
                maxe = Math.max(n1, Math.max(n2, a[i]));
                // max subarray sum is actually 2 numbers maxed together
                mine = Math.min(n1, Math.min(n2, a[i]));
            }
            max = Math.max(max, maxe);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -8, -7, 2};
        System.out.println(new MaxProductSubarray().maxProduct(nums));
    }
}
