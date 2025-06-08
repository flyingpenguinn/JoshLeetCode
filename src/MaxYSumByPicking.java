import java.util.*;

public class MaxYSumByPicking {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        Map<Integer, Integer> xm = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int xv = x[i];
            int yv = y[i];
            if (!xm.containsKey(xv)) {
                xm.put(xv, yv);
            } else if (yv > xm.get(xv)) {
                xm.put(xv, yv);
            }
        }
        List<Integer> ys = new ArrayList<>();
        for (int xk : xm.keySet()) {
            ys.add(xm.get(xk));
        }
        if (ys.size() < 3) {
            return -1;
        }
        ys.sort(Collections.reverseOrder());
        return ys.get(0) + ys.get(1) + ys.get(2);
    }
}
