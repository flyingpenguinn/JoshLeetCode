import java.util.Collections;
import java.util.PriorityQueue;

public class SumInMatrix {
    public int matrixSum(int[][] a) {
        int m = a.length;
        PriorityQueue<Integer>[] pqs = new PriorityQueue[m];
        for (int i = 0; i < m; ++i) {
            pqs[i] = new PriorityQueue<>(Collections.reverseOrder());
            for (int ai : a[i]) {
                pqs[i].offer(ai);
            }
        }
        int res = 0;
        int n = a[0].length;
        for (int j = 0; j < n; ++j) {
            int max = 0;
            for (int i = 0; i < m; ++i) {
                PriorityQueue<Integer> pq = pqs[i];
                max = Math.max(max, pq.poll());
            }
            res += max;
        }
        return res;
    }
}
