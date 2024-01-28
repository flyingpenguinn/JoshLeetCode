import java.util.*;

public class FindingNumberOfVisibleMountains {
    // convert to interval problem
    public int visibleMountains(int[][] peaks) {
        int n = peaks.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            int left = peaks[i][0] - peaks[i][1];
            int right = peaks[i][0] + peaks[i][1];
            a[i] = new int[]{left, right};
        }

        Arrays.sort(a, (x,y)-> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                // bigger first so that we can find its cover
                return Integer.compare(y[1], x[1]);
            }
        });
        int end = (int)(-1e9);
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (a[i][1] > end) {
                if(i==n-1 || !Arrays.equals(a[i], a[i+1])){
                    res++;
                }
                end = Math.max(a[i][1], end);
            }
        }
        return res;
    }
}
