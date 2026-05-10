public class MinFipsToMakeBinaryStringCoherent {
    public int minFlips(String s) {
        int n = s.length();
        if (n <= 2) {
            return 0;
        }

        int c0 = 0;
        int c1 = 0;
        for (int i = 0; i < n; ++i) {
            int cint = s.charAt(i) - '0';
            if (cint == 0) {
                ++c0;
            } else {
                ++c1;
            }
        }
        if (c1 < 2 || c0 < 1) {
            return 0;
        }


        int way1 = c1 - 1;
        int way2 = c0;
        if (s.charAt(0) == '1' && s.charAt(n - 1) == '1') {
            return Math.min(way2, c1 - 2);
        }
        return Math.min(way1, way2);
    }
}
