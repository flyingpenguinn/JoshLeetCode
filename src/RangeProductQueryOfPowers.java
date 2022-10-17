import java.util.ArrayList;
import java.util.List;

public class RangeProductQueryOfPowers {
    public int[] productQueries(int n, int[][] queries) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 32; ++i) {
            if (((n >> i) & 1) == 1) {
                arr.add((1 << i));
            }
        }
        long mod = (long) (1e9 + 7);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int[] q = queries[i];
            int start = q[0];
            int end = q[1];
            long cres = 1;
            for (int j = start; j <= end && j < arr.size(); ++j) {
                cres *= arr.get(j);
                cres %= mod;
            }
            res[i] = (int) cres;
        }
        return res;
    }
}
