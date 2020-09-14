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
    // note the special case when length==1
    public int rob(int[] a) {
        if (a.length == 1) {
            return a[0];
        }
        return Math.max(dorob(a, 1, a.length - 1), dorob(a, 0, a.length - 2));
    }

    private int dorob(int[] a, int start, int end) {
        int ap1 = 0;
        int ap2 = 0;
        int max = 0;
        for (int i = end; i >= start; i--) {
            int cur = Math.max(ap1, a[i] + ap2);
            max = Math.max(max, cur);
            ap2 = ap1;
            ap1 = cur;
        }
        return max;
    }
}

