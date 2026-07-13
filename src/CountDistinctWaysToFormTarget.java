public class CountDistinctWaysToFormTarget {
    private long Mod = (long) (1e9 + 7);

    public int interleaveCharacters(String word1, String word2, String target) {
        int wn1 = word1.length();
        int wn2 = word2.length();
        int tn = target.length();
        long[][][][] dp = new long[wn1 + 2][wn2 + 2][tn + 1][4];
        dp[0][0][0][0] = 1;
        for (int k = 1; k <= tn; ++k) {
            int tind = target.charAt(k - 1) - 'a';
            long[][][] last1 = new long[wn1 + 2][wn2 + 2][4];
            long[][][] last2 = new long[wn1 + 2][wn2 + 2][4];
            for (int i = 0; i <= wn1; ++i) {
                for (int j = 0; j <= wn2; ++j) {
                    for (int mask = 0; mask < 4; ++mask) {
                        last1[i][j][mask] = (i == 0 ? 0 : last1[i - 1][j][mask]) + dp[i][j][k - 1][mask];
                        last1[i][j][mask] %= Mod;
                        last2[i][j][mask] = (j == 0 ? 0 : last2[i][j - 1][mask]) + dp[i][j][k - 1][mask];
                        last2[i][j][mask] %= Mod;
                    }
                }
            }
            for (int i = 1; i <= wn1; ++i) {
                // when we pick from i, all j including 0 are valid
                for (int j = 0; j <= wn2; ++j) {
                    for (int mask = 0; mask < 4; ++mask) {
                        int wind1 = word1.charAt(i - 1) - 'a';
                        if (wind1 == tind) {
                            dp[i][j][k][mask | 1] += last1[i - 1][j][mask];
                            dp[i][j][k][mask | 1] %= Mod;
                        }
                    }
                }

            }


            for (int j = 1; j <= wn2; ++j) {
                // when we pick from j, all i including 0 are valid
                for (int i = 0; i <= wn1; ++i) {
                    for (int mask = 0; mask < 4; ++mask) {
                        int wind2 = word2.charAt(j - 1) - 'a';
                        if (wind2 == tind) {
                            dp[i][j][k][mask | 2] += last2[i][j - 1][mask];
                            dp[i][j][k][mask | 2] %= Mod;
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int i = 1; i <= wn1; ++i) {
            for (int j = 1; j <= wn2; ++j) {
                res += dp[i][j][tn][3];
                res %= Mod;
            }
        }
        return (int) res;
    }
}
