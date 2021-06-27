import java.util.ArrayList;
import java.util.List;

public class CyclicRotatingGrid {
    public int[][] rotateGrid(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int[][] res = new int[m][n];
        int layers = Math.min(m, n) / 2;
        int cl = 0;
        while (cl < layers) {
            List<Integer> cur = new ArrayList<>();
            process(cl, cur, a, false);
            List<Integer> ncur = new ArrayList<>();
            int csize = cur.size();
            for (int p = 0; p < csize; p++) {
                int mod = (p + k) % csize;
                ncur.add(cur.get(mod));
            }
            process(cl, ncur, res, true);
            cl++;
        }
        return res;
    }

    void process(int cl, List<Integer> list, int[][] grid, boolean input) {
        int m = grid.length;
        int n = grid[0].length;
        int i = cl;
        int j = cl;
        int l = 0;
        while (j < n - cl) {
            if (input) {
                grid[i][j] = list.get(l++);
            } else {
                list.add(grid[i][j]);
            }
            j++;
        }
        j--;
        i++;
        while (i < m - cl) {
            if (input) {
                grid[i][j] = list.get(l++);
            } else {
                list.add(grid[i][j]);
            }
            i++;
        }
        i--;
        j--;
        while (j >= cl) {
            if (input) {
                grid[i][j] = list.get(l++);
            } else {
                list.add(grid[i][j]);
            }
            j--;
        }
        j++;
        i--;
        while (i > cl) {
            if (input) {
                grid[i][j] = list.get(l++);
            } else {
                list.add(grid[i][j]);
            }
            i--;
        }
    }
}
