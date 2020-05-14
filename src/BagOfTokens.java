import base.ArrayUtils;

import java.util.Arrays;

/*
LC#948
You have an initial power P, an initial score of 0 points, and a bag of tokens.

Each token can be used at most once, has a value token[i], and has potentially two ways to use it.

If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
Return the largest number of points we can have after playing any number of tokens.



Example 1:

Input: tokens = [100], P = 50
Output: 0
Example 2:

Input: tokens = [100,200], P = 150
Output: 1
Example 3:

Input: tokens = [100,200,300,400], P = 200
Output: 2


Note:

tokens.length <= 1000
0 <= tokens[i] < 10000
0 <= P < 10000
 */
public class BagOfTokens {
    // get max power from big tokens, spend min power for points from min tokens. be wise in spending points
    public int bagOfTokensScore(int[] a, int p) {
        Arrays.sort(a);
        int power = p;
        int point = 0;
        int maxp = 0;
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            if (power < a[i]) {
                if (point == 0) {
                    break;
                }
                power += a[j--];
                point--;

            } else {
                power -= a[i++];
                point++;
            }
            maxp = Math.max(maxp, point);
        }
        return maxp;
    }

    public static void main(String[] args) {

        System.out.println(new BagOfTokens().bagOfTokensScore(ArrayUtils.read1d("100,200,300,400"), 200));
        System.out.println(new BagOfTokens().bagOfTokensScore(ArrayUtils.read1d("17,47,41,57,51"), 31));

        System.out.println(new BagOfTokens().bagOfTokensScore(ArrayUtils.read1d("43,61,92"), 97));

        System.out.println(new BagOfTokens().bagOfTokensScore(ArrayUtils.read1d("100,200"), 150));
        System.out.println(new BagOfTokens().bagOfTokensScore(ArrayUtils.read1d("100"), 50));


    }
}
