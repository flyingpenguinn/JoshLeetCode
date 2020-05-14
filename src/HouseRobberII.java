import base.ArrayUtils;

import java.util.Arrays;

/*
LC#213
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobberII {
    // either rob last,so1...n-1
    // or dobt,so 0....n-2
    public int rob(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int l = a[n - 1];
        int nl = 0;
        int max = l;// l,not 0
        // rob last
        for (int i = n - 2; i >= 1; i--) {
            int cur = Math.max(l, nl + a[i]);
            max = Math.max(max, cur);
            nl = l;
            l = cur;
        }
        // dont rob last.can do the first
        l = 0;
        nl = 0;
        for (int i = n - 2; i >= 0; i--) {
            int cur = Math.max(l, nl + a[i]);
            max = Math.max(max, cur);
            nl = l;
            l = cur;

        }
        return max;
    }
}

