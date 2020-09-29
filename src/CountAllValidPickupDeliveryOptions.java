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

    public int countOrders(int n) {
        dp = new Long[2 * n][n + 1];
        return (int) doc(0, n, n);
    }

    // ith pick, and remaining pickups
    private long doc(int i, int remp, long n) {
        if (i == 2 * n) {
            return 1;
        }
        long allrem = 2 * n - i;
        long remd = allrem - remp;
        if (remp < 0 || remd < 0) {
            return 0;
        }
        if (dp[i][remp] != null) {
            return dp[i][remp];
        }
        long didp = n - remp;
        long didd = n - remd;
        long dop = remp * doc(i + 1, remp - 1, n);
        // we can deliver on all didps but didd is already done so only do didp-didd
        long dod = (didp - didd) * doc(i + 1, remp, n);
        long rt = dop + dod;
        rt %= mod;
        dp[i][remp] = rt;
        return rt;
    }

    private int mod = 1000000007;
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