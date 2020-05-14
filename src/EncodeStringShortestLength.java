/*
LC#471
Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:

k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.


Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.


Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.


Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".


Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".


Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 */
public class EncodeStringShortestLength {
    // either break the whole string to repetition, or cut it in the middle and dp on two parts
    String[][] dp;

    public String encode(String s) {
        int n = s.length();

        dp = new String[n + 1][n + 1];
        return doe(0, n - 1, s);
    }

    String doe(int i, int e, String s) {
        if (i > e) {
            return "";
        }
        if (i == e) {
            return s.charAt(i) + "";
        }
        if (dp[i][e] != null) {
            return dp[i][e];
        }
        String min = s.substring(i, e + 1);
        for (int len = 1; len <= (e - i + 1) / 2; len++) {
            if ((e - i + 1) % len != 0) {
                continue;
            }
            int j = i;
            String stub = s.substring(i, i + len);
            int k = 0;
            while (j <= e) {
                if (s.startsWith(stub, j)) {
                    k++;
                    j += len;
                } else {
                    break;
                }
            }
            if (j == e + 1) {
                String inner = doe(i, i + len - 1, s);
                String cand = k + "[" + inner + "]";
                if (cand.length() < min.length()) {
                    min = cand;
                }
            }
        }
        for (int j = i; j < e; j++) {
            String p1 = doe(i, j, s);
            String p2 = doe(j + 1, e, s);
            String cur = p1 + p2;
            if (cur.length() < min.length()) {
                min = cur;
            }
        }
        dp[i][e] = min;
        return min;
    }
}
