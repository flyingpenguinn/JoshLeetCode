public class CircleAndRectOverlap {
    public boolean checkOverlap(int radius, int xc, int yc, int x1, int y1, int x2, int y2) {
        if (xc >= x1 && xc <= x2 && yc >= y1 && yc <= y2) {
            // in circle
            return true;
        }
        // one end of the rectangle in circle
        if (sqdist(x1, y1, xc, yc) <= radius * radius) {
            return true;
        }
        if (sqdist(x1, y2, xc, yc) <= radius * radius) {
            return true;
        }
        if (sqdist(x2, y1, xc, yc) <= radius * radius) {
            return true;
        }
        if (sqdist(x2, y2, xc, yc) <= radius * radius) {
            return true;
        }
        // one edge is a cut of the circle
        if (y1 <= yc && y2 >= yc && Math.abs(xc - x1) <= radius) {
            return true;
        }
        if (y1 <= yc && y2 >= yc && Math.abs(xc - x2) <= radius) {
            return true;
        }
        if (x1 <= xc && x2 >= xc && Math.abs(yc - y1) <= radius) {
            return true;
        }
        if (x1 <= xc && x2 >= xc && Math.abs(yc - y2) <= radius) {
            return true;
        }
        return false;
    }

    private int sqdist(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}
