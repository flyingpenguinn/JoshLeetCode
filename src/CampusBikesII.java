import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class CampusBikesII {

    // dp on status, turning an n! problem without dp to n*n^n
    int[][] b;
    int[][] w;
    Map<Integer, Map<BitSet, Integer>> dp = new HashMap<>();

    public int assignBikes(int[][] workers, int[][] bikes) {
        this.w = workers;
        this.b = bikes;
        return doassign(0, new BitSet());
    }

    int doassign(int index, BitSet used) {
        if (index == w.length) {
            return 0;
        }
        Map<BitSet, Integer> cm = dp.getOrDefault(index, new HashMap<>());
        Integer cac = cm.get(used);
        if (cac != null) {
            return cac;
        }
        int min = 1000000000;
        for (int i = 0; i < b.length; i++) {
            if (used.get(i)) {
                continue;
            }
            int dst = dist(index, i);
            BitSet nbs = (BitSet) used.clone();
            nbs.set(i);
            int lt = doassign(index + 1, nbs);

            min = Math.min(min, dst + lt);
        }
        cm.put(used, min);
        dp.put(index, cm);
        return min;
    }

    private int dist(int i, int j) {
        return Math.abs(w[i][0] - b[j][0]) + Math.abs(w[i][1] - b[j][1]);
    }
}
