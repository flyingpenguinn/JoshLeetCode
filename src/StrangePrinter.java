import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
LC#664
There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.
 */
public class StrangePrinter {
    // equivalent to remove box asking for min actions instead of score
    // given a mergable block,do it now or wait. we dont need to know how big the residual block is so dont need k
    int[][] dp;

    public int strangePrinter(String s) {
        int n = s.length();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        char[] sc = s.toCharArray();
        return doc(sc, 0, sc.length - 1);
    }

    int doc(char[] sc, int l, int u) {
        if (l > u) {
            return 0;
        }
        if (dp[l][u] != -1) {
            return dp[l][u];
        }
        int p = u - 1;
        while (p >= l && sc[p] == sc[u]) {
            p--;
        }
        // p is the first != u. we can print l..p and p+1...u
        int min = doc(sc, l, p) + 1;
        int q = p;
        while (q >= l) {
            // q== end, so end and l...q can be printed in one shot
            if (sc[q] == sc[u] && sc[q + 1] != sc[u]) {
                int cur = doc(sc, l, q) + doc(sc, q + 1, p);
                min = Math.min(min, cur);
            }
            q--;
        }
        dp[l][u] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new StrangePrinter().strangePrinter("aba"));
    }
}
