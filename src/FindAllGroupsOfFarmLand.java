import java.util.ArrayList;
import java.util.List;

public class FindAllGroupsOfFarmLand {

    public int[][] findFarmland(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i > 0 && a[i - 1][j] == 1) || (j > 0 && a[i][j - 1] == 1)) {
                    continue;
                }
                if (a[i][j] == 1) {
                    int k = i;
                    while (k < m && a[k][j] == 1) {
                        ++k;
                    }
                    int l = j;
                    while (l < n && a[i][l] == 1) {
                        ++l;
                    }
                    res.add(new int[]{i, j, k - 1, l - 1});
                }
            }
        }
        int[][] rr = new int[res.size()][4];
        for (int i = 0; i < rr.length; ++i) {
            for (int j = 0; j < 4; ++j) {
                rr[i][j] = res.get(i)[j];
            }
        }
        return rr;
    }

}
