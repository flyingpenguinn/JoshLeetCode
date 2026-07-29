import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinOperationsToMakeArrayEqualIII {
    // div and multi template
    /* for small max,
    for (int d = 1; d <= max; ++d) {
        for (int v = d; v <= max; v += d) {
            div[v] += freq[d];
            multi[d] += freq[v];
        }
    }
     */
    public long minOperations(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int[] div = new int[n];
        int[] multi = new int[n];
        Map<Integer, Integer> im = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        long res = n;
        for (int i = 0; i < n; ++i) {
            int v = a[i];

            if (v == 1) {
                freq.put(v, freq.getOrDefault(v, 0) + 1);
                continue;
            } else {
                for (int j = 2; j * j <= v; ++j) {
                    if (v % j != 0) {
                        continue;
                    }
                    addmaps(im, div, multi, v, j, i, freq);
                    if (v / j != j) {
                        addmaps(im, div, multi, v, v / j, i, freq);
                    }
                }
            }
            im.put(v, i);
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        if (freq.size() == 1) {
            return 0;
        }
        int ones = freq.getOrDefault(1, 0);
        for (int i = 0; i < n; ++i) {
            if (a[i] == 1) {
                continue;
            }
            int dv = div[i];
            int mv = multi[i];
            int good = dv + mv + ones;

            long rem = n - good - freq.get(a[i]);
            long cres = rem * 2 + good;
            res = Math.min(res, cres);
        }
        return res;
    }

    private void addmaps(Map<Integer, Integer> im, int[] div, int[] multi, int v, int j, int i, Map<Integer, Integer> freq) {
        if (!im.containsKey(j)) {
            return;
        }
        int idj = im.get(j);
        div[i] += freq.get(j);
        multi[idj] += 1;
    }
}
