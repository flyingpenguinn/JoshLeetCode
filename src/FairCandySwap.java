import java.util.HashSet;
import java.util.Set;

/*
LC#888
Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.



Example 1:

Input: A = [1,1], B = [2,2]
Output: [1,2]
Example 2:

Input: A = [1,2], B = [2,3]
Output: [1,2]
Example 3:

Input: A = [2], B = [1,3]
Output: [2,3]
Example 4:

Input: A = [1,2,5], B = [2,4]
Output: [5,4]


Note:

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
It is guaranteed that Alice and Bob have different total amounts of candy.
It is guaranteed there exists an answer.
 */
public class FairCandySwap {
    // check if we can swap bi out one by one
    public int[] fairCandySwap(int[] a, int[] b) {
        int suma = 0;
        Set<Integer> as = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            suma += a[i];
            as.add(a[i]);
        }
        int sumb = 0;
        for (int i = 0; i < b.length; i++) {
            sumb += b[i];
        }
        int t = (suma + sumb) / 2;
        for (int i = 0; i < b.length; i++) {
            int d = t - (sumb - b[i]);
            if (as.contains(d)) {
                return new int[]{d, b[i]};
            }
        }
        return null;
    }
}
