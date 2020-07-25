import java.util.Arrays;

/*

LC#16
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] a, int t) {
        // check null error if needed
        // a.length>=3
        Arrays.sort(a);
        int mindiff = Integer.MAX_VALUE;
        int res = 0;
        int i = 0;
        int n = a.length;
        while (i < n) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = a[i] + a[j] + a[k];
                if (sum == t) {
                    return t;
                }
                if (Math.abs(t - sum) < mindiff) {
                    mindiff = Math.abs(t - sum);
                    res = sum;
                }
                if (sum < t) {
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return res;
    }
}
