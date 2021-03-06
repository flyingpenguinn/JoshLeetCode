/*
LC#668
Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output:
Explanation:
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output:
Explanation:
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]
 */
public class KthSmallestInMultiplicationMap {
    // binary search look for numbers
    public int findKthNumber(int m, int n, int k) {
        int l = 1;
        int u = m * n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (count(mid, m, n) >= k) {
                // must be >= because the number in the table is the smallest that satisfies this condition
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // how many in the matrix <=v
    private int count(int v, int m, int n) {
        int i = n;
        int j = 1;
        int r = 0;
        while (i >= 1) {
            while (j <= m && i * j <= v) {
                j++;
            }
            // 1...j is <=
            r += j - 1;
            i--;
        }
        return r;
    }
}
