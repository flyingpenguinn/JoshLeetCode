import java.util.Arrays;

public class DeleteGreatestValueEachRow {
    public int deleteGreatestValue(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; ++i) {
            Arrays.sort(a[i]);
        }
        int res = 0;
        for (int j = 0; j < n; ++j) {
            int cmax = 0;
            for (int i = 0; i < m; ++i) {
                cmax = Math.max(cmax, a[i][j]);
            }
            res += cmax;
        }
        return res;
    }
}
