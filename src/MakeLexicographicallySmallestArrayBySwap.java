import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MakeLexicographicallySmallestArrayBySwap {
    // sort locally
    public int[] lexicographicallySmallestArray(int[] a, int limit) {
        int n = a.length;
        int[][] na = new int[n][2];
        for (int i = 0; i < n; ++i) {
            na[i][0] = a[i];
            na[i][1] = i;
        }
        Arrays.sort(na, (x, y) -> Integer.compare(x[0], y[0]));
        List<Integer> ids = new ArrayList<>();
        List<Integer> ns = new ArrayList<>();

        int[] res = new int[n];
        for (int i = 0; i <= n; ++i) {
            if ((i == n) || (i > 0 && na[i][0] - na[i - 1][0] > limit)) {
                Collections.sort(ids);
                int ni = 0;
                for (int id : ids) {
                    res[id] = ns.get(ni++);
                }
                ids = new ArrayList<>();
                ns = new ArrayList<>();
            }
            if (i < n) {
                ids.add(na[i][1]);
                ns.add(na[i][0]);
            }
        }
        return res;
    }
}
