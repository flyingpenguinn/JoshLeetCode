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
    // we dont need the left array as we can accumulate while moving
    // we dont need right as we can reuse the result array
    public int[] productExceptSelf(int[] a) {
        int n = a.length;
        int[] r = new int[n];
        r[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            r[i] = r[i + 1] * a[i + 1];
        }
        int left = 1;
        for (int i = 0; i < n; i++) {
            r[i] *= left;
            left *= a[i];
        }
        return r;
    }
}
