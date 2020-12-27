import java.util.Arrays;

public class WhereWillTheBallFall {
    public int[] findBall(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int j = 0; j < n; j++) {
            res[j] = fall(a, j);
        }
        return res;
    }

    private int fall(int[][] a, int j) {
        int m = a.length;
        int n = a[0].length;
        int i = 0;
        while (i < m) {
            if (a[i][j] == 1) {
                if (j == n - 1 || a[i][j + 1] == -1) {
                    return -1;
                } else {
                    i = i + 1;
                    j = j + 1;
                }
            } else {
                if (j == 0 || a[i][j - 1] == 1) {
                    return -1;
                } else {
                    i = i + 1;
                    j = j - 1;
                }
            }
        }
        return j;
    }
}
