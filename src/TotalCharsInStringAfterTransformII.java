import java.util.List;

public class TotalCharsInStringAfterTransformII {
    // turn the problem to a matrix multiplicaton problem!
    private final long Mod = (long) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        for (int i = 0; i < 26; i++) {
            iden[i][i] = 1;
        }
        long[][] trans = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int ns = nums.get(i);
            for (int j = i + 1; j <= i + ns; ++j) {
                int mj = j % 26;
                ++trans[mj][i];
            }
        }
        int n = s.length();
        long[] c = new long[26];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++c[cind];
        }
        long[][] pm = powm(trans, t);
        long[] cres = new long[26];
        for (int i = 0; i < 26; i++) {
            long sum = 0;
            for (int j = 0; j < 26; j++) {
                sum = (sum + pm[i][j] * c[j]) % Mod;
            }
            cres[i] = sum;
        }
        long res = 0;
        for (long ci : cres) {
            res = (res + ci) % Mod;
        }
        return (int) res;
    }

    private long[][] iden = new long[26][26];

    private long[][] powm(long[][] trans, int t) {
        if(t==0){
            return iden;
        }
        long[][] half = powm(trans, t/2);
        long[][] res = multi(half, half);
        if(t%2==1){
            res = multi(res, trans);
        }
        return res;
    }

    private long[][] multi(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    long cur = a[i][k] * b[k][j] % Mod;
                    res[i][j] = (res[i][j] + cur) % Mod;
                }
            }
        }
        return res;
    }
}
