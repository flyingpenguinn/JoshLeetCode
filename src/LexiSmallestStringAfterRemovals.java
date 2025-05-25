public class LexiSmallestStringAfterRemovals {
    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        // 1) full[i][j] = can s[i..j] be completely removed?
        boolean[][] full = new boolean[n][n];
        // empty substrings full by definition: we’ll treat i>j as true in checks

        // build full[i][j] for lengths = 1..n
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // try matching i with some k in (i..j]
                for (int k = i + 1; k <= j; k++) {
                    if (isConsecutive(s.charAt(i), s.charAt(k))) {
                        boolean inner = (i + 1 > k - 1) || full[i + 1][k - 1];
                        boolean right = (k + 1 > j) || (k + 1 <= j && full[k + 1][j]);
                        if (inner && right) {
                            full[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        // 2) dp[i][j] = lex smallest string from s[i..j]
        String[][] dp = new String[n][n];
        // fill bottom-up on i descending, j ascending
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // option A: delete everything if possible
                String best = full[i][j] ? "" : null;

                // option B: keep s[i]
                String keep = s.charAt(i)
                        + ((i + 1 <= j) ? dp[i + 1][j] : "");
                if (best == null || keep.compareTo(best) < 0) {
                    best = keep;
                }

                // option C: match i with k, peel off inner, continue at k+1
                for (int k = i + 1; k <= j; k++) {
                    if (isConsecutive(s.charAt(i), s.charAt(k))) {
                        boolean inner = (i + 1 > k - 1) || full[i + 1][k - 1];
                        if (inner) {
                            String cand = (k + 1 <= j) ? dp[k + 1][j] : "";
                            if (cand.compareTo(best) < 0) {
                                best = cand;
                            }
                        }
                    }
                }

                dp[i][j] = best;
            }
        }

        return dp[0][n - 1];
    }

    private boolean isConsecutive(char a, char b) {
        int d = Math.abs(a - b);
        // normal consecutive or wrap-around a/z
        return d == 1 || d == 25;
    }
}
