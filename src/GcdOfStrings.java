/*
LC#1071
For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.



Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""


Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.
 */
public class GcdOfStrings {
    // mimic number way
    public String gcdOfStrings(String s1, String s2) {
        int s1n = s1.length();
        int s2n = s2.length();

        if (s1n < s2n) {
            return gcdOfStrings(s2, s1);
        }
        if (!s1.startsWith(s2)) {
            return "";
        }
        if (s2.isEmpty()) {
            return s1;
        }
        return gcdOfStrings(s2, s1.substring(s2n));
    }
}
