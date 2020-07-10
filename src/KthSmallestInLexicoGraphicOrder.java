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
        long r = 0;
        while (k > 0) {
            long start = r == 0 ? 1 : 0;
            long i = start;
            while (i < 9) {
                long cur = nums(r, i, n);
                if (cur < k) {
                    k -= cur;
                } else {
                    break;
                }
                i++;
            }
            r = r * 10 + i;
            k--;
            // key: r is now standing one number forward than before.
            // if r==7 and i==8 before, it would be 78 here. note it's 78 without any trailing 0
        }
        return (int) r;

    }

    // 11 vs 12: 11->12, 110-> 120, 1100->1200....
    private long nums(long cur, long i, long n) {
        long r = 0;
        long now = cur * 10 + i;
        long next = cur * 10 + (i + 1);
        while (now <= n) {
            if (next <= n) {
                r += next - now;
            } else {
                r += n - now + 1;
                break;
            }
            now *= 10;
            next *= 10;
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
