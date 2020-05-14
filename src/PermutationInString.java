import java.util.HashMap;
import java.util.Map;

/*
LC#567
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.



Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False


Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {
    // just do a sliding window...
    public boolean checkInclusion(String s1, String s2) {
        int[] m1 = new int[26];
        int s1n = s1.length();
        for (int i = 0; i < s1n; i++) {
            m1[s1.charAt(i) - 'a']++;
        }
        int s2n = s2.length();
        int[] m2 = new int[26];
        for (int i = 0; i < s2n; i++) {
            m2[s2.charAt(i) - 'a']++;
            if (i >= s1n - 1 && eq(m1, m2)) {
                return true;
            }
            if (i - s1n + 1 >= 0) {
                m2[s2.charAt(i - s1n + 1) - 'a']--;
            }
        }
        return false;
    }

    boolean eq(int[] m1, int[] m2) {
        for (int i = 0; i < 26; i++) {
            if (m1[i] != m2[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new PermutationInString().checkInclusion("abc", "cccccbbbbaaa"));
    }
}
