import java.util.*;

/*
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.
 */
public class IsSubsequence {
    // O(max(s,t) -> when t is super long, it's O(t*count of s)
    public boolean isSubsequence(String s, String t) {
        int si = 0;
        int ti = 0;
        while (si < s.length() && ti < t.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
            }
            ti++;
        }
        return si == s.length();
    }
}

class IsSubSequenceBinarySearch {
    // O(lens*logt), better than O(t) when t is very very large,
    // or we have a lot of s strings to match with, because we only initialize t once
    public boolean isSubsequence(String s, String t) {
        List<Integer>[] tm = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            tm[i] = new ArrayList<>();
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int cind = c - 'a';
            tm[cind].add(i);
        }
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cind = c - 'a';
            List<Integer> list = tm[cind];
            int next = binaryFirstNonSmaller(list, j);
            if (next == -1) {
                return false;
            }
            j = next + 1;
        }
        return true;
    }

    int binaryFirstNonSmaller(List<Integer> list, int t) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l >= 0 && l < list.size()) {
            return list.get(l);  // we should return the exact number, not index
        } else {
            return -1;
        }
    }
}
