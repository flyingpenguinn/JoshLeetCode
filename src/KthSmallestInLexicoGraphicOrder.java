/*
LC#440
Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 */
public class KthSmallestInLexicoGraphicOrder {
    // mimic the way we would do this on a paper: start with 1? how many are there? start with 2?....
    // similar to #386
    public int findKthNumber(int n, int k) {
        return (int) dof(0, n, k);
    }

    private long dof(long cur, long n, long k) {
        // deal with empty concatenation, i.e. cur itself first
        if (cur > 0) {
            if (k == 1) {
                return cur;
            } else {
                k--;
            }
        }
        int start = cur == 0 ? 1 : 0;
        for (int i = start; i <= 9; i++) {
            long next = cur * 10 + i;
            long gap = gap(next, n);
            // gap>=1
            if (gap < k) {
                k -= gap;
            } else {
                // if ==, wait for eating up k in dealing with "next"
                return dof(next, n, k);
            }
        }
        return -1;
    }

    // gap between base and base+1 (exclusive) under limit n. gap must be >=1
    private long gap(long base, long n) {
        long r = 0L;
        long times = 1L;
        // base *times is like 10, gap between 10 and 20 is 10. gap between 10 and 15 is 16
        while (base * times <= n) {
            r += (base + 1L) * times <= n ? times : n - base * times + 1; // must be <=! base+1 is not included
            times *= 10;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new KthSmallestInLexicoGraphicOrder().findKthNumber(12, 1));
        System.out.println(new KthSmallestInLexicoGraphicOrder().findKthNumber(12, 2));
        System.out.println(new KthSmallestInLexicoGraphicOrder().findKthNumber(12, 3));
        System.out.println(new KthSmallestInLexicoGraphicOrder().findKthNumber(12, 4));
        System.out.println(new KthSmallestInLexicoGraphicOrder().findKthNumber(12, 5));
        System.out.println(new KthSmallestInLexicoGraphicOrder().findKthNumber(500, 289));
    }
}
