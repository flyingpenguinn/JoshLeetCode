import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching {
    // typical greedy: select minimal cover. sort by start and try to extend as much as possible
    // similar to watering garden problem
    public int videoStitching(int[][] a, int t) {
        int n = a.length;
        if (n == 0) {
            return -1;
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int start = Math.max(a[0][0], 0);
        int end = a[0][1];
        if (start > 0) { // don't forget to check the starter!
            return -1;
        }
        int res = 1;
        for (int i = 1; i < n && end < t; i++) {
            if (a[i][0] <= start) {
                end = Math.max(end, a[i][1]);
            } else if (a[i][0] > end) {
                break;
            } else {
                res++;
                start = end; // we are looking for cover from the end so ignore the portions before it
                end = a[i][1];
            }
        }
        return end >= t ? res : -1;
    }
}
