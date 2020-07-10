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
        if (a == null) {
            return -1; // or throw
        }
        int maxEnding = a[0];
        int minEnding = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            int newMaxEnding = max(maxEnding * a[i], minEnding * a[i], a[i]); // avoid maxending being overwritten- we need it later
            minEnding = min(maxEnding * a[i], minEnding * a[i], a[i]);
            maxEnding = newMaxEnding;
            max = max(max, maxEnding);
        }
        return max;
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -8, -7, 2};
        System.out.println(new MaxProductSubarray().maxProduct(nums));
    }
}
