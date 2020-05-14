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
public class MincostToMoveStones {
    // dp[i][j][p] = dp[i][j]k] +sum if p==1, or dp[i][mid][1]+dp[mid+1][j][k-1], i.e. bite one piece off then k-1 blocks afterwards
    int[][][] dp;

    public int mergeStones(int[] a, int k) {
        int n = a.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        dp = new int[n + 1][n + 1][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        // merge to k blocks first, then add up all
        int rt = dom(sum, 0, n - 1, 1, k);
        return rt >= Max ? -1 : rt;
    }

    int Max = 1000000;

    // l...u must merge to p blocks, each time we can merge k blocks together, k is given
    int dom(int[] sum, int l, int u, int p, int k) {
        if (u - l + 1 == p) {
            return 0;
        } else if (u - l + 1 < p) {
            return Max; // impossible
        } else if (p == 1) {
            return dom(sum, l, u, k, k) + getsum(sum, l, u); // the only reckon time happens when we merge them to one pile
        }
        if (dp[l][u][p] != -1) {
            return dp[l][u][p];
        }
        int min = Max;
        for (int i = l; i <= u - 1; i += k - 1) {
            // every time bite one block off l
            // either l, or l...l+k-1, or l...l+2*k-1 can be merged into one pile. we can skip other hopeless ones
            int p1 = dom(sum, l, i, 1, k);
            int p2 = dom(sum, i + 1, u, p - 1, k);
            int cur = p1 + p2;
            min = Math.min(min, cur);
        }
        dp[l][u][p] = min;
        return dp[l][u][p];
    }

    int getsum(int[] sum, int l, int u) {
        return sum[u] - (l == 0 ? 0 : sum[l - 1]);
    }


    public static void main(String[] args) {
        int[] stones4 = {3, 2, 4, 1};
        System.out.println(new MincostToMoveStones().mergeStones(stones4, 2));
        int[] stones5 = {8, 8, 9, 1, 7, 2};
        System.out.println(new MincostToMoveStones().mergeStones(stones5, 2));
        int[] stones = {3, 2, 4, 1};
        System.out.println(new MincostToMoveStones().mergeStones(stones, 3));

        int[] stones3 = {3, 8, 6};
        System.out.println(new MincostToMoveStones().mergeStones(stones3, 3));

        int[] stones2 = {3, 5, 1, 2, 6};
        System.out.println(new MincostToMoveStones().mergeStones(stones2, 3));

    }
}
