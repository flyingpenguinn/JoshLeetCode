/*
LC#1359
Given n orders, each order consist in pickup and delivery services.

Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 1
Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
Example 2:

Input: n = 2
Output: 6
Explanation: All possible orders:
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
Example 3:

Input: n = 3
Output: 90


Constraints:

1 <= n <= 500
 */
public class CountAllValidPickupDeliveryOptions {
    private Long[][] dp;
    private long mod = 1000000007;

    public int countOrders(int n) {
        dp = new Long[2 * n][n + 1];
        long res = count(0, 0, n, 2 * n);
        res %= mod;
        return (int) res;
    }

    // ith position, could be a new order, or a pick up of old open one
    // j: all orders so far
    private long count(int i, int j, int n, int limit) {
        if (i == limit) {
            return 1;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long way1 = 0;
        long way2 = 0;
        // we order a new one
        if (j + 1 <= n) {
            way1 = (n - j) * count(i + 1, j + 1, n, limit);
        }
        // j is the overall order already there. i-j is those already picked up.
        int pickedup = i - j;
        if (j > 0) {
            way2 = (j - pickedup) * count(i + 1, j, n, limit);
        }
        long rt = way1 + way2;
        rt %= mod;
        dp[i][j] = rt;
        return rt;
    }
}

class CountAllValidPickupDeliveryOptionsOneLiner {
    // O(n)
    // when n-1 is done and we are faced with n, pickup has 2*n-1 slots to insert
    // for each of these choices, delivery has 1,2,3...2n-1 places to insert
    // [P2] P1 [P2] D1 [P2]
    // [P2] X P1 X D1 X ==> 3 choices for d2
    // P1  D1 [P2] X ==> 1 choice for d2
    // so in all, 1,2,3...2n-1 choices for n th order
    // so f(n) = n*(2*n-1)*f(n-1)
    public int countOrders(int n) {
        return (int) (n == 1 ? 1 : (1L * n * (2 * n - 1) * countOrders(n - 1)) % 1000000007);
    }
}