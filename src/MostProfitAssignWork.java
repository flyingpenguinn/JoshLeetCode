import java.util.TreeMap;

public class MostProfitAssignWork {
    // check max profit each difficulty level can provide
    public int maxProfitAssignment(int[] d, int[] p, int[] w) {
        int n = d.length;
        TreeMap<Integer, Integer> dm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            Integer cur = dm.get(d[i]);
            // trap- handle duplicated difficulty
            if (cur == null || p[cur] < p[i]) {
                dm.put(d[i], i);
            }
        }
        TreeMap<Integer, Integer> dpm = new TreeMap<>();
        int max = -1;
        for (int k : dm.keySet()) {
            int cp = p[dm.get(k)];
            max = Math.max(max, cp);
            dpm.put(k, max);
        }
        int prof = 0;
        for (int wd : w) {
            Integer floor = dpm.floorKey(wd);
            if (floor != null) {
                prof += dpm.get(floor);
            }
        }
        return prof;
    }
}
