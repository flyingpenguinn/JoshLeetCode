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
        int n = a.length;
        if (n < 3) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(a);

        long min = Integer.MAX_VALUE;
        long minnum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                long sum = a[i] + a[j] + a[k];
                long diff = Math.abs(sum - t);
                if (diff < min) {
                    min = diff;
                    minnum = sum;
                }
                // cant use diff to compare
                if (sum == t) {
                    break;
                } else if (sum > t) {
                    k--;
                } else {
                    j++;
                }
            }

        }
        return (int) minnum;
    }
}
