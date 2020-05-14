/*
LC#223
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area

Example:

Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45
Note:

Assume that the total area is never beyond the maximum possible value of int.
 */
public class RectangleArea {
    public int computeArea(int a, int b, int c, int d, int e, int f, int g, int h) {
        long x1 = c - a;
        long y1 = d - b;
        long x2 = g - e;
        long y2 = h - f;
        long llx = Math.max(a, e);
        long lly = Math.max(b, f);
        long urx = Math.min(c, g);
        long ury = Math.min(d, h);
        long x3 = urx - llx;
        long y3 = ury - lly;
        long minus = 0;
        if (x3 > 0 && y3 > 0) {
            minus = x3 * y3;
        }
        return (int) (x1 * y1 + x2 * y2 - minus);
    }

    public static void main(String[] args) {
        System.out.println(new RectangleArea().computeArea(-5, -2, 5, 1, -3, -3, 3, 3));
        System.out.println(new RectangleArea().computeArea(-2, -2, 2, 2, -3, -3, 3, -1));
        System.out.println(new RectangleArea().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));

    }
}

class RectangleAreaMine {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int bigarea = area(A, B, C, D) + area(E, F, G, H);
        int o1 = overlap(A, B, C, D, E, F, G, H);
        if (o1 != 0) {
            return bigarea - o1;
        }
        int o2 = overlap(E, F, G, H, A, B, C, D);
        return bigarea - o2;
    }

    private int overlap(int A, int B, int C, int D, int E, int F, int G, int H) {
        // 4 points, totally in
        if (inside(G, H, A, B, C, D) && inside(E, F, A, B, C, D)) {
            return area(E, F, G, H);
        }
        // 2 points
        else if (inside(G, H, A, B, C, D) && inside(E, H, A, B, C, D)) {
            // upper
            return area(E, H, G, B);
        } else if (inside(E, F, A, B, C, D) && inside(G, F, A, B, C, D)) {
            // lower
            return area(E, F, G, D);
        } else if (inside(E, F, A, B, C, D) && inside(E, H, A, B, C, D)) {
            // left
            return area(E, F, C, H);
        } else if (inside(G, H, A, B, C, D) && inside(G, F, A, B, C, D)) {
            // right
            return area(A, F, G, H);
        }
        // single points
        else if (inside(G, H, A, B, C, D)) {
            return area(A, B, G, H);
        } else if (inside(E, F, A, B, C, D)) {
            return area(E, F, C, D);
        } else if (inside(E, H, A, B, C, D)) {
            return area(E, H, C, B);
        } else if (inside(G, F, A, B, C, D)) {
            return area(A, D, G, F);
        }
        // cross like overlap
        else if (E >= A && E <= C && G >= A && G <= C && F <= B && H >= D) {
            return area(E, B, G, D);
        }
        return 0;
    }

    private int area(int e, int f, int g, int h) {
        return Math.abs(g - e) * Math.abs(h - f);
    }

    private boolean inside(int g, int h, int a, int b, int c, int d) {
        return g >= a && g <= c && h >= b && h <= d;
    }

}
