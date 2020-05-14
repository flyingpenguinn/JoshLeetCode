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
    boolean[][] p;
    List<List<String>> r = new ArrayList<>();

    public List<List<String>> partition(String str) {

        int n = str.length();
        p = new boolean[n][n];

        char[] s = str.toCharArray();
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    p[i][j] = true;
                } else if (len == 2) {
                    p[i][j] = s[i] == s[j];
                } else {
                    p[i][j] = s[i] == s[j] ? p[i + 1][j - 1] : false;
                }
            }
        }
        dfs(str, 0, new ArrayList<>());
        return r;
    }

    void dfs(String s, int i, List<String> cur) {
        int n = s.length();
        if (i == n) {
            r.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < n; j++) {
            if (p[i][j]) {
                cur.add(s.substring(i, j + 1));
                dfs(s, j + 1, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
