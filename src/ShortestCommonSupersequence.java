public class ShortestCommonSupersequence {
    int[][] dp;
    int[][] sol;

    public String shortestCommonSupersequence(String s, String t) {
        dp = new int[s.length() + 1][t.length() + 1];
        sol = new int[s.length() + 1][t.length() + 1];
        dodp(s, t, 0, 0);
        return solve(0, 0, s, t);
    }

    int dodp(String s, String t, int i, int j) {
        int sn = s.length();
        int tn = t.length();
        if (i == sn) {
            dp[i][j] = tn - j + 1;
            sol[i][j] = 3;
        } else if (j == tn) {
            dp[i][j] = sn - i + 1;
            sol[i][j] = 4;
        } else {
            if (dp[i][j] != 0) {
                return dp[i][j];
            }
            if (s.charAt(i) == t.charAt(j)) {
                dp[i][j] = 1 + dodp(s, t, i + 1, j + 1);
                sol[i][j] = 2;

            } else {
                int s1 = dodp(s, t, i + 1, j);
                int s2 = dodp(s, t, i, j + 1);
                if (s1 < s2) {
                    dp[i][j] = 1 + s1;
                    sol[i][j] = 0;
                } else {
                    dp[i][j] = 1 + s2;
                    sol[i][j] = 1;
                }

            }
        }
        return dp[i][j];
    }


    String solve(int i, int j, String s, String t) {
        if (sol[i][j] == 0) {
            return s.charAt(i) + solve(i + 1, j, s, t);
        }
        if (sol[i][j] == 1) {
            return t.charAt(j) + solve(i, j + 1, s, t);
        }
        if (sol[i][j] == 2) {
            return s.charAt(i) + solve(i + 1, j + 1, s, t);
        }
        if (sol[i][j] == 3) {
            return t.substring(j, t.length());
        } else {
            return s.substring(i, s.length());
        }


    }
}
