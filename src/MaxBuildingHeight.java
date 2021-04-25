import java.util.Arrays;

public class MaxBuildingHeight {
    // scan twice: left-> right and right-> left for the appropriate max height for each i
    // then do binary for good number in between
    // scan left to right then right to left for the height restrictions between adjacent buildings.
    // then do a 3rd scan for the max height between buildings
    public int maxBuilding(int n, int[][] rs) {
        int rn = rs.length;
        if (rn == 0) {
            return n - 1;
        }
        Arrays.sort(rs, (x, y) -> Integer.compare(x[0], y[0]));
        for (int i = 0; i < rn; i++) {
            int index = rs[i][0];
            rs[i][1] = Math.min(index - 1, rs[i][1]);
            // index -1 is what we can get the most
            if (i > 0) {
                // from the left we can get left height + the diff
                int maxv = rs[i - 1][1] + (index - rs[i - 1][0]);
                rs[i][1] = Math.min(rs[i][1], maxv);
            }
        }
        int res = n - rs[rn - 1][0] + rs[rn - 1][1];
        for (int i = rn - 2; i >= 0; i--) {
            int index = rs[i][0];
            // from the right same story
            int maxv = rs[i + 1][1] + (rs[i + 1][0] - index);
            rs[i][1] = Math.min(rs[i][1], maxv);
        }
        // x-small+1 + x-big = diff+1 => x = diff + small +big
        for (int i = 0; i < rn; i++) {
            res = Math.max(res, rs[i][1]);
            if (i > 0) {
                int mid = (rs[i][1] + rs[i - 1][1] + (rs[i][0] - rs[i - 1][0])) / 2;
                res = Math.max(res, mid);
            }
        }
        return res;
    }

}
