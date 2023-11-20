import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FindBuildingWhereAliceBobMeet {
    // better solution: sort queries then binary search a mono stack
    private int[] pa;

    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        pa = new int[n];
        Arrays.fill(pa, -1);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!dq.isEmpty() && a[dq.peekLast()] < a[i]) {
                int v1 = dq.pollLast();
                pa[v1] = i;
            }
            dq.offerLast(i);
        }
        int[] res = new int[qs.length];
        int ri = 0;
        for (int[] q : qs) {
            int v1 = q[0];
            int v2 = q[1];
            if (v1 == v2) {
                res[ri++] = v1;
                continue;
            }
            if (v1 > v2) {
                int tmp = v1;
                v1 = v2;
                v2 = tmp;
            }
            while (v2 != -1 && a[v2] <= a[v1]) {
                v2 = pa[v2];
            }
            res[ri++] = v2;
        }
        return res;
    }

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int ai = find(pa[i]);
        pa[i] = ai;
        return ai;
    }
}
