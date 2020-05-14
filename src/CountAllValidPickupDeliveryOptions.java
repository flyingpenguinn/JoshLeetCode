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
    // note the tricky n-1 when we reduce open pairs
    long[][] dp;
    long Mod = 1000000007;

    public int countOrders(int n) {
        dp = new long[n + 1][n + 1];
        return (int) doc(n, n);
    }

    // remaining p, remaining d, remaining open pairs. remaining open pairs is incorporated in rp and rd
    private long doc(int rp, int rd) {
        if (rp == 0 && rd == 0) {
            return 1L;
        }
        if (rp == 0) {
            long rt = rd * doc(rp, rd - 1);
            dp[rp][rd] = rt % Mod;
            return dp[rp][rd];
        }

        if (rp > rd) {
            return 0;
        }
        if (dp[rp][rd] != 0) {
            return dp[rp][rd];
        }
        long dop = rp * doc(rp - 1, rd);
        // if we have 3 reaming ds and 1 remaining p, we can only pick from the 3-1 = 2 ds, not rd
        long dod = (rd - rp) * doc(rp, rd - 1);
        long rt = dop + dod;
        dp[rp][rd] = rt % Mod;
        return rt % Mod;
    }
}

class CountAllValidPickupDeliveryOptionsMth {
    // O(n) or even O(1) way
    long Mod = 1000000007;

    // from n-1 pairs to n pairs, pn have 2*n-1 possibilities, and dn will have 2n-1+2n-2+...1 possibilities = 2n*(2n-1)/2 = n*(2n-1)
    public int countOrders(int n) {
        return (int) doc(n);
    }

    private long doc(int n) {
        if (n == 1) {
            return 1;
        }
        long r = n * (2 * n - 1) * doc(n - 1);
        r %= Mod;
        return r;
    }
}