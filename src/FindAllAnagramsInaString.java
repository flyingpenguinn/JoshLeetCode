import java.util.ArrayList;
import java.util.Arrays;
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

    // mind the possibility that s is smaller than p
    public List<Integer> findAnagrams(String s, String p) {
        // check null and assuming lower case only
        List<Integer> r = new ArrayList<>();
        if (s.length() < p.length()) {
            return r;
        }
        int[] pm = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pm[tocode(p.charAt(i))]++;
        }
        int[] sm = new int[26];
        for (int i = 0; i < p.length() - 1 && i < s.length(); i++) {
            sm[tocode(s.charAt(i))]++;
        }
        for (int i = p.length() - 1; i < s.length(); i++) {
            sm[tocode(s.charAt(i))]++;
            int head = i - p.length() + 1;
            if (Arrays.equals(sm, pm)) {
                r.add(head);
            }
            sm[tocode(s.charAt(head))]--;
        }
        return r;
    }

    private int tocode(char c) {
        return c - 'a';
    }
}
