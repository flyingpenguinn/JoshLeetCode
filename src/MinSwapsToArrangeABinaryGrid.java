import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinSwapsToArrangeABinaryGrid {
    public int minSwaps(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    count = 0;
                } else {
                    count++;
                }
            }
            rows.add(count);
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            int needed = n - 1 - i;
            if (rows.get(i) >= needed) {
                continue;
            }
            boolean found = false;
            for (int j = i + 1; j < m; j++) {
                if (rows.get(j) >= needed) {
                    res += j - i;
                    int rj = rows.get(j);
                    rows.remove(j);
                    rows.add(i, rj);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return -1;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MinSwapsToArrangeABinaryGrid().minSwaps(ArrayUtils.read("[[0,0,1],[1,1,0],[1,0,0]]")));
    }
}
