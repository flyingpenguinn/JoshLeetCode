public class FindNearestPointThatHasSameXY {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int min = Integer.MAX_VALUE;
        int res = -1;
        for (int i = 0; i < points.length; i++) {
            int cx = points[i][0];
            int cy = points[i][1];
            if (cx != x && cy != y) {
                continue;
            }
            int dist = Math.abs(cy - y) + Math.abs(cx - x);
            if (dist < min) {
                min = dist;
                res = i;
            }
        }
        return res;
    }
}
