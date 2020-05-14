import java.util.Arrays;

/*
LC#377
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.


Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.
 */
public class CombinationSumIv {
    // actually this is permutation count... similar to combination count, can be done in pseudo polynomial time
    int[] dp;

    public int combinationSum4(int[] a, int t) {
        Arrays.sort(a);
        dp = new int[t + 1];
        Arrays.fill(dp, -1);
        return doc(a, t);
    }

    private int doc(int[] a, int t) {
        if (t == 0) {
            return 1;
        }
        if (dp[t] != -1) {
            return dp[t];
        }
        int r = 0;
        for (int ci : a) {
            if (ci <= t) {
                r += doc(a, t - ci);
            } else {
                break;
            }
        }
        dp[t] = r;
        return r;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.println(new CombinationSumIv().combinationSum4(nums, 4));
    }
}
