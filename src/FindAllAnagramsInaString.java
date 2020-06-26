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
    public List<Integer> findAnagrams(String s, String p) {
        // check null and error if needed
        List<Integer> r = new ArrayList<>();
        if (s.length() < p.length()) {
            return r;
        }
        int[] pset = new int[26];
        int k = p.length();
        for (int i = 0; i < k; i++) {
            pset[toInt(p.charAt(i))]++;
        }
        int[] sset = new int[26];
        for (int i = 0; i < k - 1; i++) {
            sset[toInt(s.charAt(i))]++;
        }
        for (int i = k - 1; i < s.length(); i++) {
            sset[toInt(s.charAt(i))]++;
            int head = i - k + 1;
            if (Arrays.equals(sset, pset)) {
                r.add((head));
            }
            sset[toInt(s.charAt(head))]--;
        }
        return r;
    }

    int toInt(char c) {
        return c - 'a';
    }

}
