public class CoordinateWithMaxNetworkQuality {
    private int Max = 1000;
    private double eps = 0.00001;

    public int[] bestCoordinate(int[][] a, int radius) {
        int minx = Max;
        int maxx = 0;
        int miny = Max;
        int maxy = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            minx = Math.min(minx, a[i][0]);
            miny = Math.min(miny, a[i][1]);
            maxx = Math.max(maxx, a[i][0]);
            maxy = Math.max(maxy, a[i][1]);
        }
        int qmax = -1;
        int[] best = null;
        for (int i = minx; i <= maxx; i++) {
            for (int j = miny; j <= maxy; j++) {
                int qsum = 0;
                for (int k = 0; k < n; k++) {
                    double dist = dist(i, j, a[k][0], a[k][1]);
                    if (dist <= radius + eps) {
                        double q = a[k][2] / (1 + dist);
                        qsum += Math.floor(q);
                    }
                }
                if (qsum > qmax) {
                    qmax = qsum;
                    best = new int[]{i, j};
                }
            }
        }
        return best;
    }

    private double dist(int i, int j, int k, int l) {
        return Math.sqrt((k - i) * (k - i) + (l - j) * (l - j));
    }
}
