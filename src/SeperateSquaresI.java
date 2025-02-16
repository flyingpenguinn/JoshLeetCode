public class SeperateSquaresI {
    private double EPS = 1e-6;

    public double separateSquares(int[][] a) {
        int n = a.length;
        double l = 0;
        double u = 1e12 + 10;
        double all = 0;
        for (int i = 0; i < n; ++i) {
            double len = a[i][2];
            all += len * len;
        }
        while (u - l > EPS) {
            double mid = l + (u - l) / 2.0;
            double above = countabove(a, mid);
            double under = all - above;
            //  System.out.println("mid="+mid+" above="+above+" under="+under);
            if (Math.abs(under - above) < EPS) {
                u = mid;
            } else if (under > above) {
                u = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }

    private double countabove(int[][] a, double m) {
        int n = a.length;
        double res = 0;
        for (int i = 0; i < n; ++i) {
            double y1 = a[i][1];
            double len = a[i][2];
            double y2 = y1 + len;
            double x = a[i][0];
            if (y1 >= m) {
                res += len * len;
            } else if (y2 <= m) {
                continue;
            } else {
                double cur = (y2 - m) * len;
                res += cur;
            }
        }
        return res;
    }
}
