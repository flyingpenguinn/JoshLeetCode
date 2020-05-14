import java.util.*;

import static base.ArrayUtils.read;
import static base.ArrayUtils.read1d;
import static base.ArrayUtils.readUnEven;

/*
LC#1298

Given n boxes, each box is given in the format [status, candies, keys, containedBoxes] where:

status[i]: an integer which is 1 if box[i] is open and 0 if box[i] is closed.
candies[i]: an integer representing the number of candies in box[i].
keys[i]: an array contains the indices of the boxes you can open with the key in box[i].
containedBoxes[i]: an array contains the indices of the boxes found in box[i].
You will start with some boxes given in initialBoxes array. You can take all the candies in any open box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.

Return the maximum number of candies you can get following the rules above.



Example 1:

Input: status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
Output: 16
Explanation: You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2. Box 1 is closed and you don't have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
Total number of candies collected = 7 + 4 + 5 = 16 candy.
Example 2:

Input: status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
Output: 6
Explanation: You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys. The total number of candies will be 6.
Example 3:

Input: status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes = [[],[],[]], initialBoxes = [1]
Output: 1
Example 4:

Input: status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []
Output: 0
Example 5:

Input: status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]], initialBoxes = [2,1,0]
Output: 7


Constraints:

1 <= status.length <= 1000
status.length == candies.length == keys.length == containedBoxes.length == n
status[i] is 0 or 1.
1 <= candies[i] <= 1000
0 <= keys[i].length <= status.length
0 <= keys[i][j] < status.length
All values in keys[i] are unique.
0 <= containedBoxes[i].length <= status.length
0 <= containedBoxes[i][j] < status.length
All values in containedBoxes[i] are unique.
Each box is contained in one box at most.
0 <= initialBoxes.length <= status.length
0 <= initialBoxes[i] < status.length
 */
public class MaxCandiesCanGet {
    // bfs from initial boxes. it smacks of the "relaxing" we see in bellman ford
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Deque<Integer> q = new ArrayDeque<>();
        int r = 0;
        Set<Integer> pending = new HashSet<>();
        Set<Integer> found = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (int i : initialBoxes) {
            if (status[i] == 1) {
                if (!seen.contains(i)) {
                    seen.add(i);
                    q.offer(i);
                }
            } else {
                pending.add(i);
            }
        }
        while (!q.isEmpty()) {
            int top = q.poll();
            r += candies[top];
            for (int cb : containedBoxes[top]) {
                if (status[cb] == 1) {
                    if (!seen.contains(cb)) {
                        seen.add(cb);
                        q.offer(cb);
                    }
                } else {
                    if (found.contains(cb)) {
                        found.remove(cb);
                        seen.add(cb);
                        q.offer(cb);
                    } else {
                        pending.add(cb);
                    }
                }
            }
            for (int k : keys[top]) {
                if (pending.contains(k)) {
                    pending.remove(k);
                    seen.add(k);
                    q.offer(k);
                } else {
                    found.add(k);
                }
            }
        }
        return r;
    }


    public static void main(String[] args) {
        System.out.println(new MaxCandiesCanGet().maxCandies(read1d("[1,0,0,0]"),
                read1d("[1,2,3,4]"),
                readUnEven("[[1,2],[3],[],[]]"),
                readUnEven("[[2],[3],[1],[]]"),
                read1d("[0]")));
    }

}

class MaxCandiesDfs {
    Set<Integer> pending = new HashSet<>();
    Set<Integer> avail = new HashSet<>();
    Set<Integer> finished = new HashSet<>();
    int r = 0;

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        for (int i = 0; i < n; i++) {
            if (status[i] == 1) {
                avail.add(i);
            }
        }
        for (int i : initialBoxes) {
            dfs(i, candies, keys, containedBoxes);
        }
        return r;
    }

    private void dfs(int i, int[] candies, int[][] keys, int[][] containedBoxes) {
        if (finished.contains(i)) {
            return;
        }
        if (avail.contains(i)) {
            finished.add(i);
            r += candies[i];
            for (int k : keys[i]) {
                avail.add(k);
                // only visit when it's in pending list
                if (pending.contains(k)) {
                    dfs(k, candies, keys, containedBoxes);
                }
            }
            for (int c : containedBoxes[i]) {
                dfs(c, candies, keys, containedBoxes);
            }
        } else {
            pending.add(i);
        }
    }
}
