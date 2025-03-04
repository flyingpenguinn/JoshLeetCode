import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxProfitFromTaskAssignment {
    public long maxProfit(int[] workers, int[][] tasks) {
        Arrays.sort(tasks, (x, y) -> Integer.compare(y[1], x[1]));
        Map<Integer, Integer> wm = new HashMap<>();
        for (int wi : workers) {
            wm.put(wi, wm.getOrDefault(wi, 0) + 1);
        }
        int tn = tasks.length;
        int used = 0;
        long res = 0;
        for (int i = 0; i < tn; ++i) {
            int req = tasks[i][0];
            long prof = tasks[i][1];
            if (wm.getOrDefault(req, 0) > 0) {
                res += prof;
                wm.put(req, wm.getOrDefault(req, 0) - 1);
            } else {
                if (used == 0) {
                    res += prof;
                    ++used;
                }
            }
        }
        return res;
    }
}
