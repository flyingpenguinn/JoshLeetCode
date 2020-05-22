import java.util.*;

/*
LC#753
There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.

While entering a password, the last n digits entered will automatically be matched against the correct password.

For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.

Return any password of minimum length that is guaranteed to open the box at some point of entering it.



Example 1:

Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:

Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.


Note:

n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.
 */
public class CrackingTheSafe {
    // brute force dfs. it has nothing to do with euler path... as far as this code goes
    String found = null;

    public String crackSafe(int n, int k) {
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < n; i++) {
            start.append("0");
        }
        int all = (int) Math.pow(k, n);

        HashSet<String> used = new HashSet<>();
        String sstart = start.toString();
        used.add(sstart);
        dfs(sstart, all, n, k, start, used);
        return found;
    }


    private void dfs(String s, int all, int n, int k, StringBuilder cur, Set<String> used) {
        if (used.size() == all) {
            found = cur.toString();
            return;
        }
        if (found != null) {
            return;
        }
        String stub = s.substring(1, n);
        for (int j = 0; j < k; j++) {
            String next = stub + j;
            if (!used.contains(next)) {
                used.add(next);
                StringBuilder newcur = cur.append(j);
                dfs(next, all, n, k, newcur, used);
                newcur.deleteCharAt(newcur.length() - 1);
                used.remove(next);
            }
        }
    }
}
