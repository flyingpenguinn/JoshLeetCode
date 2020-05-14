import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

/*
LC#490
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class TheMaze {
    // treat stuck positions as nodes in the graph
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean hasPath(int[][] m, int[] s, int[] dest) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(s);
        m[s[0]][s[1]] = 2;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            if (Arrays.equals(top, dest)) {
                return true;
            }
            for (int[] d : dirs) {
                int i = top[0];
                int j = top[1];
                // must be !=1, not !=0
                while (i + d[0] >= 0 && i + d[0] < m.length && j + d[1] >= 0 && j + d[1] < m[0].length && m[i + d[0]][j + d[1]] != 1) {
                    i += d[0];
                    j += d[1];
                }
                if (m[i][j] == 0) {
                    m[i][j] = 2;
                    q.offer(new int[]{i, j});
                }
            }
        }
        return false;
    }
}
