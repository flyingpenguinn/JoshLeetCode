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
        int n = a.length;
        int maxe = a[0];
        int mine = a[0];
        int max = a[0];
        for (int i = 1; i < n; i++) {
            int nmaxe = max(maxe * a[i], max(mine * a[i], a[i])); // need this not to override maxe for mine
            mine = min(maxe * a[i], min(mine * a[i], a[i]));
            maxe = nmaxe;
            max = Math.max(maxe, max);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -8, -7, 2};
        System.out.println(new MaxProductSubarray().maxProduct(nums));
    }
}
