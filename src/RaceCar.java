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
    // have to bfs on pos and speed or use dp(pos) only to dp. choice on how many times we go A\
    // O(tlogt) we can use the exponent of 2 to indicate the speed
    public int racecar(int target) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1, 0, 0});
        boolean[][][] seen = new boolean[2*target+1][2][16];
        while(!q.isEmpty()){
            int[] top = q.poll();
            int pos = top[0];
            int dir = top[1];
            int speed = (1<<top[2]);
            int dist = top[3];
            if(pos == target){
                return dist;
            }
            int sign = (dir==0)?-1:1;
            int npos = pos+sign*speed;
            int nspexp = top[2]+1;
            if(npos<= 2*target && npos >=0 && !seen[npos][dir][nspexp]){
                seen[npos][dir][nspexp] = true;
                q.offer(new int[]{npos, dir, nspexp, dist+1 });
            }
            int ndir = dir^1;
            int nsp = 0;
            if(!seen[pos][ndir][nsp]){
                seen[pos][ndir][nsp] = true;
                q.offer(new int[]{pos, ndir, nsp, dist+1});
            }
        }
        return -1;
    }
}

