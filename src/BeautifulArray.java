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
    // think about merging the two n/2 results to form n
    public int[] beautifulArray(int n) {
        return find(n);
    }

    // two 1,2,3 => one half 2*a[i]-1, the other 2*a[i], can merge out 1,2,3,4,5,6
    // we put the even one after odd one. between the groups, there can be no odd+even = even = a[k]*2. within the groups, we know beauty holds after arithmetic operations
    private int[] find(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        if (n == 2) {
            return new int[]{1, 2};
        }
        int p1n = (n + 1) / 2;
        int[] p1 = find(p1n);
        int p2n = n - p1n;
        int[] p2 = find(p2n);
        int[] rt = new int[n];
        for (int i = 0; i < p1n; i++) {
            rt[i] = 2 * p1[i] - 1;
        }
        for (int i = p1n; i < n; i++) {
            rt[i] = 2 * p2[i - p1n];
        }
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BeautifulArray().beautifulArray(4)));
    }
}
