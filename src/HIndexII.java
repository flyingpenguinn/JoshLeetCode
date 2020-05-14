import base.ArrayUtils;

/*
LC#275
Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
             received 0, 1, 3, 5, 6 citations respectively.
             Since the researcher has 3 papers with at least 3 citations each and the remaining
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity?
 */
public class HIndexII {
    public int hIndex(int[] c) {
        int l = 0;
        int n = c.length;
        int u = n;
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (nosmaller(c, m) >= m) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        // us is the last no smaller>=m
        return u;
    }

    // find find first >= at l then n-l is the result
    int nosmaller(int[] c, int t) {
        int l = 0;
        int n = c.length;
        int u = n - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (c[m] >= t) {
                u = m - 1;
            } else {
                l = m + 1;
            }
        }
        return n - l;
    }

    public static void main(String[] args) {
        System.out.println(new HIndexII().hIndex(ArrayUtils.read1d("100")));
        System.out.println(new HIndexII().hIndex(ArrayUtils.read1d("0")));
    }
}
