import java.util.*;

/*
LC#932
For some fixed N, an array A is beautiful if it is a permutation of the integers 1, 2, ..., N, such that:

For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] + A[j].

Given N, return any beautiful array A.  (It is guaranteed that one exists.)
..


Example 1:

Input: 4
Output: [2,1,4,3]
Example 2:

Input: 5
Output: [3,1,2,5,4]


Note:

1 <= N <= 1000
 */
public class BeautifulArray {
    // put even after odd ones. between them there won't be any a[k]
    // in the first half, 1,3,5,7 == 1,2,3,4
    // in the 2nd half,2,4,6,8== 1,2,3,4
    // so we end up recursing on a smaller scale
    public int[] beautifulArray(int n) {
        return dob(n);
    }

    int[] dob(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        if (n == 0) {
            return new int[0];
        }
        int p1 = n % 2 == 0 ? n / 2 : (n + 1) / 2;
        int p2 = n % 2 == 0 ? n / 2 : (n - 1) / 2;

        int[] r1 = dob(p1);
        int[] r2 = dob(p2);
        int[] r = new int[n];
        int ri = 0;
        for (int i = 0; i < r1.length; i++) {
            r[ri++] = 2 * r1[i] - 1;
        }
        for (int i = 0; i < r2.length; i++) {
            r[ri++] = 2 * r2[i];
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BeautifulArray().beautifulArray(4)));
    }
}
