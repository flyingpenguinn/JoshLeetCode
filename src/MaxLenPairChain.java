import java.util.Arrays;

/*
LC#646
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].
 */
public class MaxLenPairChain {
    // Greedy, select max num of interval, sort by end time
    // given the amount of data, can also dp similar to longest increasing subsequence
    public int findLongestChain(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1]));
        int n = a.length;
        int r = 1;
        int curend = a[0][1];
        for (int i = 1; i < n; i++) {
            if (a[i][0] <= curend) {
                continue;
            }
            r++;
            curend = a[i][1];
        }
        return r;
    }
}
