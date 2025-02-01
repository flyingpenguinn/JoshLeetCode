import java.util.*;

/*
LC#874
A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:

-2: turn left 90 degrees
-1: turn right 90 degrees
1 <= x <= 9: move forward x units
Some of the grid squares are obstacles.

The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])

If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)

Return the square of the maximum Euclidean distance that the robot will be from the origin.



Example 1:

Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: robot will go to (3, 4)
Example 2:

Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)


Note:

0 <= commands.length <= 10000
0 <= obstacles.length <= 10000
-30000 <= obstacle[i][0] <= 30000
-30000 <= obstacle[i][1] <= 30000
The answer is guaranteed to be less than 2 ^ 31.
 */
public class WalkingRobotSimulation {
    int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int[] left = {3, 0, 1, 2};
    int[] right = {1, 2, 3, 0};


    public int robotSim(int[] commands, int[][] obstacles) {
        int cx = 0;
        int cy = 0;
        int curdir = 2;
        Map<Integer, Set<Integer>> obx = new HashMap<>();
        for (int[] ob : obstacles) {
            Set<Integer> curob = obx.getOrDefault(ob[0], new HashSet<>());
            curob.add(ob[1]);
            obx.put(ob[0], curob);
        }
        int max = 0;
        for (int c : commands) {
            if (c > 0) {
                for (int j = 0; j < c; j++) {
                    int nx = dirs[curdir][0] + cx;
                    int ny = dirs[curdir][1] + cy;

                    if (obx.getOrDefault(nx, new HashSet<>()).contains(ny)) {
                        break;
                    } else {
                        cx = nx;
                        cy = ny;
                    }
                }
            } else if (c == -1) {
                curdir = right[curdir];
            } else if (c == -2) {
                curdir = left[curdir];
            }
            max = Math.max(max, cx * cx + cy * cy);
        }
        return max;
    }
}
