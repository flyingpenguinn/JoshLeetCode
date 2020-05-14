import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#205
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
 */
public class IsomorphicString {
    public boolean isIsomorphic(String s, String t) {
        int[] sm = new int[255];
        int[] tm = new int[255];
        int n = s.length();
        if (n != t.length()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            // still assign even if sc==tc
            if (sm[sc] != 0 && sm[sc] != tc) {
                return false;
            }
            if (tm[tc] != 0 && tm[tc] != sc) {
                return false;
            }
            sm[sc] = tc;
            tm[tc] = sc;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsomorphicString().isIsomorphic("ab", "ca"));
    }
}
