import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindGoodSubsetOfMatrix {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        if (m == 1) {
            for (int k = 0; k < n; ++k) {
                if (a[0][k] == 1) {
                    return new ArrayList<>();
                }
            }
            return List.of(0);
        }
        int[] map = new int[32];
        Arrays.fill(map, -1);
        for (int i = 0; i < m; ++i) {
            int cur = 0;
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    cur |= (1 << n - 1 - j);
                }
            }
            //   System.out.println(cur);
            for (int j = 0; j < 32; ++j) {
                if ((cur & j) == 0 && map[j] >= 0) {
                    //    System.out.println(i+ " found "+j);
                    return List.of(map[j], i);
                }
            }
            map[cur] = i;
        }
        return new ArrayList<>();
    }
}
