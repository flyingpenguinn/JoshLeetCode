import java.util.Arrays;
/*
LC#242
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int[] ms = tomap(s);
        int[] mt = tomap(t);
        return Arrays.equals(ms, mt);
    }

    int[] tomap(String s) {
        int[] r = new int[26];
        for (int i = 0; i < s.length(); i++) {
            r[s.charAt(i) - 'a']++;
        }
        return r;
    }
}

class ValidAnagramSort {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ts);
        return Arrays.equals(cs, ts);
    }
}
