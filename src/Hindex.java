/*
LC#274
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
             received 3, 0, 6, 1, 5 citations respectively.
             Since the researcher has 3 papers with at least 3 citations each and the remaining
             two with no more than 3 citations each, her h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class Hindex {
    // if n is hindex, anything <=n is. if it isn't anything > it also isnt
    // hence we can binary search
    public int hIndex(int[] c) {
        int l = 0;
        int u = 0;
        int n = c.length;
        for (int i = 0; i < n; i++) {
            u = Math.max(u, c[i]);
        }
        u = Math.min(n, u);
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (good(c, m)) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        return u;
    }

    boolean good(int[] c, int m) {
        int r = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= m) {
                r++;
            }
        }
        return r >= m;
    }
}

class HindexOn {
    // h must be 0 to n,must be a number in c
    // find the biggest i, bg[i]>=i
    public int hIndex(int[] c) {
        int n = c.length;
        int[] bg = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (c[i] > n) {
                bg[n]++;
            } else {
                bg[c[i]]++;
            }
        }
        int big = 0;
        for (int i = n; i >= 0; i--) {
            big += bg[i];
            if (big >= i) {
                return i;
            }
        }
        return -1;
    }
}
