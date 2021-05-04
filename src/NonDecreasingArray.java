/*
LC#665
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].
 */
public class NonDecreasingArray {
    public boolean checkPossibility(int[] a) {
        int n = a.length;
        for (int i = 0; i + 1 < n; i++) {
            if (a[i] > a[i + 1]) {
                // a[i] must >= a[i-1] >= earlier already so as long as a[i+1] is a good fit we can do this change
                // and this is better than raising a[i+1] as it reduces later risks
                if (i == 0 || a[i + 1] >= a[i - 1]) {
                    return nondec(a, i + 1);
                } else {
                    a[i + 1] = a[i];
                    return nondec(a, i + 1);
                }
            }
        }
        return true;
    }

    private boolean nondec(int[] a, int i) {
        for (; i + 1 < a.length; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 1};
        System.out.println(new NonDecreasingArray().checkPossibility(nums));
    }
}
