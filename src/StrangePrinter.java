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
        return dos(s.toCharArray(), 0, s.length() - 1);
    }

    int dos(char[] s, int l, int u) {
        if (l > u) {
            return 0;
        }
        if (dp[l][u] != 0) {
            return dp[l][u];
        }
        int i = u;
        while (i >= l && s[i] == s[u]) {
            i--;
        }
        // i-1 is the first place that is != u
        int p = i;
        // i+1.... u all the same, use one print
        int min = dos(s, l, p) + 1;
        for (; i >= l; i--) {
            if (s[i] == s[u] && s[i + 1] != s[u]) {
                // i+1...p are all != u
                // p+1...u can merge with i as one print only
                int cur = dos(s, l, i) + dos(s, i + 1, p);
                min = Math.min(min, cur);
            }
        }
        dp[l][u] = min;
        return dp[l][u];
    }

    public static void main(String[] args) {
        System.out.println(new StrangePrinter().strangePrinter("aba"));
    }
}
