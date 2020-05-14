import java.util.HashMap;
import java.util.Map;

/*
LC#447
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:

Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] p) {
        int n = p.length;
        int r = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> m = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int dist = dist(p[i], p[j]);
                m.put(dist, m.getOrDefault(dist, 0) + 1);
            }

            for (int k : m.keySet()) {
                int v = m.get(k);
                r += v * (v - 1);
            }
        }
        return r;
    }

    int dist(int[] x, int[] y) {
        return (y[1] - x[1]) * (y[1] - x[1]) + (y[0] - x[0]) * (y[0] - x[0]);
    }
}
