import java.util.Arrays;

public class AbsDiffBetweenMaxAndMinKElements {
    public int absDifference(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        int v1 = 0;
        for (int i = 0; i < k; ++i) {
            v1 += a[i];
        }
        int v2 = 0;
        for (int i = n - k; i < n; ++i) {
            v2 += a[i];
        }
        return v2 - v1;
    }
}
