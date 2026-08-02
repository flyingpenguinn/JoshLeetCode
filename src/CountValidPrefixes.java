public class CountValidPrefixes {
    public int countValidPrefixes(String s) {
        int n = s.length();
        int c0 = 0;
        int c1 = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            if (cind == 0) {
                ++c0;
            } else {
                ++c1;
            }
            if (Math.abs(c0 - c1) <= 1) {
                ++res;
            }
        }
        return res;
    }
}
