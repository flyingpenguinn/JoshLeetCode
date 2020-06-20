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
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();
        if (forest.get(0).get(0) == 0) {
            return -1;
        }
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }
        // height, row, col
        Collections.sort(trees, (x, y) -> Integer.compare(x[0], y[0]));
        int r = 0;
        int[] cur = new int[]{0, 0, 0};
        for (int i = 0; i < trees.size(); i++) {
            int[] t = trees.get(i);
            int dist = mindist(forest, cur, t);
            if (dist == -1) {
                return -1;
            } else {
                r += dist;
                cur = t;
            }
        }
        return r;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // walk from t1 to t2. all things below t1 are grass
    private int mindist(List<List<Integer>> forest, int[] t1, int[] t2) {

        Deque<int[]> q = new ArrayDeque<>();
        int m = forest.size();
        int n = forest.get(0).size();
        q.offer(new int[]{0, t1[1], t1[2]}); // dist, row, col
        Set<Integer> seen = new HashSet<>();
        seen.add(code(t1[1], t1[2], n));
        while (!q.isEmpty()) {
            int[] top = q.poll();
            if (top[1] == t2[1] && top[2] == t2[2]) {
                return top[0];
            }
            for (int[] d : dirs) {
                int n1 = top[1] + d[0];
                int n2 = top[2] + d[1];
                if (n1 >= 0 && n1 < m && n2 >= 0 && n2 < n && !seen.contains(code(n1, n2, n))
                        && forest.get(n1).get(n2) > 0) {
                    seen.add(code(n1, n2, n));
                    q.offer(new int[]{top[0] + 1, n1, n2});
                }
            }
        }
        return -1;

    }

    int code(int i, int j, int n) {
        return i * n + j;
    }

    public static void main(String[] args) {
        int[][] array = ArrayUtils.read("[[2,3,4],[0,0,5],[7,6,5]]");
        System.out.println(new CutoffTreesForGolf().cutOffTree(Lists.intArrayToList(array)));
    }
}
