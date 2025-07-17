public class ProcessStringWithSpecialOperationsII {
    private char solve(char[] cs, int i, long k) {
        //   System.out.println("i="+i+" k="+k);
        int n = cs.length;
        long len = clen[i];
        //  System.out.println("len="+len);
        if (cs[i] >= 'a' && cs[i] <= 'z') {
            if (k == len - 1) {
                return cs[i];
            } else {
                return solve(cs, i - 1, k);
            }
        }
        if (cs[i] == '*') {
            return solve(cs, i - 1, k);
        }
        if (cs[i] == '#') {
            long halflen = len / 2;
            if (k < halflen) {
                return solve(cs, i - 1, k);
            } else {

                return solve(cs, i - 1, k - len / 2);
            }
        }
        if (cs[i] == '%') {
            return solve(cs, i - 1, len - 1 - k);
        }
        return '.';
    }

    private long[] clen;

    public char processStr(String s, long k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        clen = new long[n];
        long len = 0;
        for (int i = 0; i < n; ++i) {
            char c = cs[i];
            if (c >= 'a' && c <= 'z') {
                ++len;
            } else if (c == '*') {
                if (len > 0) {
                    --len;
                }
            } else if (c == '#') {
                len *= 2;
            } else if (c == '%') {
                //
            }
            //    System.out.println("i="+i+" len="+len);
            clen[i] = len;

        }
        //  System.out.println("len="+len);
        if (k >= len) {
            return '.';
        } else {
            return solve(cs, n - 1, k);
        }

    }
}
