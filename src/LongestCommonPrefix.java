/*

LC#14
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
 */

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            char com = 0;
            boolean bad = false;
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    bad = true;
                    break;
                }
                if (j == 0) {
                    com = strs[j].charAt(i);
                } else if (com != strs[j].charAt(i)) {
                    bad = true;
                    break;
                }
            }
            if (bad) {
                break;
            } else {
                sb.append(com);
            }
            i++;
        }
        return sb.toString();
    }

}
