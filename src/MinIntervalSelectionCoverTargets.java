import java.util.*;

public class MinIntervalSelectionCoverTargets {
    public int minSelection(int[][] av, int[][] tg) {
        int an = av.length;
        int tn = tg.length;
        List<int[]> ts = new ArrayList<>();
        Arrays.sort(tg, (x, y) -> Integer.compare(x[0], y[0]));
        int start = tg[0][0];
        int end = tg[0][1];
        for (int i = 1; i < tn; ++i) {
            int v1 = tg[i][0];
            int v2 = tg[i][1];
            if (v1 > end) {
                ts.add(new int[]{start, end});
                start = v1;
                end = v2;
            } else {
                end = Math.max(end, v2);
            }
        }
        ts.add(new int[]{start, end});
        Arrays.sort(av, (x, y) -> Integer.compare(x[0], y[0]));
        int i = 0;
        int j = 0;
        int res = 0;
        int latest = 0;

        while (i < an && j<ts.size()) {
            // try going farthest
            int pos = Math.max(latest, ts.get(j)[0]);
            int oldlatest = latest;
            while (i < an && av[i][0] <= pos) {
                int a2 = av[i][1];
                latest = Math.max(latest, a2);
                ++i;
            }
            // check we moved
            if (latest == oldlatest) {
                return -1;
            }
            // commit target
            while (j < ts.size() && latest >= ts.get(j)[1]) {
                ++j;
            }

            ++res;
        }
        if (j != ts.size()) {
            return -1;
        }
        return res;
    }
}
