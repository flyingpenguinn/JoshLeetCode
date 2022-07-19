import java.util.Arrays;

public class QueryKthSmallestTrimmedNumber {
    private class Number {
        String value;
        int index;

        public Number(String v, int idx) {
            this.value = v;
            this.index = idx;
        }

        @Override
        public String toString() {
            return value + " @ " + index;
        }
    }

    public int[] smallestTrimmedNumbers(String[] a, int[][] qs) {
        int n = a.length;
        int maxd = 0;
        int mind = 101;
        for (int[] q : qs) {
            maxd = Math.max(maxd, q[1]);
            mind = Math.min(mind, q[1]);
        }
        Number[][] sorted = new Number[maxd + 1][n];
        for (int k = mind; k <= maxd; ++k) {
            Number[] ca = new Number[n];
            for (int j = 0; j < n; ++j) {
                ca[j] = new Number(a[j], j);
            }
            final int fk = k;
            Arrays.sort(ca, (x, y) -> compare(x.value, y.value, fk));
            //    System.out.println("k= "+k+" "+Arrays.toString(ca));
            sorted[k] = ca;
        }
        int[] ans = new int[qs.length];
        for (int i = 0; i < qs.length; ++i) {
            int[] q = qs[i];
            int k = q[0];
            int trim = q[1];
            ans[i] = sorted[trim][k - 1].index;
        }
        return ans;
    }

    private int compare(String x, String y, int k) {
        int sx = Math.max(0, x.length() - k);
        int sy = Math.max(0, y.length() - k);
        while (sx < x.length() && x.charAt(sx) == '0') {
            ++sx;
        }
        while (sy < y.length() && y.charAt(sy) == '0') {
            ++sy;
        }
        if (sx < sy) {
            return 1;
        } else if (sx > sy) {
            return -1;
        }
        while (sx < x.length() && sy < y.length()) {
            if (x.charAt(sx) < y.charAt(sy)) {
                return -1;
            } else if (x.charAt(sx) > y.charAt(sy)) {
                return 1;
            } else {
                ++sx;
                ++sy;
            }
        }
        if (sx < x.length()) {
            return 1;
        } else if (sy < y.length()) {
            return -1;
        } else {
            return 0;
        }
    }
}
