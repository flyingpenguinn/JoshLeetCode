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
    // dp ij = min length of s from i to cover t from j. keep the index that is the start of the substring
    // we use another dimension to record the starting index. O(s*t)
    int[][][] dp;

    public String minWindow(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        dp = new int[sn][tn][2];
        for (int i = 0; i < sn; i++) {
            for (int j = 0; j < tn; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int[] min = dom(0, 0, s.toCharArray(), t.toCharArray());
        if (min[0] >= Max) {
            return "";
        } else {
            return s.substring(min[1], min[1] + min[0]);
        }
    }


    int Max = 1000000;

    int[] dom(int i, int j, char[] s, char[] t) {
        int sn = s.length;
        int tn = t.length;
        if (j == tn) {
            return new int[]{0, i};
        }
        if (i == sn) {
            return new int[]{Max, i};
        }
        if (dp[i][j][0] != -1) {
            return dp[i][j];
        }
        int nj = s[i] == t[j] ? j + 1 : j;
        int[] way1 = dom(i + 1, nj, s, t);
        int[] way2 = dom(i + 1, 0, s, t);
        if (1 + way1[0] <= way2[0]) {
            dp[i][j][0] = way1[0] + 1;
            dp[i][j][1] = i;
        } else {
            dp[i][j][0] = way2[0];
            dp[i][j][1] = way2[1];
        }
        return dp[i][j];
    }


    public static void main(String[] args) {
        System.out.println(new MinWindowSubsequenceAlternativeDp().minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl",
                "l"));
    }
}

class MinWindowSubsequenceAlternativeDp {
    // in theory s^2 but in reality almost never hit
    int[][] dp;
    int Max = 1000000000;

    public String minWindow(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        dp = new int[sn][tn];
        int min = Max;
        int mini = -1;
        for (int i = 0; i + tn - 1 < sn; i++) {
            int cur = dom(s, t, i, 0);
            if (cur < min) {
                min = cur;
                mini = i;
            }
        }
        return mini == -1 ? "" : s.substring(mini, mini + min);
    }

    int dom(String s, String t, int i, int j) {
        int tn = t.length();
        int sn = s.length();

        if (j == tn) {
            return 0;
        }
        if (i == sn) {
            return Max;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int rt = 0;
        if (s.charAt(i) == t.charAt(j)) {
            rt = 1 + dom(s, t, i + 1, j + 1);
        } else {
            rt = 1 + dom(s, t, i + 1, j);
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

