import java.util.Arrays;

public class MedianOfArrayAfterRemovingSomeElements {
    public double trimMean(int[] a) {
        Arrays.sort(a);
        double sum = 0;
        int n = a.length;
        for (int i = n / 20; i < 19 * n / 20; i++) {
            sum += a[i];
        }
        return sum / (9 * n / 10);
    }
}
