import java.util.HashMap;
import java.util.Map;

public class MinOperationToMakeArrayParityAlternating {
    private int[] solve(int[] a, int par) {
        int n = a.length;
        int changes = 0;
        Map<Integer, Integer> changed = new HashMap<>();
        Map<Integer, Integer> occ = new HashMap<>();
        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (Math.abs(a[i] % 2) != par) {
                changed.put(a[i], changed.getOrDefault(a[i], 0) + 1);
                ++changes;
            }
            minv = Math.min(minv, a[i]);
            maxv = Math.max(maxv, a[i]);
            par ^= 1;
            occ.put(a[i], occ.getOrDefault(a[i], 0) + 1);
        }
        if (maxv == minv && changes > 0) {
            return new int[]{changes, 1};
        }
        if (occ.get(minv) == changed.getOrDefault(minv, 0)) {
            minv += 1;
        }
        if (occ.get(maxv) == changed.getOrDefault(maxv, 0)) {
            maxv -= 1;
        }
        return new int[]{changes, Math.abs(maxv - minv)};
    }

    public int[] makeParityAlternating(int[] a) {
        int n = a.length;
        int[] rodd = solve(a, 1);
        int[] reven = solve(a, 0);


        if (rodd[0] < reven[0]) {
            return rodd;
        } else if (reven[0] < rodd[0]) {
            return reven;
        } else {
            if (rodd[1] < reven[1]) {
                return rodd;
            } else {
                return reven;
            }
        }
    }
}
