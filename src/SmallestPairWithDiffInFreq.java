import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestPairWithDiffInFreq {
    public int[] minDistinctFreqPair(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        Map<Integer, Integer> fm = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            fm.put(v, fm.getOrDefault(v, 0) + 1);
        }
        int Max = (int) 1e9;
        int[] res = new int[]{Max, Max};
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[i] == a[j]) {
                    continue;
                }
                int fi = fm.get(a[i]);
                int fj = fm.get(a[j]);
                if (fi != fj) {
                    if (a[i] < res[0]) {
                        res = new int[]{a[i], a[j]};
                    } else if (a[i] == res[0] && a[j] < res[1]) {
                        res = new int[]{a[i], a[j]};
                    }
                }
            }
        }
        if (res[0] >= Max) {
            res = new int[]{-1, -1};
        }
        return res;
    }
}
