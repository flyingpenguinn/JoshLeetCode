import java.util.ArrayList;
import java.util.List;

/*
LC#131
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */
public class PalindromePartition {
    private List<List<String>> r = new ArrayList<>();
    private boolean[][] p;

    public List<List<String>> partition(String s) {
        int n = s.length();
        p = new boolean[n][n];
        // n^3 but ok in this case since it's an exponential dfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ispalin(s, i, j)) {
                    p[i][j] = true;
                }
            }
        }
        dfs(s, 0, new ArrayList<>());
        return r;
    }

    private void dfs(String s, int i, List<String> cur) {
        if (i == s.length()) {
            r.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (p[i][j]) {
                cur.add(s.substring(i, j + 1));
                dfs(s, j + 1, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean ispalin(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
