import java.util.*;

/*

LC#1062
Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.



Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.


Note:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500
 */
public class LongestRepeatingSubstring {

    // On2 suffix array... could have used hashset + rolling hash for On
    public int longestRepeatingSubstring(String s) {

        int n = s.length();
        String[] ss = new String[n];

        for (int i = 0; i < n; i++) {
            ss[i] = s.substring(i);
        }

        Arrays.sort(ss);
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            if (ss[i].length() <= max || ss[i + 1].length() <= max) {
                continue;
            }
            max = Math.max(max, comlen(ss[i], ss[i + 1]));
        }
        return max;
    }

    int comlen(String s1, String s2) {
        int i = 0;
        for (; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new LrsRollingHash().longestRepeatingSubstring("banana"));
    }
}

class LrsDp {
    // on2
    // dpij means the longest common substring for starting point i and j
    // note we scan i backward!  scan i backward because we depend on i+1. j 's order doesnt really matter...
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 < n && j + 1 < n) {
                        dp[i][j] = dp[i + 1][j + 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

class LrsBinarySearch {
    // O n^2logn
    public int longestRepeatingSubstring(String s) {
        // if unfound it would be 0
        int l = 1;
        int u = s.length();
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (exist(s, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    // there exists a len = mid repeated string
    private boolean exist(String s, int mid) {
        Set<String> seen = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (sb.length() == mid) {
                if (seen.contains(sb.toString())) {
                    return true;
                }
                seen.add(sb.toString());
                sb.deleteCharAt(0);
            }
        }
        return false;
    }
}

class LrsRollingHash {
    int Mod = 1000000007;

    public int longestRepeatingSubstring(String s) {
        int n = s.length();

        int l = 1;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (exist(s, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    boolean exist(String s, int len) {
        int n = s.length();
        Map<Long, Set<Integer>> seen = new HashMap<>(); // hash to starts
        long hash = 0L;
        long base = 1L;// 26^(len-1)
        for (int i = 0; i < n; i++) {
            // i is end point
            hash = (hash * 26L + (s.charAt(i) - 'a')) % Mod;

            if (i - len + 1 >= 0) {
                int head = i - len + 1;
                Set<Integer> pres = seen.getOrDefault(hash, new HashSet<>());
                // need double check on match
                for (int pre : pres) {
                    if (same(s, pre, i, len)) {
                        return true;
                    }
                }
                pres.add(i);
                seen.put(hash, pres);
                hash = (hash - (s.charAt(head) - 'a') * base + Mod) % Mod; // for next step.note hash- x* base+mod to avoid negative values after minus

            } else {
                base *= 26L;
                base %= Mod;
            }
        }
        return false;
    }

    // end points
    boolean same(String s, int i, int j, int len) {

        while (len > 0) {
            if (s.charAt(i--) != s.charAt(j--)) {
                return false;
            }
            len--;
        }
        return true;
    }
}