import java.util.Arrays;
import java.util.TreeMap;

public class MostProfitAssignWork {
    // check max profit each difficulty level can provide
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] ws = new int[difficulty.length][2];
        for (int i = 0; i < difficulty.length; ++i) {
            ws[i] = new int[]{difficulty[i], profit[i]};
        }
        Arrays.sort(ws, (x, y) -> Integer.compare(x[0], y[0]));
        Arrays.sort(worker);
        int best = 0;
        int j = 0;
        int res = 0;
        for (int i = 0; i < worker.length; ++i) {
            while (j < ws.length && ws[j][0] <= worker[i]) {
                best = Math.max(best, ws[j][1]);
                ++j;
            }
            res += best;
        }
        return res;
    }
}
