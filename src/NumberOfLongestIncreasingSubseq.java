import java.util.Arrays;

/*
LC#673
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class NumberOfLongestIncreasingSubseq {
    // record the length and count for each index, then find max
    public int findNumberOfLIS(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int[] dpcount = new int[n];
        int max = 0;
        int maxcount = 0;
        for (int i = 0; i < n; i++) {
            int curmax = 1;
            int cmaxcount = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < a[i]) {
                    int len = dp[j] + 1;
                    if (len > curmax) {
                        curmax = len;
                        cmaxcount = dpcount[j];
                    } else if (len == curmax) {
                        cmaxcount += dpcount[j];
                    }
                }
            }
            dp[i] = curmax;
            dpcount[i] = cmaxcount;
            if (curmax > max) {
                max = curmax;
                maxcount = cmaxcount;
            } else if (curmax == max) {
                maxcount += cmaxcount;
            }
        }
        return maxcount;
    }
}
