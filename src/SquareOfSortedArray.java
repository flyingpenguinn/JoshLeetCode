import java.util.Arrays;

public class SquareOfSortedArray {
    public int[] sortedSquares(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n];
        int ri = n - 1;
        int i = 0;
        int j = n - 1;
        while (ri >= 0) {
            if (Math.abs(a[i]) > Math.abs(a[j])) {
                r[ri--] = a[i] * a[i];
                i++;
            } else {
                r[ri--] = a[j] * a[j];
                j--;
            }
        }
        return r;
    }
}
