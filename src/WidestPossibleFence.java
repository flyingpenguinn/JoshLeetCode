import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidestPossibleFence {
    public int maximumWidth(int[] planks) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int v : planks) {
            long x = v;
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        List<Long> vals = new ArrayList<>(freq.keySet());
        Map<Long, Integer> width = new HashMap<>();

        int res = 1;

        for (long x : vals) {
            int cx = freq.get(x);
            width.put(x, width.getOrDefault(x, 0) + cx);
            res = Math.max(res, width.get(x));
        }

        for (int i = 0; i < vals.size(); ++i) {
            long x = vals.get(i);
            int cx = freq.get(x);

            for (int j = i; j < vals.size(); ++j) {
                long y = vals.get(j);
                int cy = freq.get(y);

                int add;
                if (i == j) {
                    add = cx / 2;
                } else {
                    add = Math.min(cx, cy);
                }

                long h = x + y;
                width.put(h, width.getOrDefault(h, 0) + add);
                res = Math.max(res, width.get(h));
            }
        }

        return res;
    }
}
