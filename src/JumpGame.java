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
    // the only thing matters is the min can index. as long as we can reach there it's good
    public boolean canJump(int[] a) {
        int n = a.length;
        int mincan = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int dest = a[i] + i;
            // only need to know mincan
            if (dest >= mincan) {
                mincan = i;
            }
        }
        return mincan == 0;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new JumpGame().canJump(nums));
    }
}

class JumpGameDp {

    private int[] dp = null;

    public boolean canJump(int[] nums) {
        dp = new int[nums.length];
        return doCanJump(nums, 0);
    }

    private boolean doCanJump(int[] nums, int i) {
        if (i >= nums.length - 1) {
            return true;
        }
        if (dp[i] != 0) {
            return dp[i] == 1;
        }
        for (int j = nums[i]; j >= 1; j--) {
            boolean can = doCanJump(nums, i + j);
            if (can) {
                dp[i] = 1;
                return true;
            }
        }
        dp[i] = 2;
        return false;
    }
}
