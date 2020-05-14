import java.util.*;

/*
LC#818
Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)

Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).

When you get an instruction "A", your car does the following: position += speed, speed *= 2.

When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input:
target = 3
Output: 2
Explanation:
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.
Example 2:
Input:
target = 6
Output: 5
Explanation:
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.


Note:

1 <= target <= 10000.
 */
public class RaceCar {
    // can't directly dp on pos and speed because there is a circle in the graph
    // have to bfs on pos and speed or use dp(pos) only to dp. choice on how many times we go A
    // @todo implement that solution
    public int racecar(int t) {
        Deque<int[]> q = new ArrayDeque<>();
        int[] st = new int[]{0, 1, 0};
        q.offer(st);
        Set<Long> seen = new HashSet<>();
        seen.add(code(st));
        while (!q.isEmpty()) {
            int[] top = q.poll();
            //System.out.println(Arrays.toString(top));
            int p = top[0];
            int s = top[1];
            int step = top[2];
            if (p == t) {
                return step;
            }
            if (p > 2 * t || p < 0) {
                // we'd rather overshoot then come back
                continue;
            }
            int[] w1 = new int[]{p + s, 2 * s, step + 1};
            long cw1 = code(w1);
            if (!seen.contains(cw1)) {
                seen.add(cw1);
                q.offer(w1);
            }
            int[] w2 = new int[]{p, s > 0 ? -1 : 1, step + 1};
            long cw2 = code(w2);

            if (!seen.contains(cw2)) {
                seen.add(cw2);

                q.offer(w2);
            }
        }
        return -1;
    }

    long code(int[] t) {
        return 10000 * t[0] + t[1];
    }
}
