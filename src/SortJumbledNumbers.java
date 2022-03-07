import java.util.Arrays;

public class SortJumbledNumbers {
    public int[] sortJumbled(int[] mapping, int[] a) {
        int n = a.length;
        int[][] ma = new int[n][2];
        for (int i = 0; i < n; ++i) {
            ma[i][1] = a[i];
            ma[i][0] = map(mapping, a[i]);
        }
        Arrays.sort(ma, (x, y) -> Integer.compare(x[0], y[0]));
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = ma[i][1];
        }
        return res;
    }

    private int map(int[] mapping, int n) {
        if (n == 0) {
            return mapping[0];
        }
        int res = 0;
        int base = 1;
        while (n > 0) {
            int digit = n % 10;
            int nd = mapping[digit];
            res = nd * base + res;
            base *= 10;
            n /= 10;
        }
        return res;
    }
}
