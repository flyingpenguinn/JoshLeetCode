import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxSumWithAtmostKElements {
    public long maxSum(int[][] a, int[] l, int k) {
        int m = a.length;
        int n = a[0].length;
        List<int[]> v = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                v.add(new int[]{i, a[i][j]});
            }
        }
        Collections.sort(v, (x, y) -> Integer.compare(y[1], x[1]));
        long res = 0;
        for (int i = 0; i < v.size() && k > 0; ++i) {
            int row = v.get(i)[0];
            long val = v.get(i)[1];
            if (l[row] > 0) {
                res += val;
                --l[row];
                --k;
                //   System.out.println("picking "+val+" at row "+row);
            } else {
                continue;
            }
        }
        return res;
    }
}
