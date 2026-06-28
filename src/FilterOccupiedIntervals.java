import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterOccupiedIntervals {
    public List<List<Integer>> filterOccupiedIntervals(int[][] oc, int fs, int fe) {
        Arrays.sort(oc, (x, y) -> Integer.compare(x[0], y[0]));
        int start = oc[0][0];
        int end = oc[0][1];
        int n = oc.length;
        List<List<Integer>> merged = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            int cstart = oc[i][0];
            int cend = oc[i][1];
            if (cstart <= end + 1) {
                end = Math.max(cend, end);
            } else {
                merged.add(List.of(start, end));
                start = cstart;
                end = cend;
            }
        }
        merged.add(List.of(start, end));
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> mi : merged) {
            int cstart = mi.get(0);
            int cend = mi.get(1);
            if (fs <= cstart && fe >= cend) {
                continue;
            } else if (cstart <= fs && cend >= fe) {
                int cend1 = fs - 1;
                if(cstart<=cend1) {
                    res.add(List.of(cstart, cend1));
                }
                int cstart2 = fe+1;
                if(cstart2<=cend) {
                    res.add(List.of(cstart2, cend));
                }
            } else if (fs > cend) {
                res.add(List.of(cstart, cend));
            } else if (fe < cstart) {
                res.add(List.of(cstart, cend));
            } else if (fs > cstart ) {
                cend = fs - 1;
                res.add(List.of(cstart, cend));
            } else {
                cstart = fe + 1;
                res.add(List.of(cstart, cend));
            }
        }
        return res;
    }
}
