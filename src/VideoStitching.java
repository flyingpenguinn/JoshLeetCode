import base.ArrayUtils;

import java.util.Arrays;

public class VideoStitching {
    // typical greedy: select minimal cover. sort by start and try to extend as much as possible
    // similar to watering garden problem
    public int videoStitching(int[][] a, int time) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int last = 0;
        int i = 0;
        int res = 0;
        while (i < n) {
            int pos = last;
            int oldlast = last;
            while (i < n && a[i][0] <= pos) {
                last = Math.max(last, a[i][1]);
                ++i;
            }
            if (last == oldlast) {
                return -1;
            }

            ++res;
            if (last >= time) {
                break;
            }
        }
        return last >= time ? res : -1;
    }

    public static void main(String[] args) {
        System.out.println(new VideoStitching().videoStitching(ArrayUtils.read("[[17,18],[25,26],[16,21],[3,3],[19,23],[1,5],[0,2],[9,20],[5,17],[8,10]]"), 15));
    }
}
