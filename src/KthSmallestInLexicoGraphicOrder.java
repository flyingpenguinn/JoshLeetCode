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
        int res = 0;
        while (k > 0) {
            int i = res == 0 ? 1 : 0;
            while (i <= 8) {
                int gap = gap(res, i, n);
                if (gap >= k) {
                    // found, i is the number
                    break;
                } else {
                    k -= gap;
                }
                i++;
            }
            res = res * 10 + i;
            k--;
            //we are putting down i here.  count in res*10+i itself. note in next steps we started counting res*10+xx so this res should be counted
        }
        return res;
    }

    // n==123   i = 4
    // nums between 1234xxx and 1235xxx
    private int gap(long curn, int i, long n) {
        long cn = curn * 10 + i;
        long nn = curn * 10 + (i + 1);// i<=8
        int res = 0;
        while (cn <= n) {
            if (nn <= n) {
                res += nn - cn;
            } else {
                res += n - cn + 1;
                break;
            }
            cn *= 10;
            nn *= 10;
        }
        return res;
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
