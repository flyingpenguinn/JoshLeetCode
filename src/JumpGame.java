/*
LC#55
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */

public class JumpGame {
    // think of each interval as [i, i+a[i]] question is then whether they can cover 0... n-1
    // note down the last safe point, which is the START of the interval above
    public boolean canJump(int[] a) {
        int n = a.length;
        int last = n - 1; // last safe place
        for (int i = n - 2; i >= 0; i--) {
            // as long as we can jump ahead of the last safe place
            if (i + a[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }
}
