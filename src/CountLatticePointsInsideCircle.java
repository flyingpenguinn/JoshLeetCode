public class CountLatticePointsInsideCircle {
    public int countLatticePoints(int[][] circles) {
        int sx = (int) 1e9;
        int sy = (int) 1e9;
        int bx = (int) (-1e9);
        int by = (int) (-1e9);
        for (int[] c : circles) {
            int cx = c[0];
            int cy = c[1];
            int rc = c[2];
            sx = Math.min(sx, cx - rc);
            bx = Math.max(bx, cx + rc);
            sy = Math.min(sy, cy - rc);
            by = Math.max(by, cy + rc);
        }
        // System.out.println(sx+" "+bx+" "+sy+" "+by);
        int res = 0;
        for (int i = sx; i <= bx; ++i) {
            for (int j = sy; j <= by; ++j) {
                for (int[] c : circles) {
                    int cx = c[0];
                    int cy = c[1];
                    int rc = c[2];
                    int drc = rc * rc;
                    int dist = (i - cx) * (i - cx) + (j - cy) * (j - cy);
                    if (dist <= drc) {
                        ++res;
                        break;
                    }
                }
            }
        }
        return res;
    }
}
