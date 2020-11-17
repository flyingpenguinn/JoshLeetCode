import java.util.Arrays;

/*
LC#1000
There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.



Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation:
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 2:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Example 3:

Input: stones = [3,5,1,2,6], K = 3
Output: 25
Explanation:
We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.


Note:

1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100
 */
public class MincostToMergeStones {
    // think about i...j, and the ith stone. it's either merged with some stone first as left (x) then merge with the right part (k-x)
    // or merged with sth coming out of right which means right = k-1
    private int Max = 100000000;
    private int[] sum;
    private Integer[][][] dp;

    public int mergeStones(int[] a, int k) {
        int n = a.length;
        dp = new Integer[n][n][k + 1];
        sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        int rt = domerge(a, 0, a.length - 1, 1, k);
        return rt >= Max ? -1 : rt;
    }

    private int range(int s, int t) {
        return sum[t] - (s == 0 ? 0 : sum[s - 1]);
    }

    // merge i.. j into k piles. we can merge mk together
    // need i...j because the left part is not an O(1) calculation but a recursion too
    private int domerge(int[] a, int i, int j, int k, int mk) {
        int n = a.length;
        if (j - i + 1 == k) {
            // this part first otherwise we dive into mk when k==1 and i==j
            return 0;
        }
        if (j - i + 1 < k) {
            return Max;
        }
        if (k == 1) {
            // key: we can only merge mk together
            return range(i, j) + domerge(a, i, j, mk, mk);
        }
        if (dp[i][j][k] != null) {
            return dp[i][j][k];
        }
        int min = Max;
        // looking at i, it's either merged last, hence p==i, or merged with some stones first, hence i..p merged together first
        for (int p = i; p + 1 <= j; p++) {
            int left = domerge(a, i, p, 1, mk);
            int right = domerge(a, p + 1, j, k - 1, mk);
            int cur = left + right;
            min = Math.min(min, cur);
        }
        dp[i][j][k] = min;
        return min;
    }
}
