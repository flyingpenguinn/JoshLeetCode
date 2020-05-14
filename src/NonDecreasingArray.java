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

    // either change a big one, or change a small one
    public boolean checkPossibility(int[] a) {
        return cb(a) || cs(a);
    }

    // change sth too big
    boolean cb(int[] a) {
        int n = a.length;
        int min = a[n - 1];
        int c = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] > min) {
                c++;
                if (c == 2) {
                    return false;
                }
            } else {
                min = Math.min(a[i], min);
            }

        }
        return true;
    }

    // change sth too small
    boolean cs(int[] a) {
        int max = a[0];
        int n = a.length;
        int c = 0;

        for (int i = 1; i < n; i++) {
            if (a[i] < max) {
                c++;
                if (c == 2) {
                    return false;
                }
            } else {
                max = Math.max(a[i], max);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 1};
        System.out.println(new NonDecreasingArray().checkPossibility(nums));
    }
}
