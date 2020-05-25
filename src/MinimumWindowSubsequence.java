import java.util.Arrays;

/*
LC#727
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.


Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
 */
public class MinimumWindowSubsequence {
    // dp ij = min length to cover 1...j in t using 1...i in s, with i AS THE END POINT
    // we use another dimension to record the starting index. O(s*t)
    int Max = 1000000;

    public String minWindow(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int[][] dp = new int[sn + 1][tn + 1];
        // when s==0 can use nothing to cover t, so Max. but s[0][0] =0 because we can use 0 to cover 0
        for (int j = 1; j <= tn; j++) {
            dp[0][j] = Max;
        }
        int min = Max;
        int mini = -1;
        for (int i = 1; i <= sn; i++) {
            for (int j = 1; j <= tn; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (j == tn && dp[i][j] < min) {
                    min = dp[i][j];
                    mini = i;
                }
            }
        }
        if (min >= Max) {
            return "";
        }
        // end at mini, len = min, so just substring it
        return s.substring(mini - min, mini);
    }

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubsequence().minWindow("cnhczmccqouqadqtmjjzl",
                "cm"));
        System.out.println(new MinimumWindowSubsequence().minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl",
                "k"));
        System.out.println(new MinimumWindowSubsequence().minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl",
                "u"));
        System.out.println(new MinimumWindowSubsequence().minWindow("abcdebdde",
                "bde"));
        System.out.println(new MinimumWindowSubsequence().minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl",
                "l"));
    }
}

class MinimumWindowSubsequenceMemoization {
    int[][] dp;

    public String minWindow(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        dp = new int[sn][tn];
        for (int i = 0; i < sn; i++) {
            Arrays.fill(dp[i], -1);
        }

        dom(0, 0, s.toCharArray(), t.toCharArray());
        int min = Max;
        int mini = -1;
        for (int i = 0; i < sn; i++) {
            if (dp[i][0] < min) {
                min = dp[i][0];
                mini = i;
            }
        }
        if (min >= Max) {
            return "";
        }
        return s.substring(mini, mini + min);
    }

    int Max = 1000000;

    int dom(int i, int j, char[] s, char[] t) {
        int sn = s.length;
        int tn = t.length;
        if (j == tn) {

            return 0;
        }
        if (i == sn) {

            return Max;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int rt = 0;
        int later = dom(i + 1, j, s, t); // do this to kick it to work for later is hence we dont need another for loop outside
        if (s[i] == t[j]) {
            rt = 1 + dom(i + 1, j + 1, s, t);
        } else {
            rt = 1 + later;
        }
        dp[i][j] = rt;
        return rt;
    }
}


class MinimumWindowSubsequenceTwoPointers {
    // worst case O(st), but a novel way of doing two pointer: instead of having low/high there is actually high only
    // we improve on top of current high
    public String minWindow(String s, String t) {
        int low = 0;
        int high = -1;
        int tj = 0;
        int tn = t.length();
        int sn = s.length();
        int min = sn + 1;
        int mini = -1;
        while (true) {
            if (tj < tn) {
                high++;
                if (high >= sn) {
                    break;
                }
                if (s.charAt(high) == t.charAt(tj)) {
                    tj++;
                }
            } else {
                // from low to high it's a match to t. now we go back from high to see what's the min string we can get
                tj--;
                int si = high;
                while (tj >= 0) {
                    if (s.charAt(si) == t.charAt(tj)) {
                        tj--;
                    }
                    si--;
                }
                // from si+1 to high it's a match to t
                int cur = high - si;
                if (cur < min) {
                    min = cur;
                    mini = si + 1;
                }
                tj = 0;
                high = si + 2;// certain about si+1...high. now start from si+2
            }
        }
        return mini == -1 ? "" : s.substring(mini, mini + min);
    }
}

class MinWindowSubsequenceBruteForce {
    // O(s^2t), can pass OJ...
    int[][] dp;

    public String minWindow(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        dp = new int[sn][tn];
        for (int i = 0; i < sn; i++) {
            Arrays.fill(dp[i], -1);
        }
        int min = Max;
        int mini = -1;
        for (int i = 0; i < sn; i++) {
            if (ss[i] == ts[0]) {
                int len = substr(i, ss, ts);
                if (len < min) {
                    min = len;
                    mini = i;
                }
            }
        }
        if (min >= Max) {
            return "";
        }
        return s.substring(mini, mini + min);
    }

    private int substr(int i, char[] ss, char[] ts) {
        int j = 0;
        int k = 0;
        while (i < ss.length && j < ts.length) {
            if (ss[i] == ts[j]) {
                j++;
            }
            i++;
            k++;
        }
        return j == ts.length ? k : Max;
    }

    int Max = 1000000;
}
