public class CircleAndRectOverlap {
    public boolean checkOverlap(int radius, int xc, int yc, int x1, int y1, int x2, int y2) {
        int x = Math.max(x1, Math.min(xc, x2));
        int y = Math.max(y1, Math.min(yc, y2));

        long dx = xc - x;
        long dy = yc - y;
        return dx * dx + dy * dy <= (long) radius * radius;
    }

}
