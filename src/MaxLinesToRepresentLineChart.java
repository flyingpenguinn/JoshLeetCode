import java.util.Arrays;

public class MaxLinesToRepresentLineChart {
    double INF = 2e9;

    private class Fraction implements Comparable<Fraction> {
        int x;
        int y;


        public Fraction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Fraction o) {
            return Integer.compare(this.y * o.x, o.y * this.x);
        }
    }

    public int minimumLines(int[][] sp) {

        int n = sp.length;
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        Arrays.sort(sp, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });

        Fraction lastk = findk(sp[0], sp[1]);
        int res = 1;
        for (int i = 2; i < n; ++i) {
            Fraction ck = findk(sp[i - 1], sp[i]);
            if (ck.compareTo(lastk) != 0) {
                ++res;
                lastk = ck;
            }
        }
        return res;
    }

    private Fraction findk(int[] p1, int[] p2) {
        int y = p2[1] - p1[1];
        int x = p2[0] - p1[0];
        return new Fraction(x, y);
    }

}
