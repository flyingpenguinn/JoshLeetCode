import java.util.Arrays;

public class MinRectCoverPoints {
    public int minRectanglesToCoverPoints(int[][] a, int w) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int i = 0;
        int res = 0;
        while (i < n) {
            int j = i;
            while (j < n && a[j][0] - a[i][0] <= w) {
                ++j;
            }
            // i... j-1
            ++res;
            i = j;
        }
        return res;
    }
}
