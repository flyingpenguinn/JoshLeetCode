import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
LC#1392
A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).

Given a string s. Return the longest happy prefix of s .

Return an empty string if no such prefix exists.



Example 1:

Input: s = "level"
Output: "l"
Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
Example 2:

Input: s = "ababab"
Output: "abab"
Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
Example 3:

Input: s = "leetcodeleet"
Output: "leet"
Example 4:

Input: s = "a"
Output: ""


Constraints:

1 <= s.length <= 10^5
s contains only lowercase English letters.
 */
public class LongestHappyPrefix {
    // rolling hash from 0 and end simultaneously
    long mod = 1000000000000007L;
    int hashbase = 31;

    public String longestPrefix(String s) {
        int n = s.length();
        long hash1 = 0l;
        long hash2 = 0l;
        long base = 1l;
        List<Integer> matches = new ArrayList<>();
        for (int j = 0; j < n - 1; j++) {
            hash1 = (hash1 * hashbase + (s.charAt(j) - 'a')) % mod;
            hash2 = (hash2 + base * (s.charAt(n - 1 - j) - 'a')) % mod;
            base = (base * hashbase) % mod;
            if (hash1 == hash2) {
                matches.add(j);
            }
        }
        for (int i = matches.size() - 1; i >= 0; i--) {
            int j = matches.get(i);
            if (s.substring(0, j + 1).equals(s.substring(n - 1 - j, n))) {
                return s.substring(0, j + 1);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new LongestHappyPrefix().longestPrefix("abaaabab"));
        System.out.println(new LongestHappyPrefix().longestPrefix("bba"));

    }
}


class LongestHappyPrefixKmp {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = 0;// length of the longest prefix: 0...next[i] -1 == the prefix. the suffix ends at i
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c == s.charAt(next[i - 1])) {
                // 0... next[i-1] + c matches suffix ending at c
                next[i] = next[i - 1] + 1;
            } else {
                // abaaabab, macthing at the last b
                // when we mismatch with pos j, we try out next[j-1] it's a shorter prefix but may give new hope
                int j = next[i - 1];
                while (j > 0 && c != s.charAt(j)) {
                    j = next[j - 1];
                }
                // if j==0 give a chance to match s[0]
                next[i] = c == s.charAt(j) ? j + 1 : 0;
            }
        }
        return s.substring(0, next[n - 1]);
    }
}
