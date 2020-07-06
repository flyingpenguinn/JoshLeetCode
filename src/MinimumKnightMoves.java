import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
LC#1197
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


Constraints:

|x| + |y| <= 300
 */
public class MinimumKnightMoves {
    // everything in first quadrant, at most go to -2 because if we further there is no better solution
    private int[][] dirs = {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        int sx = 0;
        int sy = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 0});
        Set<String> seen = new HashSet<>();
        seen.add(toCode(sx, sy));
        while (!q.isEmpty()) {
            int[] top = q.poll();
            if (top[0] == x && top[1] == y) {
                return top[2];
            }
            for (int[] d : dirs) {
                int nx = top[0] + d[0];
                int ny = top[1] + d[1];
                String code = toCode(nx, ny);
                if (nx >= -2 && ny >= -2 && nx <= x + 2 && ny <= y + 2 && !seen.contains(code)) {
                    seen.add(code);
                    q.offer(new int[]{nx, ny, top[2] + 1});
                }
            }
        }
        return -1;
    }

    private String toCode(int x, int y) {
        return x + "," + y;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumKnightMoves().minKnightMoves(300, 0));
    }
}
