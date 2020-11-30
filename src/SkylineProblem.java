import base.ArrayUtils;

import java.util.*;

/*
LC#218
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class SkylineProblem {
    // assuming we have a priority queue that we can delete from easily. if priorityqueue is too slow, use treemap
    // two reasons to add a point: entering edge and higher than rest, or exiting edge and higher than what's remaining.
    // mind the "addresult" to remove duplicated points
    public List<List<Integer>> getSkyline(int[][] a) {
        int n = a.length;
        // x, y, type=0 enter type =1 exit
        List<int[]> ps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x1 = a[i][0];
            int x2 = a[i][1];
            int y = a[i][2];
            ps.add(new int[]{x1, y, 0});
            ps.add(new int[]{x2, y, 1});
        }
        Collections.sort(ps, (x, y) -> {
            if (x[0] != y[0]) {
                // small x first
                return Integer.compare(x[0], y[0]);
            } else if (x[1] != y[1]) {
                // big y first
                return Integer.compare(y[1], x[1]);
            } else {
                // same x and y edges, entering edge comes first
                return Integer.compare(x[2], y[2]);
            }
        });

        // keep a big first pq on current ys. we assume pq.remove can be fast
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < ps.size(); i++) {
            int[] cur = ps.get(i);
            int type = cur[2];
            if (type == 0) {
                // enter a building add its height if it's higher than what we have record a point
                if (pq.isEmpty() || cur[1] > pq.peek()) {
                    res.add(list(cur[0], cur[1]));
                }
                pq.offer(cur[1]);
            } else {
                pq.remove(cur[1]); // leaving a building taking out its height
                int landon = pq.isEmpty() ? 0 : pq.peek();
                if (cur[1] > landon) {
                    addresult(res, list(cur[0], landon));
                }
            }
        }
        return res;
    }

    private void addresult(List<List<Integer>> r, List<Integer> p) {
        if (!r.isEmpty() && r.get(r.size() - 1).get(0).equals(p.get(0))) {
            r.remove(r.size() - 1);
        }
        r.add(p);
    }

    private List<Integer> list(int x, int y) {
        return List.of(x, y);
    }
}