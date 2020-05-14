import java.util.Arrays;

/*
LC#452
There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */
public class MinArrowToBurstBallon {
    // if current public space intersects with interval use it. otherwise start a new one
    // can also sort by end point an then we dont need to maintain the min end
    public int findMinArrowShots(int[][] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int r = 1;
        // dont need to involve cs at all... we only care about s vs ce
        int ce = a[0][1];
        for (int i = 1; i < n; i++) {
            int s = a[i][0];
            int e = a[i][1];
            if (s > ce) {
                r++;
                ce = e;
            } else {
                ce = Math.min(e, ce);
            }
        }
        return r;
    }
}
