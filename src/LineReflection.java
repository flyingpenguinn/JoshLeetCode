import java.util.*;

/*
LC#356
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:

Input: [[1,1],[-1,1]]
Output: true
Example 2:

Input: [[1,1],[-1,-1]]
Output: false
Follow up:
Could you do better than O(n2) ?
 */
public class LineReflection {
    // don't need to sort, just need to get min and max. the line must be between min x and max x no matter of their y
    // also dont need to find the line of type double, just min+max
    // seems the same dot can be treated as one and those on the line are always good no need to check even counts
    public boolean isReflected(int[][] ps) {
        if (ps.length == 0) {
            return true;
        }
        Map<Integer, Set<Integer>> pmap = new HashMap<>();
        for (int[] p : ps) {
            Set<Integer> pm = pmap.getOrDefault(p[1], new HashSet<>());
            pm.add(p[0]);
            pmap.put(p[1], pm);
        }
        int y = pmap.keySet().iterator().next();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x : pmap.get(y)) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        long line = min + max;
        return check(line, pmap);
    }

    boolean check(long line, Map<Integer, Set<Integer>> pmap) {
        for (int y : pmap.keySet()) {
            Set<Integer> xmap = pmap.get(y);
            for (int kx : xmap) {
                int other = (int) (line - kx);
                if (!xmap.contains(other)) {
                    return false;
                }
            }
        }
        return true;
    }
}
