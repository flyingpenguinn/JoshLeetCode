public class MinMovesToReachTargetInGrid {
    // TODO
    public int minMoves(int sx, int sy, int tx, int ty) {
        if (sx == 0 && sy == 0) {
            return (tx == 0 && ty == 0) ? 0 : -1;
        }
        if (sx == tx && sy == ty) return 0;
        if (sx > tx || sy > ty)     return -1;

        long sx0 = sx, sy0 = sy;
        long x = tx,  y  = ty;
        int res = 0;

        while (x != sx0 || y != sy0) {
            if (x < sx0 || y < sy0) return -1;

            if (x > y) {
                if ((x & 1) == 0 && x/2 >= y) {
                    x /= 2;
                } else {
                    long px = x - y;
                    if (px < sx0 || px > y) return -1;
                    x = px;
                }
            } else if (y > x) {
                if ((y & 1) == 0 && y/2 >= x) {
                    y /= 2;
                } else {
                    long py = y - x;
                    if (py < sy0 || py > x) return -1;
                    y = py;
                }
            } else {
                boolean can1 = (sx0 == 0 && y >= sy0);
                boolean can2 = (sy0 == 0 && x >= sx0);
                if (!can1 && !can2) return -1;
                if (can1 && !can2) {
                    x = 0;
                } else if (!can1 && can2) {
                    y = 0;
                } else {
                    x = 0;
                    y = 0;
                }
            }

            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinMovesToReachTargetInGrid().minMoves(0,3,0,7));
        System.out.println(new MinMovesToReachTargetInGrid().minMoves(0,1,2,3));
        System.out.println(new MinMovesToReachTargetInGrid().minMoves(4, 1, 25968, 65488));
         System.out.println(new MinMovesToReachTargetInGrid().minMoves(1, 1, 2, 2));
            System.out.println(new MinMovesToReachTargetInGrid().minMoves(1, 2, 5, 4));
    }
}
