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

    public int racecar(int t) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1, 0});
        Set<Long> seen = new HashSet<>();
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int pos = top[0];
            if (pos < 0 || pos > 2 * t) {
                // no point to go back: we'd rather overshoot then return....
                continue;
            }
            int speed = top[1];
            int steps = top[2];
            if (pos == t) {
                return steps;
            }
            int npos = pos + speed;
            int nspeed = speed * 2;
            int nsteps = steps + 1;
            populate(npos, nspeed, nsteps, seen, q);
            npos = pos;
            nspeed = speed > 0 ? -1 : 1;
            populate(npos, nspeed, nsteps, seen, q);
        }
        return -1;
    }

    void populate(int npos, int nspeed, int nsteps, Set<Long> seen, Deque<int[]> q) {
        Long change = tocode(npos, nspeed);
        if (seen.add(change)) {
            q.offer(new int[]{npos, nspeed, nsteps});
        }
    }

    long tocode(int pos, int speed) {
        return pos * 10000000 + speed;
    }

    public static void main(String[] args) {
        System.out.println(new RaceCar().racecar(5));
        System.out.println(new RaceCarDp().racecar(5));

    }
}

class RaceCarDp {
    // similar to "least operator to express number". we either undershoot or overshoot
    // if overshoot, just come back
    // we can undershoot, then go back 0,1,2 steps, then reverse
    // NOTE WE ONLY UNDERSHOOT once at the nearest under place. we dont check further. actually lack mathematical proof that this works
    int[] dp;

    public int racecar(int t) {
        dp = new int[2 * t];
        Arrays.fill(dp, -1);
        return domin(t);
    }

    // speed is 1. pos == 0
    private int domin(int t) {
        if (t == 0) {
            return 0;
        }
        if (dp[t] != -1) {
            return dp[t];
        }
        int speed = 1;
        int pos = 0;
        int steps = 0;
        while (pos < t) {
            pos += speed;
            speed *= 2;
            steps++;
        }
        if (pos == t) {
            return steps;
        }
        int min = steps + 1 + domin(pos - t);
        pos -= speed / 2;// back to the earlier pos
        speed = 1;
        // no steps-- because we reverse here so it's like steps-- (to get to the earlier step) then steps++ (to reverse);

        // now we enumerate the spot where we turn back from a reverse journey
        while (pos > 0) {
            // facing left when we are here. but we decide to go right, so +1 every time for the reverse
            int cur = steps + 1 + domin(t - pos); // +1 for the reverse
            min = Math.min(min, cur);
            steps++;
            pos -= speed;
            speed *= 2;
        }
        dp[t] = min;
        return min;
    }
}
