/*
LC#238
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    // use the result, not original array, for left/right purpose
    public int[] productExceptSelf(int[] a) {
        int n = a.length;
        // use r to cache left
        int[] r = new int[n];
        r[0] = 1;
        for (int i = 1; i < n; i++) {
            r[i] = r[i - 1] * a[i - 1];
        }
        int prod = 1;
        // later lefts are useless. we scan backward to avoid using right
        for (int i = n - 2; i >= 0; i--) {
            prod *= a[i + 1];
            r[i] *= prod;
        }
        return r;
    }
}
