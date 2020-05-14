import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
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
    // min steps: dp or bfs
    // BFS. using a deque here to avoid visiting useless edges. can also remove the key after visiting all the values
    public int minJumps(int[] a) {
        Map<Integer, ArrayDeque<Integer>> map = new HashMap<>();
        int n = a.length;
        for (int i = n - 1; i >= 0; i--) {
            ArrayDeque<Integer> list = map.getOrDefault(a[i], new ArrayDeque<>());
            list.add(i);
            map.put(a[i], list);
        }
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[n];
        q.offer(new int[]{0, 0});
        v[0] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int cur = top[0];
            if (cur == n - 1) {
                return top[1];
            }
            if (cur == n - 2) {
                return top[1] + 1;
            }
            int bd = top[1] + 1;
            ArrayDeque<Integer> nq = map.getOrDefault(a[cur], new ArrayDeque<>());
            while (!nq.isEmpty()) {
                int next = nq.poll();
                if (next != cur && !v[next]) {
                    v[next] = true;
                    q.offer(new int[]{next, bd});
                }
            }
            if (cur - 1 >= 0 && !v[cur - 1]) {
                v[cur - 1] = true;
                q.offer(new int[]{cur - 1, bd});
            }
            if (cur + 1 < n && !v[cur + 1]) {
                v[cur + 1] = true;
                q.offer(new int[]{cur + 1, bd});
            }
        }
        return -1;
    }
}
