import java.util.Arrays;

public class MaxPriceToFillABag {
    // partial knapcsack problem
    public double maxPrice(int[][] items, int capacity) {
        Arrays.sort(items, (x, y) -> Long.compare(1L * x[1] * y[0], 1L * x[0] * y[1]));
        int n = items.length;
        double res = 0;
        double cap = capacity;
        for (int i = 0; i < n && cap > 0; ++i) {
            double cw = items[i][1];
            double cp = items[i][0];
            double gainw = Math.min(cw, cap);
            res += cp * gainw / cw;
            cap -= gainw;
        }
        return cap == 0 ? res : -1;
    }
}
