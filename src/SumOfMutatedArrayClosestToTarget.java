/*
LC#1300
Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.

In case of a tie, return the minimum such integer.

Notice that the answer is not neccesarilly a number from arr.



Example 1:

Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
Example 2:

Input: arr = [2,3,5], target = 10
Output: 5
Example 3:

Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361


Constraints:

1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5
 */
public class SumOfMutatedArrayClosestToTarget {
    // if diff bigger go smaller otherwise go bigger. finally compare l and u. l and u are the best results making the diff > or < target respectively
    public int findBestValue(int[] a, int t) {
        int l = 0;
        int u = t;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int diff = diff(a, mid, t);
            if (diff == 0) {
                return mid;
            } else if (diff > 0) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int ud = Math.abs(diff(a, u, t));
        int ld = Math.abs(diff(a, l, t));
        return ud <= ld ? u : l; // u<l and we need a smaller one
    }

    private int diff(int[] a, int mid, int t) {
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            r += Math.min(a[i], mid);
        }
        return r - t;
    }
}
