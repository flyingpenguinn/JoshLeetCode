import java.util.Arrays;

public class DivideArrayIntoArraysWithMaxDiff {
    public int[][] divideArray(int[] a, int k) {
        int n = a.length;
        int[][] res = new int[n / 3][3];
        Arrays.sort(a);
        int ri = 0;
        for (int i = 0; i + 2 < n; i += 3) {
            if (a[i + 2] - a[i] > k) {
                return new int[0][0];
            } else {
                res[ri++] = new int[]{a[i], a[i + 1], a[i + 2]};
            }
        }
        return res;
    }
}
