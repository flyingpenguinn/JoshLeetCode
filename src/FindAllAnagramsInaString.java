import java.util.ArrayList;
import java.util.List;
/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInaString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> r = new ArrayList<>();
        int pn = p.length();
        int sn = s.length();
        if (sn < pn) {
            return r;
        }
        int[] pm = new int[26];
        for (int i = 0; i < pn; i++) {
            pm[p.charAt(i) - 'a']++;
        }
        int[] sm = new int[26];
        for (int i = 0; i < pn - 1; i++) {
            sm[s.charAt(i) - 'a']++;
        }
        for (int i = pn - 1; i < sn; i++) {
            sm[s.charAt(i) - 'a']++;
            if (eq(sm, pm)) {
                r.add(i - pn + 1);
            }
            sm[s.charAt(i - pn + 1) - 'a']--;
        }
        return r;
    }

    boolean eq(int[] s1, int[] s2) {
        for (int i = 0; i < 26; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }
}
