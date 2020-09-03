/*
LC#161
Given two strings s and t, determine if they are both one edit distance apart.

Note:

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
 */
public class OneEditDistance {

    // because it's just one distance we know what to do when they differ: when their len diff is 1
    public boolean isOneEditDistance(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int i = 0;
        int j = 0;
        boolean diff = false;
        if (sn == tn + 1) {
            return isOneEditDistance(t, s);
        } else if (sn + 1 == tn) {
            while (i < sn && j < tn) {
                if (s.charAt(i) != t.charAt(j)) {
                    if (diff) {
                        return false;
                    } else {
                        diff = true;
                        j++;
                    }
                } else {
                    i++;
                    j++;
                }
            }
            return true;
        } else if (sn == tn) {
            while (i < sn && j < tn) {
                if (s.charAt(i) != t.charAt(j)) {
                    if (diff) {
                        return false;
                    } else {
                        diff = true;
                    }
                }
                i++;
                j++;
            }
            return diff; // note it must be exactly one distance away! so must return if it has diff here
        } else {
            return false;
        }
    }
}
