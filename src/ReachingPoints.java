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
        // take it reversely
        return doable(tx, ty, sx, sy);
    }

    private boolean doable(int sx, int sy, int tx, int ty) {
        if (sx < tx || sy < ty) {
            return false; // we will only decrease sx or sy
        }
        if (sx == tx && sy == ty) {
            return true;
        }
        if (sx == tx) {
            // 9,10 vs 9, 19. 10 and 19 mod 9 = 1
            return (sy - ty) % sx == 0;
        }
        if (sy == ty) {
            return (sx - tx) % sy == 0;
        }
        if (sy > sx) {
            return doable(sx, sy % sx, tx, ty);
        } else if (sy < sx) {
            return doable(sx % sy, sy, tx, ty);
        } else {
            // sx == sy, we can't make 0...
            return false;
        }
    }
}
