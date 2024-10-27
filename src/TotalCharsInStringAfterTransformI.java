public class TotalCharsInStringAfterTransformI {
    // use a counter to calc the length!
    private long Mod = (long) (1e9 + 7);

    public int lengthAfterTransformations(String s, int t) {
        long[] c = new long[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++c[cind];
        }
        for (int i = 0; i < t; ++i) {
            long oldy = c[24];
            for (int j = 24; j >= 1; --j) {
                c[j] = c[j - 1];
            }
            c[0] = 0;
            c[0] += c[25];
            c[1] += c[25];
            c[25] = oldy;
            for (int j = 0; j < 26; ++j) {
                c[j] %= Mod;
            }
            //System.out.println("after "+i +" array is "+Arrays.toString(c));
        }
        long res = 0;
        for (long ci : c) {
            res += ci;
            res %= Mod;
        }
        return (int) res;
    }
}
