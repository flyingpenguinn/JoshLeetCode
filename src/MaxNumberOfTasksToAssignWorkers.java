import java.util.Arrays;
import java.util.TreeMap;

public class MaxNumberOfTasksToAssignWorkers {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {

        int m = tasks.length;
        int n = workers.length;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int l = 1;
        int u = Math.min(m, n);
        while (l <= u) {
            int rem = pills;
            int k = l + (u - l) / 2;
            int j = n - k;
            boolean bad = false;
            TreeMap<Integer, Integer> wm = new TreeMap<>();
            for (int i = 0; i < n; ++i) {
                update(wm, workers[i], 1);
            }
            for (int i = k - 1; i >= 0; --i) {
                Integer last = wm.lastKey();
                if (tasks[i] <= last) {
                    wm.remove(last);
                } else {
                    if (rem == 0) {
                        bad = true;
                        break;
                    }
                    Integer found = wm.ceilingKey(tasks[i]-strength);
                    if (found == null) {
                        bad = true;
                        break;
                    }
                    wm.remove(found);
                    --rem;
                }
            }
            if (bad) {
                u = k - 1;
            } else {
                l = k + 1;
            }
        }
        return u;
    }

    private void update(TreeMap<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
