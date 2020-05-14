import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/*
LC#526
Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.


Now given N, how many beautiful arrangements can you construct?

Example 1:

Input: 2
Output: 2
Explanation:

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.


Note:

N is a positive integer and will not exceed 15.
 */
public class BeautifulArrangement {
    // permutation count => combination. when we are at position i, with a status = st, we know how many ways next
    int[][] dp;

    public int countArrangement(int n) {
        dp = new int[n][(1 << n)];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return doc(0, 0, n);
    }

    int doc(int i, int st, int n) {
        if (i == n) {
            return 1;
        }
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        int ways = 0;
        for (int j = 0; j < n; j++) {
            int bit = ((st >> j) & 1);
            if (bit == 0 && ((j + 1) % (i + 1) == 0 || (i + 1) % (j + 1) == 0)) {
                int nst = st | (1 << j);
                ways += doc(i + 1, nst, n);
            }
        }
        dp[i][st] = ways;
        return ways;
    }
}
