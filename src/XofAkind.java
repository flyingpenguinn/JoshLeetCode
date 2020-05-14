import base.ArrayUtils;

import java.util.Arrays;
/*
LC#914
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.


Example 1:

Input: [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
Example 2:

Input: [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.
Example 3:

Input: [1]
Output: false
Explanation: No possible partition.
Example 4:

Input: [1,1]
Output: true
Explanation: Possible partition [1,1]
Example 5:

Input: [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2]

Note:

1 <= deck.length <= 10000
0 <= deck[i] < 10000
 */

public class XofAkind {
    // gcd of all occurences!
    public boolean hasGroupsSizeX(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int curs = 1;
        int gcd = n;
        for (int i = 1; i <= n; i++) {
            if (i < n && a[i] == a[i - 1]) {
                curs++;
            } else {
                gcd = gcd(curs, gcd);
                curs = 1;
            }
        }
        return gcd >= 2 && n % gcd == 0;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1,2,3,4,4,3,2,1]")));
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1,1,1,2,2,2,3,3]")));
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1]")));
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1,1]")));
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1,1,2,2,2,2]")));
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1,1,2,2,2,2,2]")));
        System.out.println(new XofAkind().hasGroupsSizeX(ArrayUtils.read1d("[1,1,1,1,2,2,2,2,2,2]")));
    }

}
