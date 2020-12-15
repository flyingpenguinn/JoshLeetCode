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


class SquareOfSortedArrayFromSmaller {
    public int[] sortedSquares(int[] a) {
        int n = a.length;
        int i = 0;
        while (i < n && a[i] < 0) {
            i++;
        }
        // 0...i-1 <0, i...n-1 >=0;
        int j = i - 1;
        int[] r = new int[n];
        int ri = 0;
        while (i < n && j >= 0) {
            int sqi = a[i] * a[i];
            int sqj = a[j] * a[j];
            if (sqi <= sqj) {
                r[ri++] = sqi;
                i++;
            } else {
                r[ri++] = sqj;
                j--;
            }
        }
        while (i < n) {
            r[ri++] = a[i] * a[i];
            i++;
        }
        while (j >= 0) {
            r[ri++] = a[j] * a[j];
            j--;
        }
        return r;
    }
}
