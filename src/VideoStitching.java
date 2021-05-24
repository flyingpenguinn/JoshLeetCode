import base.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching {
    // typical greedy: select minimal cover. sort by start and try to extend as much as possible
    // similar to watering garden problem
    public int videoStitching(int[][] a, int t) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        return minIntervalCoverage(a, t, n);
    }

    protected int minIntervalCoverage(int[][] a, int t, int n) {
        int start = -1;
        int end = 0;
        int res = 0;
        for (int i = 0; i < n && end < t; i++) {
            int cstart = a[i][0];
            int cend = a[i][1];
            if (cstart <= start) {
                end = Math.max(end, cend);
            } else if (cstart > end) {
                break;
            } else {
                if(cend >end) {
                    res++;
                    start = end; // we are looking for cover from the end so ignore the portions before it
                    end = cend;
                }
            }
        }
        return end >= t ? res : -1;
    }

    public static void main(String[] args) {
        System.out.println(new VideoStitching().videoStitching(ArrayUtils.read("[[17,18],[25,26],[16,21],[3,3],[19,23],[1,5],[0,2],[9,20],[5,17],[8,10]]"), 15));
    }
}
