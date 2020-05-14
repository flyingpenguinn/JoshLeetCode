/*
LC#387
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharInString {
    public int firstUniqChar(String s) {
        int[] c = new int[256];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (c[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
