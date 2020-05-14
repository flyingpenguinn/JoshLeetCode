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
    List<String> r = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, "", 0);
        return r;
    }

    void dfs(String s, int i, String cur, int parts) {
        int n = s.length();
        if (i == n) {
            if (parts == 4) {
                r.add(cur);
            }
            return;
        }
        // if more than 4 parts for sure
        if (parts >= 4) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < n && j - i < 3; j++) {
            char jc = s.charAt(j);
            sb.append(jc);
            int num = Integer.valueOf(sb.toString());
            if (sb.length() > 1 && s.charAt(i) == '0') {
                break;
            } else if (num <= 255) {
                String ns = cur.isEmpty() ? sb.toString() : cur + "." + sb.toString();
                dfs(s, j + 1, ns, parts + 1);
            }

        }
    }
}
