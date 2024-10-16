import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimizeConnectedGroupsByInsertingInterval {
    public int minConnectedGroups(int[][] ints, int k) {
        Arrays.sort(ints, (x, y) -> Integer.compare(x[0], y[0]));
        int n = ints.length;
        int cstart = ints[0][0];
        int cend = ints[0][1];
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            if (ints[i][0] > cend) {
                list.add(new int[]{cstart, cend});
                cstart = ints[i][0];
                cend = ints[i][1];
            } else {
                cend = Math.max(cend, ints[i][1]);
            }
        }
        list.add(new int[]{cstart, cend});
        int j = 0;
        int ln = list.size();
        int res = ln;
        for (int i = 0; i < ln; ++i) {
            while (j < ln && list.get(j)[0] <= k + list.get(i)[1]) {
                ++j;
            }
            int rem = ln - j;
            int cur = i + 1 + rem;
            res = Math.min(res, cur);
        }
        return res;
    }
}
