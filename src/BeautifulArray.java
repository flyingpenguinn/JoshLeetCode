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
        if (n == 1) {
            return new int[]{1};
        }
        int half = n / 2;
        int[] p1 = beautifulArray(half);
        int[] p2 = beautifulArray(n - half);
        int[] res = new int[n];
        int ri = 0;
        // the shroter one *2. if half might be > n/2 then it's the first part *2-1
        for (int i = 0; i < p1.length; i++) {
            res[ri++] = p1[i] * 2;
        }
        for (int i = 0; i < p2.length; i++) {
            res[ri++] = p2[i] * 2 - 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BeautifulArray().beautifulArray(4)));
    }
}
