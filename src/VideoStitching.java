import java.util.Arrays;
import java.util.Comparator;

public class VideoStitching {
    class ArrayComp implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]);

        }
    }

    // greedy: keep expanding till start > oldConv
    public int videoStitching(int[][] c, int t) {
        if (c.length == 0) {
            return t == 0 ? 0 : -1;
        }
        Arrays.sort(c, new ArrayComp());

        int cov = 0;
        int i = 0;
        int cnt = 0;
        int oldcov = 0;
        // find the longest whose start <=oldcov then set new val
        while (true) {
            if (cov >= t) {
                return cnt;
            }
            if (i == c.length) {
                return -1;
            }
            if (c[i][0] > cov) {
                return -1;
            }

            while (i < c.length && c[i][0] <= oldcov) {
                cov = Math.max(cov, c[i][1]);
                i++;
            }
            cnt++;
            oldcov = cov;
        }

    }
}

class VideoStitchingDp {

    class ArrayComp implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]);

        }
    }

    int[][] dp;
    int VB = 101;

    public int videoStitching(int[][] c, int t) {
        Arrays.sort(c, new ArrayComp());
        dp = new int[c.length][t];
        int rt = domin(c, 0, 0, t);
        return rt >= VB ? -1 : rt;
    }

    int domin(int[][] c, int i, int covered, int t) {
        if (covered >= t) {
            return 0;
        }
        if (i == c.length) {
            // fall short
            return VB;
        }
        if (c[i][0] > covered) {
            // disconnect
            return VB;
        }
        if (dp[i][covered] != 0) {
            return dp[i][covered];
        }
        int np = domin(c, i + 1, covered, t);
        int pick = VB;
        if (c[i][1] > covered) {
            pick = 1 + domin(c, i + 1, c[i][1], t);
        }
        int rt = Math.min(pick, np);
        dp[i][covered] = rt;
        return rt;
    }


}
