public class SeparateBlackAndWhiteBalls {
    public long minimumSteps(String s) {
        char[] c = s.toCharArray();
        int n = s.length();
        int lastone = -1;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            char cur = c[i];
            if (cur == '0') {
                if (lastone == -1) {
                    continue;
                } else {
                    res += 0L + i - lastone;
                    ++lastone;
                }
            } else {
                if (lastone == -1) {
                    lastone = i;
                }
            }
        }
        return res;
    }
}
