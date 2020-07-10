import java.util.ArrayList;
import java.util.List;

/*
LC#93
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses {
    //no leading zeros for non zero number in recursion
    // handle string too long
    public List<String> restoreIpAddresses(String s) {
        // s is digit only
        List<String> r = new ArrayList<>();
        if (s == null) {
            return r;
        }
        dfs(s, 0, 0, "", r);
        return r;
    }

    private void dfs(String s, int i, int seg, String cur, List<String> r) {
        int n = s.length();
        if (seg == 4 && i == n) {
            r.add(cur);
            return;
        } else if (seg == 4 || i == n) {
            // early terminate on strings too long for ip address
            return;
        }
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int j = i; j < n && j - i <= 2; j++) {
            if (s.charAt(i) == '0' && j > i) {
                break;
            }
            sb.append(s.charAt(j));
            String str = sb.toString();
            num = num * 10 + (s.charAt(j) - '0');
            if (num <= 255) {
                String nstr = cur.isEmpty() ? str : cur + "." + str;
                dfs(s, j + 1, seg + 1, nstr, r);
            }
        }
    }
}
