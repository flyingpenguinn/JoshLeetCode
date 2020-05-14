/*
LC#780
A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:

sx, sy, tx, ty will all be integers in the range [1, 10^9].
 */
public class ReachingPoints {

    // think reverse from tx ty to sx sy. based on which one is bigger there is only one choice, like in a tree there is only one parent per node
    // make sure we deal with cx==sx and cy==sy well
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        return dor(sx, sy, tx, ty);
    }

    boolean dor(int sx, int sy, int cx, int cy) {
        //  System.out.println(cx+","+cy);
        if (cx == sx && cy == sy) {
            return true;
        }
        if (cx < sx || cy < sy) {
            return false;
        }
        if (cx == sx) {
            return cy % cx == sy % sx;
        }
        if (cy == sy) {
            return cx % cy == sx % sy;
        }
        if (cx > cy) {
            return dor(sx, sy, cx % cy, cy);
        } else if (cy > cx) {
            return dor(sx, sy, cx, cy % cx);
        } else {
            return false;
        }
    }
}
