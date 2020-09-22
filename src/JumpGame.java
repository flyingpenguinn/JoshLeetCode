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
    public boolean canJump(int[] a) {
        int end = a[0];
        for (int i = 1; i<a.length;i++) {
            if(i>end){
                return false;
            }else{
                end = Math.max(end, i+a[i]);
            }
            if(end >= a.length-1){
                return true;
            }
        }
        return end >= a.length-1;
    }
}
