import java.util.Arrays;

public class MaxElementAfterDecreasingAndRearranging {
    public int maximumElementAfterDecrementingAndRearranging(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        a[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1] + 1) {
                a[i] = a[i - 1] + 1;
            }
            max = Math.max(max, a[i]);
        }
        return max;
    }
}
