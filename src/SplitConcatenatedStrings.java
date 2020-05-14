/*
LC#555
Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
where '-' represents the looped status.
The answer string came from the fourth looped one,
where you could cut from the middle character 'a' and get "zyxcba".
Note:
The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.
 */

public class SplitConcatenatedStrings {
    // enumerate each string as the split point.
    // rest of the strings will be in their "optimal position" that can be deducted from comparing reversed and non reversed string
    public String splitLoopedString(String[] ss) {
        String r = "";
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i + 1; j < n; j++) {
                sb.append(bigger(ss[j]));
            }
            for (int j = 0; j < i; j++) {
                sb.append(bigger(ss[j]));
            }
            String stub = sb.toString();
            int sn = ss[i].length();
            for (int j = 0; j < sn; j++) {
                String cur = ss[i].substring(j, sn) + stub + ss[i].substring(0, j);
                if (cur.compareTo(r) > 0) {
                    r = cur;
                }
            }
            String rev = new StringBuilder(ss[i]).reverse().toString();
            for (int j = 0; j < sn; j++) {
                String cur = rev.substring(j, sn) + stub + rev.substring(0, j);
                if (cur.compareTo(r) > 0) {
                    r = cur;
                }
            }
        }
        return r;
    }

    private String bigger(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        if (rev.compareTo(s) > 0) {
            return rev;
        } else {
            return s;
        }

    }
}
