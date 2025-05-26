import java.util.Arrays;

public class LexiSmallestStringAfterRemovals {
    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        boolean[][] gone = new boolean[n + 1][n];
        for (int i = 1; i <= n; ++i) {
            gone[i][i - 1] = true;
        }
        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;
                if (isadj(sc[i], sc[j])) {
                    gone[i][j] = gone[i + 1][j - 1];
                }
                if (gone[i][j]) {
                    continue;
                }
                for (int k = i + 1; k <= j; ++k) {
                    if (gone[i][k] && gone[k + 1][j]) {
                        gone[i][j] = true;
                        break;
                    }
                }
            }
        }
        String[] dp = new String[n + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i] = s.substring(i);
        }

        for (int i = n - 1; i >= 0; --i) {
            if (gone[i][n - 1]) {
                dp[i] = "";
            } else {
                dp[i] = sc[i] + dp[i + 1];
                for (int j = i + 1; j < n; ++j) {
                    if (isadj(sc[i], sc[j]) && gone[i + 1][j - 1]) {
                        String cur = dp[j + 1];
                        if (cur.compareTo(dp[i]) < 0) {
                            dp[i] = cur;
                        }
                    }
                }
            }
        }
        return dp[0];
    }

    private boolean isadj(char a, char b) {
        if (a > b) {
            return isadj(b, a);
        }
        if (a == 'a' && b == 'z') {
            return true;
        }
        return b - a == 1;
    }

    public static void main(String[] args) {
        System.out.println(new LexiSmallestStringAfterRemovals().lexicographicallySmallestString("yyxwvz"));
        System.out.println(new LexiSmallestStringAfterRemovals().lexicographicallySmallestString("abc"));
        System.out.println(new LexiSmallestStringAfterRemovals().lexicographicallySmallestString("bcda"));
        System.out.println(new LexiSmallestStringAfterRemovals().lexicographicallySmallestString("zdce"));
    }
}
