import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindKthLargestXorValue {
    public int kthLargestValue(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int[][] xor = new int[m][n];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    xor[i][j] = a[i][j];
                } else if (i == 0) {
                    xor[i][j] = xor[i][j - 1] ^ a[i][j];
                } else if (j == 0) {
                    xor[i][j] = xor[i - 1][j] ^ a[i][j];
                } else {
                    xor[i][j] = xor[i - 1][j - 1] ^ xor[i - 1][j] ^ xor[i][j - 1] ^ a[i][j];
                }
                list.add(xor[i][j]);
            }
        }
        Collections.sort(list);
        return list.get(list.size() - k);
    }
}
