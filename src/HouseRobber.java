import java.util.Arrays;

/*
LC#198
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {
    // we just need i, i+1, i+2, so only 3 vars needed
    public int rob(int[] a) {
        int n = a.length;
        int ap1 = 0;
        int ap2 = 0;
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = Math.max(ap1, a[i] + ap2);
            max = Math.max(max, cur);
            ap2 = ap1;
            ap1 = cur;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 1, 3, 100};
        System.out.println(new HouseRobber().rob(array));
    }

}
