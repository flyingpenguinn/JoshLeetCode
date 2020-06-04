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
        return canreach(sx, sy, tx, ty);
    }

    boolean canreach(int sx, int sy, int tx, int ty) {
        //    System.out.println(sx+" "+sy+" "+tx+" "+ty);
        if (tx < sx || ty < sy) {
            return false;
        } else if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            // must do these checks!  otherwise we can't handle things like 18,5 vs 23,5
            return (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return (tx - sx) % ty == 0;
        } else if (ty > tx) {
            // otherwise it's safe to % because we need to get smaller than the smaller one to let it decrease
            return canreach(sx, sy, tx, ty % tx);
        } else if (tx > ty) {
            return canreach(sx, sy, tx % ty, ty);
        } else {
            // == but not == any of tx or ty, must be false
            return false;
        }
    }
}
