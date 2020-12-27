import java.util.*;
/*
LC#1345
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
Example 4:

Input: arr = [6,1,9]
Output: 2
Example 5:

Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3


Constraints:

1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8
 */

public class JumpGameIV {
    // min steps: dp or bfs. here we can go back or same value so dp is not suitable
    // BFS. remove the value from map to avoid useless revisiting
    public int minJumps(int[] a) {
        int n = a.length;
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.computeIfAbsent(a[i], k -> new HashSet<>()).add(i);
        }

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        boolean[] v = new boolean[n];
        v[0] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int dist = top[1];
            if (i == n - 1) {
                return dist;
            }
            if (i + 1 < n && !v[i + 1]) {
                v[i + 1] = true;
                q.offer(new int[]{i + 1, dist + 1});
            }
            if (i > 0 && !v[i - 1]) {
                v[i - 1] = true;
                q.offer(new int[]{i - 1, dist + 1});
            }
            for (int next : m.getOrDefault(a[i], new HashSet<>())) {
                if (!v[next]) {
                    v[next] = true;
                    m.remove(a[i]); // key: avoid revisiting big sets again and again. we only need to do it once
                    q.offer(new int[]{next, dist + 1});
                }
            }

        }
        return -1;
    }
}
