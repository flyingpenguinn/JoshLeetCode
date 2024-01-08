import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class MinOperationsToMakeXYEqual {
    private int Max = (int) 1e9;

    public int minimumOperationsToMakeEqual(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, 0});
        Set<Integer> seen = new HashSet<>();
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int v = top[0];
            int d = top[1];
            if (v == y) {
                return d;
            }
            if (v % 11 == 0) {
                int nv = v / 11;
                if (!seen.contains(nv)) {
                    seen.add(nv);
                    q.offer(new int[]{nv, d + 1});
                }
            }
            if (v % 5 == 0) {
                int nv = v / 5;
                if (!seen.contains(nv)) {
                    seen.add(nv);
                    q.offer(new int[]{nv, d + 1});
                }
            }
            int nv = v - 1;
            if (!seen.contains(nv)) {
                seen.add(nv);
                q.offer(new int[]{nv, d + 1});
            }
            nv = v + 1;
            if (!seen.contains(nv)) {
                seen.add(nv);
                q.offer(new int[]{nv, d + 1});
            }
        }
        return -1;
    }
}
