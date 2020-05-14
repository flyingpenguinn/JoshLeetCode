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

    // line sweep. use a pq to store the heights of the currently "living" buildings. each higher entering or leaving is a point. handle multiple points with the same x
    class Edge {
        int x;
        int y;
        int t;

        Edge(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Edge> es = new ArrayList<>();
        for (int[] b : buildings) {
            es.add(new Edge(b[0], b[2], 0));
            es.add(new Edge(b[1], b[2], 1));
        }
        Collections.sort(es, new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                if (e1.x != e2.x) {
                    return e1.x - e2.x;
                } else if (e1.y != e2.y) {
                    // higher first
                    return e2.y - e1.y;
                } else {
                    return e1.t - e2.t;
                }
            }
        });
        // higher first
        PriorityQueue<Integer> ys = new PriorityQueue<>(Collections.reverseOrder());
        List<List<Integer>> r = new ArrayList<>();
        for (Edge e : es) {
            if (e.t == 0) {
                if (ys.isEmpty() || e.y > ys.peek()) {
                    // visible entering points
                    addr(r, mp(e.x, e.y));
                }
                // else a shorter one entered just record
                ys.add(e.y);
            } else {
                // visible dropping points. can use a treemap to make priority queue removal faster
                ys.remove(e.y);
                int nb = ys.isEmpty() ? 0 : ys.peek();
                if (e.y > nb) {
                    addr(r, mp(e.x, nb));
                }
            }
        }
        return r;
    }

    List<Integer> mp(int x, int y) {
        List<Integer> r = new ArrayList<>();
        r.add(x);
        r.add(y);
        return r;
    }

    void addr(List<List<Integer>> r, List<Integer> add) {
        // handle same drop: because under same h lowest ponit visible
        int n = r.size();
        if (n > 0 && r.get(n - 1).get(0).equals(add.get(0))) {
            //  System.out.println(r.get(n-1)+"=>"+add);
            r.set(n - 1, add);
        } else {
            //System.out.println("+"+add);
            r.add(add);
        }
    }
}