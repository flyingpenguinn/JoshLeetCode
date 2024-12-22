import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckIfGridCanbeCutIntoSections {
    public boolean checkValidCuts(int n, int[][] rects) {
        List<int[]> xs = new ArrayList<>();
        List<int[]> ys = new ArrayList<>();
        for (int[] rc : rects) {
            int x1 = rc[0];
            int y1 = rc[1];
            int x2 = rc[2];
            int y2 = rc[3];
            xs.add(new int[]{x1, x2});
            ys.add(new int[]{y1, y2});
        }
        return has2(xs) || has2(ys);
    }

    private boolean has2(List<int[]> a) {
        int n = a.size();
        if (a.isEmpty()) {
            return false;
        }
        Collections.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int start = a.get(0)[0];
        int end = a.get(0)[1];
        int res = 1;
        for (int i = 1; i < n; ++i) {
            int cs = a.get(i)[0];
            int ce = a.get(i)[1];
            if (cs >= end) {
                ++res;
                start = cs;
                end = ce;
            } else {
                end = Math.max(end, ce);
            }
        }
        return res >= 3;
    }
}
