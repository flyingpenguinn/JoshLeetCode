import base.ArrayUtils;
import base.Lists;

import java.util.*;

/*
LC#675
You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
In one step you can walk in any of the four directions top, bottom, left and right also when standing in a point which is a tree you can decide whether or not to cut off the tree.

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:

Input:
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6


Example 2:

Input:
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1


Example 3:

Input:
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.


Constraints:

1 <= forest.length <= 50
1 <= forest[i].length <= 50
0 <= forest[i][j] <= 10^9
 */
public class CutoffTreesForGolf {
    // sort tree points then do bfs
    public int cutOffTree(List<List<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (v(a, i, j) > 1) {
                    trees.add(new int[]{i, j, v(a, i, j)});
                }
            }
        }
        Collections.sort(trees, (x, y) -> Integer.compare(x[2], y[2]));
        int[] start = new int[]{0, 0, 1};
        int r = 0;
        for (int i = 0; i < trees.size(); i++) {
            int[] t = trees.get(i);
            int shortest = path(a, start, t);
            if (shortest == -1) {
                return -1;
            }
            start = t;
            a.get(t[0]).set(t[1], 1);
            r += shortest;
        }
        return r;
    }


    int v(List<List<Integer>> a, int i, int j) {
        return a.get(i).get(j);
    }

    int code(int i, int j, int n) {
        return i * n + j;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    int path(List<List<Integer>> a, int[] s, int[] t) {
        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        int m = a.size();
        int n = a.get(0).size();
        q.offer(new int[]{s[0], s[1], 0});
        seen.add(code(s[0], s[1], n));
        while (!q.isEmpty()) {
            int[] top = q.poll();
            if (top[0] == t[0] && top[1] == t[1]) {
                return top[2];
            }
            for (int[] d : dirs) {
                int ni = top[0] + d[0];
                int nj = top[1] + d[1];
                int ncode = code(ni, nj, n);
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && v(a, ni, nj) > 0 && !seen.contains(ncode)) {
                    seen.add(ncode);
                    q.offer(new int[]{ni, nj, top[2] + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] array = ArrayUtils.read("[[2,3,4],[0,0,5],[7,6,5]]");
        System.out.println(new CutoffTreesForGolf().cutOffTree(Lists.intArrayToList(array)));
    }
}
