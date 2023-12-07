public class NumberOfSameEndSubstrings {
    public int[] sameEndSubstringCount(String s, int[][] qs) {
        int n = s.length();
        int[][] m = new int[n][26];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            for (int j = 0; j < 26; ++j) {
                if (j == cind) {
                    m[i][j] = (i == 0 ? 0 : m[i - 1][j]) + 1;
                } else {
                    m[i][j] = (i == 0 ? 0 : m[i - 1][j]);
                }
            }
        }
        int[] res = new int[qs.length];
        int ri = 0;
        for (int[] q : qs) {
            int v1 = q[0];
            int v2 = q[1];
            for (int i = 0; i < 26; ++i) {
                int ci = m[v2][i] - (v1 == 0 ? 0 : m[v1 - 1][i]);
                int count = (1 + ci) * ci / 2;
                res[ri] += count;
            }
            ++ri;
        }
        return res;
    }
}
