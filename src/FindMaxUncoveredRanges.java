import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMaxUncoveredRanges {
    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        if (ranges.length == 0) {
            return new int[][]{{0, n - 1}};
        }
        Arrays.sort(ranges, (x, y) -> Integer.compare(x[0], y[0]));
        List<int[]> res = new ArrayList<>();
        int start = ranges[0][0];
        int end = ranges[0][1];
        if (start > 0) {
            res.add(new int[]{0, start - 1});
        }
        for (int[] ran : ranges) {
            int ns = ran[0];
            int ne = ran[1];
            if (ns <= end + 1) {
                end = Math.max(end, ne);
            } else {
                res.add(new int[]{end + 1, ns - 1});
                start = ns;
                end = ne;
            }
        }
        if (end < n - 1) {
            res.add(new int[]{end + 1, n - 1});
        }
        int[][] rr = new int[res.size()][2];
        int ri = 0;
        for (int[] re : res) {
            rr[ri++] = re;
        }
        return rr;
    }
}
