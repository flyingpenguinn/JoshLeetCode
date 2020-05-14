/*
LC#1310
Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri], for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ). Return an array containing the result for the given queries.


Example 1:

Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
Output: [2,7,14,8]
Explanation:
The binary representation of the elements in the array are:
1 = 0001
3 = 0011
4 = 0100
8 = 1000
The XOR values for queries are:
[0,1] = 1 xor 3 = 2
[1,2] = 3 xor 4 = 7
[0,3] = 1 xor 3 xor 4 xor 8 = 14
[3,3] = 8
Example 2:

Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
Output: [8,0,4,4]


Constraints:

1 <= arr.length <= 3 * 10^4
1 <= arr[i] <= 10^9
1 <= queries.length <= 3 * 10^4
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class XorQueriesOfSubarray {
    // can do this because ^ is commutative and is its own inverse
    public int[] xorQueries(int[] a, int[][] qs) {
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] ^ a[i];
        }
        int[] r = new int[qs.length];
        for (int i = 0; i < qs.length; i++) {
            r[i] = qs[i][0] == 0 ? sum[qs[i][1]] : sum[qs[i][1]] ^ sum[qs[i][0] - 1];
        }
        return r;
    }
}

class XorQueriesCount1 {
    // another way i used during the contest
    public int[] xorQueries(int[] a, int[][] q) {
        int n = a.length;
        // sum of digit i's 1's up to  number at j
        int[][] dp = new int[n][32];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                int bitij = (a[i] >> j) & 1;
                dp[i][j] = (i == 0 ? 0 : dp[i - 1][j]) + bitij;
            }
        }
        int[] r = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            int end = q[i][1];
            int start = q[i][0];
            int ri = 0;
            if (start == end) {
                ri = a[start];
            } else {
                for (int j = 0; j < 32; j++) {
                    int ones = dp[end][j] - (start == 0 ? 0 : dp[start - 1][j]);
                    int rc = ones % 2;
                    if (rc == 1) {
                        ri += (1 << j);
                    }
                }
            }
            r[i] = ri;
        }
        return r;
    }
}