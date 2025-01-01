public class FindLexicographicallyLargestStringFromBoxII {
    // Duval algo similar to "LastSubstringLexicographicalOrder"
    public String answerString(String s, int t) {
        if (t == 1) {
            return s;
        }
        int n = s.length();
        int len = n - t + 1;
        int l = 0;
        int r = 1;
        int i = 0;
        char[] c = s.toCharArray();
        while (l + i < n && r + i < n) {
            if (c[l + i] == c[r + i]) {
                ++i;
            } else if (c[l + i] > c[r + i]) {
                r = r + i + 1;
                i = 0;
            } else {
                l = Math.max(l + i + 1, r);
                r = l + 1;
                i = 0;
            }
        }
        return s.substring(l, Math.min(l + len, n));
    }
}
