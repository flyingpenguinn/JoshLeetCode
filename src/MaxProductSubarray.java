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
    // max end at ai is either max ending at ai-1 *ai,or min ending at ai-1*ai,or ai itself
    public int maxProduct(int[] a) {
        int maxe = 1;
        int mine = 1;
        int max = Integer.MIN_VALUE;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int oldmaxe = maxe; // must copy them out! otherwise maxe is polluted can't be used in mine
            int oldmine = mine;
            maxe = Math.max(oldmaxe * a[i], Math.max(oldmine * a[i], a[i]));
            mine = Math.min(oldmaxe * a[i], Math.min(oldmine * a[i], a[i]));
            max = Math.max(max, maxe);
            if (a[i] == 0) {
                maxe = 1;
                mine = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -8, -7, 2};
        System.out.println(new MaxProductSubarray().maxProduct(nums));
    }
}
