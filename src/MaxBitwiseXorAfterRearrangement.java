public class MaxBitwiseXorAfterRearrangement {
    public String maximumXor(String s, String t) {
        int n = s.length();
        int[] cnt = new int[2];
        for (int i = 0; i < n; ++i) {
            int tind = t.charAt(i) - '0';
            ++cnt[tind];
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int sind = s.charAt(i) - '0';
            int other = (sind ^ 1);
            if (cnt[other] > 0) {
                res.append(1);
                --cnt[other];
            } else {
                res.append(0);
                --cnt[sind];
            }
        }
        return res.toString();
    }
}
