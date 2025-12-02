import java.util.HashMap;
import java.util.Map;

public class MaxAbsDistanceBetweenMirrorPairs {
    public int minMirrorPairDistance(int[] a) {
        int n = a.length;
        int Max = (int) 1e9;
        Map<Integer, Integer> pre = new HashMap<>();
        int res = Max;
        for (int i = 0; i < n; ++i) {

            if (pre.containsKey(a[i])) {
                int diff = i - pre.get(a[i]);
                res = Math.min(res, diff);
            }
            int mi = mirror(a[i]);
            //      System.out.println(a[i]+"->"+mi);
            pre.put(mi, i);
        }
        return res >= Max ? -1 : res;
    }

    private int mirror(int v) {
        String sv = String.valueOf(v);
        String rev = new StringBuilder(sv).reverse().toString();
        return Integer.parseInt(rev);
    }
}
